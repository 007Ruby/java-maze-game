import java.util.*;

public class Gamestate {
    Grid grid;
    Player player;
    List<Shard> shards;
    Key key;
    public Gamestate(Grid grid, Player player, List<Shard> shards, Key key) {
        this.grid = grid;
        this.player = player;
        this.shards = shards;
        this.key = key;
    }
    public void movePlayer (Direction d) {
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

    public boolean canExit() {
        return key.isComplete() &&
        shards.isEmpty() &&
        player.getPlayerForm() == Form.GREY &&
        grid.getTileAt(player.getPlayerPosition()).getType() == TileType.EXIT;

    }
}
