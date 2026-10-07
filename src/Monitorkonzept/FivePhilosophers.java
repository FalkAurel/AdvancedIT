package Monitorkonzept;

import java.util.Arrays;


enum State {
    EATING,
    HUNGRY,
    THINKING
}
public class FivePhilosophers {
    private final State[] philosophers;

    public FivePhilosophers() {
        philosophers = new State[5];
        Arrays.fill(philosophers, State.THINKING);
    }

    synchronized public void startEat(int id) throws InterruptedException {
        int left = (id + philosophers.length - 1) % philosophers.length;
        int right = (id + 1) % philosophers.length;

        while (!(philosophers[left] != State.EATING && philosophers[right] != State.EATING )) {
            philosophers[id] = State.HUNGRY;
            this.wait();
        }

        philosophers[id] = State.EATING;
        System.out.println("Philosopher " + id + " is Eating");
    }

    synchronized public void endEat(int id) {
        philosophers[id] = State.THINKING;
        this.notifyAll();
    }

    void think(int id) {
        System.out.println("Philosopher " + id + " is Thinking");
    }
}
