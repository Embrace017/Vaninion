package Vaninion_Main.monsters;

public class Goblin extends Monster {
    public Goblin() {
        super("Goblin", 500, 10, 1, 1);

        addPossibleDrop("gold coin", 100.0);  // 100% drop rate
        addPossibleDrop("goblin ear", 75.0);  // 75% drop rate
        addPossibleDrop("rusty dagger", 25.0);


    }
}
