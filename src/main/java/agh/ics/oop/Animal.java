package agh.ics.oop;

public class Animal {
    private MapDirection direction;
    private Vector2d coordinates;
    public Animal(){
        this.direction = MapDirection.NORTH;
        this.coordinates = new Vector2d(2,2);
    }
    public String toString(){
        return "pozycja Zwierzecia: " + coordinates.toString() + ", orientacja zwierzecia: " + direction.toString();
    }
    public boolean isAt(Vector2d position){
        return this.coordinates.equals(position);
    }

    public void move(MoveDirection direction){
        int coordinateX = 0;
        int coordinateY = 0;
        Vector2d movement;
        switch (direction) {
            case FORWARD, BACKWARD -> {
                switch (this.direction) {
                    case NORTH -> {
                        coordinateY = 1;
                    }
                    case SOUTH -> {
                        coordinateY = -1;
                    }
                    case WEST -> {
                        coordinateX = -1;
                    }
                    case EAST -> {
                        coordinateX = 1;
                    }
                }
                movement = new Vector2d(coordinateX, coordinateY);
                if(direction == MoveDirection.BACKWARD)
                    movement = movement.opposite();
                if(coordinates.add(movement).x < 5 && coordinates.add(movement).x > -1 && coordinates.add(movement).y < 5 && coordinates.add(movement).y > -1)
                    this.coordinates = coordinates.add(movement);
            }
            case LEFT -> this.direction = this.direction.previous();
            case RIGHT -> this.direction = this.direction.next();
        }
    }
    public Vector2d getVector2d(){
        return this.coordinates;
    }

    public MapDirection getDirection() {
        return direction;
    }
}
