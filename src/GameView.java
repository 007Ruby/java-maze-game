import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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
        Grid grid = game.getGrid();
        int tileSize = 80;
        Player player = new Player((game.getMergedPlayer().getPlayerPosition()), Form.GREY);

        // assume that the player will always be grey for now
        if (game.areMerged()) {
            player = game.getMergedPlayer();
        }

        Position pos = player.getPlayerPosition();

        double centerX = pos.getX() * tileSize + tileSize / 2.0;
        double centerY = pos.getY() * tileSize + tileSize / 2.0;

        Circle robot = new Circle(centerX, centerY, tileSize / 3.0);
        robot.setFill(Color.DARKGREY);

        root.getChildren().add(robot);
    }

    private void drawShards() {

        for (Shard shard : game.getShards()) {

            Position pos = shard.getShardPosition();

            double centerX = pos.getX() * tileSize + tileSize / 2.0;
            double centerY = pos.getY() * tileSize + tileSize / 2.0;

            Circle shardCircle = new Circle(centerX, centerY, tileSize / 6.0);

            // Color based on shard form
            switch (shard.getShardForm()) {
                case BLACK -> shardCircle.setFill(Color.BLACK);
                case WHITE -> shardCircle.setFill(Color.WHITE);
                case GREY -> shardCircle.setFill(Color.DARKGREY);
            }

            root.getChildren().add(shardCircle);
        }
    }


}