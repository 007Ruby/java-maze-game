import java.util.*;
public class DemoLevel {
    public static Gamestate create() {
        Tile[][] tiles = {
            { new Tile(TileType.NEUTRAL), new Tile(TileType.BLACK), new Tile(TileType.EXIT) },
            { new Tile(TileType.WALL),    new Tile(TileType.WHITE), new Tile(TileType.NEUTRAL) }
        };

        Grid grid = new Grid(tiles);
        Player player = new Player(new Position(0, 0), Form.BLACK);

        List<Shard> shards = List.of(
            new Shard(new Position(1, 0), Form.BLACK),
            new Shard(new Position(1, 1), Form.WHITE)
        );

        Key key = new Key(2);

        return new Gamestate(grid, player, new ArrayList<>(shards), key);
    }
}