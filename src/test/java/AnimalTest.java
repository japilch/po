import agh.ics.oop.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    @Test
    void shouldntExitMapGoingForward() {
        Animal animal1 = new Animal();
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        Vector2d coordinates = new Vector2d(2, 4);
        assertEquals(animal1.getVector2d(), coordinates);
    }
    @Test
    void shouldntExitMapGoingBackward() {
        Animal animal1 = new Animal();
        animal1.move(MoveDirection.BACKWARD);
        animal1.move(MoveDirection.BACKWARD);
        animal1.move(MoveDirection.BACKWARD);
        Vector2d coordinates = new Vector2d(2, 0);
        assertEquals(animal1.getVector2d(), coordinates);
    }

    @Test
    void isOrientationCorrectAfterCreation(){
        Animal animal1 = new Animal();
        assertEquals(animal1.getDirection(), MapDirection.NORTH);
    }
    @Test
    void isOrientationCorrectAfterLeftTurn(){
        Animal animal1 = new Animal();
        animal1.move(MoveDirection.LEFT);
        assertEquals(animal1.getDirection(), MapDirection.WEST);
    }
    @Test
    void isOrientationCorrectAfterRightTurn(){
        Animal animal1 = new Animal();
        animal1.move(MoveDirection.RIGHT);
        assertEquals(animal1.getDirection(), MapDirection.EAST);
    }
    @Test
    void doesAnimalMoveCorrectly(){
        Animal animal1 = new Animal();
        for(int i = 0; i < 4; i++){
            animal1.move(MoveDirection.RIGHT);
            animal1.move(MoveDirection.FORWARD);
        }
        Vector2d correctCoordinates = new Vector2d(2,2);
        assertEquals(animal1.getVector2d(), correctCoordinates);
    }

    @Test
    void areFullDirectionNamesCorrectlyInterpreted(){
        Animal animal1 = new Animal();
        String[] fullNames;
        fullNames = new String[]{"forward", "backward", "left", "right"};
        OptionsParser parser1 = new OptionsParser();
        MoveDirection[] parsedFullNames;
        parsedFullNames = parser1.parse(fullNames);
        Vector2d correctCoordinates = new Vector2d(2,2);
        for(MoveDirection processedDirection:parsedFullNames)
            animal1.move(processedDirection);

        assertEquals(animal1.getVector2d(),correctCoordinates);
    }

    @Test
    void doesPlaceWorkCorrectly() {
        IWorldMap map = new RectangularMap(7, 7);
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
    @Test
    void doesObjectAtReturnCorrectObject() {
        IWorldMap map = new RectangularMap(5, 5);
        Vector2d position;
        Animal animal1;
        boolean isPlacedCorrectly = true;
        for(int i = 0; i <= 5; i++) {
            for(int j = 0; j <= 5; j++) {
                position = new Vector2d(i, j);
                animal1 = new Animal(map, position);
                map.place(animal1);
                isPlacedCorrectly = (animal1.equals(map.objectAt(position)));
            }
        }
        assertTrue(isPlacedCorrectly);
    }
    @Test
    void shouldntMoreAnimalsBeInSamePosition() {
        IWorldMap map = new RectangularMap(5, 5);
        Vector2d position = new Vector2d(2,2);
        Animal animal1 = new Animal(map, position);
        Animal animal2 = new Animal(map, position);
        map.place(animal1);

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            map.place(animal2);
        });
        assertTrue(exception.getMessage().contains("Unable to place an animal on field of coordinates "));
    }

}
