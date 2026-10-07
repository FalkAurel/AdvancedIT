package Monitorkonzept;

class Philosopher extends Thread {
    private final FivePhilosophers philosophers;
    private final int id;

    public Philosopher(int id, FivePhilosophers philosophers) {
        this.philosophers = philosophers;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1; i++) {
            try {
                philosophers.startEat(id);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            philosophers.endEat(id);
            philosophers.think(id);
        }
    }
}

public class RunMain {
    public static void main(String[] args) {
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            threads[i] = new Philosopher(i, new FivePhilosophers());
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {}
        }

        System.out.println("ENDE");
    }
}
