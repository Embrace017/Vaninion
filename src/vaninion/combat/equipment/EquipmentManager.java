package vaninion.combat.equipment;

import vaninion.players.Player;
import static vaninion.ColoredConsole.*;

import java.util.EnumMap;
import java.util.Map;

/**
 * Manages a player's equipped items
 */
public class EquipmentManager {
    private final Map<EquipmentSlot, EquipmentItem> equippedItems;
    
    /**
     * Create a new equipment manager with no items equipped
     */
    public EquipmentManager() {
        this.equippedItems = new EnumMap<>(EquipmentSlot.class);
    }
    
    /**
     * Equip an item in the appropriate slot
     * @param item The item to equip
     * @return The item that was previously equipped in that slot, or null if no item was equipped
     */
    public EquipmentItem equipItem(EquipmentItem item) {
        return equippedItems.put(item.getSlot(), item);
    }
    
    /**
     * Get the item equipped in the specified slot
     * @param slot The slot to check
     * @return The item equipped in that slot, or null if no item is equipped
     */
    public EquipmentItem getEquippedItem(EquipmentSlot slot) {
        return equippedItems.get(slot);
    }
    
    /**
     * Unequip the item in the specified slot
     * @param slot The slot to unequip
     * @return The item that was unequipped, or null if no item was equipped
     */
    public EquipmentItem unequipItem(EquipmentSlot slot) {
        return equippedItems.remove(slot);
    }
    
    /**
     * Unequip all items
     */
    public void unequipAll() {
        equippedItems.clear();
    }
    
    /**
     * Calculate the total defense boost from all equipped items
     */
    public int getTotalDefenceBoost() {
        return equippedItems.values().stream()
                .mapToInt(EquipmentItem::getDefenceBoost)
                .sum();
    }
    
    /**
     * Calculate the total strength boost from all equipped items
     */
    public int getTotalStrengthBoost() {
        return equippedItems.values().stream()
                .mapToInt(EquipmentItem::getStrengthBoost)
                .sum();
    }
    
    /**
     * Calculate the total health boost from all equipped items
     */
    public int getTotalHealthBoost() {
        if (equippedItems != null) {


            return equippedItems.values().stream()
                    .mapToInt(EquipmentItem::getMaxHealthBoost)
                    .sum();
        }
        return 0;
    }
    
    /**
     * Calculate the total mana boost from all equipped items
     */
    public int getTotalManaBoost() {
        if (equippedItems != null) {
            return equippedItems.values().stream()
                    .mapToInt(EquipmentItem::getMaxManaBoost)
                    .sum();
        }
        return 0;
    }
    
    /**
     * Equip an item by name
     * @param itemName The name of the item to equip
     * @param player The player who is equipping the item
     * @return True if the item was equipped successfully, false otherwise
     */
    public boolean equipItemByName(String itemName, Player player) {
        // Convert to lowercase for consistent comparison
        itemName = itemName.toLowerCase();
        
        // Check if player has the item
        if (!player.hasItem(itemName)) {
            System.out.println(RED + "You don't have this item!" + RESET);
            return false;
        }
        
        // Get the equipment item
        EquipmentItem item = EquipmentItem.getByName(itemName);
        if (item == null) {
            System.out.println(RED + "This item cannot be equipped!" + RESET);
            return false;
        }
        
        // Get the slot for this item
        EquipmentSlot slot = item.getSlot();
        
        // Store the currently equipped item to swap
        EquipmentItem currentItem = getEquippedItem(slot);
        
        // Equip the new item
        equipItem(item);
        
        // Remove the item from inventory
        player.removeItem(itemName, 1);
        
        // If there was an item equipped, add it back to inventory
        if (currentItem != null) {
            player.addItem(currentItem.getName().toLowerCase(), 1);
            System.out.println(YELLOW + currentItem.getName() + " was unequipped and returned to inventory." + RESET);
        }
        
        System.out.println(GREEN + "Successfully equipped " + PURPLE + itemName + RESET);
        return true;
    }
    
    /**
     * Get a string representation of the player's equipment status
     */
    public String getEquipmentStatus() {
        StringBuilder status = new StringBuilder(YELLOW + "\n=== Current Equipment ===" + RESET + "\n");
        
        // Equipment slots with their status
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            EquipmentItem item = equippedItems.get(slot);
            status.append(PURPLE + slot.getDisplayName() + ": ");
            
            if (item != null) {
                status.append(GREEN + item.getName());
            } else {
                status.append(RED + "Empty");
            }
            
            status.append(RESET + "\n");
        }
        
        // Bonus section
        status.append(YELLOW + "\nTotal Bonuses:" + RESET + "\n");
        status.append(BLUE + "Defense: " + GREEN + "+" + getTotalDefenceBoost() + RESET + "\n");
        status.append(BLUE + "Strength: " + GREEN + "+" + getTotalStrengthBoost() + RESET + "\n");
        status.append(BLUE + "Health: " + GREEN + "+" + getTotalHealthBoost() + RESET + "\n");
        status.append(BLUE + "Mana: " + GREEN + "+" + getTotalManaBoost() + RESET + "\n");
        
        return status.toString();
    }
}