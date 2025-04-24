package Vaninion_Main.monsters;

public class Goblin extends Monster {
    public Goblin() {
        super("Goblin", 10, 10, 1, 1);

        addPossibleDrop("gold coin", 10.0, 100);
        addPossibleDrop("goblin ear", 100.0, 5);
        addPossibleDrop("rusty dagger", 50.0, 10);
        addPossibleDrop("goblin ear", 100.0, 5);
        addPossibleDrop("goblin ear", 100.0, 5);
        addPossibleDrop("goblin ear", 100.0, 5);
        addPossibleDrop("goblin ear", 100.0, 5);
        


    }
}
