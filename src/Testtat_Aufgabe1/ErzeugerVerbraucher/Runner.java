package Testtat_Aufgabe1.ErzeugerVerbraucher;

public class Runner {
    public static void main(String[] args) {
        run(Lokmotive0IstLangsamer::new);
        //run(NormaleLokomotive::new);
    }

    static void run(ConstructLokomotive constructor) {
        Weiche weiche = new Weiche();
        Lokomotive[] locomotive = new Lokomotive[2];

        for (int i = 0; i < locomotive.length; i++) {
            locomotive[i] = constructor.construct(weiche, i);
        }

        for (Thread thread : locomotive) {
            thread.start();
        }

        for (Thread thread : locomotive) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

@FunctionalInterface
interface ConstructLokomotive {
    Lokomotive construct(Weiche weiche, int id);
}