package vaninion.dojo;
// Unlock dojo quick commands from main menu?
import vaninion.dojo.crafting.Crafting;
import vaninion.dojo.crafting.Recipes;
import vaninion.foodAndPotions.Food;
import vaninion.players.Player;


import java.util.Map;
import java.util.Scanner;
import java.util.Random;

import static vaninion.ColoredConsole.*;

public class Dojo extends Crafting {
    private static final Food food = new Food();
    private final Recipes recipes = new Recipes();
    private final Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    private boolean furnaceLit = false;

    // Update to the existing Dojo constructor to add new cooking recipes
    public Dojo() {
    }
    public void enterDojo (Player player) {
        while (true) {
            System.out.println(YELLOW + "\n=== Welcome to the Dojo ===" + RESET);
            System.out.println("1. " + PURPLE + "Rest" + RESET);
            System.out.println("2. " + PURPLE + "Eat Food" + RESET);
            System.out.println("3. " + PURPLE + "Smelt Ores" + RESET);
            System.out.println("4. " + PURPLE + "Cook Fish" + RESET);
            System.out.println("5. " + PURPLE + "Craft Items" + RESET); // Changed option name
            System.out.println("* " + RED + "Leave Dojo" + RESET); // Renumbered leave option
            System.out.println(CYAN + "\n'inv', 'res', 'armour' for inventories" + RESET);

            String choice = scanner.nextLine().toLowerCase().trim();

            switch (choice) {
                case "1", "rest" -> rest(player);
                case "2", "eat" -> eatFood(player);
                case "3", "smelt" -> smelt(player);
                case "4", "cook" -> cook(player);
                case "5", "craft" -> craft(player);
                case "armour" -> player.displayArmourInventory();
                case "res" -> player.displayResourceInventory();
                case "inv" -> player.displayRegularInventory();
                case "*", "leave", "exit", "back" -> {
                    System.out.println(YELLOW + "Leaving the Dojo..." + RESET);
                    return;
                }
                default -> System.out.println(RED + "Invalid choice!" + RESET);
            }
        }
    }

    private void rest(Player player) {
        int res = 0;
        System.out.println(BLUE + "\n===== Resting =====" + RESET);
        if (player.getHealth() < player.getMaxHealth() / 2) {
            res = Math.max(10 + player.getWisdom(), random.nextInt(player.getMaxHealth() / 2)); // Restore up to half of your max health
            player.setHealth(player.getHealth() + res);



        } else {
            System.out.println(RED + "Health too high too rest. Get back to work!" + RESET);
            return;
        }
        player.setMana(random.nextInt(player.getMaxMana() / 2));
        System.out.println(GREEN + "You feel much better... along with a weird magical sensation." + RESET);
        System.out.println(GREEN + "HP restored: " + res + ". total: " + player.getHealth() + "." + RESET);
        System.out.println(GREEN + "Mana: " + player.getMana() + RESET);
    }

    private void eatFood(Player player) {
        System.out.println(BLUE + "\n=== Eat Food ===" + RESET);

        // Display available food
        food.displayAvailableFood(player);


        System.out.println(YELLOW + "\nWhat would you like to eat? (type the food name or 'back')" + RESET);
        String choice = scanner.nextLine().trim();

        if (choice.equalsIgnoreCase("back")) return;

        food.consumeFood(player, choice);
    }

    private void showEquipmentMenu(Player player) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(YELLOW + "\n=== Equipment Menu ===" + RESET);
            System.out.println("Currently Equipped:");
            System.out.println("1. Helmet: " + (player.getEquipment().getHelmet() != null ? PURPLE + "Equipped" + RESET : RED + "Empty" + RESET));
            System.out.println("2. Chestplate: " + (player.getEquipment().getChestplate() != null ? PURPLE + "Equipped" + RESET : RED + "Empty" + RESET));
            System.out.println("3. Leggings: " + (player.getEquipment().getLeggings() != null ? PURPLE + "Equipped" + RESET : RED + "Empty" + RESET));
            System.out.println("4. Shield: " + (player.getEquipment().getShield() != null ? PURPLE + "Equipped" + RESET : RED + "Empty" + RESET));
            System.out.println("5. Weapon: " + (player.getEquipment().getWeapon() != null ? PURPLE + "Equipped" + RESET : RED + "Empty" + RESET));
            System.out.println("6. Back");

            System.out.print(CYAN + "Choose slot to manage (1-5): " + RESET);
            String choice = scanner.nextLine().trim();

            if (choice.equals("6")) break;

            if (choice.matches("[1-5]")) {
                showEquipmentSlotOptions(player, Integer.parseInt(choice));
            }
        }
    }

    private void showEquipmentSlotOptions(Player player, int slot) {
        // Show available items for that slot from player's inventory
        // Allow equipping/unequipping items
        // Update player stats when equipment changes
    }

}
