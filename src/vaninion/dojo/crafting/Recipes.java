package vaninion.dojo.crafting;

import java.util.HashMap;
import java.util.Map;

public class Recipes {

    public final Map<String, Map<String, Integer>> smeltingRecipes = new HashMap<>();
    public final Map<String, String> cookingRecipes = new HashMap<>();
    public final Map<String, Map<String, Integer>> craftingRecipes = new HashMap<>(); // Changed to Map<String, Integer> for ingredients

    public void initializeRecipes() {

        // Initialize smelting recipes with input -> output mappings
        smeltingRecipes.put("steel bar", Map.of("iron ore", 3, "coal", 3));
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

        // Initialize crafting recipes with input (ingredients -> quantity) and output
        // MISC
        craftingRecipes.put("copper wire", Map.of("copper bar", 1));
        craftingRecipes.put("werewolf mask", Map.of("werewolf fur", 10, "werewolf fangs", 2));

        // Steel items
        craftingRecipes.put("steel sword", Map.of("steel bar", 5));
        craftingRecipes.put("steel helmet", Map.of("steel bar", 5));
        craftingRecipes.put("steel chestplate", Map.of("steel bar", 5));
        craftingRecipes.put("steel leggings", Map.of("steel bar", 5));
        craftingRecipes.put("steel shield", Map.of("steel bar", 5));
        // Gorganite
        craftingRecipes.put("gorganite sword", Map.of("gorganite bar", 20));
        craftingRecipes.put("gorganite helmet", Map.of("gorganite bar", 20));
        craftingRecipes.put("gorganite chestplate", Map.of("gorganite bar", 20));
        craftingRecipes.put("gorganite leggings", Map.of("gorganite bar", 20));
        craftingRecipes.put("gorganite shield", Map.of("gorganite bar", 20));
        // Vaninite
        craftingRecipes.put("vaninite sword", Map.of("vaninite bar", 5));
        craftingRecipes.put("vaninite helmet", Map.of("vaninite bar", 5));
        craftingRecipes.put("vaninite chestplate", Map.of("vaninite bar", 5));
        craftingRecipes.put("vaninite leggings", Map.of("vaninite bar", 5));


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
}
