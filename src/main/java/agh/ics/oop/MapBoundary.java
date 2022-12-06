package agh.ics.oop;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

//uznalem, ze dodanie mozliwosci rozroznienia na zwierzeta i trawe spowodowaloby spore zamieszanie - prawdopodobnie musialbym utworzyc nowy typ danych
//np krotke, ktora skladalaby sie z Vector2d i typu obiektu na wspolrzednych, wymusilaby tez zmiane w positionChanged, a zatem i w interfejsie
//poniewaz kepki trawy sie nie poruszaja, mozna w czasie liniowym, przy dodawaniu policzyc ich lowerLeft i upperRight

public class MapBoundary implements IPositionChangeObserver{
    private TreeSet<Vector2d> sortedByXCoordinate = new TreeSet<Vector2d>(new Vector2dXComparator());
    private TreeSet<Vector2d> sortedByYCoordinate = new TreeSet<Vector2d>(new Vector2dYComparator());

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        this.remove(oldPosition);
        this.add(newPosition);
    }
    public void add(Vector2d position) {
        sortedByXCoordinate.add(position);
        sortedByYCoordinate.add(position);
    }
    public void remove(Vector2d position) {
        sortedByXCoordinate.remove(position);
        sortedByYCoordinate.remove(position);
    }
    public Vector2d lowerLeft() {
        return new Vector2d(sortedByXCoordinate.first().getX(),sortedByYCoordinate.first().getY());
    }
    public Vector2d upperRight() {
        return new Vector2d(sortedByXCoordinate.last().getX(),sortedByYCoordinate.last().getY());
    }
}
