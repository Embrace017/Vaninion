package vaninion.combat.equipment;

import static vaninion.ColoredConsole.*;

/**
 * Represents an item that can be equipped by the player
 */
public class EquipmentItem {
    private final String name;
    private final EquipmentSlot slot;
    private final int defenceBoost;
    private final int strengthBoost;
    private final int maxHealthBoost;
    private final int maxManaBoost;
    private final int level;
    private final String rarity;
    
    /**
     * Create a new equipment item
     */
    public EquipmentItem(String name, EquipmentSlot slot, int defenceBoost, int strengthBoost, 
                         int maxHealthBoost, int maxManaBoost, int level, String rarity) {
        this.name = name;
        this.slot = slot;
        this.defenceBoost = defenceBoost;
        this.strengthBoost = strengthBoost;
        this.maxHealthBoost = maxHealthBoost;
        this.maxManaBoost = maxManaBoost;
        this.level = level;
        this.rarity = rarity;
    }
    
    /**
     * Get the name of the item
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the slot this item is equipped in
     */
    public EquipmentSlot getSlot() {
        return slot;
    }
    
    /**
     * Get the defence boost provided by this item
     */
    public int getDefenceBoost() {
        return defenceBoost;
    }
    
    /**
     * Get the strength boost provided by this item
     */
    public int getStrengthBoost() {
        return strengthBoost;
    }
    
    /**
     * Get the max health boost provided by this item
     */
    public int getMaxHealthBoost() {
        return maxHealthBoost;
    }
    
    /**
     * Get the max mana boost provided by this item
     */
    public int getMaxManaBoost() {
        return maxManaBoost;
    }
    
    /**
     * Get the level requirement for this item
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * Get the rarity of this item
     */
    public String getRarity() {
        return rarity;
    }
    
    /**
     * Get a colored display of the item name based on rarity
     */
    public String getColoredName() {
        return switch(rarity.toLowerCase()) {
            case "common" -> WHITE + name + RESET;
            case "uncommon" -> GREEN + name + RESET;
            case "rare" -> BLUE + name + RESET;
            case "epic" -> PURPLE + name + RESET;
            case "legendary" -> YELLOW + name + RESET;
            default -> name;
        };
    }
    
    /**
     * Get a string representation of this item's stats
     */
    public String getStatsString() {
        StringBuilder stats = new StringBuilder();
        
        if (defenceBoost > 0) {
            stats.append(BLUE).append("+").append(defenceBoost).append(" Defence").append(RESET).append("\n");
        }
        if (strengthBoost > 0) {
            stats.append(RED).append("+").append(strengthBoost).append(" Strength").append(RESET).append("\n");
        }
        if (maxHealthBoost > 0) {
            stats.append(GREEN).append("+").append(maxHealthBoost).append(" Health").append(RESET).append("\n");
        }
        if (maxManaBoost > 0) {
            stats.append(PURPLE).append("+").append(maxManaBoost).append(" Mana").append(RESET).append("\n");
        }
        
        return stats.toString();
    }
    
    @Override
    public String toString() {
        return getColoredName() + " (" + slot.getDisplayName() + ")\n" + getStatsString();
    }
    
    // Static factory methods for creating equipment items
    
    // Iron equipment
    public static EquipmentItem createIronHelmet() {
        return new EquipmentItem("Iron Helmet", EquipmentSlot.HEAD, 1, 0, 0, 0, 1, "Common");
    }
    
    public static EquipmentItem createIronChestplate() {
        return new EquipmentItem("Iron Chestplate", EquipmentSlot.CHEST, 1, 0, 0, 0, 1, "Common");
    }
    
    public static EquipmentItem createIronLeggings() {
        return new EquipmentItem("Iron Leggings", EquipmentSlot.LEGS, 1, 0, 0, 0, 1, "Common");
    }
    
    public static EquipmentItem createIronShield() {
        return new EquipmentItem("Iron Shield", EquipmentSlot.SHIELD, 1, 0, 0, 0, 1, "Common");
    }
    
    public static EquipmentItem createIronSword() {
        return new EquipmentItem("Iron Sword", EquipmentSlot.WEAPON, 0, 1, 0, 0, 1, "Common");
    }
    
    // Steel equipment
    public static EquipmentItem createSteelHelmet() {
        return new EquipmentItem("Steel Helmet", EquipmentSlot.HEAD, 3, 2, 5, 5, 5, "Uncommon");
    }
    
    public static EquipmentItem createSteelChestplate() {
        return new EquipmentItem("Steel Chestplate", EquipmentSlot.CHEST, 3, 2, 5, 5, 5, "Uncommon");
    }
    
    public static EquipmentItem createSteelLeggings() {
        return new EquipmentItem("Steel Leggings", EquipmentSlot.LEGS, 3, 2, 5, 5, 5, "Uncommon");
    }
    
    public static EquipmentItem createSteelShield() {
        return new EquipmentItem("Steel Shield", EquipmentSlot.SHIELD, 3, 2, 5, 5, 5, "Uncommon");
    }
    
    public static EquipmentItem createSteelSword() {
        return new EquipmentItem("Steel Sword", EquipmentSlot.WEAPON, 0, 3, 0, 10, 5, "Uncommon");
    }
    
    // Gorganite equipment
    public static EquipmentItem createGorganiteHelmet() {
        return new EquipmentItem("Gorganite Helmet", EquipmentSlot.HEAD, 10, 10, 10, 10, 10, "Rare");
    }
    
    public static EquipmentItem createGorganiteChestplate() {
        return new EquipmentItem("Gorganite Chestplate", EquipmentSlot.CHEST, 10, 10, 10, 10, 10, "Rare");
    }
    
    public static EquipmentItem createGorganiteLeggings() {
        return new EquipmentItem("Gorganite Leggings", EquipmentSlot.LEGS, 10, 10, 10, 10, 10, "Rare");
    }
    
    public static EquipmentItem createGorganiteShield() {
        return new EquipmentItem("Gorganite Shield", EquipmentSlot.SHIELD, 10, 10, 10, 10, 10, "Rare");
    }
    
    public static EquipmentItem createGorganiteSword() {
        return new EquipmentItem("Gorganite Sword", EquipmentSlot.WEAPON, 0, 20, 0, 60, 10, "Rare");
    }
    
    // Vaninite equipment
    public static EquipmentItem createVaniniteHelmet() {
        return new EquipmentItem("Vaninite Helmet", EquipmentSlot.HEAD, 100, 100, 100, 100, 20, "Epic");
    }
    
    public static EquipmentItem createVaniniteChestplate() {
        return new EquipmentItem("Vaninite Chestplate", EquipmentSlot.CHEST, 100, 100, 100, 100, 20, "Epic");
    }
    
    public static EquipmentItem createVaniniteLeggings() {
        return new EquipmentItem("Vaninite Leggings", EquipmentSlot.LEGS, 100, 100, 100, 100, 20, "Epic");
    }
    
    public static EquipmentItem createVaniniteShield() {
        return new EquipmentItem("Vaninite Shield", EquipmentSlot.SHIELD, 100, 100, 100, 100, 20, "Epic");
    }
    
    public static EquipmentItem createVaniniteSword() {
        return new EquipmentItem("Vaninite Sword", EquipmentSlot.WEAPON, 0, 100, 0, 1000, 20, "Epic");
    }
    
    // Dragon equipment
    public static EquipmentItem createDragonHelmet() {
        return new EquipmentItem("Dragon Helmet", EquipmentSlot.HEAD, 30, 20, 50, 50, 15, "Legendary");
    }
    
    public static EquipmentItem createDragonChestplate() {
        return new EquipmentItem("Dragon Chestplate", EquipmentSlot.CHEST, 40, 20, 50, 50, 15, "Legendary");
    }
    
    public static EquipmentItem createDragonLeggings() {
        return new EquipmentItem("Dragon Leggings", EquipmentSlot.LEGS, 30, 20, 50, 50, 15, "Legendary");
    }
    
    public static EquipmentItem createDragonShield() {
        return new EquipmentItem("Dragon Shield", EquipmentSlot.SHIELD, 30, 20, 50, 50, 15, "Legendary");
    }
    
    public static EquipmentItem createDragonSword() {
        return new EquipmentItem("Dragon Sword", EquipmentSlot.WEAPON, 5, 50, 20, 100, 15, "Legendary");
    }
    
    // Mage equipment
    public static EquipmentItem createMageHat() {
        return new EquipmentItem("Mage Hat", EquipmentSlot.HEAD, 5, 5, 10, 100, 10, "Rare");
    }
    
    public static EquipmentItem createMageRobe() {
        return new EquipmentItem("Mage Robe", EquipmentSlot.CHEST, 5, 5, 10, 150, 10, "Rare");
    }
    
    public static EquipmentItem createMagePants() {
        return new EquipmentItem("Mage Pants", EquipmentSlot.LEGS, 5, 5, 10, 100, 10, "Rare");
    }
    
    public static EquipmentItem createMageAmulet() {
        return new EquipmentItem("Mage Amulet", EquipmentSlot.AMULET, 0, 10, 0, 200, 10, "Rare");
    }
    
    public static EquipmentItem createFireStaff() {
        return new EquipmentItem("Fire Staff", EquipmentSlot.WEAPON, 0, 15, 0, 200, 10, "Rare");
    }
    
    public static EquipmentItem createIceStaff() {
        return new EquipmentItem("Ice Staff", EquipmentSlot.WEAPON, 0, 15, 0, 200, 10, "Rare");
    }
    
    public static EquipmentItem createLightningStaff() {
        return new EquipmentItem("Lightning Staff", EquipmentSlot.WEAPON, 0, 15, 0, 200, 10, "Rare");
    }
    
    // Misc
    public static EquipmentItem createStarterHelmet() {
        return new EquipmentItem("Starter Helmet", EquipmentSlot.HEAD, 0, 0, 10, 0, 1, "Common");
    }
    
    public static EquipmentItem createWerewolfMask() {
        return new EquipmentItem("Werewolf Mask", EquipmentSlot.HEAD, 5, 2, 20, 20, 5, "Uncommon");
    }
    
    /**
     * Get an equipment item by name
     */
    public static EquipmentItem getByName(String name) {
        name = name.toLowerCase();
        
        return switch(name) {
            // Iron equipment
            case "iron helmet" -> createIronHelmet();
            case "iron chestplate" -> createIronChestplate();
            case "iron leggings" -> createIronLeggings();
            case "iron shield" -> createIronShield();
            case "iron sword" -> createIronSword();
            
            // Steel equipment
            case "steel helmet" -> createSteelHelmet();
            case "steel chestplate" -> createSteelChestplate();
            case "steel leggings" -> createSteelLeggings();
            case "steel shield" -> createSteelShield();
            case "steel sword" -> createSteelSword();
            
            // Gorganite equipment
            case "gorganite helmet" -> createGorganiteHelmet();
            case "gorganite chestplate" -> createGorganiteChestplate();
            case "gorganite leggings" -> createGorganiteLeggings();
            case "gorganite shield" -> createGorganiteShield();
            case "gorganite sword" -> createGorganiteSword();
            
            // Vaninite equipment
            case "vaninite helmet" -> createVaniniteHelmet();
            case "vaninite chestplate" -> createVaniniteChestplate();
            case "vaninite leggings" -> createVaniniteLeggings();
            case "vaninite shield" -> createVaniniteShield();
            case "vaninite sword" -> createVaniniteSword();
            
            // Dragon equipment
            case "dragon helmet" -> createDragonHelmet();
            case "dragon chestplate" -> createDragonChestplate();
            case "dragon leggings" -> createDragonLeggings();
            case "dragon shield" -> createDragonShield();
            case "dragon sword" -> createDragonSword();
            
            // Mage equipment
            case "mage hat" -> createMageHat();
            case "mage robe" -> createMageRobe();
            case "mage pants" -> createMagePants();
            case "mage amulet" -> createMageAmulet();
            case "fire staff" -> createFireStaff();
            case "ice staff" -> createIceStaff();
            case "lightning staff" -> createLightningStaff();
            
            // Misc
            case "starter helmet" -> createStarterHelmet();
            case "werewolf mask" -> createWerewolfMask();
            
            default -> null;
        };
    }
}