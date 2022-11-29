package agh.ics.oop;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;

public class RectangularMap extends AbstractWorldMap implements IWorldMap {
    private int width;
    private int height;
    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
    }

    public boolean canMoveTo(Vector2d position) {
        if(position.getX() > width || position.getX() < 0)
            return false;
        if(position.getY() > height || position.getY() < 0)
            return false;
        return !this.isOccupied(position);
    }

    public boolean place(Animal animal) {
        if(this.canMoveTo(animal.getVector2d())) {
            animals.put(animal.getVector2d(), animal);
            return true;
        }
        return false;
    }

    public Vector2d lowerLeft() {
        return new Vector2d(0,0);
    }

    public Vector2d upperRight() {
        return new Vector2d(width, height);
    }

    public IWorldMap returnThis() {
        return this;
    }


}
