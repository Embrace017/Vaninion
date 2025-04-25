package Vaninion_Main.player;

public class Viking extends Player {
    private int berserkStacks;
    private static final int MAX_BERSERK_STACKS = 5;

    public Viking(String name) {
        super(name);
        this.berserkStacks = 0;
        // Set Viking-specific stats
        setHealth(getHealth() + 10);  // Vikings are hardy
        setStrength(getStrength() + 1);  // Vikings start stronger
    }



    public void useRacialAbility() {
        if (berserkStacks < MAX_BERSERK_STACKS) {
            this.berserkStacks++;
            System.out.println(getName() + " enters a berserk state! (Stacks: " + berserkStacks + ")");
        } else {
            System.out.println(getName() + " is already at maximum berserk power!");
        }
    }

    public int getBerserkStacks() {
        return berserkStacks;
    }
}
