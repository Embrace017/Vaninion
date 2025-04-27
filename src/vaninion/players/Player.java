package vaninion.players;
import vaninion.ColoredConsole;
import vaninion.adventure.Riddle;
import vaninion.combat.equipment.EquipmentManager;
import vaninion.combat.equipment.EquipmentItem;

import java.util.*;

import static vaninion.ColoredConsole.*;
//import Vaninion_Main.foodAndPotions.Food;

public class Player {
    private final String name;
    private int money;
    private int level;
    private int experience;
    private int skillPoints;
    private int health;
    private int maxHealth;
    private int mana;
    private int maxMana;
    private int strength;
    private int maxStrength;
    private int defense;
    private int maxDefense;
    private int wisdom;
    private int charisma;

    // Equipment
    private EquipmentManager equipment;


    // Skill stats
    private int fishingLevel;
    private int fishingExp;
    private int cookingLevel;
    private int cookingExp;
    private int miningLevel;
    private int miningExp;

    // Temp stats
    private int tempStrength = 0;
    private int tempMaxHealth = 0;


    // Inventories
    public Map<String, Integer> inventory;
    public Map<String, Integer> resourceInventory;


    public Player(String name) {
        this.name = name;
        this.money = 100;
        this.level = 1;
        this.experience = 0;
        this.skillPoints = 0;
        this.health = 40;
        this.maxHealth = health;
        this.mana = 20;
        this.maxMana = mana;
        this.strength = 1;
        this.maxStrength = strength;
        this.defense = 1;
        this.maxDefense = defense;
        this.wisdom = 1;
        this.charisma = 1;
        // Stats
        this.miningLevel = 1;
        this.miningExp = 0;
        this.fishingLevel = 1;
        this.fishingExp = 0;
        this.cookingLevel = 1;
        this.cookingExp = 0;

        // Equipment
        this.equipment = new Equipment();

        // Inventories
        this.inventory = new HashMap<>();
        this.resourceInventory = new HashMap<>();

    }


    public String mapRng(Map<String, Double> itemMap) {
        double totalWeight = itemMap.values().stream().mapToDouble(d -> d).sum();
        double random = Math.random() * totalWeight;
        double cumulative = 0.0;

        for (Map.Entry<String, Double> entry : itemMap.entrySet()) {
            cumulative += entry.getValue();
            if (random < cumulative) {
                return entry.getKey();
            }
        }
        return null;
    }


    // Getters and setters
    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    // HP
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getMaxHealth() {
        return maxHealth + tempMaxHealth + equipment.getTotalHealthBoost();
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    // Mana
    public int getMana() {
        return mana;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }
    public int getMaxMana() {
        return maxMana + equipment.getTotalManaBoost();
    }
    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    // Strength
    public int getStrength() {
        return strength + equipment.getTotalStrengthBoost();
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public int getMaxStrength() {
        return maxStrength;
    }
    public void setMaxStrength(int maxStrength) {
        this.maxStrength = maxStrength;
    }

    // Defense
    public int getDefense() {
        return defense + equipment.getTotalDefenceBoost();
    }
    public void setDefense(int defence) {
        this.defense = defence;
    }
    public int getMaxDefense() {
        return maxDefense;
    }
    public void setMaxDefense(int maxDefence) {
        this.maxDefense = maxDefence;
    }

    // Wisdom
    public int getWisdom() {
        return wisdom;
    }
    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }
    public int getCharisma() {
        return charisma;
    }
    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    // Levels
    public int getLevel() {
        return level;
    } // Player level
    public void setLevel(int level) {
        this.level = level;
    } // Player level
    public int getExperience() {
        return experience;
    } // Player level experience
    public void setExperience(int experience) {
        this.experience = experience;
    }
    public int getSkillPoints() {
        return skillPoints;
    } // Skill points from level up
    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }
    // Fishing
    public int getFishingLevel() {
        return fishingLevel;
    }
    public void setFishingLevel(int fishingLevel) {
        this.fishingLevel = fishingLevel;
    }
    public int getFishingExp() {
        return fishingExp;
    }
    public void setFishingExp(int fishingExp) {
        this.fishingExp = fishingExp;
    }
    // Cooking
    public int getCookingLevel() {
        return cookingLevel;
    }
    public void setCookingLevel(int cookingLevel) {
        this.cookingLevel = cookingLevel;
    }
    public int getCookingExp() {
        return cookingExp;
    }
    public void setCookingExp(int cookingExp) {
        this.cookingExp = cookingExp;
    }
    // Mining
    public int getMiningLevel() {
        return miningLevel;
    }
    public void setMiningLevel(int miningLevel) {
        this.miningLevel = miningLevel;
    }
    public int getMiningExp() {
        return miningExp;
    }
    public void setMiningExp(int miningExp) {
        this.miningExp = miningExp;
    }

    // Temp stats
    public int getTempStrength() {
        return tempStrength;
    }
    public void setTempStrength(int tempStrength) {
        this.tempStrength = tempStrength;
    }
    public int getTempMaxHealth() {
        return tempMaxHealth;
    }
    public void setTempMaxHealth(int tempMaxHealth) {
        this.tempMaxHealth = tempMaxHealth;
    }

    // Equipment
    public Equipment getEquipment() {
        return equipment;
    }

    // You might want to modify the getMaxHealth method to include temporary boosts

    // Add other getters/setters as needed


    public void resetTemporaryEffects() {
        tempStrength = 0;
        tempMaxHealth = 0;
        // Reset any fishing level boosts here as well
        // You might want to store the base fishing level separately
    }

    public void heal(int amount) {
        setHealth(Math.min(getMaxHealth(), getHealth() + amount));
        System.out.println("Healed for " + amount + " health. Current health: " + getHealth());
    }
    public void equipItem(String itemName) {
        equipment.equipItemByName(itemName, this);
    }

    // Start inventory management methods
    public void displayRegularInventory() {
        if (inventory.isEmpty()) {
            System.out.println(RED + "Your inventory is empty!" + RESET);
            return;
        }

        System.out.println(YELLOW + "\n=== Full Inventory ===" + RESET);
        List<Map.Entry<String, Integer>> sortedItems = new ArrayList<>(inventory.entrySet());
        sortedItems.sort(Map.Entry.comparingByKey());

        for (Map.Entry<String, Integer> item : sortedItems) {
            String itemName = ColoredConsole.capitalizeWords(item.getKey());
            int count = item.getValue();
            System.out.printf(PURPLE + "%-20s" + GREEN + "x%d" + RESET + "%n", itemName, count);
        }
        System.out.println(YELLOW + "=========================\n" + RESET);
    }

    public void displayResourceInventory() {
        if (resourceInventory.isEmpty()) {
            System.out.println(RED + "Your resource inventory is empty!" + RESET);
            return;
        }

        System.out.println(YELLOW + "\n=== Resource Inventory ===" + RESET);
        List<Map.Entry<String, Integer>> sortedResources = new ArrayList<>(resourceInventory.entrySet());
        sortedResources.sort(Map.Entry.comparingByKey());

        for (Map.Entry<String, Integer> resource : sortedResources) {
            String resourceName = ColoredConsole.capitalizeWords(resource.getKey());
            int count = resource.getValue();
            System.out.printf(PURPLE + "%-20s" + GREEN + "x%d" + RESET + "%n", resourceName, count);
        }
        System.out.println(YELLOW + "=========================\n" + RESET);
    }

    public void addItem(String item, int count) {
        if (isResource(item)) {
            resourceInventory.put(item, resourceInventory.getOrDefault(item, 0) + count);
        } else {
            inventory.put(item, inventory.getOrDefault(item, 0) + count);
        }
    }
    public void displayArmourInventory() {
        if (inventory.isEmpty()) {
            System.out.println(RED + "Your inventory is empty!" + RESET);
            return;
        }

        System.out.println(YELLOW + "\n=== Armour Inventory ===" + RESET);
        List<Map.Entry<String, Integer>> sortedItems = new ArrayList<>(inventory.entrySet());
        sortedItems.sort(Map.Entry.comparingByKey());
        boolean hasArmour = false;

        for (Map.Entry<String, Integer> item : sortedItems) {
            String itemName = item.getKey().toLowerCase();
            if (isArmourItem(itemName)) {
                hasArmour = true;
                int count = item.getValue();
                System.out.printf(PURPLE + "%-20s" + GREEN + "x%d" + RESET + "%n",
                        ColoredConsole.capitalizeWords(itemName), count);
            }
        }

        if (!hasArmour) {
            System.out.println(RED + "No armour items in inventory!" + RESET);
        }
        System.out.println(YELLOW + "=========================\n" + RESET);
    }

    private boolean isArmourItem(String itemName) {
        return itemName.contains("helmet") ||
                itemName.contains("mask") ||
                itemName.contains("hat") ||

                itemName.contains("chestplate") ||
                itemName.contains("leggings") ||
                itemName.contains("shield") ||
                itemName.contains("staff") ||

                itemName.contains("sword");

    }


    public boolean removeItem(String item, int count) {
        if (isResource(item)) {
            return removeFromInventory(resourceInventory, item, count);
        } else {
            return removeFromInventory(inventory, item, count);
        }
    } // Works together

    private boolean removeFromInventory(Map<String, Integer> inventory, String item, int count) {
        int currentCount = inventory.getOrDefault(item, 0);
        if (currentCount >= count) {
            int newCount = currentCount - count;
            if (newCount == 0) {
                inventory.remove(item);
            } else {
                inventory.put(item, newCount);
            }
            return true;
        }
        return false;
    } // Works together

    public boolean hasItem(String item) {
        if (isResource(item)) {
            return resourceInventory.containsKey(item);
        }
        return inventory.containsKey(item);
    } // True or false

    public int getItemCount(String item) {
        if (isResource(item)) {
            return resourceInventory.getOrDefault(item, 0);
        }
        return inventory.getOrDefault(item, 0);
    } // Count of item in inventory

    private boolean isResource(String item) {
        return item.contains("ore") || // Mining
                item.contains("bar") ||
                item.contains("stone") ||
                item.contains("coal") ||
                item.contains("crystal") ||
                item.contains("diamond") ||
                item.contains("raw") || // Cooking
                item.contains("cooked") ||
                item.contains("wood");

    }
    // End inventory management methods


    // Expierience management methods
    public void stats() {
        System.out.println(YELLOW + "\n====== " + getName() + "'s Stats ======" + RESET);
        System.out.println(GREEN + "Level: " + getLevel() + RESET);
        System.out.println(GREEN + "Experience: " + getExperience() + "/" + (100 * getLevel()) + RESET);
        System.out.println(GREEN + "Skill Points: " + getSkillPoints() + RESET);
        System.out.println(getHealth() < getMaxHealth() / 2 ? RED : GREEN + "Health: " + getHealth() + "/" + getMaxHealth() + RESET);
        System.out.println(GREEN + "Mana: " + getMana() + RESET);
        System.out.println(GREEN + "Strength: " + getStrength() + RESET);
        System.out.println(GREEN + "Defence: " + getDefense() + RESET);
        System.out.println(GREEN + "Wisdom: " + getWisdom() + RESET);
        System.out.println(GREEN + "Charisma: " + getCharisma() + RESET);
        System.out.println(GREEN + "Mining: " + getMiningLevel() + RESET);
        //System.out.println(GREEN + "Cooking: " + getCookingLevel() + RESET);
        System.out.println(GREEN + "Fishing: " + getFishingLevel() + RESET);
    }

    public void gainExperience(int expGained) {
        setExperience(getExperience() + expGained);
        if (getExperience() >= 100 * getLevel()) {
            setSkillPoints(getSkillPoints() + 1);
            setLevel(getLevel() + 1);
            setExperience(getExperience() - ((100 * (getLevel() - 1)) / 2)); //hopefully only decreases current level exp and not total
            System.out.println(GREEN + "You gained a level!" + RESET);
            System.out.println(GREEN + "Your new level is " + getLevel() + "!" + RESET);
        }
    }

    // Level up logic
    public void useSkillPoint() {
        if (getSkillPoints() > 0) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("\nYou have " + getSkillPoints() + " skill point(s).");
            System.out.println("Which attribute would you like to upgrade?");
            System.out.println("1. Health");
            System.out.println("2. Mana");
            System.out.println("3. Strength");
            System.out.println("4. Defense");
            System.out.println("5. Wisdom");
            System.out.println("6. Charisma");

            System.out.print("Enter the number corresponding to your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    setMaxHealth(getMaxHealth() + 10);
                    System.out.println("Your Health has increased to " + getMaxHealth() + "!");
                }
                case "2" -> {
                    setMaxMana(getMaxMana() + 10);
                    System.out.println("Your Mana has increased to " + getMaxMana() + "!");
                }
                case "3" -> {
                    setMaxStrength(getMaxStrength() + 1);
                    setStrength(getMaxStrength());
                    System.out.println("Your Strength has increased to " + getMaxStrength() + "!");
                }
                case "4" -> {
                    setMaxDefense(getMaxDefense() + 1);

                    System.out.println("Your Defense has increased to " + getMaxDefense() + "!");
                }
                case "5" -> {
                    setWisdom(getWisdom() + 1);
                    System.out.println("Your Wisdom has increased to " + getWisdom() + "!");
                }
                case "6" -> {
                    setCharisma(getCharisma() + 1);
                    System.out.println("Your Charisma has increased to " + getCharisma() + "!");
                }
                default -> {
                    System.out.println("Invalid choice! No skill point was used.");
                    return; // Exit without consuming skill points
                }
            }

            // Deduct one skill point after a valid upgrade
            setSkillPoints(getSkillPoints() - 1);
            System.out.println("You now have " + getSkillPoints() + " skill point(s) remaining.");

        } else {
            System.out.println("\n" + PURPLE + getName() + " you don't have any skill points!" + RESET);
        }
        if (getSkillPoints() > 0) {
            useSkillPoint();
        }
    }

    public Map<String, Integer> getInventory() {
        return (Map<String, Integer>) (Object) inventory;
    }

    public void applyEffect(String confusion, int i) {
    }

    public void addEffect(String wisdom, int i, int i1) {
    }

}
