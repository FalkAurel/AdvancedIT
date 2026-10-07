package Testtat_Aufgabe1.ErzeugerVerbraucher;

public abstract class Lokomotive extends Thread {
    protected Weiche weiche;
    protected int id;

    public Lokomotive(Weiche weiche, int id) {
        this.weiche = weiche;
        this.id = id;
    }
}
