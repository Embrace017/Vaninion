package Vaninion_Main.monsters;

public class Skeleton extends Monster {
    public Skeleton() {
        super("Skeleton", 100, 20, 5, 5);

        addPossibleDrop("gold coin", 100.0);  // 100% drop rate
        addPossibleDrop("bone", 75.0);  // 75% drop rate
        addPossibleDrop("skull", 25.0);

    }
}
