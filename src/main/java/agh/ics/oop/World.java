/*
Aby zaimplementować mechanizm, który wyklucza pojawienie się dwóch zwierząt w tym samym miejscu, można np.:
a) utworzyć obiekt będący dwuwymiarową tablicą, która przechowuje 1 jeżeli jakieś zwierzę już tam jest i 0 jeśli żadnego nie ma
b) utworzyć tablicę obiektów klasy Animal i za każdym razem sprawdzać wszystkie z nich
 */

package agh.ics.oop;

import java.sql.SQLOutput;

public class World {
    public static void main(String args[]){
        Animal animal1 = new Animal();
        /*System.out.println(animal1.toString());
        animal1.move(MoveDirection.RIGHT);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        System.out.println(animal1.toString());*/
        OptionsParser parser1 = new OptionsParser();
        MoveDirection [] controlArray = parser1.parse(args);
        for(MoveDirection processedDirection:controlArray){
            animal1.move(processedDirection);
            System.out.println(animal1.toString());
        }
    }
}
