import java.util.*;
public class DemoLevel {
    public static Gamestate create() {
        Tile[][] tiles = {
            { new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.EXIT) },
            { new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.GREY),    new Tile(TileType.GREY), new Tile(TileType.GREY) }
        };

        Grid grid = new Grid(tiles);
        Player black = new Player(new Position(0, 0), Form.BLACK);
        Player white = new Player(new Position(1, 0), Form.WHITE);

        List<Shard> shards = List.of(
            new Shard(new Position(1, 0), Form.GREY),
            new Shard(new Position(1, 1), Form.GREY),
            new Shard(new Position(4, 1), Form.BLACK),
            new Shard(new Position(3, 0), Form.WHITE)
        );

        Key key = new Key(4);

        return new Gamestate(grid, white, black, new ArrayList<>(shards), key);
    }
}