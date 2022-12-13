package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class SimulationEngine implements IEngine, Runnable {
    private MoveDirection directions[];
    private IWorldMap map;
    private Vector2d initialCoordinates[];

    private LinkedList<AnimalChangeObserver> observerList = new LinkedList<>();
    private IPositionChangeObserver observer;

    public SimulationEngine(MoveDirection directions[], IWorldMap map, Vector2d initialCoordinates[]/*, IPositionChangeObserver observer*/) {
        this.directions = directions;
        this.map = map;
        this.initialCoordinates = initialCoordinates;
//        this.observer = observer;
        for(Vector2d initializingVector: initialCoordinates) {
            Animal animalToPlace = new Animal(this.map, initializingVector);
//            animalToPlace.addObserver(observer);
            map.place(animalToPlace);
        }
    }

    public void run() {
        for(int i = 0; i < directions.length; i++) {
            //umyslnie korzystam z tego, ze animalToMove przechowuje referencje, aby ominac problem zmiany koordynatow, ale nie wiem, czy taka praktyka jest poprawna
            Animal animalToMove = ((Animal)map.objectAt(initialCoordinates[i% initialCoordinates.length]));
            animalToMove.move(directions[i]);
            initialCoordinates[i% initialCoordinates.length] = animalToMove.getVector2d();
            System.out.println(map.toString());
            notifyObservers();
        }
    }
    public void addObserver(AnimalChangeObserver observer) {
        observerList.add(observer);
    }
    public void removeObserver(AnimalChangeObserver observer) {
        observerList.remove(observer);
    }
    public void notifyObservers() {
        for(AnimalChangeObserver observer: observerList) {
            observer.changeHappened();
        }
    }
}
