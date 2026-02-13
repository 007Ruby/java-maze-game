import java.util.*;
public class DemoLevel {
    public static Gamestate create() {
        Tile[][] tiles = {
            { new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.EXIT) },
            { new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.GREY),    new Tile(TileType.GREY), new Tile(TileType.GREY) }
        };

        Grid grid = new Grid(tiles);
        Player player = new Player(new Position(0, 0), Form.GREY);

        List<Shard> shards = List.of(
            new Shard(new Position(1, 0), Form.GREY),
            new Shard(new Position(1, 1), Form.GREY)
        );

        Key key = new Key(2);

        return new Gamestate(grid, player, new ArrayList<>(shards), key);
    }
}