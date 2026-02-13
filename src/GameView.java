import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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


}