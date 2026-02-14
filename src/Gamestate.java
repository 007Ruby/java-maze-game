import java.util.*;

public class Gamestate {
    private List<GameStateListener> listeners = new ArrayList<>();
    private GameStatus status = GameStatus.PLAYING;
    private boolean showExitWarning = false;
    private boolean tutorialMode = false;
    private String tutorialMessage = null;
    private int tutorialStep = 1;

    Grid grid;
    Player black;
    Player white;
    Player merged;
    List<Shard> shards;
    Key key;
    public Gamestate(Grid grid, Player merged, List<Shard> shards, Key key) {
        this.grid = grid;
        this.merged = merged;
        this.white = null;
        this.black = null;
        this.shards = shards;
        this.key = key;
    }

   public void enableTutorialMode() {
        tutorialMode = true;
        tutorialMessage = "Use arrow keys to move\nUse SPACE to split & M to merge";
    }

    public String getTutorialMessage() {
        return tutorialMessage;
    }
    //checks grid bounds
    //moves player
    //checks and updates status 
    public void applyInstruction (Player player, Direction d) {
        if (status != GameStatus.PLAYING) return;
        tutorialModeManger();
        Position current = player.getPlayerPosition();
        Position next = current.move(d);

        if (!grid.isWithinBounds(next)) return;
        Tile tile = grid.getTileAt(next);
        if (!tile.isAccessibleBy(player.getPlayerForm())) return;

        // Now we know movement is valid
        player.setPlayerPosition(next);

        collectShardIfPresent(player, next);

        checkKeyCompletionAtExit();

        if (tile.isDeadlyFor(player.getPlayerForm())) {
            status = GameStatus.LOST;
        } else if (canExit()) {
            status = GameStatus.WON;
        }

        notifyListeners();
    
    }

    public void tutorialModeManger() {
        if (tutorialMode && tutorialStep == 1) {
            tutorialStep = 2;
            tutorialMessage = "Use A,S,W,D to move WHITE player\nCollect all shards & merge before exit";
        } else if (tutorialMode && tutorialStep == 2) {
            tutorialMessage = null;
            tutorialMode = false;
        }
    }

    //if merged player is at the exit, check if the key is complete
    //if key is not complete, display a message
    public void checkKeyCompletionAtExit() {
        if (merged != null &&
            grid.getTileAt(merged.getPlayerPosition()).getTileType() == TileType.EXIT &&
            !key.isComplete()) {
            showExitWarning = true;
        } else {
            showExitWarning = false;
        }
    }

    //shows exit warning if requried
    public boolean shouldShowExitWarning() {
        return showExitWarning;
    }
    
    //moves player if tile is accessible
    public void movePlayer(Player player, Position newPosition) {
         player.setPlayerPosition(newPosition);
            notifyListeners();
            //check if any compatible shard is at the new position so it can be collected
            for (Shard shard: shards) {
                Boolean shardAtPlayerPosition = shard.getShardPosition().equals(newPosition);
                Boolean shardHasPlayerForm = shard.getShardForm().equals(player.getPlayerForm());
                if (shardAtPlayerPosition && shardHasPlayerForm) {
                    //remove shard from grid, add to key
                    key.addShardToKey();
                    shards.remove(shard);
                    break;
                }
            }
    }

    private void collectShardIfPresent(Player player, Position position) {
        Iterator<Shard> it = shards.iterator();
        while (it.hasNext()) {
            Shard shard = it.next();
            if (shard.getShardPosition().equals(position) &&
                shard.getShardForm().equals(player.getPlayerForm())) {

                key.addShardToKey();
                it.remove();
                break;
            }
        }
    }

    public void moveBlack(Direction dir) {
        if (black != null) applyInstruction(black, dir);
    }

    public void moveWhite(Direction dir) {
        if (white != null) applyInstruction(white, dir);
    }

    public void moveMerged(Direction dir) {
        if (merged != null) applyInstruction(merged, dir);
    }
    //checks if black and white entities can merge
    //to do so, they must be at the same position
    public boolean canMerge() {
        return white != null &&
        black != null && 
        black.getPlayerPosition().equals(white.getPlayerPosition());
    }

    //if entities can merge, merged entity is created in that position
    //black and white entities become null
    public void merge() {
        if (!canMerge()) return;
        merged = new Player (black.getPlayerPosition(), Form.GREY);
        black = null;
        white = null;
        notifyListeners();

    }

    //return GreyPlayer
    public Player getMergedPlayer() {
        if((merged == null)) return null;
        return merged;
    }

    //return white player
    public Player getWhitePlayer() {
        if((white == null)) return null;
        return white;
    }

    //return black player 
    public Player getBlackPlayer() {
        if((black == null)) return null;
        return black;
    }

    public List<Shard> getShards() {
        return shards;
    }

    //checks if entities can split
    //to be able to do so, they must be on a neutral tile
    public boolean canSplit() {
        if (merged == null) return false;
        if (grid.getTileAt(merged.getPlayerPosition()).getTileType() == TileType.GREY) return true;
        return false;
    }

    //if entities can split, they split on that tile
    //two new entities (black and white) are created, and merged entity becomes null
    public void split() {
        if (!canSplit()) return;
        black = new Player (merged.getPlayerPosition(), Form.BLACK);
        white = new Player (merged.getPlayerPosition(), Form.WHITE);
        merged = null;
        notifyListeners();
    }

    //checks if the entity can exit
    //to do, entities must be merged (in grey form) and must be at the exit tile
    public boolean canExit() {
        System.out.println("im in here");
        if (key.isComplete()) {
            System.out.println("KEY IS COMPLETE");
        }
        if (shards.isEmpty()) {
            System.out.println("SHARDS ARE TAKEN");
        }
        return key.isComplete() &&
        shards.isEmpty() &&
        merged != null  &&
        grid.getTileAt(merged.getPlayerPosition()).getTileType() == TileType.EXIT;
    }

    public GameStatus getStatus() {
        return status;
    }

    public interface GameStateListener {
        void onStateChanged();
    }

    public void addListener(GameStateListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (GameStateListener listener : listeners) {
            listener.onStateChanged();
        }
    }

    public Grid getGrid() {
        return this.grid;
    }

    public int getCollectedShardCount() {
        return key.getCollectedShardCount();
    }

    public int getTotalShardCount() {
        return key.getRequiredShardCount();
    }


}
