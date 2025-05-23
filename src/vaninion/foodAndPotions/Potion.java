package vaninion.foodAndPotions;

import java.util.Scanner;
import vaninion.players.Player;
import static vaninion.ColoredConsole.*;


public class Potion {
    private final Scanner scanner = new Scanner(System.in);

    public boolean defensePotion;
    public boolean strengthPotion;
    public boolean healthPotion;
    public boolean manaPotion;
    public int getHealthPotionCount(Player player) {
        return player.getItemCount("health potion");
    }
    public int getManaPotionCount(Player player) {
        return player.getItemCount("mana potion");
    }
    public int getStrengthPotionCount(Player player) {
        return player.getItemCount("strength potion");
    }
    public int getDefensePotionCount(Player player) {
        return player.getItemCount("defence potion");
    }

    public void usePotion(Player player) {

        boolean playerTurnEnded = false;

        System.out.println(YELLOW + "\nPotion effects reset after battle" + RESET);
        System.out.println(YELLOW + "\nWhat would you like to drink?" + RESET);
        System.out.println("1. " + PURPLE + "Health Potion: " + RESET + getHealthPotionCount(player));
        System.out.println("2. " + PURPLE + "Mana Potion: " + RESET + getManaPotionCount(player) );
        System.out.println("3. " + PURPLE + "Strength Potion: " + RESET + getStrengthPotionCount(player));
        System.out.println("4. " + PURPLE + "Defence Potion: " + RESET + getDefensePotionCount(player));

        System.out.println("* " + RED + "Back" + RESET);
        String choice = scanner.nextLine().toLowerCase().trim();
        //  boolean defense;
        //  boolean strength;
        switch (choice) {
            case "1", "health potion", "health" -> {
                choice = "health potion";
                if (player.hasItem(choice)) {
                    player.setHealth(player.getHealth() + 50);
                    drinkPotion(player, choice);
                    healthPotion = true;
                }
            }
            case "2", "mana potion", "mana" -> {
                choice = "mana potion";
                if (player.hasItem(choice)) {
                    player.setMana(player.getMana() + 50);
                    drinkPotion(player, choice);
                    manaPotion = true;
                }
            }
            case "3", "strength potion", "strength" -> {
                choice = "strength potion";
                if (player.hasItem(choice) && !strengthPotion) {
                    drinkPotion(player, choice);
                    player.setStrength(Math.max(player.getStrength() + 1, player.getStrength() + player.getStrength() / 2));
                    strengthPotion = true;
                    System.out.println("Strength: " + player.getStrength());
                } else {
                    System.out.println(RED + "You already drank a strength potion, or don't have one!!" + RESET);
                }

            }
            case "4", "defense potion", "defence" -> {
                choice = "defence potion";
                if (player.hasItem(choice) && !defensePotion) {
                    player.setDefense(Math.max(player.getDefense() + 1, player.getDefense() + player.getDefense() / 2));
                    drinkPotion(player, choice);
                    defensePotion = true;
                }
                System.out.println("Defence: " + player.getDefense());
            }
            case "*", "back", "exit", "leave" -> System.out.println("Not thirsty?");
            default -> System.out.println(RED + "Invalid choice!" + RESET);
        }
    }

    public void drinkPotion(Player player, String potion) {
        if (player.hasItem(potion)) {
            System.out.println(" ");
            System.out.println(BLUE + BOLD + "\n====== Combat Continued ======" + RESET);
            System.out.println(GREEN + "You used a " + potion + RESET);
            player.removeItem(potion, 1);
        } else {
            System.out.println(BLUE + BOLD + "\n====== Please Choose Again ======" + RESET);
            System.out.println(RED + "No " + potion + "'s available." + RESET);
        }
    }
    public void potionDrainer(Player player) {
        if (strengthPotion) { //&& player.getStrength() > player.getMaxStrength()) {
            player.setStrength(player.getStrength() - 2);
            if (player.getStrength() < player.getMaxStrength()) {
                player.setStrength(player.getMaxStrength());
                strengthPotion = false;
            }
        }
        if (defensePotion) { //&& player.getDefense() > player.getMaxDefense()) {
            player.setDefense(player.getDefense() - 2);
            if (player.getDefense() < player.getMaxDefense()) {
                player.setDefense(player.getMaxDefense());
                defensePotion = false;
            }
        }
    }
    public void potionStatReset(Player player) {
        if (strengthPotion) {
            player.setStrength(player.getMaxStrength());
            if (player.getStrength() < player.getMaxStrength()) {
                player.setStrength(player.getMaxStrength());
                strengthPotion = false;
            }
        }
        if (defensePotion) {
            player.setDefense(player.getMaxDefense());
            if (player.getDefense() < player.getMaxDefense()) {
                player.setDefense(player.getMaxDefense());
                defensePotion = false;
            }
        }
    }
}
