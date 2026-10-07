package SynchronisationsProblem;

class Philosopher extends Thread {
    int id;
    private final FivePhilosophers philosophers;
    Philosopher(int id, FivePhilosophers philosophers) {
        this.id = id;
        this.philosophers = philosophers;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            philosophers.denken(id);
            try {
                philosophers.eat(id);
            } catch (InterruptedException e) {}
        }
    }
}

public class PhilosophersTest {
    public static void main(String[] args) throws InterruptedException {
        FivePhilosophers philosophers = new FivePhilosophers();
        Thread[] threads = new Thread[5];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Philosopher(i, philosophers);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Fertig");
    }
}
