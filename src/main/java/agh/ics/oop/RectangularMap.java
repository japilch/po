package agh.ics.oop;
import java.util.List;
import java.util.ArrayList;

public class RectangularMap implements IWorldMap {
    private int width;
    private int height;
    List<Animal> animals = new ArrayList<>();
    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
    }

    public Object objectAt(Vector2d position) {
        for(Animal iter: animals) {
            if(position.equals(iter.getVector2d())) {
                return iter;
            }
        }
        return null;
    }

    public boolean isOccupied(Vector2d position) {
        return (this.objectAt(position) != null);
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
            animals.add(animal);
            return true;
        }
        return false;
    }

    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        Vector2d lowerLeft = new Vector2d(0,0);
        Vector2d upperRight = new Vector2d(width, height);
        return visualizer.draw(lowerLeft, upperRight);
    }


}
