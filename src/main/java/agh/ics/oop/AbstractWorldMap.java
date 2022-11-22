package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap {
    //wydaje mi sie, ze przeniesienie tutaj listy animals nie ma sensu "ideowo", bo wiekszy sens "ideowo" mialoby dziedziczenie GrassField po RectangularMap
    //aczkolwiek przenioslem ja tutaj, aby spelnic polecenie "Dodaj klasę abstrakcyjną AbstractWorldMap, która zawiera kod wspólny dla tych klas."
    protected List<Animal> animals = new ArrayList<>();

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

    public abstract Vector2d lowerLeft();
    public abstract Vector2d upperRight();

    public abstract IWorldMap returnThis();

    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(returnThis());
        Vector2d lowerLeft = this.lowerLeft();
        Vector2d upperRight = this.upperRight();
        return visualizer.draw(lowerLeft, upperRight);
    }

}
