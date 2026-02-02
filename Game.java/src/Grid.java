public class Grid {
    Tile[][] tiles;

    //ensures that position is within bounds
    public Boolean isWithinBounds (Position p) {
        return p.getX() >= 0 && p.getY() >= 0
        && p.getX() < tiles.length && p.getY() < tiles[0].length;
    }

    //returns the specific tile associated with a position
    public Tile getTileAt(Position p) {
        return tiles[p.getY()][p.getX()];
    }
}
