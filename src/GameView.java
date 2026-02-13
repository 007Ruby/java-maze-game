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
        drawPlayers();
    }

    public void draw() {
            root.getChildren().clear();
            drawGrid();
        }

        private void drawGrid() {
            for (int row = 0; row < game.grid.getHeight(); row++) {
                for (int col = 0; col < game.grid.getWidth(); col++) {

                    Rectangle tile = new Rectangle(
                            col * tileSize,
                            row * tileSize,
                            tileSize,
                            tileSize
                    );

                    tile.setFill(Color.LIGHTGRAY);
                    tile.setStroke(Color.BLACK);

                    root.getChildren().add(tile);
                }
            }
        }

    private void drawPlayers() {
        Grid grid = game.getGrid();
        int tileSize = 80;
        Position position = new Position(0,0);
        Player player = new Player((position), Form.BLACK);

        // assume that the player will always be black for now
        if (game.areMerged()) {
            player = game.getMergedPlayer();
        }

        Position pos = player.getPlayerPosition();

        double centerX = pos.getX() * tileSize + tileSize / 2.0;
        double centerY = pos.getY() * tileSize + tileSize / 2.0;

        Circle robot = new Circle(centerX, centerY, tileSize / 3.0);
        robot.setFill(Color.BLACK);

        root.getChildren().add(robot);
    }


}