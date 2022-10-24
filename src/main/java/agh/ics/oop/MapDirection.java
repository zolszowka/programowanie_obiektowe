package agh.ics.oop;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    public String toString() {
        return switch (this) {
            case EAST -> "Wschód";
            case SOUTH -> "Południe";
            case WEST -> "Zachód";
            case NORTH -> "Północ";
        };
    }

    public MapDirection next(){
        return switch (this) {
            case EAST -> MapDirection.SOUTH;
            case SOUTH -> MapDirection.WEST;
            case WEST -> MapDirection.NORTH;
            case NORTH -> MapDirection.EAST;
        };
    }

    public MapDirection previous(){
        return switch (this) {
            case EAST -> MapDirection.NORTH;
            case SOUTH -> MapDirection.EAST;
            case WEST -> MapDirection.SOUTH;
            case NORTH -> MapDirection.WEST;
        };
    }

    public Vector2d toUnitVector(){
        return switch (this) {
            case EAST -> new Vector2d (1,0);
            case SOUTH -> new Vector2d (0,-1);
            case WEST -> new Vector2d (-1,0);
            case NORTH -> new Vector2d (0,1);
        };
    }
}
