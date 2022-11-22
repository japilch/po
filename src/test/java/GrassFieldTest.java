import agh.ics.oop.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {

    @Test
    void canAnimalBePlacedOnGrass() {
        GrassField field = new GrassField(10);
        for(int i = 0; i <= field.upperRight().getX(); i++) {
            for(int j = 0; j <= field.upperRight().getY(); j++) {
                if(field.objectAt(new Vector2d(i, j)) instanceof Grass) {
                    field.place(new Animal(field,new Vector2d(i,j)));
                }
            }
        }
        int numberOfAnimals = 0;
        for(int i = 0; i <= field.upperRight().getX(); i++) {
            for(int j = 0; j <= field.upperRight().getY(); j++) {
                if(field.objectAt(new Vector2d(i, j)) instanceof Animal) {
                    numberOfAnimals++;
                }
            }
        }
        assertEquals(numberOfAnimals,10);
    }

    void isGrassDetectedByIsOccupiedFunction() {
        GrassField field = new GrassField(6);
        int numberOfGrass = 0;
        for(int i = 0; i <= field.upperRight().getX(); i++) {
            for(int j = 0; j <= field.upperRight().getY(); j++) {
                if(field.isOccupied(new Vector2d(i, j))) {
                    numberOfGrass++;
                }
            }
        }
        assertEquals(numberOfGrass,6);
    }

    @Test
    void shouldntMoreAnimalsBeInSamePosition() {
        IWorldMap map = new GrassField(10);
        Vector2d position = new Vector2d(2,2);
        Animal animal1 = new Animal(map, position);
        Animal animal2 = new Animal(map, position);
        map.place(animal1);
        assertFalse(map.place(animal2));
    }

    @Test
    void doesPlaceWorkCorrectly() {
        IWorldMap map = new GrassField(10);
        Vector2d position;
        Animal animal1;
        boolean isPlaced = true;
        for(int i = 0; i <= 7; i++) {
            for(int j = 0; j <= 7; j++) {
                position = new Vector2d(i, j);
                animal1 = new Animal(map, position);
                map.place(animal1);
                isPlaced = (isPlaced && map.isOccupied(position));
            }
        }
        assertTrue(isPlaced);
    }

}
