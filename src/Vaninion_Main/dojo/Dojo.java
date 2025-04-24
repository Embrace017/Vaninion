package Vaninion_Main.dojo;

import Vaninion_Main.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static Vaninion_Main.ColoredConsole.*;

public class Dojo {
    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, String> smeltingRecipes = new HashMap<>();
    private final Map<String, String> cookingRecipes = new HashMap<>();


    public Dojo() {
        // Initialize smelting recipes with input -> output mappings
        smeltingRecipes.put("copper ore", "copper bar");
        smeltingRecipes.put("iron ore", "iron bar");
        smeltingRecipes.put("gold ore", "gold bar");
        smeltingRecipes.put("gorganite ore", "gorganite bar");
        smeltingRecipes.put("vaninite ore", "vaninite bar");

        // Initialize cooking recipes with input -> output mappings
        cookingRecipes.put("raw common fish", "cooked common fish");
        cookingRecipes.put("raw uncommon fish", "cooked uncommon fish");
        cookingRecipes.put("raw rare fish", "cooked rare fish");
        cookingRecipes.put("raw legendary fish", "cooked legendary fish");
    }
        public void enterDojo (Player player) {
            while (true) {
                System.out.println(YELLOW + "\n=== Welcome to the Dojo ===" + RESET);
                System.out.println("1. " + PURPLE + "Rest" + GREEN + " (Restore half HP and Mana)" + RESET);
                System.out.println("2. " + PURPLE + "Smelt Ores" + RESET);
                System.out.println("3. " + PURPLE + "Cook Fish" + RESET);
                System.out.println("4. " + RED + "Leave Dojo" + RESET);

                String choice = scanner.nextLine().toLowerCase().trim();

                switch (choice) {
                    case "1", "rest" -> rest(player);
                    case "2", "smelt" -> smelt(player);
                    case "3", "cook" -> cook(player);
                    case "4", "leave", "exit", "back" -> {
                        System.out.println(YELLOW + "Leaving the Dojo..." + RESET);
                        return;
                    }
                    default -> System.out.println(RED + "Invalid choice!" + RESET);
                }
            }
        }

        private void rest (Player player){
            System.out.println(BLUE + "\n===== Resting =====" + RESET);
            player.setHealth(Math.max(player.getHealth(), player.getMaxHealth() / 2));
            player.setMana(Math.max(player.getMana(), player.getMaxMana() / 2));
            System.out.println(GREEN + "You feel refreshed!" + RESET);
            System.out.println(GREEN + "HP restored to: " + player.getHealth() + RESET);
            System.out.println(GREEN + "Mana restored to: " + player.getMana() + RESET);
        }

        private void smelt (Player player){
            System.out.println(BLUE + "\n=== Smeltable Ores ===" + RESET);

            // Show available ores from recipes that player has
            boolean hasOres = false;
            for (String ore : smeltingRecipes.keySet()) {
                int count = player.getItemCount(ore);  // This will now check resource inventory
                if (count > 0) {
                    hasOres = true;
                    System.out.println(PURPLE + ore + RESET + " x" + count);
                }
            }

            if (!hasOres) {
                System.out.println(RED + "You don't have any ores to smelt!" + RESET);
                return;
            }

            System.out.println(YELLOW + "\nWhat would you like to smelt? (type the ore name or 'back')" + RESET);
            String choice = scanner.nextLine().toLowerCase().trim();

            if (choice.equals("back")) return;

            if (player.hasItem(choice) && smeltingRecipes.containsKey(choice)) {
                System.out.println("How many would you like to smelt?");
                try {
                    int amount = Integer.parseInt(scanner.nextLine().trim());
                    if (amount > 0 && player.getItemCount(choice) >= amount) {
                        player.removeItem(choice, amount);
                        String barName = smeltingRecipes.get(choice);
                        player.addItem(barName, amount);
                        System.out.println(GREEN + "Successfully smelted " + amount + " " + choice + " into " + barName + "!" + RESET);
                    } else {
                        System.out.println(RED + "Invalid amount or not enough ore!" + RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(RED + "Please enter a valid number!" + RESET);
                }
            } else {
                System.out.println(RED + "Invalid ore type or you don't have any!" + RESET);
            }
        }

        private void cook (Player player){
            System.out.println(BLUE + "\n=== Cookable Fish ===" + RESET);

            // Show available raw fish from recipes that player has
            boolean hasRawFish = false;
            for (String fish : cookingRecipes.keySet()) {
                int count = player.getItemCount(fish);
                if (count > 0) {
                    hasRawFish = true;
                    System.out.println(PURPLE + fish + RESET + " x" + count);
                }
            }

            if (!hasRawFish) {
                System.out.println(RED + "You don't have any fish to cook!" + RESET);
                return;
            }

            System.out.println(YELLOW + "\nWhat would you like to cook? (type the fish name or 'back')" + RESET);
            String choice = scanner.nextLine().toLowerCase().trim();

            if (choice.equals("back") || choice.equals("exit")) return;

            if (player.hasItem(choice) && cookingRecipes.containsKey(choice)) {
                System.out.println("How many would you like to cook?");
                try {
                    int amount = Integer.parseInt(scanner.nextLine().trim());
                    if (amount > 0 && player.getItemCount(choice) >= amount) {
                        player.removeItem(choice, amount);
                        String cookedName = cookingRecipes.get(choice);
                        player.addItem(cookedName, amount);
                        System.out.println(GREEN + "Successfully cooked " + amount + " " + choice + " into " + cookedName + "!" + RESET);
                    } else {
                        System.out.println(RED + "Invalid amount or not enough fish!" + RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(RED + "Please enter a valid number!" + RESET);
                }
            } else {
                System.out.println(RED + "Invalid fish type or you don't have any!" + RESET);
            }
        }
    }