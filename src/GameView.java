import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GameView {

    private Pane root;
    private Gamestate game;
    private int tileSize = 80;

    public GameView(Gamestate game, Pane root) {
        this.game = game;
        this.root = root;
        draw();
    }

    public void draw() {
            root.getChildren().clear();
            drawGrid();
            drawPlayers();
            drawShards();
            checkForWin();
            checkForLoss();
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
                        case WALL -> drawnTile.setFill(Color.DARKGRAY);
                    }
                    drawnTile.setStroke(Color.BLACK);

                    root.getChildren().add(drawnTile);
                }
            }
            
        }

    private void drawPlayers() {

    int tileSize = 80;

    placePlayer(game.getMergedPlayer(), tileSize);
    placePlayer(game.getWhitePlayer(), tileSize);
    placePlayer(game.getBlackPlayer(), tileSize);
}

    private void placePlayer (Player player, int tileSize) {
        if (player == null) return;
        Position pos = player.getPlayerPosition();

        double centerX = pos.getX() * tileSize + tileSize / 2.0;
        double centerY = pos.getY() * tileSize + tileSize / 2.0;

        Circle playerCircle = new Circle(centerX, centerY, tileSize / 3.0);
        
        switch (player.getPlayerForm()) {
                case BLACK -> {playerCircle.setFill(Color.BLACK); playerCircle.setStroke(Color.WHITE);}
                case WHITE -> {playerCircle.setFill(Color.WHITE); playerCircle.setStroke(Color.BLACK);}
                case GREY -> {playerCircle.setFill(Color.DARKGREY); playerCircle.setStroke(Color.BLACK);}
            }
        root.getChildren().add(playerCircle);
    }

    private void drawShards() {

        for (Shard shard : game.getShards()) {

            Position pos = shard.getShardPosition();

            double centerX = pos.getX() * tileSize + tileSize / 2.0;
            double centerY = pos.getY() * tileSize + tileSize / 2.0;

            Circle shardCircle = new Circle(centerX, centerY, tileSize / 6.0);

            // Color based on shard form
            switch (shard.getShardForm()) {
                case BLACK -> {shardCircle.setFill(Color.BLACK); shardCircle.setStroke(Color.WHITE);}
                case WHITE -> {shardCircle.setFill(Color.WHITE); shardCircle.setStroke(Color.BLACK);}
                case GREY -> {shardCircle.setFill(Color.DARKGREY); shardCircle.setStroke(Color.BLACK);}
            }

            root.getChildren().add(shardCircle);
        }
    }

    //if all winning requirements have been met, visuals will refelct winning
    public void checkForWin() {
        if (game.getStatus() == GameStatus.WON) {

        Rectangle overlay = new Rectangle(
            game.getGrid().getWidth() * tileSize,
            game.getGrid().getHeight() * tileSize
        );
        overlay.setFill(Color.color(0, 0, 0, 0.6));

        Text winText = new Text (
            100,
            100,
            "YOU WIN"
        );
        winText.setFill(Color.WHITE);
        winText.setStyle("-fx-font-size: 40px;");

        root.getChildren().addAll(overlay, winText);
    }
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
            root.getChildren().addAll(overlay, loseText, restartText);
        }
    }

    public void setGame(Gamestate game) {
        this.game = game;
    }


}