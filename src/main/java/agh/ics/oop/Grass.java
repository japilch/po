package agh.ics.oop;

public class Grass {
    private Vector2d position;
    Grass(Vector2d position) {
        this.position = position;
    }

    Vector2d getPosition() {
        return position;
    }

    public String toString() {
        return "*";
    }

}
