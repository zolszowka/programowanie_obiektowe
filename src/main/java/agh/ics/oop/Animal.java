package agh.ics.oop;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    public String toString(){
        return "(" + this.orientation + ", " + this.position + ")";
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

    public void move(MoveDirection direction){
        Vector2d new_position;

        switch(direction){
            case RIGHT -> {
                this.orientation = this.orientation.next();
                return;
            }
            case LEFT -> {
                this.orientation = this.orientation.previous();
                return;
            }
            case FORWARD -> new_position = this.position.add(this.orientation.toUnitVector());
            case BACKWARD -> new_position = this.position.subtract(this.orientation.toUnitVector());
            default -> {
                return;
            }
        }

        if (new_position.x >= 0 && new_position.x <= 4 && new_position.y>=0 && new_position.y<=4){
            this.position = new_position;
        }
    }


}
