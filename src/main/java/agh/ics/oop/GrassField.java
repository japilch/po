package agh.ics.oop;

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

    private List<Grass> grassFields = new ArrayList<>();

    public GrassField(int numberOfGrassFields) {
        this.numberOfGrassFields = numberOfGrassFields;
        Random generator = new Random();
        for(int i = 0; i < numberOfGrassFields; i++) {
            Boolean isXCoordinateDistinct;
            Boolean isYCoordinateDistinct;
            int randomXCoordinate;
            int randomYCoordinate;
            do {
                isXCoordinateDistinct = true;
                randomXCoordinate = Math.abs(generator.nextInt())%(int)Math.sqrt((double)10*numberOfGrassFields);
                for(Grass iter: grassFields) {
                    if(iter.getPosition().getX()==randomXCoordinate) {
                        isXCoordinateDistinct = false;
                        break;
                    }
                }
            } while(!isXCoordinateDistinct);
            do {
                isYCoordinateDistinct = true;
                randomYCoordinate = Math.abs(generator.nextInt())%(int)Math.sqrt((double)10*numberOfGrassFields);
                for(Grass iter: grassFields) {
                    if(iter.getPosition().getY()==randomYCoordinate) {
                        isYCoordinateDistinct = false;
                        break;
                    }
                }
            } while(!isYCoordinateDistinct);
            grassFields.add(new Grass(new Vector2d(randomXCoordinate, randomYCoordinate)));
        }
    }

    public Object objectAt(Vector2d position) {
        if(super.objectAt(position) != null)
            return super.objectAt(position);
        for(Grass iter: grassFields) {
            if(position.equals(iter.getPosition())) {
                return iter;
            }
        }
        return null;
    }

    public boolean canMoveTo(Vector2d position) {
        return !(this.objectAt(position) instanceof Animal);
    }

    public boolean place(Animal animal) {
        if(canMoveTo(animal.getVector2d())) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    public Vector2d lowerLeft() {
        Vector2d result = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for(Animal iter: animals) {
            result = iter.getVector2d().lowerLeft(result);
        }
        for(Grass iter: grassFields) {
            result = iter.getPosition().lowerLeft(result);
        }
        return result;
    }

    public Vector2d upperRight() {
        Vector2d result = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for(Animal iter: animals) {
            result = iter.getVector2d().upperRight(result);
        }
        for(Grass iter: grassFields) {
            result = iter.getPosition().upperRight(result);
        }
        return result;
    }

    public IWorldMap returnThis() {
        return this;
    }

}
