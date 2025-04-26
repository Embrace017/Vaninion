package vaninion.monsters;

public class Tarantula extends Monster {
    public Tarantula() {
        super("Giant tarantula", 400, 200, 20, 40);

        addPossibleDrop("spider web", 100.0, 50);
        addPossibleDrop("spider legs", 50.0, 100);
        addPossibleDrop("venom", 10.0, 150);
    }
}
