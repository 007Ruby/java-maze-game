import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage stage) {
        GameController controller = new GameController(stage);
        Scene scene = controller.createScene();
        stage.setScene(scene);
        stage.show();
    }
}
