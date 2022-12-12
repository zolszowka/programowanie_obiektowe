package agh.ics.oop;

import com.sun.jdi.event.MonitorContendedEnteredEvent;

import java.util.ArrayList;

public class SimulationEngine implements IEngine, Runnable{
    MoveDirection[] moves;
    private final IWorldMap map;
    private final ArrayList<Animal> animals;
    private final int moveDelay;

    public SimulationEngine(IWorldMap map, Vector2d[] initialPositions, int moveDelay) {
        this.map = map;
        this.animals = new ArrayList<>();
        this.moveDelay = moveDelay;
        for (Vector2d position : initialPositions) {
            Animal animal = new Animal(map, position);
            if (map.place(animal)) {this.animals.add(animal);}
        }
    }

    public void setMoves(MoveDirection[] newMoves) {
        this.moves = newMoves;
    }
    @Override
    public void run() {
        if (animals.size() == 0) {
            return;
        }
        int size = this.moves.length;
        int numAnimals = this.animals.size();

        for (int i = 0; i < size; i++) {
            try{
                Thread.sleep(this.moveDelay);
                Animal animal = this.animals.get(i % numAnimals);
                animal.move(this.moves[i]);
            } catch (InterruptedException e ){
                e.printStackTrace();
            }
        }

    }



}
