package agh.ics.oop;

import java.sql.SQLOutput;

public class World {


    static Direction[] translate(String array[]){
        Direction translatedArray[];
        translatedArray = new Direction[array.length];
        for(int i = 0; i < array.length; i++){
            switch(array[i]) {
                case "f":
                    translatedArray[i] = Direction.FORWARD;
                    break;
                case "b":
                    translatedArray[i] = Direction.BACKWARD;
                    break;
                case "l":
                    translatedArray[i] = Direction.LEFT;
                    break;
                case "r":
                    translatedArray[i] = Direction.RIGHT;
                    break;
                default:
                    translatedArray[i] = Direction.NONE;
                    break;
            }
        }
        return translatedArray;
    }
    public static void run(Direction array[]){
        for(int i = 0; i < array.length; i++){
            switch(array[i]) {
                case FORWARD:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case LEFT:
                    System.out.println("Zwierzak skręca w lewo");
                    break;
                case RIGHT:
                    System.out.println("Zwierzak skręca w prawo");
                    break;
            }
        }
    }

/*    public static void run(String array[]){
        System.out.println("Zwierzak idzie do przodu");
        for(int i = 0; i < array.length - 1; i++){
            System.out.print(array[i]+", ");
        }
        System.out.println(array[array.length-1]);

        for(int i = 0; i < array.length; i++){
            switch(array[i]) {
                case "f":
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case "b":
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case "l":
                    System.out.println("Zwierzak skręca w lewo");
                    break;
                case "r":
                    System.out.println("Zwierzak skręca w prawo");
                    break;
            }
        }
    }
    */

    public static void main(String args[]){
        /*System.out.println("System wystartował");
        run(translate(args));
        System.out.println("System zakończył działanie");*/
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        MapDirection direction = MapDirection.SOUTH;
        String directionAsText = direction.toString();
        for(int i = 0; i < 4; i++){
            System.out.println(directionAsText);
            Vector2d vector = direction.toUnitVector();
            System.out.println(vector.toString());
            direction = direction.next();
            directionAsText = direction.toString();
        }
        for(int i = 0; i < 4; i++){
            System.out.println(directionAsText);
            Vector2d vector = direction.toUnitVector();
            System.out.println(vector.toString());
            direction = direction.previous();
            directionAsText = direction.toString();
        }

    }
}
