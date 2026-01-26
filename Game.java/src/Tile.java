public class Tile {
    TileType type;

    public boolean isAccessibleBy(PlayerForm form) {
        return type.isAccessibleBy(form);
    }

    public TileType getType() {
        return type;
    }
}
