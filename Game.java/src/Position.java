public class Position {
    //make positions immutable; avoids equality issues later in development
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position getPosition() {
        return this;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    //returns the corresponding position to each move 
    public Position move (Direction dir) {
        if (dir == Direction.UP) {
            return new Position(this.x, this.y + 1);
        } else if (dir == Direction.DOWN) {
            return new Position(this.x, this.y - 1);
        } else if (dir == Direction.RIGHT) {
            return new Position(this.x + 1, this.y);
        } else if (dir == Direction.LEFT) {
            return new Position(this.x - 1, this.y);
        } 
        return this;
    }
}
