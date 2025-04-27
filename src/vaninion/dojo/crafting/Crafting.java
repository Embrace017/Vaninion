package vaninion.dojo.crafting;

import vaninion.players.Player;

import java.util.Map;

import static vaninion.ColoredConsole.*;
import static vaninion.ColoredConsole.BLUE;
import static vaninion.ColoredConsole.GREEN;
import static vaninion.ColoredConsole.PURPLE;
import static vaninion.ColoredConsole.RED;
import static vaninion.ColoredConsole.RESET;
import static vaninion.ColoredConsole.YELLOW;

public class Crafting extends Recipes {


    private final java.util.Scanner scanner = new java.util.Scanner(System.in);

    protected void smelt(Player player) {
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

    protected void cook(Player player) {
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
    protected void craft(Player player) {
        System.out.println(BLUE + "\n=== Craftable Items ===" + RESET);

        if (craftingRecipes.isEmpty()) {
            System.out.println(RED + "There are no crafting recipes available." + RESET);
            return;
        }

        // Show all recipes with improved formatting
        boolean canCraftAnything = false;
        System.out.println(YELLOW + "╔════════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(YELLOW + "║ " + PURPLE + "ITEM NAME                  " + YELLOW + "│ " + GREEN + "INGREDIENTS                    " + YELLOW + "║" + RESET);
        System.out.println(YELLOW + "╠════════════════════════════════════════════════════════════╣" + RESET);

        for (Map.Entry<String, Map<String, Integer>> recipeEntry : craftingRecipes.entrySet()) {
            String itemName = recipeEntry.getKey();
            Map<String, Integer> ingredients = recipeEntry.getValue();

            // Calculate maximum craftable amount
            int maxCraftable = Integer.MAX_VALUE;
            for (Map.Entry<String, Integer> ingredient : ingredients.entrySet()) {
                int available = player.getItemCount(ingredient.getKey());
                int possibleCrafts = available / ingredient.getValue();
                maxCraftable = Math.min(maxCraftable, possibleCrafts);
            }

            // Format item name with craftable amount
            String displayName = PURPLE + String.format("%-25s", itemName) + RESET;
            if (maxCraftable > 0) {
                canCraftAnything = true;
                displayName += GREEN + " (Can craft: " + maxCraftable + ")" + RESET;
            }

            System.out.println(YELLOW + "║ " + displayName + YELLOW + "│                                  ║" + RESET);

            // Display ingredients with better formatting
            boolean firstIngredient = true;
            for (Map.Entry<String, Integer> ingredient : ingredients.entrySet()) {
                String ingName = ingredient.getKey();
                int required = ingredient.getValue();
                int available = player.getItemCount(ingName);

                String prefix = firstIngredient ? YELLOW + "║ " + RESET + "                          " + YELLOW + "│ " + RESET : 
                                                YELLOW + "║ " + RESET + "                          " + YELLOW + "│ " + RESET;

                String ingredientText = String.format("%d %s", required, ingName);
                String availableText = String.format("(%d available)", available);

                String color = available >= required ? GREEN : RED;
                System.out.println(prefix + color + String.format("%-20s %-10s", ingredientText, availableText) + YELLOW + " ║" + RESET);

                firstIngredient = false;
            }

            System.out.println(YELLOW + "╟────────────────────────────────────────────────────────────╢" + RESET);
        }
        System.out.println(YELLOW + "╚════════════════════════════════════════════════════════════╝" + RESET);

        if (!canCraftAnything) {
            System.out.println(RED + "You don't have enough materials to craft anything!" + RESET);
            return;
        }

        System.out.println(YELLOW + "\nWhat would you like to craft? (type the item name or 'back')" + RESET);
        String choice = scanner.nextLine().toLowerCase().trim();

        if (choice.equals("back")) return;

        if (craftingRecipes.containsKey(choice)) {
            Map<String, Integer> ingredients = craftingRecipes.get(choice);
            boolean canCraft = true;

            System.out.println("How many would you like to craft?");
            try {
                int amountToCraft = Integer.parseInt(scanner.nextLine().trim());
                if (amountToCraft <= 0) {
                    System.out.println(RED + "Please enter a valid positive number." + RESET);
                    return;
                }

                // Check if the player has enough ingredients
                for (Map.Entry<String, Integer> ingredientEntry : ingredients.entrySet()) {
                    String ingredientName = ingredientEntry.getKey();
                    int requiredAmount = ingredientEntry.getValue() * amountToCraft;
                    if (player.getItemCount(ingredientName) < requiredAmount) {
                        System.out.println(RED + "Not enough " + ingredientName + ". You need " + requiredAmount + "." + RESET);
                        canCraft = false;
                        break;
                    }
                }

                // If the player has enough ingredients, remove them and add the crafted item
                if (canCraft) {
                    for (Map.Entry<String, Integer> ingredientEntry : ingredients.entrySet()) {
                        String ingredientName = ingredientEntry.getKey();
                        int requiredAmount = ingredientEntry.getValue() * amountToCraft;
                        player.removeItem(ingredientName, requiredAmount);
                    }
                    player.addItem(choice, amountToCraft);
                    System.out.println(GREEN + "Successfully crafted " + amountToCraft + " " + choice + "!" + RESET);
                }

            } catch (NumberFormatException e) {
                System.out.println(RED + "Please enter a valid number!" + RESET);
            }
        } else {
            System.out.println(RED + "Invalid item to craft!" + RESET);
        }
    }
}
