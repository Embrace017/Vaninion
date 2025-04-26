package vaninion.monsters;

public class Werewolf extends Monster {
    public Werewolf() {
        super("Werewolf", 200, 100, 10, 20);

        addPossibleDrop( "gold coin", 30, 100);
        addPossibleDrop( "werewolf fur", 100, 50);
        addPossibleDrop( "werewolf fangs", 50, 100);
    }
}
