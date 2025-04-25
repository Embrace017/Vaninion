package Vaninion_Main.dojo;

import Vaninion_Main.foodAndPotions.Food;
import Vaninion_Main.player.Player;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

import static Vaninion_Main.ColoredConsole.*;

public class Dojo {
    private static final Food food = new Food();
    private final Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    private final Map<String, Map<String, Integer>> smeltingRecipes = new HashMap<>();
    private final Map<String, String> cookingRecipes = new HashMap<>();
    private final Map<String, Map<String, Integer>> smithingRecipes = new HashMap<>(); // Changed to Map<String, Integer> for ingredients
    private boolean furnaceLit = false;

    // Update to the existing Dojo constructor to add new cooking recipes
    public Dojo() {
        // Initialize smelting recipes with input -> output mappings
        smeltingRecipes.put("iron bar", Map.of("iron ore", 3, "coal", 3));

        smeltingRecipes.put("copper bar", Map.of("copper ore", 3, "coal", 5));

        smeltingRecipes.put("gold bar", Map.of("gold ore", 3, "coal", 10));

        smeltingRecipes.put("gorganite bar", Map.of("gorganite ore", 3,
                "coal", 50,
                "gold bar", 10,
                "copper bar", 10));

        smeltingRecipes.put("vaninite bar", Map.of("vaninite ore", 3,
                "gorganite bar", 20,
                "gold bar", 20,
                "coal", 100));

        // Initialize smithing recipes with input (ingredients -> quantity) and output
        smithingRecipes.put("copper wire", Map.of("copper bar", 1));
        smithingRecipes.put("iron shield", Map.of("iron bar", 3));
        smithingRecipes.put("steel sword", Map.of("iron bar", 3, "coal", 6));

        // Initialize cooking recipes for fish
        cookingRecipes.put("raw lake minnow", "cooked lake minnow");
        cookingRecipes.put("raw river perch", "cooked river perch");
        cookingRecipes.put("raw steel snapper", "cooked steel snapper");
        cookingRecipes.put("raw crystal bass", "cooked crystal bass");
        cookingRecipes.put("raw shadow pike", "cooked shadow pike");
        cookingRecipes.put("raw magma ray", "cooked magma ray");
        cookingRecipes.put("raw frost barracuda", "cooked Frost Barracuda");
        cookingRecipes.put("raw thunder eel", "cooked thunder eel");
        // Note: We're not adding Leviathan and Kraken as requested
    }
    public void enterDojo (Player player) {
        while (true) {
            System.out.println(YELLOW + "\n=== Welcome to the Dojo ===" + RESET);
            System.out.println("1. " + PURPLE + "Rest" + RESET);
            System.out.println("2. " + PURPLE + "Eat Food" + RESET);
            System.out.println("3. " + PURPLE + "Smelt Ores" + RESET);
            System.out.println("4. " + PURPLE + "Cook Fish" + RESET);
            System.out.println("5. " + PURPLE + "Smith Items" + RESET); // Changed option name
            System.out.println("* " + RED + "Leave Dojo" + RESET); // Renumbered leave option
            System.out.println(CYAN + "\n'reg' & 'res' for inventories" + RESET);

            String choice = scanner.nextLine().toLowerCase().trim();

            switch (choice) {
                case "1", "rest" -> rest(player);
                case "2", "eat" -> eatFood(player);
                case "3", "smelt" -> smelt(player);
                case "4", "cook" -> cook(player);
                case "5", "smith" -> smith(player);
                case "res" -> player.displayResourceInventory();
                case "reg" -> player.displayRegularInventory();
                case "*", "leave", "exit", "back" -> {
                    System.out.println(YELLOW + "Leaving the Dojo..." + RESET);
                    return;
                }
                default -> System.out.println(RED + "Invalid choice!" + RESET);
            }
        }
    }

    private void rest (Player player){
        int res = 0;
        System.out.println(BLUE + "\n===== Resting =====" + RESET);
        if (player.getHealth() < player.getMaxHealth() / 2) {
            res = random.nextInt(Math.max(10, player.getHealth() * 2)); // Restore up to half of your max health
            player.setHealth(player.getHealth() + res);



        } else {
            System.out.println(RED + "Health too high too rest. Get back to work!" + RESET);
            return;
        }
        player.setMana(random.nextInt(player.getMana()));
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


    private void smelt(Player player) {
        System.out.println(BLUE + "\n====== Smelting Ores ======" + RESET);

        if (smeltingRecipes.isEmpty()) {
            System.out.println(RED + "There are no smelting recipes available." + RESET);
            return;
        }

        // Show craftable items with available materials
        boolean canSmeltAnything = false;
        for (Map.Entry<String, Map<String, Integer>> recipeEntry : smeltingRecipes.entrySet()) {
            String itemName = recipeEntry.getKey();
            Map<String, Integer> ingredients = recipeEntry.getValue();

            // Calculate maximum craftable amount
            int maxCraftable = Integer.MAX_VALUE;
            for (Map.Entry<String, Integer> ingredient : ingredients.entrySet()) {
                int available = player.getItemCount(ingredient.getKey());
                int possibleCrafts = available / ingredient.getValue();
                maxCraftable = Math.min(maxCraftable, possibleCrafts);
            }

            if (maxCraftable > 0) {
                canSmeltAnything = true;
                System.out.println(PURPLE + itemName + RESET + GREEN + " (Can smelt: " + maxCraftable + ")" + RESET);
                System.out.print(YELLOW + "  Requires: " + RESET);
                ingredients.forEach((ing, amount) ->
                        System.out.print(amount + " " + ing + " (" + player.getItemCount(ing) + " available), "));
                System.out.println();
            }
        }

        if (!canSmeltAnything) {
            System.out.println(RED + "You don't have enough materials to smelt anything!" + RESET);
            return;
        }

        System.out.println(YELLOW + "\nWhat would you like to smelt? (type the bar name or 'back')" + RESET);
        String choice = scanner.nextLine().toLowerCase().trim();

        if (choice.equals("back")) return;

        if (smeltingRecipes.containsKey(choice)) {
            Map<String, Integer> ingredients = smeltingRecipes.get(choice);

            System.out.println("How many would you like to smelt?");
            try {
                int amountToSmelt = Integer.parseInt(scanner.nextLine().trim());
                if (amountToSmelt <= 0) {
                    System.out.println(RED + "Please enter a valid positive number." + RESET);
                    return;
                }

                boolean canSmelt = true;
                // Check if player has enough ingredients
                for (Map.Entry<String, Integer> ingredientEntry : ingredients.entrySet()) {
                    String ingredientName = ingredientEntry.getKey();
                    int requiredAmount = ingredientEntry.getValue() * amountToSmelt;
                    if (player.getItemCount(ingredientName) < requiredAmount) {
                        System.out.println(RED + "Not enough " + ingredientName + ". You need " + requiredAmount + "." + RESET);
                        canSmelt = false;
                        break;
                    }
                }

                if (canSmelt) {
                    // Remove ingredients
                    for (Map.Entry<String, Integer> ingredientEntry : ingredients.entrySet()) {
                        String ingredientName = ingredientEntry.getKey();
                        int requiredAmount = ingredientEntry.getValue() * amountToSmelt;
                        player.removeItem(ingredientName, requiredAmount);
                    }
                    // Add the result
                    player.addItem(choice, amountToSmelt);
                    System.out.println(GREEN + "Successfully smelted " + amountToSmelt + " " + choice + "!" + RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Please enter a valid number!" + RESET);
            }
        } else {
            System.out.println(RED + "Invalid item to smelt!" + RESET);
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
    private void smith(Player player) {
        System.out.println(BLUE + "\n=== Smithable Items ===" + RESET);

        if (smithingRecipes.isEmpty()) {
            System.out.println(RED + "There are no smithing recipes available." + RESET);
            return;
        }

        // Show only items that can be crafted with available materials
        boolean canCraftAnything = false;
        for (Map.Entry<String, Map<String, Integer>> recipeEntry : smithingRecipes.entrySet()) {
            String itemName = recipeEntry.getKey();
            Map<String, Integer> ingredients = recipeEntry.getValue();

            // Calculate maximum craftable amount
            int maxCraftable = Integer.MAX_VALUE;
            for (Map.Entry<String, Integer> ingredient : ingredients.entrySet()) {
                int available = player.getItemCount(ingredient.getKey());
                int possibleCrafts = available / ingredient.getValue();
                maxCraftable = Math.min(maxCraftable, possibleCrafts);
            }

            if (maxCraftable > 0) {
                canCraftAnything = true;
                System.out.println(PURPLE + itemName + RESET + GREEN + " (Can craft: " + maxCraftable + ")" + RESET);
                System.out.print(YELLOW + "  Requires: " + RESET);
                ingredients.forEach((ing, amount) ->
                        System.out.print(amount + " " + ing + " (" + player.getItemCount(ing) + " available), "));
                System.out.println();
            }
        }

        if (!canCraftAnything) {
            System.out.println(RED + "You don't have enough materials to craft anything!" + RESET);
            return;
        }


        // Show available items to smith
        if (smithingRecipes.isEmpty()) {
            System.out.println(RED + "There are no smithing recipes available." + RESET);
            return;
        }

        for (Map.Entry<String, Map<String, Integer>> recipeEntry : smithingRecipes.entrySet()) {
            String itemName = recipeEntry.getKey();
            System.out.println(PURPLE + itemName + RESET);
            System.out.print(YELLOW + "  Requires: " + RESET);
            for (Map.Entry<String, Integer> ingredientEntry : recipeEntry.getValue().entrySet()) {
                System.out.print(ingredientEntry.getValue() + " " + ingredientEntry.getKey() + ", ");
            }
            System.out.println();
        }

        System.out.println(YELLOW + "\nWhat would you like to smith? (type the item name or 'back')" + RESET);
        String choice = scanner.nextLine().toLowerCase().trim();

        if (choice.equals("back")) return;

        if (smithingRecipes.containsKey(choice)) {
            Map<String, Integer> ingredients = smithingRecipes.get(choice);
            boolean canSmith = true;

            System.out.println("How many would you like to smith?");
            try {
                int amountToSmith = Integer.parseInt(scanner.nextLine().trim());
                if (amountToSmith <= 0) {
                    System.out.println(RED + "Please enter a valid positive number." + RESET);
                    return;
                }

                // Check if the player has enough ingredients
                for (Map.Entry<String, Integer> ingredientEntry : ingredients.entrySet()) {
                    String ingredientName = ingredientEntry.getKey();
                    int requiredAmount = ingredientEntry.getValue() * amountToSmith;
                    if (player.getItemCount(ingredientName) < requiredAmount) {
                        System.out.println(RED + "Not enough " + ingredientName + ". You need " + requiredAmount + "." + RESET);
                        canSmith = false;
                        break;
                    }
                }

                // If the player has enough ingredients, remove them and add the crafted item
                if (canSmith) {
                    for (Map.Entry<String, Integer> ingredientEntry : ingredients.entrySet()) {
                        String ingredientName = ingredientEntry.getKey();
                        int requiredAmount = ingredientEntry.getValue() * amountToSmith;
                        player.removeItem(ingredientName, requiredAmount);
                    }
                    player.addItem(choice, amountToSmith);
                    System.out.println(GREEN + "Successfully crafted " + amountToSmith + " " + choice + "!" + RESET);
                }

            } catch (NumberFormatException e) {
                System.out.println(RED + "Please enter a valid number!" + RESET);
            }
        } else {
            System.out.println(RED + "Invalid item to smith!" + RESET);
        }
    }
}
