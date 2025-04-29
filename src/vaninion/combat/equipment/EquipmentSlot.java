package vaninion.combat.equipment;

/**
 * Represents the different equipment slots a player can have
 */
public enum EquipmentSlot {
    HEAD("Head"),
    CHEST("Chest"),
    LEGS("Legs"),
    SHIELD("Shield"),
    WEAPON("Weapon"),
    AMULET("Amulet");
    
    private final String displayName;
    
    EquipmentSlot(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Determine the appropriate slot for an item based on its name
     */
    public static EquipmentSlot getSlotForItem(String itemName) {
        itemName = itemName.toLowerCase();
        
        if (itemName.contains("helmet") || itemName.contains("mask") || itemName.contains("hat")) {
            return HEAD;
        } else if (itemName.contains("chestplate") || itemName.contains("robe")) {
            return CHEST;
        } else if (itemName.contains("leggings") || itemName.contains("pants")) {
            return LEGS;
        } else if (itemName.contains("shield")) {
            return SHIELD;
        } else if (itemName.contains("sword") || itemName.contains("staff")) {
            return WEAPON;
        } else if (itemName.contains("amulet") || (itemName.contains("necklace"))) {
            return AMULET;
        }
        
        // Default to weapon if unknown
        return WEAPON;
    }
}