package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedList;

public class Animal {
    private MapDirection direction;
    private Vector2d coordinates;
    private IWorldMap mapReference;

    private LinkedList<IPositionChangeObserver> observerList = new LinkedList<>();
    public Animal(){
        this.direction = MapDirection.NORTH;
        this.coordinates = new Vector2d(2,2);
    }

    public Animal(IWorldMap map){
        mapReference = map;
        this.direction = MapDirection.NORTH;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        mapReference = map;
        this.coordinates = initialPosition;
        this.direction = MapDirection.NORTH;
    }



    public String toString(){
        //return "pozycja Zwierzecia: " + coordinates.toString() + ", orientacja zwierzecia: " + direction.toString();
        String result = "";
        switch (this.direction) {
            case NORTH -> {
                result = "^";
            }
            case SOUTH -> {
                result = "v";
            }
            case WEST -> {
                result = "<";
            }
            case EAST -> {
                result = ">";
            }
        }
        return result;
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
                if(mapReference.canMoveTo(this.coordinates.add(movement))) {
                    this.positionChanged(this.coordinates, this.coordinates.add(movement));
                    this.coordinates = coordinates.add(movement);
                }

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
    public void addObserver(IPositionChangeObserver observer) {
        observerList.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer) {
        observerList.remove(observer);
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for(IPositionChangeObserver observer: observerList) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }
}
