import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Gamestate game = DemoLevel.create();
        Pane root = new Pane();
        Scene scene = new Scene(root,
        game.getGrid().getWidth() * 80,
        game.getGrid().getHeight() * 80);
        GameView view = new GameView(game, root);


    scene.setOnKeyPressed(e -> {
        switch (e.getCode()) {

            case UP -> {
                if (game.getMergedPlayer() != null)
                    game.moveMerged(Direction.UP);
                else
                    game.moveBlack(Direction.UP);
            }

            case DOWN -> {
                if (game.getMergedPlayer() != null)
                    game.moveMerged(Direction.DOWN);
                else
                    game.moveBlack(Direction.DOWN);
            }

            case LEFT -> {
                if (game.getMergedPlayer() != null)
                    game.moveMerged(Direction.LEFT);
                else
                    game.moveBlack(Direction.LEFT);
            }

            case RIGHT -> {
                if (game.getMergedPlayer() != null)
                    game.moveMerged(Direction.RIGHT);
                else
                    game.moveBlack(Direction.RIGHT);
            }

            case W -> game.moveWhite(Direction.UP);
            case S -> game.moveWhite(Direction.DOWN);
            case A -> game.moveWhite(Direction.LEFT);
            case D -> game.moveWhite(Direction.RIGHT);

            case M -> game.merge();
            case SPACE -> game.split();
        }

        view.draw();
    });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
