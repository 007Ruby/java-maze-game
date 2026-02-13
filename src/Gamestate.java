import java.util.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class Gamestate {
    private List<GameStateListener> listeners = new ArrayList<>();
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
    public void movePlayer (Player player, Direction d) {
        //find the player's new position 
        Position playerPosition = player.getPlayerPosition();
        Position newPosition = playerPosition.move(d);
        Tile newPositionTile = grid.getTileAt(newPosition);
        //check that the new position is within the bounds of the grid
        Boolean newPositionWithinBounds = grid.isWithinBounds(newPosition);
        //and check that the player has tile accessibility to that position
        Boolean newPositionIsAccessible = newPositionTile.isAccessibleBy(player.getPlayerForm());
        if (newPositionWithinBounds && newPositionIsAccessible) {
            player.setPlayerPosition(newPosition);
            //check if any compatible shard is at the new position so it can be collected
            for (Shard shard: shards) {
                Boolean shardAtPlayerPosition = shard.getShardPosition().equals(newPosition);
                Boolean shardHasPlayerForm = shard.getShardForm().equals(player.getPlayerForm());
                if (shardAtPlayerPosition && shardHasPlayerForm) {
                    //remove shard from grid, add to key
                    key.addShardToKey(shard);
                    shards.remove(shard);
                    break;
                }
            }
        }
    }

    public void moveBlack(Direction dir) {
        if (black != null) movePlayer(black, dir);
    }

    public void moveWhite(Direction dir) {
        if (white != null) movePlayer(white, dir);
    }

    public void moveMerged(Direction dir) {
        if (merged != null) movePlayer(merged, dir);
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
    }

    //returns if the players are merged
    public boolean areMerged() {
        return merged != null;
    }

    //return GreyPlayer
    public Player getMergedPlayer() {
        if(areMerged()) 
            {return this.merged;}
        return null;
    }

    //checks if entities can split
    //to be able to do so, they must be on a neutral tile
    public boolean canSplit() {
        if (merged == null) return false;
        if (grid.getTileAt(merged.getPlayerPosition()).getType() == TileType.NEUTRAL) return true;
        return false;
    }

    //if entities can split, they split on that tile
    //two new entities (black and white) are created, and merged entity becomes null
    public void split() {
        if (!canSplit()) return;
        black = new Player (merged.getPlayerPosition(), Form.BLACK);
        white = new Player (merged.getPlayerPosition(), Form.WHITE);
        merged = null;
    }

    //checks if the entity can exit
    //to do, entities must be merged (in grey form) and must be at the exit tile
    public boolean canExit() {
        return key.isComplete() &&
        shards.isEmpty() &&
        merged != null  &&
        grid.getTileAt(merged.getPlayerPosition()).getType() == TileType.EXIT;
    }

    public boolean isGameWon() {
        return canExit();
    }

    public GameStatus getStatus() {
        if (canExit()) return GameStatus.WON;
        return GameStatus.PLAYING;
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


}
