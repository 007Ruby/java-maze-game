import java.util.List;

public class LevelManager {
    private List<Level> levels;
    private int currentLevelIndex;

    public LevelManager() {
        levels = List.of(
            new Level1(), new Level2(), new Level3()
        );
        currentLevelIndex = 0;
    }

    //loads the level corresponding to the current level index
    public Gamestate loadCurrentLevel() {
        return levels.get(currentLevelIndex).create();
    }

    //increments the level index to load the next one
    public void nextLevel() {
        if (currentLevelIndex < levels.size() - 1) {
            currentLevelIndex++;
        }
    }
}