package agh.ics.oop;

import java.util.Comparator;

public class Vector2dYComparator implements Comparator<Vector2d> {
    @Override public int compare(Vector2d v1, Vector2d v2) {
        if(v1.getY() < v2.getY())
            return -1;
        else if(v1.getY() == v2.getY()) {
            if(v1.getX() < v2.getX())
                return -1;
            else return 1;
        }
        return 1;
    }
}