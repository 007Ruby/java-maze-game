import javafx.scene.layout.*;
import javafx.scene.input.KeyCode;

public class GameController {

    private Gamestate game;
    private GameView view;
    private LevelManager levelManager;

    public GameController(Pane root) {
        levelManager = new LevelManager();
        game = levelManager.loadCurrentLevel();
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
        }
    }

    private void movePrimary(Direction dir) {
        if (game.getMergedPlayer() != null)
            game.moveMerged(dir);
        else
            game.moveBlack(dir);
    }
}
