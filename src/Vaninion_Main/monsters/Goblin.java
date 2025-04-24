package Vaninion_Main.monsters;

public class Goblin extends Monster {
    public Goblin() {
        super("Goblin", 10, 10, 1, 1);

        addPossibleDrop("gold coin", 10.0);
        addPossibleDrop("goblin ear", 100.0);
        addPossibleDrop("rusty dagger", 50.0);


    }
}
