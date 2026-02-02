public class Position {
    //make positions immutable; avoids equality issues later in development
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //equality is true for positions with the same x and y coordinates
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        //if object is not a position, return false
        if (!(o instanceof Position)) return false;
        //if object is a position, check for equality through x and y coordinates
        Position other = (Position) o;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
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
            return new Position(this.x, this.y - 1);
        } else if (dir == Direction.DOWN) {
            return new Position(this.x, this.y + 1);
        } else if (dir == Direction.RIGHT) {
            return new Position(this.x + 1, this.y);
        } else if (dir == Direction.LEFT) {
            return new Position(this.x - 1, this.y);
        } 
        

        return this;
    }
}
