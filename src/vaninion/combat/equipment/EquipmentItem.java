package vaninion.combat.equipment;

import java.util.HashMap;
import java.util.Map;
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

    // Static map to store all equipment items
    private static final Map<String, EquipmentItem> ALL_ITEMS = new HashMap<>();

    // Static initializer to populate the equipment items
    static {
        // Iron equipment
        registerItem(new EquipmentItem("Iron Helmet", EquipmentSlot.HEAD, 1, 0, 0, 0, 1, "Common"));
        registerItem(new EquipmentItem("Iron Chestplate", EquipmentSlot.CHEST, 1, 0, 0, 0, 1, "Common"));
        registerItem(new EquipmentItem("Iron Leggings", EquipmentSlot.LEGS, 1, 0, 0, 0, 1, "Common"));
        registerItem(new EquipmentItem("Iron Shield", EquipmentSlot.SHIELD, 1, 0, 0, 0, 1, "Common"));
        registerItem(new EquipmentItem("Iron Sword", EquipmentSlot.WEAPON, 0, 1, 0, 0, 1, "Common"));

        // Steel equipment
        registerItem(new EquipmentItem("Steel Helmet", EquipmentSlot.HEAD, 3, 2, 5, 5, 5, "Uncommon"));
        registerItem(new EquipmentItem("Steel Chestplate", EquipmentSlot.CHEST, 3, 2, 5, 5, 5, "Uncommon"));
        registerItem(new EquipmentItem("Steel Leggings", EquipmentSlot.LEGS, 3, 2, 5, 5, 5, "Uncommon"));
        registerItem(new EquipmentItem("Steel Shield", EquipmentSlot.SHIELD, 3, 2, 5, 5, 5, "Uncommon"));
        registerItem(new EquipmentItem("Steel Sword", EquipmentSlot.WEAPON, 0, 3, 0, 10, 5, "Uncommon"));

        // Gorganite equipment
        registerItem(new EquipmentItem("Gorganite Helmet", EquipmentSlot.HEAD, 10, 10, 10, 10, 10, "Rare"));
        registerItem(new EquipmentItem("Gorganite Chestplate", EquipmentSlot.CHEST, 10, 10, 10, 10, 10, "Rare"));
        registerItem(new EquipmentItem("Gorganite Leggings", EquipmentSlot.LEGS, 10, 10, 10, 10, 10, "Rare"));
        registerItem(new EquipmentItem("Gorganite Shield", EquipmentSlot.SHIELD, 10, 10, 10, 10, 10, "Rare"));
        registerItem(new EquipmentItem("Gorganite Sword", EquipmentSlot.WEAPON, 0, 20, 0, 60, 10, "Rare"));

        // Vaninite equipment
        registerItem(new EquipmentItem("Vaninite Helmet", EquipmentSlot.HEAD, 100, 100, 100, 100, 20, "Epic"));
        registerItem(new EquipmentItem("Vaninite Chestplate", EquipmentSlot.CHEST, 100, 100, 100, 100, 20, "Epic"));
        registerItem(new EquipmentItem("Vaninite Leggings", EquipmentSlot.LEGS, 100, 100, 100, 100, 20, "Epic"));
        registerItem(new EquipmentItem("Vaninite Shield", EquipmentSlot.SHIELD, 100, 100, 100, 100, 20, "Epic"));
        registerItem(new EquipmentItem("Vaninite Sword", EquipmentSlot.WEAPON, 0, 100, 0, 1000, 20, "Epic"));

        // Dragon equipment
        registerItem(new EquipmentItem("Dragon Helmet", EquipmentSlot.HEAD, 30, 20, 50, 50, 15, "Legendary"));
        registerItem(new EquipmentItem("Dragon Chestplate", EquipmentSlot.CHEST, 40, 20, 50, 50, 15, "Legendary"));
        registerItem(new EquipmentItem("Dragon Leggings", EquipmentSlot.LEGS, 30, 20, 50, 50, 15, "Legendary"));
        registerItem(new EquipmentItem("Dragon Shield", EquipmentSlot.SHIELD, 30, 20, 50, 50, 15, "Legendary"));
        registerItem(new EquipmentItem("Dragon Sword", EquipmentSlot.WEAPON, 5, 50, 20, 100, 15, "Legendary"));

        // Mage equipment
        registerItem(new EquipmentItem("Mage Hat", EquipmentSlot.HEAD, 5, 5, 10, 100, 10, "Rare"));
        registerItem(new EquipmentItem("Mage Robe", EquipmentSlot.CHEST, 5, 5, 10, 150, 10, "Rare"));
        registerItem(new EquipmentItem("Mage Pants", EquipmentSlot.LEGS, 5, 5, 10, 100, 10, "Rare"));
        registerItem(new EquipmentItem("Mage Amulet", EquipmentSlot.AMULET, 0, 10, 0, 200, 10, "Rare"));
        registerItem(new EquipmentItem("Fire Staff", EquipmentSlot.WEAPON, 0, 15, 0, 200, 10, "Rare"));
        registerItem(new EquipmentItem("Ice Staff", EquipmentSlot.WEAPON, 0, 15, 0, 200, 10, "Rare"));
        registerItem(new EquipmentItem("Lightning Staff", EquipmentSlot.WEAPON, 0, 15, 0, 200, 10, "Rare"));

        // Misc
        registerItem(new EquipmentItem("Starter Helmet", EquipmentSlot.HEAD, 0, 0, 10, 0, 1, "Common"));
        registerItem(new EquipmentItem("Werewolf Mask", EquipmentSlot.HEAD, 5, 2, 20, 20, 5, "Uncommon"));
    }

    /**
     * Register an equipment item in the static map
     */
    private static void registerItem(EquipmentItem item) {
        ALL_ITEMS.put(item.getName().toLowerCase(), item);
    }

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

    /**
     * Get an equipment item by name
     */
    public static EquipmentItem getByName(String name) {
        return ALL_ITEMS.get(name.toLowerCase());
    }

    /**
     * Get all available equipment items
     */
    public static Map<String, EquipmentItem> getAllItems() {
        return ALL_ITEMS;
    }
}