public class Tile {
    TileType type;
    
    public Tile(TileType type) {
        this.type = type;
    }
    public boolean isAccessibleBy(Form playerForm) {
        return type.isAccessibleBy(playerForm);
    }

    public TileType getTileType() {
        return type;
    }


}
