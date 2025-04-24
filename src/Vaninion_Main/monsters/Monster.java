package Vaninion_Main.monsters;
import Vaninion_Main.Player;
import Vaninion_Main.Shop;

import java.util.*;
import static Vaninion_Main.ColoredConsole.*;
import static Vaninion_Main.Shop.shopItems;

public abstract class Monster {
    private String name;
    private int exp;
    private int health;
    private int maxHealth;
    private int defence;
    private int strength;
    private Map<String, Double> possibleDrops;
    private Random random;
    Shop shop = new Shop();

    protected Monster(String name, int exp, int health, int defence, int strength) {
        this.name = name;
        this.exp = exp;
        this.health = health;
        this.maxHealth = health;
        this.defence = defence;
        this.strength = strength;
        this.possibleDrops = new HashMap<>();
        this.random = new Random();
    }


    // Add drops system
    protected void addPossibleDrop(String item, double dropRate, int price) {
        possibleDrops.put(item, dropRate);
        if (!shopItems.containsKey(item)) // Stop price overrides
            shopItems.put(item, price);
    }

    public Map<String, Integer> generateDrops() {
        Map<String, Integer> drops = new HashMap<>();
        for (Map.Entry<String, Double> entry : possibleDrops.entrySet()) {
            String item = entry.getKey();
            double dropRate = entry.getValue();
            if (random.nextDouble() * 100 < dropRate) {
                drops.put(item, 1); // For now, each item drops 1 quantity
            }
        }
        return drops;
    }
    public void reset() {
        this.health = this.maxHealth;
    }
    public void attack(Player player) {
        int damage = Math.max(1, this.strength - player.getDefense());
        player.setHealth(player.getHealth() - damage);
        System.out.println(" ");
        System.out.println(this.name + " attacks " + player.getName() + "for " + damage + " damage!");
        //System.out.println(BOLD + BLUE + "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    // Getters and Setters
    public int getExp() {
        return exp;
    }
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}