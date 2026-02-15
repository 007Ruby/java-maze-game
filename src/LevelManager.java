import java.util.List;

public class LevelManager {
    private List<String> levels;
    private int currentLevelIndex;

    public LevelManager() {
        levels = List.of(
            "levels/level1.txt",
            "levels/level2.txt",
            "levels/level3.txt"
        );
        currentLevelIndex = 0;
    }

    //loads the level corresponding to the current level index
    public Gamestate loadCurrentLevel() {
        return LevelLoader.load(levels.get(currentLevelIndex));
    }

    //increments the level index to load the next one
    public void nextLevel() {
        if (currentLevelIndex < levels.size() - 1) {
            currentLevelIndex++;
        }
    }

    //returns true if the current level is the final level
    //returns false if not
    public boolean isLastLevel() {
        return currentLevelIndex == levels.size() - 1;
    }
}