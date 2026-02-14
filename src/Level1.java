import java.util.*;
public class Level1 implements Level {
    @Override
    public Gamestate create() {
        Tile[][] tiles = {
            { new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.WHITE), new Tile(TileType.GREY), new Tile(TileType.EXIT) },
            { new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.GREY), new Tile(TileType.GREY),    new Tile(TileType.BLACK), new Tile(TileType.GREY) }
        };

        Grid grid = new Grid(tiles);
        Player merged = new Player(new Position(0, 0), Form.GREY);

        List<Shard> shards = List.of(
            new Shard(new Position(1, 0), Form.GREY),
            new Shard(new Position(1, 1), Form.GREY),
            new Shard(new Position(4, 1), Form.BLACK),
            new Shard(new Position(3, 0), Form.WHITE)
        );

        Key key = new Key(4);
        Gamestate game = new Gamestate(grid, merged, new ArrayList<>(shards), key);
        game.enableTutorialMode(); 
        return game;
    }
}