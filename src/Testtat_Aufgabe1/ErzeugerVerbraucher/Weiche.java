package Testtat_Aufgabe1.ErzeugerVerbraucher;
import java.util.concurrent.Semaphore;

public class Weiche {
    private final Semaphore mutex = new Semaphore(1, true);
    private final Semaphore[] lokomotiven = new Semaphore[2];


    public Weiche() {
        for (int i = 0; i < lokomotiven.length; i++) {
            lokomotiven[i] = new Semaphore(i, true); // Lokomotive 0 hat die Reihenfolgebedingung 0 und Lokomotive 1 die Reihenfolgebedingung 1
        }
    }

    public void enter(int lokomotive) throws InterruptedException {
        assert lokomotive >= 0 && lokomotive < lokomotiven.length;
        int index = (lokomotive + 1) % lokomotiven.length;

        lokomotiven[index].acquire();
        mutex.acquire();

        System.out.println("Lokomotive: " + lokomotive + " befährt die Mittelstrecke.");
    }

    public void exit(int lokomotive) throws InterruptedException {
        assert lokomotive >= 0 && lokomotive < lokomotiven.length;
        System.out.println("Lokomotive: " + lokomotive + " verlässt die Mittelstrecke.");

        mutex.release();
        lokomotiven[lokomotive].release();
    }
}
