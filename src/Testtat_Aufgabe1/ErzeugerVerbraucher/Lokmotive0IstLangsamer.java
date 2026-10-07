package Testtat_Aufgabe1.ErzeugerVerbraucher;

public class Lokmotive0IstLangsamer extends Lokomotive {
    public Lokmotive0IstLangsamer(Weiche weiche, int id) {
        super(weiche, id);
    }

    @Override
    public void run() {
        try {
            while(true) {
                if (id == 0) {
                    Thread.sleep(5000);
                }
                weiche.enter(id);
                weiche.exit(id);

                // Erwarteter Output Start --> 0 --> 1 --> Ende
                break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
