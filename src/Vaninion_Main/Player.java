package Vaninion_Main;
import Vaninion_Main.monsters.*;

import java.util.*;

import static Vaninion_Main.ColoredConsole.*;

public class Player {
    private String name;
    private int money;
    private int level;
    private int experience;
    private int health;
    //private int maxHealth;
    private int mana;
    private int strength;
    private int defence;
    private int wisdom;
    private int charisma;
    protected Map<String, Integer> itemAndCounts;  // Changed to protected for subclass access
    private Random random = new Random();

    public Player(String name) {
        this.name = name;
        this.money = 1000000;
        this.level = 1;
        this.experience = 0;
        this.health = 100;
        //this.maxHealth = health;
        this.mana = 50;
        this.strength = 1;
        this.defence = 1;
        this.wisdom = 1;
        this.charisma = 1;
        this.itemAndCounts = new HashMap<>();  // Initialize here in parent class
    }

    // Getters and setters
    public String getName() { return name; }
    public int getMoney() { return money; }
    public void setMoney(int money) { this.money = money; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    //public int getMaxHealth() { return maxHealth; }
    //public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }
    public int getMana() { return mana; }
    public void setMana(int mana) { this.mana = mana; }
    public int getStrength() { return strength; }
    public void setStrength(int strength) { this.strength = strength; }
    public int getDefence() { return defence; }
    public void setDefence(int defence) { this.defence = defence; }
    public int getWisdom() { return wisdom; }
    public void setWisdom(int wisdom) { this.wisdom = wisdom; }
    public int getCharisma() { return charisma; }
    public void setCharisma(int charisma) { this.charisma = charisma; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }
    //public Map<String, Integer> getItemAndCounts() { return itemAndCounts; }
    //public void setItemAndCounts(Map<String, Integer> itemAndCounts) { this.itemAndCounts = itemAndCounts; }

    // Add other getters/setters as needed

    // Start inventory management methods
    public void addItem(String item, int count) {
        itemAndCounts.put(item, itemAndCounts.getOrDefault(item, 0) + count);
    }
    public void displayInventory() {
        if (itemAndCounts.isEmpty()) {
            System.out.println(RED + "Your inventory is empty!" + RESET);
            return;
        }

        System.out.println(YELLOW + "\n=== Your Inventory ===" + RESET);

        // Convert map entries to a list and sort it
        List<Map.Entry<String, Integer>> sortedItems = new ArrayList<>(itemAndCounts.entrySet());
        sortedItems.sort(Map.Entry.comparingByKey()); // Sort alphabetically by item name

        // Display sorted items
        for (Map.Entry<String, Integer> item : sortedItems) {
            String itemName = ColoredConsole.capitalizeWords(item.getKey());
            int count = item.getValue();
            System.out.printf(PURPLE + "%-20s" + GREEN + "x%d" + RESET + "%n", itemName, count);
        }
        System.out.println(YELLOW + "======================\n" + RESET);
    }
    public boolean removeItem(String item, int count) {
        int currentCount = itemAndCounts.getOrDefault(item, 0);
        if (currentCount >= count) {
            int newCount = currentCount - count;
            if (newCount == 0) {
                itemAndCounts.remove(item);
            } else {
                itemAndCounts.put(item, newCount);
            }
            return true;
        }
        return false;
    }
    public boolean hasItem(String item) {
        return itemAndCounts.containsKey(item);
    }
    public int getItemCount(String item) {
        return itemAndCounts.getOrDefault(item, 0);
    }
    // End inventory management methods

    // Expierience management methods
    public void gainExperience(int expGained) {
        this.experience += expGained;
        if (this.experience >= 1000 * (this.level) && this.level < 100) {
            this.level++;
            this.experience -= 1000;
            System.out.println(GREEN + "You gained a level!" + RESET);
            System.out.println(GREEN + "Your new level is " + this.level + "!" + RESET);
        }
    }
    public void attack(Monster target) {
        int number = random.nextInt(this.getStrength() * 10);
        int damage = Math.max(1, number + this.getStrength() - target.getDefence());
        target.setHealth(target.getHealth() - damage);
        // Player damage message
        System.out.println(" ");
        System.out.println(BOLD + BLUE + "\n====== Combat Continued ======" + RESET);
        System.out.println(this.getName() + " attacks " + target.getName() + " for " + damage + " damage!");
    }
}

class Human extends Player {
    private String bonusSkill;
    public Human(String name) {
        super(name);
        setHealth(getHealth() + 50);
        bonusSkill = "diplomacy";
    }

    public void useRacialAbility() {
        System.out.println(getName() + " uses their " + bonusSkill + " to influence others.");
        setCharisma(getCharisma() + 1);  // Temporary charisma boost
    }
}

class Ork extends Player {
    private int rage;
    private static final int MAX_RAGE = 100;

    public Ork(String name) {
        super(name);
        this.rage = 0;
        // Set Ork-specific stats
        setHealth(getHealth() + 20);  // Orks are tougher
        setStrength(getStrength() + 2);  // Orks are stronger
    }

    @Override
    public void attack(Monster target) {
        super.attack(target);
        this.rage = Math.min(MAX_RAGE, this.rage + 10);
        System.out.println(getName() + "'s rage increases to " + rage + "!");
    }

    public void useRacialAbility() {
        System.out.println(getName() + " unleashes a mighty roar!");
        if (rage >= 50) {
            System.out.println("RAGE BONUS ACTIVATED!");
            setStrength(getStrength() + 2);
            rage = 0;
        }
    }
}

class Viking extends Player {
    private int berserkStacks;
    private static final int MAX_BERSERK_STACKS = 5;

    public Viking(String name) {
        super(name);
        this.berserkStacks = 0;
        // Set Viking-specific stats
        setHealth(getHealth() + 10);  // Vikings are hardy
        setStrength(getStrength() + 1);  // Vikings start stronger
    }

    @Override
    public void attack(Monster target) {
        super.attack(target);
        if (berserkStacks > 0) {
            int bonusDamage = berserkStacks * 2;
            target.setHealth(target.getHealth() - bonusDamage);
            System.out.println("Berserk bonus damage: " + bonusDamage + "!");
        }
    }

    public void useRacialAbility() {
        if (berserkStacks < MAX_BERSERK_STACKS) {
            this.berserkStacks++;
            System.out.println(getName() + " enters a berserk state! (Stacks: " + berserkStacks + ")");
        } else {
            System.out.println(getName() + " is already at maximum berserk power!");
        }
    }
}