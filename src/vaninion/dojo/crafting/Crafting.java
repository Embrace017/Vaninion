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
        System.out.println(BLUE + "\n====== Smelting Menu ======" + RESET);
        if (smeltingRecipes.isEmpty()) {
            System.out.println(RED + "There are no smelting recipes available." + RESET);
            return;
        }

        // Menu for choosing what to display
        System.out.println("1. " + GREEN + "Show Smeltable Items Only" + RESET + " (items you can smelt now)");
        System.out.println("2. " + YELLOW + "Show All Smelting Recipes" + RESET + " (including items you can't smelt yet)");
        System.out.println("*. " + RED + "Back" + RESET);

        String menuChoice = scanner.nextLine().toLowerCase().trim();

        if (menuChoice.equals("*") || menuChoice.equals("back") || menuChoice.equals("exit")) {
            return;
        }

        boolean showOnlySmeltable = menuChoice.equals("1");

        // Display recipes based on user choice
        boolean canSmeltAnything = displaySmeltingRecipes(player, showOnlySmeltable);

        if (showOnlySmeltable && !canSmeltAnything) {
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
        System.out.println(BLUE + "\n=== Cooking Menu ===" + RESET);

        if (cookingRecipes.isEmpty()) {
            System.out.println(RED + "There are no cooking recipes available." + RESET);
            return;
        }

        // Menu for choosing what to display
        System.out.println("1. " + GREEN + "Show Cookable Items Only" + RESET + " (fish you have now)");
        System.out.println("2. " + YELLOW + "Show All Cooking Recipes" + RESET + " (all possible fish)");
        System.out.println("*. " + RED + "Back" + RESET);

        String menuChoice = scanner.nextLine().toLowerCase().trim();

        if (menuChoice.equals("*") || menuChoice.equals("back") || menuChoice.equals("exit")) {
            return;
        }

        boolean showOnlyCookable = menuChoice.equals("1");

        // Display recipes based on user choice
        boolean hasCookableItems = displayCookingRecipes(player, showOnlyCookable);

        if (showOnlyCookable && !hasCookableItems) {
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
        System.out.println(BLUE + "\n=== Crafting Menu ===" + RESET);

        if (craftingRecipes.isEmpty()) {
            System.out.println(RED + "There are no crafting recipes available." + RESET);
            return;
        }

        // Menu for choosing what to display
        System.out.println("1. " + GREEN + "Show Craftable Items Only" + RESET + " (items you can make now)");
        System.out.println("2. " + YELLOW + "Show All Recipes" + RESET + " (including items you can't make yet)");
        System.out.println("*. " + RED + "Back" + RESET);

        String menuChoice = scanner.nextLine().toLowerCase().trim();

        if (menuChoice.equals("*") || menuChoice.equals("back") || menuChoice.equals("exit")) {
            return;
        }

        boolean showOnlyCraftable = menuChoice.equals("1");

        // Display recipes based on user choice
        boolean canCraftAnything = displayRecipes(player, showOnlyCraftable);

        if (showOnlyCraftable && !canCraftAnything) {
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

    /**
     * Displays smelting recipes with improved formatting and filtering options
     * @param player The player
     * @param onlySmeltable If true, only shows recipes that the player can smelt
     * @return True if there are any smeltable recipes, false otherwise
     */
    private boolean displaySmeltingRecipes(Player player, boolean onlySmeltable) {
        String title = onlySmeltable ? BLUE + "\n=== Smeltable Items Only ===" + RESET : BLUE + "\n=== All Smelting Recipes ===" + RESET;
        System.out.println(title);

        boolean canSmeltAnything = false;
        System.out.println(PURPLE + "ITEM NAME                  " + YELLOW + "| " + GREEN + "INGREDIENTS" + RESET);
        System.out.println("----------------------------------------------------------");

        for (Map.Entry<String, Map<String, Integer>> recipeEntry : smeltingRecipes.entrySet()) {
            String itemName = recipeEntry.getKey();
            Map<String, Integer> ingredients = recipeEntry.getValue();

            // Calculate maximum smeltable amount
            int maxSmeltable = Integer.MAX_VALUE;
            for (Map.Entry<String, Integer> ingredient : ingredients.entrySet()) {
                int available = player.getItemCount(ingredient.getKey());
                int possibleSmelts = available / ingredient.getValue();
                maxSmeltable = Math.min(maxSmeltable, possibleSmelts);
            }

            // Skip this recipe if we're only showing smeltable items and this one isn't smeltable
            if (onlySmeltable && maxSmeltable <= 0) {
                continue;
            }

            // Format item name with smeltable amount
            String displayName = PURPLE + String.format("%-25s", itemName) + RESET;
            if (maxSmeltable > 0) {
                canSmeltAnything = true;
                displayName += GREEN + " (Can smelt: " + maxSmeltable + ")" + RESET;
            } else {
                displayName += RED + " (Cannot smelt)" + RESET;
            }

            System.out.println(displayName + YELLOW + " | " + RESET);

            // Display ingredients with better formatting
            boolean firstIngredient = true;
            for (Map.Entry<String, Integer> ingredient : ingredients.entrySet()) {
                String ingName = ingredient.getKey();
                int required = ingredient.getValue();
                int available = player.getItemCount(ingName);

                String prefix = "                           | ";

                String ingredientText = String.format("%d %s", required, ingName);
                String availableText = String.format("(%d available)", available);

                String color = available >= required ? GREEN : RED;
                System.out.println(prefix + color + String.format("%-20s %-10s", ingredientText, availableText) + RESET);

                firstIngredient = false;
            }

            System.out.println("----------------------------------------------------------");
        }
        System.out.println();

        return canSmeltAnything;
    }

    /**
     * Displays cooking recipes with improved formatting and filtering options
     * @param player The player
     * @param onlyCookable If true, only shows recipes for fish that the player has
     * @return True if there are any cookable items, false otherwise
     */
    private boolean displayCookingRecipes(Player player, boolean onlyCookable) {
        String title = onlyCookable ? BLUE + "\n=== Cookable Fish Only ===" + RESET : BLUE + "\n=== All Cooking Recipes ===" + RESET;
        System.out.println(title);

        boolean hasCookableItems = false;
        System.out.println(PURPLE + "RAW FISH                   " + YELLOW + "| " + GREEN + "COOKED RESULT" + RESET);
        System.out.println("----------------------------------------------------------");

        for (Map.Entry<String, String> recipeEntry : cookingRecipes.entrySet()) {
            String rawFish = recipeEntry.getKey();
            String cookedFish = recipeEntry.getValue();
            int count = player.getItemCount(rawFish);

            // Skip this recipe if we're only showing cookable items and player doesn't have this fish
            if (onlyCookable && count <= 0) {
                continue;
            }

            if (count > 0) {
                hasCookableItems = true;
            }

            // Format fish names with quantity
            String rawFishDisplay = PURPLE + String.format("%-25s", rawFish) + RESET;
            if (count > 0) {
                rawFishDisplay += GREEN + " (x" + count + ")" + RESET;
            } else {
                rawFishDisplay += RED + " (x0)" + RESET;
            }

            String cookedFishDisplay = GREEN + String.format("%-25s", cookedFish) + RESET;

            System.out.println(rawFishDisplay + YELLOW + " | " + cookedFishDisplay + RESET);
            System.out.println("----------------------------------------------------------");
        }
        System.out.println();

        return hasCookableItems;
    }

    /**
     * Displays crafting recipes with improved formatting and filtering options
     * @param player The player
     * @param onlyCraftable If true, only shows recipes that the player can craft
     * @return True if there are any craftable recipes, false otherwise
     */
    private boolean displayRecipes(Player player, boolean onlyCraftable) {
        String title = onlyCraftable ? BLUE + "\n=== Craftable Items Only ===" + RESET : BLUE + "\n=== All Crafting Recipes ===" + RESET;
        System.out.println(title);

        boolean canCraftAnything = false;
        System.out.println(PURPLE + "ITEM NAME                  " + YELLOW + "| " + GREEN + "INGREDIENTS" + RESET);
        System.out.println("----------------------------------------------------------");

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

            // Skip this recipe if we're only showing craftable items and this one isn't craftable
            if (onlyCraftable && maxCraftable <= 0) {
                continue;
            }

            // Format item name with craftable amount
            if (maxCraftable > 0) {
                canCraftAnything = true;
                System.out.println(YELLOW + "║ " + PURPLE + String.format("%-25s", itemName) + YELLOW + "│                                  ║" + RESET);
                System.out.println(YELLOW + "║                           " + GREEN + String.format("(Can craft: %d)", maxCraftable) + YELLOW + "│                                  ║" + RESET);
            } else {
                System.out.println(YELLOW + "║ " + PURPLE + String.format("%-25s", itemName) + YELLOW + "│                                  ║" + RESET);
                System.out.println(YELLOW + "║                           " + RED + "(Cannot craft)" + YELLOW + "│                                  ║" + RESET);
            }

            // Display ingredients with better formatting
            boolean firstIngredient = true;
            for (Map.Entry<String, Integer> ingredient : ingredients.entrySet()) {
                String ingName = ingredient.getKey();
                int required = ingredient.getValue();
                int available = player.getItemCount(ingName);

                String prefix = YELLOW + "║ " + RESET + "                          " + YELLOW + "│ " + RESET;

                String ingredientText = String.format("%d %s", required, ingName);
                String availableText = String.format("(%d available)", available);

                String color = available >= required ? GREEN : RED;
                System.out.println(prefix + color + String.format("%-20s %-10s", ingredientText, availableText) + YELLOW + " ║" + RESET);

                firstIngredient = false;
            }

            System.out.println(YELLOW + "╟────────────────────────────────────────────────────────────╢" + RESET);
        }
        System.out.println(YELLOW + "╚════════════════════════════════════════════════════════════╝" + RESET);

        return canCraftAnything;
    }
}
