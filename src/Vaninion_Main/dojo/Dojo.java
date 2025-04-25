package Vaninion_Main.dojo;

import Vaninion_Main.player.Player;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

import static Vaninion_Main.ColoredConsole.*;

public class Dojo {
    private final Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    private final Map<String, String> smeltingRecipes = new HashMap<>();
    private final Map<String, String> cookingRecipes = new HashMap<>();
    private final Map<String, Map<String, Integer>> smithingRecipes = new HashMap<>(); // Changed to Map<String, Integer> for ingredients
    private boolean furnaceLit = false;

    public Dojo() {
        // Initialize smelting recipes with input -> output mappings
        smeltingRecipes.put("copper ore", "copper bar");
        smeltingRecipes.put("iron ore", "iron bar");
        smeltingRecipes.put("gold ore", "gold bar");
        smeltingRecipes.put("gorganite ore", "gorganite bar");
        smeltingRecipes.put("vaninite ore", "vaninite bar");

        // Initialize smithing recipes with input (ingredients -> quantity) and output
        smithingRecipes.put("copper wire", Map.of("copper bar", 1));
        smithingRecipes.put("iron shield", Map.of("iron bar", 3));
        smithingRecipes.put("steel sword", Map.of("iron bar", 3, "coal", 6)); // Example with multiple ingredients

        // Initialize cooking recipes with input -> output mappings
        cookingRecipes.put("raw common fish", "cooked common fish");
        cookingRecipes.put("raw uncommon fish", "cooked uncommon fish");
        cookingRecipes.put("raw rare fish", "cooked rare fish");
        cookingRecipes.put("raw legendary fish", "cooked legendary fish");
    }
    public void enterDojo (Player player) {
        while (true) {
            System.out.println(YELLOW + "\n=== Welcome to the Dojo ===" + RESET);
            System.out.println("1. " + PURPLE + "Rest" + RESET);
            //System.out.println("2. " + PURPLE + "Eat Food" + RESET);
            System.out.println("3. " + PURPLE + "Smelt Ores" + RESET);
            System.out.println("4. " + PURPLE + "Cook Fish" + RESET);
            System.out.println("5. " + PURPLE + "Smith Items" + RESET); // Changed option name
            System.out.println("* " + RED + "Leave Dojo" + RESET); // Renumbered leave option

            String choice = scanner.nextLine().toLowerCase().trim();

            switch (choice) {
                case "1", "rest" -> rest(player);
                //case "2", "eat" -> food.eat(player);
                case "3", "smelt" -> smelt(player);
                case "4", "cook" -> cook(player);
                case "5", "smith" -> smith(player); // Call the smith method
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

    private void smelt (Player player){
        System.out.println(BLUE + "\n=== Smelting Ores ===" + RESET);

        if (!furnaceLit) {
            if (player.getItemCount("coal") > 0) {
                System.out.println(YELLOW + "The furnace is cold. Do you want to add coal to light it? (yes/no)" + RESET);
                String lightChoice = scanner.nextLine().toLowerCase().trim();
                if (lightChoice.equals("yes")) {
                    player.removeItem("coal", 1);
                    furnaceLit = true;
                    System.out.println(GREEN + "You add coal to the furnace. It begins to glow." + RESET);
                } else {
                    System.out.println(RED + "The furnace remains unlit. You cannot smelt without fuel." + RESET);
                    return;
                }
            } else {
                System.out.println(RED + "The furnace is cold and you have no coal to light it!" + RESET);
                return;
            }
        }

        System.out.println(GREEN + "(One " + RESET + "bar" + GREEN + " requires three " + RESET + "Ores and one " + RESET + "Coal)" + RESET);

        // Show available ores from recipes that player has
        boolean hasOres = false;
        for (String ore : smeltingRecipes.keySet()) {
            int count = player.getItemCount(ore);  // This will now check resource inventory
            if (count >= 3) { // Only show ores the player has at least 3 of
                hasOres = true;
                System.out.println(PURPLE + ore + RESET + " x" + count + " (can smelt " + count / 3 + ")");
            }
        }

        if (!hasOres) {
            System.out.println(RED + "You don't have enough ores (at least 3) to smelt!" + RESET);
            return;
        }

        System.out.println(YELLOW + "\nWhat would you like to smelt? (type the ore name or 'back')" + RESET);
        String choice = scanner.nextLine().toLowerCase().trim();

        if (choice.equals("back")) return;

        if (player.hasItem(choice) && smeltingRecipes.containsKey(choice)) {
            System.out.println("How many would you like to smelt (in sets of 3)?");
            try {
                int numSets = Integer.parseInt(scanner.nextLine().trim());
                if (numSets > 0) {
                    int requiredOre = numSets * 3;
                    int requiredCoal = numSets; // 1 coal per bar (which is 1 set of 3 ores)
                    if (player.getItemCount(choice) >= requiredOre && player.getItemCount("coal") >= requiredCoal) {
                        player.removeItem(choice, requiredOre);
                        player.removeItem("coal", requiredCoal);
                        String barName = smeltingRecipes.get(choice);
                        player.addItem(barName, numSets); // Output is now based on the number of sets
                        System.out.println(GREEN + "Successfully smelted " + numSets * 3 + " " + choice + " into " + numSets + " " + barName + " using " + requiredCoal + " coal." + RESET);
                    } else {
                        if (player.getItemCount(choice) < requiredOre) {
                            System.out.println(RED + "Not enough " + choice + " to smelt that many sets!" + RESET);
                        }
                        if (player.getItemCount("coal") < requiredCoal) {
                            System.out.println(RED + "Not enough coal to smelt that many bars!" + RESET);
                        }
                    }
                } else {
                    System.out.println(RED + "Please enter a valid positive number of sets!" + RESET);
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
    private void smith (Player player){
        System.out.println(BLUE + "\n=== Smithable Items ===" + RESET);

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
