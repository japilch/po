package agh.ics.oop;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;


/*
Do zadania 10: Dodanie interfejsu IMapElement moim zdaniem pozwoliloby zamienic aktualnie istniejace dwie listy na jedna, na ktorej obiektach
mozna by uruchamiac funkcje implementowane przez rozne klasy, co z kolei pozwoli skrocic kod
Do zadania 11: Z racji, ze jedynym wspolnym elementem klas Animal i Grass jest Vector2D, watpie, aby dziedziczenie w tym wypadku mialo wieksy sens
*/

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private int numberOfGrassFields;

    private HashMap<Vector2d,Grass> grassFields = new HashMap<>();

    public GrassField(int numberOfGrassFields) {
        this.numberOfGrassFields = numberOfGrassFields;
        Random generator = new Random();
        for(int i = 0; i < numberOfGrassFields; i++) {
            int randomXCoordinate;
            int randomYCoordinate;
            do {
                randomXCoordinate = Math.abs(generator.nextInt())%(int)Math.sqrt((double)10*numberOfGrassFields);
                randomYCoordinate = Math.abs(generator.nextInt())%(int)Math.sqrt((double)10*numberOfGrassFields);
            } while(grassFields.containsKey(new Vector2d(randomXCoordinate,randomYCoordinate)));
            grassFields.put(new Vector2d(randomXCoordinate, randomYCoordinate),new Grass(new Vector2d(randomXCoordinate, randomYCoordinate)));
        }
    }

    public Object objectAt(Vector2d position) {
        if(super.objectAt(position) != null)
            return super.objectAt(position);
        return grassFields.get(position);
    }

    public boolean canMoveTo(Vector2d position) {
        return !(this.objectAt(position) instanceof Animal);
    }

    public boolean place(Animal animal) {
        if(canMoveTo(animal.getVector2d())) {
            animals.put(animal.getVector2d(),animal);
            return true;
        }
        return false;
    }

    public Vector2d lowerLeft() {
        Vector2d result = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for(Vector2d vector: animals.keySet()) {
            result = vector.lowerLeft(result);
        }
        for(Vector2d vector: grassFields.keySet()) {
            result = vector.lowerLeft(result);
        }
        return result;
    }

    public Vector2d upperRight() {
        Vector2d result = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for(Vector2d vector: animals.keySet()) {
            result = vector.upperRight(result);
        }
        for(Vector2d vector: grassFields.keySet()) {
            result = vector.lowerLeft(result);
        }
        return result;
    }

    public IWorldMap returnThis() {
        return this;
    }

}
