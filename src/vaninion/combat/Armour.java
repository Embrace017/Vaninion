package vaninion.combat;

public class Armour {
    private String name;
    private int defenceBoost;
    private int strengthBoost;
    private int maxHealthBoost;
    private int maxManaBoost;
    private int level;
    private int experience;

    public Armour(String name, int defenceBoost, int strengthBoost, int maxHealthBoost, int maxManaBoost, int level, int experience) {
        this.name = name;
        this.defenceBoost = defenceBoost;
        this.strengthBoost = strengthBoost;
        this.maxHealthBoost = maxHealthBoost;
        this.maxManaBoost = maxManaBoost;
        this.level = level;
        this.experience = experience;
    }

    // Getters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getDefenceBoost() {
        return defenceBoost;
    }
    public void setDefenceBoost(int defenceBoost) {
        this.defenceBoost = defenceBoost;
    }

    public int getStrengthBoost() {
        return strengthBoost;
    }
    public void setStrengthBoost(int strengthBoost) {
        this.strengthBoost = strengthBoost;
    }

    public int getMaxHealthBoost() {
        return maxHealthBoost;
    }
    public void setMaxHealthBoost(int maxHealthBoost) {
        this.maxHealthBoost = maxHealthBoost;
    }

    public int getMaxManaBoost() {
        return maxManaBoost;
    }
    public void setMaxManaBoost(int maxManaBoost) {
        this.maxManaBoost = maxManaBoost;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }


    // Weapons
    public static final Armour IRON_SWORD = new Armour("iron sword", 0, 1, 0, 0, 1, 0);
    public static final Armour STEEL_SWORD = new Armour("steel sword", 0, 3, 0, 10, 1, 0);
    public static final Armour GORGANITE_SWORD = new Armour("gorganite sword", 0, 20, 0, 60, 1, 0);
    public static final Armour VANINITE_SWORD = new Armour("vaninite sword", 0, 100, 0, 1000, 1, 0);

    // Iron armor
    public static final Armour IRON_HELMET = new Armour("iron helmet", 1, 0, 0, 0, 1, 0);
    public static final Armour IRON_CHESTPLATE = new Armour("iron chestplate", 1, 0, 0, 0, 1, 0);
    public static final Armour IRON_LEGGINGS = new Armour("iron leggings", 1, 0, 0, 0, 1, 0);
    public static final Armour IRON_SHIELD = new Armour("iron shield", 1, 0, 0, 0, 1, 0);

    // Steel armor
    public static final Armour STEEL_HELMET = new Armour("steel helmet", 3, 2, 5, 5, 1, 0);
    public static final Armour STEEL_CHESTPLATE = new Armour("steel chestplate", 3, 2, 5, 5, 1, 0);
    public static final Armour STEEL_LEGGINGS = new Armour("steel leggings", 3, 2, 5, 5, 1, 0);
    public static final Armour STEEL_SHIELD = new Armour("steel shield", 3, 2, 5, 5, 1, 0);
    // Gorganite
    public static final Armour GORGANITE_HELMET = new Armour("gorganite helmet", 10, 10, 10, 10, 1, 0);
    public static final Armour GORGANITE_CHESTPLATE = new Armour("gorganite chestplate", 10, 10, 10, 10, 1, 0);
    public static final Armour GORGANITE_LEGGINGS = new Armour("gorganite leggins", 10, 10, 10, 10, 1, 0);
    public static final Armour GORGANITE_SHIELD = new Armour("gorganite shield", 10, 10, 10, 10, 1, 0);
    // Vaninite
    public static final Armour VANINITE_HELMET = new Armour("vaninite helmet", 100, 100, 100, 100, 1, 0);
    public static final Armour VANINITE_CHESTPLATE = new Armour("vaninite chestplate", 100, 100, 100, 100, 1, 0);
    public static final Armour VANINITE_LEGGINGS = new Armour("vaninite leggins", 100, 100, 100, 100, 1, 0);
    public static final Armour VANINITE_SHIELD = new Armour("vaninite sheild", 100, 100, 100, 100, 1, 0);


    // Misc
    public static final Armour WOLF_MASK = new Armour("wolf mask", 10, 0, 10, 10, 1, 0);

}
