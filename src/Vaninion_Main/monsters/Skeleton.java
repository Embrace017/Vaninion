package Vaninion_Main.monsters;

public class Skeleton extends Monster {
    public Skeleton() {
        super("Skeleton", 100, 50, 5, 10);

        addPossibleDrop("gold coin", 20.0, 100);
        addPossibleDrop("bone", 100.0, 10);
        addPossibleDrop("skull", 50.0, 20);
        // Add drops to shop list
    }
}
