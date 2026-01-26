public class Tile {
    TileType type;

    public boolean isAccessibleBy(Form playerForm) {
        return type.isAccessibleBy(playerForm);
    }

    public TileType getType() {
        return type;
    }
}
