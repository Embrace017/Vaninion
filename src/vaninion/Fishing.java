package vaninion;

import vaninion.players.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import static vaninion.ColoredConsole.*;

public class Fishing {
    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, Double> catchRates = new HashMap<>();
    private final Random random = new Random();

    public Fishing() {
        initializeCatchRates();
    }

    public void fish(Player player) {
        System.out.println(YELLOW + "Welcome to fishing!" + RESET);

        // Show current fish in inventory
        System.out.println(BOLD + GREEN + "Your current catches:" + RESET);
        displayFishInventory(player);

        // Show current fishing equipment
        displayFishingEquipment(player);

        // Start fishing at the small lake
        smallLake(player);
    }

    private void displayFishingEquipment(Player player) {
        // Show available rods
        System.out.println(YELLOW + "\n=== Your Fishing Equipment ===" + RESET);
        System.out.println(PURPLE + "Rods:" + RESET);
        if (player.hasItem("basic rod")) {
            System.out.println(CYAN + "- Basic Rod" + (player.hasItem("basic rod") ? GREEN + " (Available)" : RED + " (Not Available)") + RESET);
        }
        if (player.hasItem("super rod")) {
            System.out.println(CYAN + "- Super Rod" + (player.hasItem("super rod") ? GREEN + " (Available)" : RED + " (Not Available)") + RESET);
        }
        if (player.hasItem("legendary rod")) {
            System.out.println(CYAN + "- Legendary Rod" + (player.hasItem("legendary rod") ? GREEN + " (Available)" : RED + " (Not Available)") + RESET);
        }

        // Show available bait
        System.out.println(PURPLE + "\nBait:" + RESET);
        System.out.println(CYAN + "- Basic Bait: " + player.getItemCount("basic bait") + RESET);
        System.out.println(CYAN + "- Super Bait: " + player.getItemCount("super bait") + RESET);
        System.out.println(CYAN + "- Legendary Bait: " + player.getItemCount("legendary bait") + RESET);
    }
    public double calculateTotalCatchRate() {
        double total = 0.0;
        for (Double rate : catchRates.values()) {
            total += rate;
        }
        return total;
    }

    /**
     * Calculate catch rate bonus based on the rod type
     * @param player The player
     * @return Multiplier for catch rates (1.0 = no bonus)
     */
    private double getRodBonus(Player player) {
        if (player.hasItem("legendary rod")) {
            return 1000.0; // Legendary rod doubles catch rates for rare fish
        } else if (player.hasItem("super rod")) {
            return 100.0; // Super rod gives 50% bonus to catch rates
        } else {
            return 1.0; // Basic rod has no bonus
        }
    }

    /**
     * Calculate quantity bonus based on the bait type
     * @param baitType The type of bait being used
     * @return Multiplier for fish quantity (1.0 = no bonus)
     */
    private double getBaitBonus(String baitType) {
        return switch (baitType) {
            case "legendary bait" -> 10.0; // Legendary bait multiplies the catch quantity by 10
            case "super bait" -> 2.0;     // Super bait doubles the catch quantity
            default -> 1.0;               // Basic bait has no bonus
        };
    }

    /**
     * Let the player choose which bait type to use
     * @param player The player
     * @return The selected bait type or null if canceled
     */
    private String chooseBaitType(Player player) {
        System.out.println(YELLOW + "\n=== Choose Bait Type ===" + RESET);

        // Display available bait options
        boolean hasBasicBait = player.getItemCount("basic bait") > 0;
        boolean hasSuperBait = player.getItemCount("super bait") > 0;
        boolean hasLegendaryBait = player.getItemCount("legendary bait") > 0;

        if (hasBasicBait) {
            System.out.println("1. " + CYAN + "Basic Bait" + GREEN + " (x" + player.getItemCount("basic bait") + ")" + RESET + 
                              " - Normal fishing");
        }

        if (hasSuperBait) {
            System.out.println("2. " + CYAN + "Super Bait" + GREEN + " (x" + player.getItemCount("super bait") + ")" + RESET + 
                              " - " + PURPLE + "Doubles" + RESET + " catch quantity");
        }

        if (hasLegendaryBait) {
            System.out.println("3. " + CYAN + "Legendary Bait" + GREEN + " (x" + player.getItemCount("legendary bait") + ")" + RESET + 
                              " - " + PURPLE + "10x" + RESET + " catch quantity");
        }

        System.out.println("*. " + RED + "Cancel" + RESET);

        // Get player choice
        String choice = scanner.nextLine().toLowerCase().trim();

        return switch (choice) {
            case "1", "basic", "basic bait" -> hasBasicBait ? "basic bait" : null;
            case "2", "super", "super bait" -> hasSuperBait ? "super bait" : null;
            case "3", "legendary", "legendary bait" -> hasLegendaryBait ? "legendary bait" : null;
            case "*", "cancel", "back", "exit" -> null;
            default -> {
                System.out.println(RED + "Invalid choice!" + RESET);
                yield null;
            }
        };
    }

    /**
     * Apply rod bonus to catch rates for better fish
     * @param player The player
     * @param fishName The name of the fish
     * @return Modified catch rate
     */
    private double getModifiedCatchRate(Player player, String fishName, double baseRate) {
        double rodBonus = getRodBonus(player);

        // Apply rod bonus differently based on fish rarity
        if (fishName.equals("Abyssal Leviathan") || 
            fishName.equals("Spectral Kraken") || 
            fishName.equals("Thunder Eel") || 
            fishName.equals("Frost Barracuda")) {
            // Legendary rod has a bigger impact on rare fish
            return baseRate * rodBonus * 1.5;
        } else if (fishName.equals("Magma Ray") || 
                  fishName.equals("Shadow Pike") || 
                  fishName.equals("Crystal Bass")) {
            // Medium impact on uncommon fish
            return baseRate * rodBonus * 1.2;
        } else {
            // Smaller impact on common fish
            return baseRate * rodBonus;
        }
    }
    private void initializeCatchRates() {
        catchRates.put("Abyssal Leviathan", 0.0001);     // 0.0001% (1 in 1,000,000)
        catchRates.put("Spectral Kraken", 0.001);        // 0.001% (1 in 100,000)
        catchRates.put("Thunder Eel", 0.01);             // 0.01% (1 in 10,000)
        catchRates.put("Frost Barracuda", 0.02);         // 0.02% (1 in 5,000)
        catchRates.put("Magma Ray", 0.1);                // 0.1% (1 in 1,000)
        catchRates.put("Shadow Pike", 0.2);              // 0.2% (1 in 500)
        catchRates.put("Crystal Bass", 0.6689);          // Approx 1/150
        catchRates.put("Steel Snapper", 1.0);            // 1.0% (1 in 100)
        catchRates.put("River Perch", 5.0);              // 5.0% (1 in 20)
        catchRates.put("Lake Minnow", 30.0);             // 30.0% (1 in 3.33)
        catchRates.put("seaweed", 63.0);                 // leftovers
    }




    private void displayFishInventory(Player player) {
        boolean hasFish = false;
        for (Map.Entry<String, Integer> entry : player.inventory.entrySet()) {
            if (isFish(entry.getKey())) {
                if (!hasFish) {
                    System.out.println(YELLOW + "\n=== Your Fish Collection ===" + RESET);
                    hasFish = true;
                }
                System.out.printf(PURPLE + "%-20s" + GREEN + "x%d" + RESET + "%n",
                        entry.getKey(), entry.getValue());
            }
        }
        if (!hasFish) {
            System.out.println(RED + "No fish caught yet!" + RESET);
        }
        System.out.println();
    }

    private boolean isFish(String item) {
        // Check for raw and cooked versions of all fish types
        return item.contains("raw") || item.contains("cooked") || 
               item.contains("Abyssal Leviathan") || item.contains("Spectral Kraken") ||
               item.contains("Thunder Eel") || item.contains("Frost Barracuda") ||
               item.contains("Magma Ray") || item.contains("Shadow Pike") ||
               item.contains("Crystal Bass") || item.contains("Steel Snapper") ||
               item.contains("River Perch") || item.contains("Lake Minnow");
    }

    private void smallLake(Player player) {
        if (!player.hasItem("basic rod") &&
                (!player.hasItem("super rod")) &&
                (!player.hasItem("legendary rod"))) {

            System.out.println(RED + "You need a fishing rod to fish here!" + RESET);
            return;
        }

        // Check if player has any type of bait
        boolean hasBait = player.getItemCount("basic bait") > 0 || 
                          player.getItemCount("super bait") > 0 || 
                          player.getItemCount("legendary bait") > 0;

        if (!hasBait) {
            System.out.println(RED + "You need some bait to fish here!" + RESET);
            return;
        }

        // Let player choose bait type
        String baitType = chooseBaitType(player);
        if (baitType == null) {
            return; // Player canceled or invalid selection
        }

        System.out.println(BRIGHT_BLACK_BACKGROUND + "How many " + baitType + " would you like to use? (1-20)" + RESET);
        int baitAmount;
        try {
            baitAmount = Integer.parseInt(scanner.nextLine().trim());
            if (baitAmount < 1 || baitAmount > 20) {
                System.out.println(RED + "Please choose between 1 and 20 bait." + RESET);
                return;
            }
            if (baitAmount > player.getItemCount(baitType)) {
                System.out.println(RED + "You don't have that much bait!" + RESET);
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println(RED + "Please enter a valid number." + RESET);
            return;
        }

        // Get bait bonus for the selected bait type
        double baitBonus = getBaitBonus(baitType);

        for (int i = 0; i < baitAmount; i++) {
            try {
                System.out.println(i + 1);
                System.out.println(BLUE + BOLD + "~~~ You cast your rod in the Small Lake using " + baitType + " ~~~" + RESET);
                Thread.sleep(1000 / player.getFishingLevel());
                player.removeItem(baitType, 1);

                // Apply bait bonus to catch quantity
                int baseQuantity = Math.max(1, random.nextInt(player.getFishingLevel()));
                int res = (int) Math.max(1, baseQuantity * baitBonus);
                String itemCaught = player.mapRng(catchRates);
                switch (itemCaught) {
                    case null -> System.out.println(YELLOW + "You didn't even get a bite." + RESET);
                    case "Abyssal Leviathan" -> {
                        System.out.println(BLUE + BOLD + UNDERLINE + "This is literally a 1 in a million chance to catch! You found a " + PURPLE + "mythical card!");
                        player.addItem("mythical card", 1);
                        // Maybe initialize combat?
                    }
                    case "Spectral Kraken" -> player.addItem("raw spectral kraken", res);
                        // Maybe initialize combat?
                    case "Thunder Eel" -> player.addItem("raw thunder eel", res);
                    case "Frost Barracuda" -> player.addItem("raw frost barracuda", res);
                    case "Magma Ray" -> player.addItem("raw magma ray", res);
                    case "Shadow Pike" -> player.addItem("raw shadow pike", res);
                    case "Crystal Bass" -> player.addItem("raw crystal bass", res);
                    case "Steel Snapper" -> player.addItem("raw steel snapper", res);
                    case "River Perch" -> player.addItem("raw river perch", res);
                    case "Lake Minnow" -> player.addItem("raw lake minnow", res);
                    case "seaweed" -> {
                        player.addItem("seaweed", res);
                        player.setFishingExp(player.getFishingExp() + 10);
                    }
                    default -> System.out.println("You didn't see any fish");
                }
                // Display catch message with bait bonus information
                if (baitBonus > 1.0) {
                    System.out.println("You caught " + res + " " + itemCaught + "! " + 
                                      PURPLE + "(Bonus from " + baitType + ")" + RESET + "\n");
                } else {
                    System.out.println("You caught " + res + " " + itemCaught + "!\n");
                }
                player.setFishingExp(player.getFishingExp() + res * 10);
                if (player.getFishingExp() > 500 * player.getFishingLevel()){
                    player.setFishingLevel(player.getFishingLevel() + 1);
                    System.out.println(GREEN + "You gained a level!" + RESET);
                    System.out.println(PURPLE + "Fishing Level: " + player.getFishingLevel() + RESET);
                }
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
    }
}
