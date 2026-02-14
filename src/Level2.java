import java.util.*;

public class Level2 implements Level {

    @Override
    public Gamestate create() {

        Tile[][] tiles = {
            { new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.EXIT) },

            { new Tile(TileType.GREY), new Tile(TileType.WHITE), new Tile(TileType.WHITE), new Tile(TileType.GREY),  new Tile(TileType.BLACK), new Tile(TileType.GREY) },

            { new Tile(TileType.GREY), new Tile(TileType.WHITE), new Tile(TileType.GREY),  new Tile(TileType.GREY),  new Tile(TileType.BLACK), new Tile(TileType.GREY) },

            { new Tile(TileType.GREY), new Tile(TileType.WHITE), new Tile(TileType.GREY),  new Tile(TileType.GREY),  new Tile(TileType.BLACK), new Tile(TileType.GREY) },

            { new Tile(TileType.GREY), new Tile(TileType.GREY),  new Tile(TileType.GREY),  new Tile(TileType.GREY),  new Tile(TileType.GREY),  new Tile(TileType.GREY) },

            { new Tile(TileType.GREY), new Tile(TileType.GREY),  new Tile(TileType.GREY),  new Tile(TileType.GREY),  new Tile(TileType.GREY),  new Tile(TileType.GREY) }
        };

        Grid grid = new Grid(tiles);

        Player merged = new Player(new Position(0, 0), Form.GREY);

        List<Shard> shards = List.of(
            new Shard(new Position(1, 1), Form.WHITE),   // white corridor
            new Shard(new Position(1, 3), Form.WHITE),

            new Shard(new Position(4, 1), Form.BLACK),   // black corridor
            new Shard(new Position(4, 3), Form.BLACK),

            new Shard(new Position(2, 5), Form.GREY)     // neutral shard
        );

        Key key = new Key(5);

        return new Gamestate(grid, merged, new ArrayList<>(shards), key);
    }
}
