package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Vector;

public abstract class AbstractWorldMap implements IPositionChangeObserver {
    //wydaje mi sie, ze przeniesienie tutaj listy animals nie ma sensu "ideowo", bo wiekszy sens "ideowo" mialoby dziedziczenie GrassField po RectangularMap
    //aczkolwiek przenioslem ja tutaj, aby spelnic polecenie "Dodaj klasę abstrakcyjną AbstractWorldMap, która zawiera kod wspólny dla tych klas."
    protected HashMap<Vector2d, Animal> animals = new HashMap<Vector2d, Animal>();
    protected IPositionChangeObserver observer;

    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }
    public boolean isOccupied(Vector2d position) {
        return (this.objectAt(position) != null);
    }

    public abstract Vector2d lowerLeft();
    public abstract Vector2d upperRight();

    public abstract IWorldMap returnThis();

    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(returnThis());
        Vector2d lowerLeft = this.lowerLeft();
        Vector2d upperRight = this.upperRight();
        return visualizer.draw(lowerLeft, upperRight);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animalToMove = animals.get(oldPosition);
        animals.remove(oldPosition);
        //animalToMove.addObserver(observer);
        animals.put(newPosition, animalToMove);
    }

}
