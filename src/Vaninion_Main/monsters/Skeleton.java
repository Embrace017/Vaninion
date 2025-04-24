package Vaninion_Main.monsters;

public class Skeleton extends Monster {
    public Skeleton() {
        super("Skeleton", 100, 20, 5, 5);

        addPossibleDrop("gold coin", 20.0);
        addPossibleDrop("bone", 100.0);
        addPossibleDrop("skull", 50.0);
        // Add drops to shop list
    }
}
