package vaninion.combat;

public class ArmourStats {
    private String name;
    private int defenceBoost;
    private int strengthBoost;
    private int maxHealthBoost;
    private int maxManaBoost;
    private int level;
    private int experience;

    public ArmourStats(String name, int defenceBoost, int strengthBoost, int maxHealthBoost, int maxManaBoost, int level, int experience) {
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
    public static final ArmourStats IRON_SWORD = new ArmourStats("iron sword", 0, 1, 0, 0, 1, 0);
    public static final ArmourStats STEEL_SWORD = new ArmourStats("steel sword", 0, 3, 0, 10, 1, 0);
    public static final ArmourStats GORGANITE_SWORD = new ArmourStats("gorganite sword", 0, 20, 0, 60, 1, 0);
    public static final ArmourStats VANINITE_SWORD = new ArmourStats("vaninite sword", 0, 100, 0, 1000, 1, 0);

    // New weapons
    public static final ArmourStats DRAGON_SWORD = new ArmourStats("dragon sword", 5, 50, 20, 100, 1, 0);
    public static final ArmourStats FIRE_STAFF = new ArmourStats("fire staff", 0, 15, 0, 200, 1, 0);
    public static final ArmourStats ICE_STAFF = new ArmourStats("ice staff", 0, 15, 0, 200, 1, 0);
    public static final ArmourStats LIGHTNING_STAFF = new ArmourStats("lightning staff", 0, 15, 0, 200, 1, 0);

    // Iron armour
    public static final ArmourStats IRON_HELMET = new ArmourStats("iron helmet", 1, 0, 0, 0, 1, 0);
    public static final ArmourStats IRON_CHESTPLATE = new ArmourStats("iron chestplate", 1, 0, 0, 0, 1, 0);
    public static final ArmourStats IRON_LEGGINGS = new ArmourStats("iron leggings", 1, 0, 0, 0, 1, 0);
    public static final ArmourStats IRON_SHIELD = new ArmourStats("iron shield", 1, 0, 0, 0, 1, 0);

    // Steel armour
    public static final ArmourStats STEEL_HELMET = new ArmourStats("steel helmet", 3, 2, 5, 5, 1, 0);
    public static final ArmourStats STEEL_CHESTPLATE = new ArmourStats("steel chestplate", 3, 2, 5, 5, 1, 0);
    public static final ArmourStats STEEL_LEGGINGS = new ArmourStats("steel leggings", 3, 2, 5, 5, 1, 0);
    public static final ArmourStats STEEL_SHIELD = new ArmourStats("steel shield", 3, 2, 5, 5, 1, 0);
    // Gorganite armour
    public static final ArmourStats GORGANITE_HELMET = new ArmourStats("gorganite helmet", 10, 10, 10, 10, 1, 0);
    public static final ArmourStats GORGANITE_CHESTPLATE = new ArmourStats("gorganite chestplate", 10, 10, 10, 10, 1, 0);
    public static final ArmourStats GORGANITE_LEGGINGS = new ArmourStats("gorganite leggins", 10, 10, 10, 10, 1, 0);
    public static final ArmourStats GORGANITE_SHIELD = new ArmourStats("gorganite shield", 10, 10, 10, 10, 1, 0);
    // Vaninite armour
    public static final ArmourStats VANINITE_HELMET = new ArmourStats("vaninite helmet", 100, 100, 100, 100, 1, 0);
    public static final ArmourStats VANINITE_CHESTPLATE = new ArmourStats("vaninite chestplate", 100, 100, 100, 100, 1, 0);
    public static final ArmourStats VANINITE_LEGGINGS = new ArmourStats("vaninite leggins", 100, 100, 100, 100, 1, 0);
    public static final ArmourStats VANINITE_SHIELD = new ArmourStats("vaninite sheild", 100, 100, 100, 100, 1, 0);


    // Dragon armour
    public static final ArmourStats DRAGON_HELMET = new ArmourStats("dragon helmet", 30, 20, 50, 50, 1, 0);
    public static final ArmourStats DRAGON_CHESTPLATE = new ArmourStats("dragon chestplate", 40, 20, 50, 50, 1, 0);
    public static final ArmourStats DRAGON_LEGGINGS = new ArmourStats("dragon leggings", 30, 20, 50, 50, 1, 0);
    public static final ArmourStats DRAGON_SHIELD = new ArmourStats("dragon shield", 30, 20, 50, 50, 1, 0);

    // Mage armour
    public static final ArmourStats MAGE_HAT = new ArmourStats("mage hat", 5, 5, 10, 100, 1, 0);
    public static final ArmourStats MAGE_ROBE = new ArmourStats("mage robe", 5, 5, 10, 150, 1, 0);
    public static final ArmourStats MAGE_PANTS = new ArmourStats("mage pants", 5, 5, 10, 100, 1, 0);
    public static final ArmourStats MAGE_AMULET = new ArmourStats("mage amulet", 0, 10, 0, 200, 1, 0);

    // Misc
    public static final ArmourStats STARTER_HELMET = new ArmourStats("starter helmet", 0, 0, 10, 0, 1, 0);
    public static final ArmourStats WEREWOLF_MASK = new ArmourStats("werewolf mask", 5, 2, 20, 20, 1, 0);

}
