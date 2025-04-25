package Vaninion_Main.combat;

public class Armour {
    private int defenceBoost;
    private int strengthBoost;
    private int maxHealthBoost;
    private int maxManaBoost;
    private int level;
    private int experience;

    public Armour(int defenceBoost, int strengthBoost, int maxHealthBoost, int maxManaBoost, int level, int experience) {
        this.defenceBoost = defenceBoost;
        this.strengthBoost = strengthBoost;
        this.maxHealthBoost = maxHealthBoost;
        this.maxManaBoost = maxManaBoost;
        this.level = level;
        this.experience = experience;
    }

    // Weapons
    Armour ironSword = new Armour(0, 1, 0, 0, 1, 0);
    Armour steelSword = new Armour(0, 10, 0, 20, 1, 0);

    // Armour
    Armour ironHelmet = new Armour(1, 0, 0, 0, 1, 0);
}
