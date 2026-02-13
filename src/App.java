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
        Scene scene = new Scene(root, 800, 600);
        GameView view = new GameView(game, root);


        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W -> game.moveMerged(Direction.UP);
                case S -> game.moveMerged(Direction.DOWN);
                case A -> game.moveMerged(Direction.LEFT);
                case D -> game.moveMerged(Direction.RIGHT);

                case M -> game.merge();
                case SPACE -> game.split();

                default -> {}
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
