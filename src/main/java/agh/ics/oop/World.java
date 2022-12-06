/*
Aby zaimplementować mechanizm, który wyklucza pojawienie się dwóch zwierząt w tym samym miejscu, można np.:
a) utworzyć obiekt będący dwuwymiarową tablicą, która przechowuje 1 jeżeli jakieś zwierzę już tam jest i 0 jeśli żadnego nie ma
b) utworzyć tablicę obiektów klasy Animal i za każdym razem sprawdzać wszystkie z nich
 */

package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.sql.SQLOutput;

public class World {
    public static void main(String args[]){

        try {
            Application.launch(App.class, args);

            /*MoveDirection[] directions = new OptionsParser().parse(args);
            IWorldMap map = new GrassField(6);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            //Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2,2) };
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();*/

        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            System.exit(-1);
        }

    }
}
