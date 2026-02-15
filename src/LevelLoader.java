import java.io.*;
import java.util.*;

public class LevelLoader {

    public static Gamestate load(String resourcePath) {

        try {
            InputStream input = LevelLoader.class
                    .getResourceAsStream("/" + resourcePath);

            if (input == null) {
                throw new RuntimeException("Level file not found: " + resourcePath);
            }

            Scanner scanner = new Scanner(input);

            // Read grid size
            int rows = scanner.nextInt();
            int cols = scanner.nextInt();
            scanner.nextLine(); // consume end of line

            Tile[][] tiles = new Tile[rows][cols];
            Position playerStart = null;

            // Read grid
            for (int r = 0; r < rows; r++) {
                String line = scanner.nextLine();

                for (int c = 0; c < cols; c++) {
                    char ch = line.charAt(c);

                    switch (ch) {
                        case '.' -> tiles[r][c] = new Tile(TileType.GREY);
                        case '#' -> tiles[r][c] = new Tile(TileType.WALL);
                        case 'W' -> tiles[r][c] = new Tile(TileType.WHITE);
                        case 'B' -> tiles[r][c] = new Tile(TileType.BLACK);
                        case 'E' -> tiles[r][c] = new Tile(TileType.EXIT);
                        case 'P' -> {
                            tiles[r][c] = new Tile(TileType.GREY);
                            playerStart = new Position(r, c);
                        }
                        default -> throw new RuntimeException("Unknown tile: " + ch);
                    }
                }
            }

            Grid grid = new Grid(tiles);

            int keyValue = 0;
            List<Shard> shards = new ArrayList<>();

            // Parse metadata
            while (scanner.hasNext()) {
                String token = scanner.next();

                if (token.equals("KEY")) {
                    keyValue = scanner.nextInt();
                }

                else if (token.equals("SHARD")) {
                    int row = scanner.nextInt();
                    int col = scanner.nextInt();
                    String formStr = scanner.next();

                    Form form = Form.valueOf(formStr);
                    shards.add(new Shard(new Position(row, col), form));
                }
            }

            scanner.close();

            Player merged = new Player(playerStart, Form.GREY);
            Key key = new Key(keyValue);

            return new Gamestate(grid, merged, shards, key);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load level: " + e.getMessage());
        }
    }
}
