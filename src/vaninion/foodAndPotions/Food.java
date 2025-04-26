package vaninion.foodAndPotions;

import vaninion.players.Player;
import java.util.HashMap;
import java.util.Map;

import static vaninion.ColoredConsole.*;

public class Food {
    // Map to store food effects: foodName -> Map of stat effects
    private final Map<String, Map<String, Integer>> foodEffects = new HashMap<>();

    public Food() {
        initializeFoodEffects();
    }

    private void initializeFoodEffects() {
        // Regular fish provide basic health restoration
        foodEffects.put("cooked lake minnow", Map.of(
                "health", 5,
                "mana", 2
        ));

        foodEffects.put("cooked river perch", Map.of(
                "health", 10,
                "mana", 5
        ));

        foodEffects.put("cooked steel snapper", Map.of(
                "health", 20,
                "mana", 10,
                "fishingBoost", 1
        ));

        foodEffects.put("cooked crystal bass", Map.of(
                "health", 30,
                "mana", 15,
                "fishingBoost", 2
        ));

        foodEffects.put("cooked shadow pike", Map.of(
                "health", 50,
                "mana", 25,
                "strengthBoost", 1,
                "fishingBoost", 2
        ));

        foodEffects.put("cooked magma ray", Map.of(
                "health", 80,
                "mana", 40,
                "strengthBoost", 2,
                "fishingBoost", 3
        ));

        foodEffects.put("cooked frost barracuda", Map.of(
                "health", 120,
                "mana", 60,
                "strengthBoost", 3,
                "fishingBoost", 4
        ));

        foodEffects.put("cooked thunder eel", Map.of(
                "health", 200,
                "mana", 100,
                "strengthBoost", 5,
                "fishingBoost", 5,
                "tempMaxHealth", 20
        ));
    }

    public void consumeFood(Player player, String foodName) {
        if (!player.hasItem(foodName)) {
            System.out.println(RED + "You don't have any " + foodName + " to eat!" + RESET);
            return;
        }

        Map<String, Integer> effects = foodEffects.get(foodName);
        if (effects == null) {
            System.out.println(RED + "This item cannot be eaten!" + RESET);
            return;
        }

        // Apply effects
        System.out.println(GREEN + "You eat the " + foodName + "..." + RESET);

        for (Map.Entry<String, Integer> effect : effects.entrySet()) {
            switch (effect.getKey()) {
                case "health" -> {
                    int healthToAdd = effect.getValue();
                    player.setHealth(Math.min(player.getHealth() + healthToAdd, player.getMaxHealth()));
                    System.out.println(GREEN + "You restored " + healthToAdd + " health!" + RESET);
                }
                case "mana" -> {
                    int manaToAdd = effect.getValue();
                    player.setMana(player.getMana() + manaToAdd);
                    System.out.println(BLUE + "You restored " + manaToAdd + " mana!" + RESET);
                }
                case "strengthBoost" -> {
                    int strengthBoost = effect.getValue();
                    // Add temporary strength boost logic here
                    player.setTempStrength(player.getTempStrength() + strengthBoost);
                    System.out.println(RED + "Your strength increased by " + strengthBoost + " for a limited time!" + RESET);
                }
                case "fishingBoost" -> {
                    int fishingBoost = effect.getValue();
                    // Add temporary fishing skill boost logic here
                    player.setFishingLevel(player.getFishingLevel() + fishingBoost);
                    System.out.println(CYAN + "Your fishing skill increased by " + fishingBoost + " for a limited time!" + RESET);
                }
                case "tempMaxHealth" -> {
                    int tempMaxHealth = effect.getValue();
                    // Add temporary max health boost logic here
                    player.setTempMaxHealth(player.getTempMaxHealth() + tempMaxHealth);
                    System.out.println(PURPLE + "Your maximum health increased by " + tempMaxHealth + " for a limited time!" + RESET);
                }
            }
        }

        // Remove the consumed food from inventory
        player.removeItem(foodName, 1);
    }

    public void displayAvailableFood(Player player) {
        System.out.println(YELLOW + "=== Available Food ===" + RESET);
        boolean hasFood = false;

        for (String foodName : foodEffects.keySet()) {
            int count = player.getItemCount(foodName);
            if (count > 0) {
                hasFood = true;
                System.out.print(PURPLE + foodName + RESET + " x" + count + " - Effects: ");

                Map<String, Integer> effects = foodEffects.get(foodName);
                for (Map.Entry<String, Integer> effect : effects.entrySet()) {
                    String effectDesc = switch (effect.getKey()) {
                        case "health" -> GREEN + "+" + effect.getValue() + " HP" + RESET;
                        case "mana" -> BLUE + "+" + effect.getValue() + " MP" + RESET;
                        case "strengthBoost" -> RED + "+" + effect.getValue() + " STR" + RESET;
                        case "fishingBoost" -> CYAN + "+" + effect.getValue() + " Fishing" + RESET;
                        case "tempMaxHealth" -> PURPLE + "+" + effect.getValue() + " Max HP" + RESET;
                        default -> effect.getKey() + ": " + effect.getValue();
                    };
                    System.out.print(effectDesc + ", ");
                }
                System.out.println();
            }
        }

        if (!hasFood) {
            System.out.println(RED + "You don't have any food to eat!" + RESET);
        }
    }
}