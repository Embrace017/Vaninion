package vaninion.combat;
import vaninion.players.Player;
import static vaninion.ColoredConsole.*;

public class Equipment {
    private ArmourStats helmet;
    private ArmourStats chestplate;
    private ArmourStats leggings;
    private ArmourStats shield;
    private ArmourStats weapon;

    // Getters and setters
    public ArmourStats getHelmet() { return helmet; }
    public void equipHelmet(ArmourStats helmet) { this.helmet = helmet; }

    public ArmourStats getChestplate() { return chestplate; }
    public void equipChestplate(ArmourStats chestplate) { this.chestplate = chestplate; }

    public ArmourStats getLeggings() { return leggings; }
    public void equipLeggings(ArmourStats leggings) { this.leggings = leggings; }

    public ArmourStats getShield() { return shield; }
    public void equipShield(ArmourStats shield) { this.shield = shield; }

    public ArmourStats getWeapon() { return weapon; }
    public void equipWeapon(ArmourStats weapon) { this.weapon = weapon; }

    // Calculate total defense boost from all equipped items
    public int getTotalDefenceBoost() {
        int total = 0;
        if (helmet != null) total += helmet.getDefenceBoost();
        if (chestplate != null) total += chestplate.getDefenceBoost();
        if (leggings != null) total += leggings.getDefenceBoost();
        if (shield != null) total += shield.getDefenceBoost();
        if (weapon != null) total += weapon.getDefenceBoost();
        return total;
    }

    // Calculate total strength boost from all equipped items
    public int getTotalStrengthBoost() {
        int total = 0;
        if (helmet != null) total += helmet.getStrengthBoost();
        if (chestplate != null) total += chestplate.getStrengthBoost();
        if (leggings != null) total += leggings.getStrengthBoost();
        if (shield != null) total += shield.getStrengthBoost();
        if (weapon != null) total += weapon.getStrengthBoost();
        return total;
    }

    // Calculate total health boost from all equipped items
    public int getTotalHealthBoost() {
        int total = 0;
        if (helmet != null) total += helmet.getMaxHealthBoost();
        if (chestplate != null) total += chestplate.getMaxHealthBoost();
        if (leggings != null) total += leggings.getMaxHealthBoost();
        if (shield != null) total += shield.getMaxHealthBoost();
        if (weapon != null) total += weapon.getMaxHealthBoost();
        return total;
    }

    // Calculate total mana boost from all equipped items
    public int getTotalManaBoost() {
        int total = 0;
        if (helmet != null) total += helmet.getMaxManaBoost();
        if (chestplate != null) total += chestplate.getMaxManaBoost();
        if (leggings != null) total += leggings.getMaxManaBoost();
        if (shield != null) total += shield.getMaxManaBoost();
        if (weapon != null) total += weapon.getMaxManaBoost();
        return total;
    }

    // Method to display current equipment and their stats
    public String getEquipmentStatus() {
        StringBuilder status = new StringBuilder(YELLOW + "\n=== Current Equipment ===" + RESET + "\n");

        // Equipment slots with their status
        status.append(PURPLE + "Helmet: ").append(helmet != null ? GREEN + "Equipped" : RED + "Empty").append(RESET + "\n");
        status.append(PURPLE + "Chestplate: ").append(chestplate != null ? GREEN + "Equipped" : RED + "Empty").append(RESET + "\n");
        status.append(PURPLE + "Leggings: ").append(leggings != null ? GREEN + "Equipped" : RED + "Empty").append(RESET + "\n");
        status.append(PURPLE + "Shield: ").append(shield != null ? GREEN + "Equipped" : RED + "Empty").append(RESET + "\n");
        status.append(PURPLE + "Weapon: ").append(weapon != null ? GREEN + "Equipped" : RED + "Empty").append(RESET + "\n");

        // Bonus section
        status.append(YELLOW + "\nTotal Bonuses:" + RESET + "\n");
        status.append(BLUE + "Defense: " + GREEN + "+" + getTotalDefenceBoost() + RESET + "\n");
        status.append(BLUE + "Strength: " + GREEN + "+" + getTotalStrengthBoost() + RESET + "\n");
        status.append(BLUE + "Health: " + GREEN + "+" + getTotalHealthBoost() + RESET + "\n");
        status.append(BLUE + "Mana: " + GREEN + "+" + getTotalManaBoost() + RESET + "\n");

        return status.toString();
    }
    public void equipItemByName(String itemName, Player player) {
        // Convert to lowercase for consistent comparison
        itemName = itemName.toLowerCase();

        // Check if player has the item
        if (!player.hasItem(itemName)) {
            System.out.println(RED + "You don't have this item!" + RESET);
            return;
        }

        // Find the matching Armour object
        ArmourStats armourPiece = null;

        // Check all static Armour fields in Armour class
        switch (itemName) {

            // Iron gear
            case "iron sword" -> armourPiece = ArmourStats.IRON_SWORD;
            case "iron helmet" -> armourPiece = ArmourStats.IRON_HELMET;
            case "iron chestplate" -> armourPiece = ArmourStats.IRON_CHESTPLATE;
            case "iron leggings" -> armourPiece = ArmourStats.IRON_LEGGINGS;
            case "iron shield" -> armourPiece = ArmourStats.IRON_SHIELD;

            // Steel gear
            case "steel sword" -> armourPiece = ArmourStats.STEEL_SWORD;
            case "steel helmet" -> armourPiece = ArmourStats.STEEL_HELMET;
            case "steel chestplate" -> armourPiece = ArmourStats.STEEL_CHESTPLATE;
            case "steel leggings" -> armourPiece = ArmourStats.STEEL_LEGGINGS;
            case "steel shield" -> armourPiece = ArmourStats.STEEL_SHIELD;

            // Gorganite gear
            case "gorganite sword" -> armourPiece = ArmourStats.GORGANITE_SWORD;
            case "gorganite helmet" -> armourPiece = ArmourStats.GORGANITE_HELMET;
            case "gorganite chestplate" -> armourPiece = ArmourStats.GORGANITE_CHESTPLATE;
            case "gorganite leggings" -> armourPiece = ArmourStats.GORGANITE_LEGGINGS;
            case "gorganite shield" -> armourPiece = ArmourStats.GORGANITE_SHIELD;

            // Vaninite gear
            case "vaninite sword" -> armourPiece = ArmourStats.VANINITE_SWORD;
            case "vaninite helmet" -> armourPiece = ArmourStats.VANINITE_HELMET;
            case "vaninite chestplate" -> armourPiece = ArmourStats.VANINITE_CHESTPLATE;
            case "vaninite leggings" -> armourPiece = ArmourStats.VANINITE_LEGGINGS;
            case "vaninite shield" -> armourPiece = ArmourStats.VANINITE_SHIELD;

            // MISC
            case "starter helmet" -> armourPiece = ArmourStats.STARTER_HELMET;
            case "werewolf mask" -> armourPiece = ArmourStats.WEREWOLF_MASK;


            default -> {
                System.out.println(RED + "This item cannot be equipped!" + RESET);
                return;
            }
        }
        // Store the currently equipped item to swap
        ArmourStats currentItem = null;

        // Equip the armor based on its type and get the current item
        if (itemName.contains("helmet") || itemName.contains("mask") || itemName.contains("hat")) {
            currentItem = getHelmet();
            equipHelmet(armourPiece);
        } else if (itemName.contains("chestplate")) {
            currentItem = getChestplate();
            equipChestplate(armourPiece);
        } else if (itemName.contains("leggings")) {
            currentItem = getLeggings();
            equipLeggings(armourPiece);
        } else if (itemName.contains("shield")) {
            currentItem = getShield();
            equipShield(armourPiece);
        } else if (itemName.contains("sword") || itemName.contains("staff")) {
            currentItem = getWeapon();
            equipWeapon(armourPiece);
        }
        player.removeItem(itemName, 1); // Equipped (remove from inv temporarily)

        // If there was an item equipped, add it back to inventory
        if (currentItem != null) {
            player.addItem(currentItem.getName(), 1);
            System.out.println(YELLOW + currentItem.getName() + " was unequipped and returned to inventory." + RESET);
        }

        System.out.println(GREEN + "Successfully equipped " + PURPLE + itemName + RESET);
    }

    // Method to unequip all items
    public void unequipAll() {
        helmet = null;
        chestplate = null;
        leggings = null;
        shield = null;
        weapon = null;
    }

}