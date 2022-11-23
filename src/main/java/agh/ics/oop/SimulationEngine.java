package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SimulationEngine implements IEngine {
    private MoveDirection directions[];
    private IWorldMap map;
    private Vector2d initialCoordinates[];

    public SimulationEngine(MoveDirection directions[], IWorldMap map, Vector2d initialCoordinates[]) {
        this.directions = directions;
        this.map = map;
        this.initialCoordinates = initialCoordinates;
        for(Vector2d initializingVector: initialCoordinates) {
            map.place(new Animal(this.map, initializingVector));

        }
    }

    public void run() {
        for(int i = 0; i < directions.length; i++) {
            //umyslnie korzystam z tego, ze animalToMove przechowuje referencje, aby ominac problem zmiany koordynatow, ale nie wiem, czy taka praktyka jest poprawna
            Animal animalToMove = ((Animal)map.objectAt(initialCoordinates[i% initialCoordinates.length]));
            animalToMove.move(directions[i]);
            initialCoordinates[i% initialCoordinates.length] = animalToMove.getVector2d();
            System.out.println(map.toString());
        }
    }
}
