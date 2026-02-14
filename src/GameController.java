import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class GameController {

    private Gamestate game;
    private GameView view;
    private LevelManager levelManager;
    private Pane root;

    public GameController() {
        levelManager = new LevelManager();
        game = levelManager.loadCurrentLevel();
        root = new Pane();
        view = new GameView(game, root);

        game.addListener(() -> view.draw());
    }

    public void handleKey(KeyCode key) {

        switch (key) {

            case UP -> movePrimary(Direction.UP);
            case DOWN -> movePrimary(Direction.DOWN);
            case LEFT -> movePrimary(Direction.LEFT);
            case RIGHT -> movePrimary(Direction.RIGHT);

            case W -> game.moveWhite(Direction.UP);
            case S -> game.moveWhite(Direction.DOWN);
            case A -> game.moveWhite(Direction.LEFT);
            case D -> game.moveWhite(Direction.RIGHT);

            case M -> game.merge();
            case SPACE -> game.split();
            case R -> restartLevel();
        }
    }

    private void restartLevel() {
        game = levelManager.loadCurrentLevel();
        view.setGame(game);          
        game.addListener(() -> view.draw());
        view.draw();
    }

    private void movePrimary(Direction dir) {
        if (game.getMergedPlayer() != null)
            game.moveMerged(dir);
        else
            game.moveBlack(dir);
    }

    public Scene createScene() {
        int tileSize = 80;
        int width = game.getGrid().getWidth() * tileSize;
        int height = game.getGrid().getHeight() * tileSize;

        Scene scene = new Scene(root, width, height);
        scene.setOnKeyPressed(e -> handleKey(e.getCode()));
        return scene;
    }
}
