public class Tile {
    TileType type;
    
    public Tile(TileType type) {
        this.type = type;
    }
    public boolean isAccessibleBy(Form playerForm) {
        return type.isAccessibleBy(playerForm);
    }
    public boolean isDeadlyFor(Form PlayerForm) {
        return type.isDeadlyFor(PlayerForm);
    }

    public TileType getTileType() {
        return type;
    }


}
