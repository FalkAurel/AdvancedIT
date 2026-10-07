package Testtat_Aufgabe1.ErzeugerVerbraucher;

public class NormaleLokomotive extends Lokomotive {
    public NormaleLokomotive(Weiche weiche, int id) {
        super(weiche, id);
    }

    @Override
    public void run() {
        try {
            while(true) {
                weiche.enter(id);
                weiche.exit(id);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
