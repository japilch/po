package agh.ics.oop;

public class OptionsParser {
    public MoveDirection [] parse(String [] args){
        MoveDirection[] array;
        array = new MoveDirection[args.length];
        int iterator = 0;
        for(String x:args){
            switch(x){
                case "forward", "f" -> {
                    array[iterator] = MoveDirection.FORWARD;
                    iterator++;
                }
                case "backward", "b" -> {
                    array[iterator] = MoveDirection.BACKWARD;
                    iterator++;
                }
                case "right", "r" -> {
                    array[iterator] = MoveDirection.RIGHT;
                    iterator++;
                }
                case "left", "l" -> {
                    array[iterator] = MoveDirection.LEFT;
                    iterator++;
                }
                default -> {
                    throw new IllegalArgumentException(x + " is not legal move specification");
                }
            }
        }
        MoveDirection[] returnedArray;
        returnedArray = new MoveDirection[iterator];
        for(int i = 0; i < iterator; i++){
            returnedArray[i] = array[i];
        }
        return returnedArray;
    }
}
