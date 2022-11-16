import agh.ics.oop.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

}
