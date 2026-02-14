import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.image.WritableImage;
import javafx.animation.AnimationTimer;


public class GameView {

    private Pane root;
    private Gamestate game;
    private int tileSize = 80;
    private double time = 0;
    private Circle mergedCircle;
    private Circle blackCircle;
    private Circle whiteCircle;
    private Pane staticLayer = new Pane();
    private Pane dynamicLayer = new Pane();
    private double renderMergedX, renderMergedY;
    private double renderBlackX, renderBlackY;
    private double renderWhiteX, renderWhiteY;

    public GameView(Gamestate game, Pane root) {
        this.game = game;
        this.root = root;
        root.getChildren().addAll(staticLayer, dynamicLayer);
        playerSetup();
        animationTimer.start();
    }

    public void draw() {
            staticLayer.getChildren().clear();
            drawGrid();
            drawShards();
            checkForWin();
            checkForLoss();
            displayShardCount();
            drawExitWarning();
            drawTutorialMessage();
        }

    public void playerSetup() {
        mergedCircle = new Circle(tileSize / 3.0);
        mergedCircle.setFill(Color.DARKGREY);
        mergedCircle.setStroke(Color.BLACK);

        blackCircle = new Circle(tileSize / 5.0);
        blackCircle.setFill(Color.BLACK);
        blackCircle.setStroke(Color.WHITE);

        whiteCircle = new Circle(tileSize / 5.0);
        whiteCircle.setFill(Color.WHITE);
        whiteCircle.setStroke(Color.BLACK);

        dynamicLayer.getChildren().addAll(mergedCircle, blackCircle, whiteCircle);
    }

    

    private void drawTutorialMessage() {
        if (game.getTutorialMessage() == null) return;
        String message = game.getTutorialMessage();
        Text text = new Text(30, 80, message);
        text.setFill(Color.NAVY);
        text.setStyle("-fx-font-size: 18px;");
        staticLayer.getChildren().add(text);
    }

    private void drawGrid() {
        for (int row = 0; row < game.grid.getHeight(); row++) {
            for (int col = 0; col < game.grid.getWidth(); col++) {
                //The tile being drawn on the grid
                Rectangle drawnTile = new Rectangle(
                        col * tileSize,
                        row * tileSize,
                        tileSize,
                        tileSize
                );
                //The tile object in the backend
                Tile tile = game.getGrid().getTileAt(new Position(col, row));

                //Drawing the tile on the grid in correspondance to the object in the backend
                switch (tile.getTileType()) {
                    case GREY -> drawnTile.setFill(Color.GREY);
                    case BLACK -> drawnTile.setFill(Color.BLACK);
                    case WHITE -> drawnTile.setFill(Color.WHITE);
                    case EXIT -> drawnTile.setFill(Color.GREEN);
                    case WALL -> {
                        drawnTile.setFill(createStripedWallPattern(tileSize));
                        drawnTile.setStroke(Color.BLACK);
                        drawnTile.setStrokeWidth(2);
                    }
                }
                drawnTile.setStroke(Color.BLACK);

                staticLayer.getChildren().add(drawnTile);
            }
        }
        
    }


    public void drawExitWarning() {
        if (game.shouldShowExitWarning()) {
            Text warning = new Text(
                100,
                60,
                "Collect all shards to exit!"
            );
            warning.setFill(Color.RED);
            warning.setStyle("-fx-font-size: 20px;");
            warning.setOpacity(0.9);
            staticLayer.getChildren().add(warning);
        }
    }

    private void drawShards() {

        for (Shard shard : game.getShards()) {

            Position pos = shard.getShardPosition();

            double centerX = pos.getX() * tileSize + tileSize / 2.0;
            double centerY = pos.getY() * tileSize + tileSize / 2.0;
            double offsetY = Math.sin(time) * 3;
            Circle shardCircle = new Circle(centerX, centerY + offsetY, tileSize / 8.0);

            // Color based on shard form
            switch (shard.getShardForm()) {
                case BLACK -> {shardCircle.setFill(Color.BLACK); shardCircle.setStroke(Color.WHITE);}
                case WHITE -> {shardCircle.setFill(Color.WHITE); shardCircle.setStroke(Color.BLACK);}
                case GREY -> {shardCircle.setFill(Color.DARKGREY); shardCircle.setStroke(Color.BLACK);}
            }

            staticLayer.getChildren().add(shardCircle);
        }
    }

    //if all winning requirements have been met, visuals will refelct winning
   public void checkForWin() {
        if (game.getStatus() != GameStatus.WON) return;

        double width = game.getGrid().getWidth() * tileSize;
        double height = game.getGrid().getHeight() * tileSize;

        Rectangle overlay = new Rectangle(width, height);
        overlay.setFill(Color.color(0, 0, 0, 0.6));

        Text wonText = new Text("YOU WON");
        wonText.setFill(Color.WHITE);
        wonText.setStyle("-fx-font-size: 40px;");
        wonText.setX((width - wonText.getLayoutBounds().getWidth()) / 2);
        wonText.setY(height / 2 - 20);

        Text restartText = new Text("Press R to Restart");
        restartText.setFill(Color.WHITE);
        restartText.setStyle("-fx-font-size: 18px;");
        restartText.setX((width - restartText.getLayoutBounds().getWidth()) / 2);
        restartText.setY(height / 2 + 10);

        Text nextText = new Text("Press N for Next Level");
        nextText.setFill(Color.WHITE);
        nextText.setStyle("-fx-font-size: 18px;");
        nextText.setX((width - nextText.getLayoutBounds().getWidth()) / 2);
        nextText.setY(height / 2 + 35);

        staticLayer.getChildren().addAll(overlay, wonText, restartText, nextText);
}

      //if all winning requirements have been met, visuals will refelct winning
    public void checkForLoss() {
        if (game.getStatus() == GameStatus.LOST) {

            Rectangle overlay = new Rectangle(
                game.getGrid().getWidth() * tileSize,
                game.getGrid().getHeight() * tileSize
            );
            overlay.setFill(Color.color(0, 0, 0, 0.6));

            Text loseText = new Text(
                100,
                100,
                "YOU LOST"
            );
            loseText.setFill(Color.WHITE);
            loseText.setStyle("-fx-font-size: 40px;");

            Text restartText = new Text(
                100,
                140,
                "Press R to Restart"
            );
            restartText.setFill(Color.WHITE);
            restartText.setStyle("-fx-font-size: 18px;");
            staticLayer.getChildren().addAll(overlay, loseText, restartText);
        }
    }

    public void displayShardCount() {
        Text shardCounter = new Text(
            10,
            12,
            "Shards: " + game.getCollectedShardCount() + " / " + game.getTotalShardCount()
        );
        shardCounter.setFill(Color.WHITE);
        shardCounter.setStyle("-fx-font-size: 12px;");
        staticLayer.getChildren().add(shardCounter);
    }

    public void setGame(Gamestate game) {
        this.game = game;
    }

    //allows for the creation of triepd tiles for walls
    private Paint createStripedWallPattern(int tileSize) {

        Canvas canvas = new Canvas(tileSize, tileSize);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Base color
        gc.setFill(Color.DARKSLATEGRAY);
        gc.fillRect(0, 0, tileSize, tileSize);

        // Stripes
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(4);

        for (int i = -tileSize; i < tileSize * 2; i += 12) {
            gc.strokeLine(i, 0, i - tileSize, tileSize);
        }

        WritableImage image = new WritableImage(tileSize, tileSize);
        canvas.snapshot(null, image);

        return new ImagePattern(image);
    }

    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            time += 0.05;
            updatePlayers();
            draw();
            
        }
    };


    private void updatePlayers() {
        double speed = 0.2; 

        if (game.getMergedPlayer() == null && (game.getWhitePlayer().getPlayerPosition().equals(game.getBlackPlayer().getPlayerPosition()))) {
            mergedCircle.setVisible(true);
            blackCircle.setVisible(false);
            whiteCircle.setVisible(false);
            blackCircle.setRadius(tileSize / 5.0);
            whiteCircle.setRadius(tileSize / 5.0);
            blackCircle.setCenterX(blackCircle.getCenterX() + 15);
            whiteCircle.setCenterX(blackCircle.getCenterX() - 15);
        }
        
        if (game.getMergedPlayer() != null) {
            mergedCircle.setVisible(true);
            blackCircle.setVisible(false);
            whiteCircle.setVisible(false);
            Position pos = game.getMergedPlayer().getPlayerPosition();
            double targetX = pos.getX() * tileSize + tileSize / 2.0;
            double targetY = pos.getY() * tileSize + tileSize / 2.0;
            renderMergedX += (targetX - renderMergedX) * speed;
            renderMergedY += (targetY - renderMergedY) * speed;

            mergedCircle.setCenterX(renderMergedX);
            mergedCircle.setCenterY(renderMergedY);
        }

        if (game.getBlackPlayer() != null) {
            mergedCircle.setVisible(false);
            blackCircle.setVisible(true);
            whiteCircle.setVisible(true);
            Position pos = game.getBlackPlayer().getPlayerPosition();
            double targetX = pos.getX() * tileSize + tileSize / 2.0;
            double targetY = pos.getY() * tileSize + tileSize / 2.0;

            renderBlackX += (targetX - renderBlackX) * speed;
            renderBlackY += (targetY - renderBlackY) * speed;

            blackCircle.setCenterX(renderBlackX);
            blackCircle.setCenterY(renderBlackY);
        }

        if (game.getWhitePlayer() != null) {
            mergedCircle.setVisible(false);
            blackCircle.setVisible(true);
            whiteCircle.setVisible(true);
            Position pos = game.getWhitePlayer().getPlayerPosition();
            double targetX = pos.getX() * tileSize + tileSize / 2.0;
            double targetY = pos.getY() * tileSize + tileSize / 2.0;

            renderWhiteX += (targetX - renderWhiteX) * speed;
            renderWhiteY += (targetY - renderWhiteY) * speed;

            whiteCircle.setCenterX(renderWhiteX);
            whiteCircle.setCenterY(renderWhiteY);
        }
    }



}