package Vaninion_Main.player;

public class Ork extends Player {
    private int rage;
    private static final int MAX_RAGE = 100;

    public Ork(String name) {
        super(name);
        this.rage = 0;
        // Set Ork-specific stats
        setHealth(getHealth() + 20);  // Orks are tougher
        setStrength(getStrength() + 2);  // Orks are stronger
    }



    public void useRacialAbility() {
        System.out.println(getName() + " unleashes a mighty roar!");
        if (rage >= 50) {
            System.out.println("RAGE BONUS ACTIVATED!");
            setStrength(getStrength() + 2);
            rage = 0;
        }
    }

    public void increaseRage(int i) {
        rage += i;
        if (rage > MAX_RAGE) {
            rage = MAX_RAGE;
        }
        System.out.println(getName() + " now has " + rage + " rage!");
    }
}
