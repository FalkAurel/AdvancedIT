package SynchronisationsProblem;

import java.util.concurrent.Semaphore;

public class FivePhilosophers  {
    private final Semaphore[] philosophers = new Semaphore[5];
    private final Boolean[] is_eating = new Boolean[5];
    private final Boolean[] is_hungry = new Boolean[5];
    private final Semaphore mutex = new Semaphore(1, true);

    public FivePhilosophers() {
        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new Semaphore(0, true);
            is_eating[i] = false;
            is_hungry[i] = false;
        }
    }

    public void denken(int id) {
        System.out.println("Denken: " + id);
    }

    public void eat(int id)  throws InterruptedException {
        int left = (id + philosophers.length - 1) % philosophers.length;
        int right = (id + 1) % philosophers.length;

        mutex.acquire();
        if (!is_eating[left] && !is_eating[right]) {
            philosophers[id].release();
            is_eating[id] = true;
        } else {
            is_hungry[id] = true;
        }

        mutex.release();
        philosophers[id].acquire();

        System.out.println("Philosopher " + id + " is Eating");

        mutex.acquire();
        is_eating[id] = false;
        is_hungry[id] = false;

        if (is_hungry[left]) {
            int leftleft = (left + philosophers.length - 1)  % philosophers.length;
            if (!is_eating[leftleft]) {
                is_hungry[left] = false;
                is_eating[left] = true;
                philosophers[left].release();
            }
        }

        if (is_hungry[right]) {
            int rightright = (right + 1) % philosophers.length;
            if (!is_eating[rightright]) {
                is_hungry[right] = false;
                is_eating[right] = true;

                philosophers[right].release();
            }
        }
        mutex.release();
    }
}
