package Vaninion_Main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static Vaninion_Main.ColoredConsole.*;

public class Fishing {
    private final Scanner scanner = new Scanner(System.in);


    public void fish(Player player) {
        Map<String, Double> catchRates = new HashMap<>();
        catchRates.put("mythical", 0.0001);     // 0.0001% (1 in 1,000,000)
        catchRates.put("legendary", 0.001);     // 0.001% (1 in 100,000)
        catchRates.put("extreme", 0.01);        // 0.01% (1 in 10,000)
        catchRates.put("very rare", 0.02);      // 0.02% (1 in 5,000)
        catchRates.put("rare", 0.1);            // 0.1% (1 in 1,000)
        catchRates.put("great", 0.2);           // 0.2% (1 in 500)
        catchRates.put("good", 0.6689);         // Approx 1/150
        catchRates.put("decent", 1.0);          // 1.0% (1 in 100)
        catchRates.put("uncommon", 5.0);        // 5.0% (1 in 20)
        catchRates.put("common", 30.0);         // 30.0% (1 in 3.33)
        catchRates.put("miss", 62.0);        // leftovers

        System.out.println(YELLOW + "Welcome to fishing!" + RESET);

        String choice;
        do {
            // Show current fish in inventory
            System.out.println(BOLD + GREEN + "Your current catches:" + RESET);
            displayFishInventory(player);

            // Show current bait
            System.out.println(CYAN + "Basic Bait: " + player.getItemCount("basic bait") + RESET);
            System.out.println(CYAN + "Super Bait: " + player.getItemCount("super bait") + RESET);
            System.out.println(CYAN + "Legendary Bait: " + player.getItemCount("legendary bait") + RESET);

            System.out.println(YELLOW + "What lake are we visiting today?" + RESET);
            System.out.println("1. " + PURPLE + "Small Lake" + RESET + " (Requires: Basic Rod + Basic Bait)");
            System.out.println("2. " + PURPLE + "Medium Lake" + RESET + " (Requires: Super Rod + Super Bait)");
            System.out.println("3. " + PURPLE + "Large Lake" + RESET + " (Requires: Legendary Rod + Legendary Bait)");
            System.out.println("4. " + RED + "Leave" + RESET);
            System.out.print(CYAN + "Enter your choice: " + RESET);

            choice = scanner.nextLine().toLowerCase().trim();

            switch (choice) {
                case "1", "small" -> smallLake(player);
                case "2", "medium" -> mediumLake(player);
                case "3", "large" -> largeLake(player);
                case "4", "back", "leave", "exit" -> {
                    System.out.println(YELLOW + "Thanks for fishing!" + RESET);
                    return;
                }
                default -> System.out.println(RED + "Invalid choice." + RESET);
            }
        } while (true);
    }

    private void displayFishInventory(Player player) {
        boolean hasFish = false;
        for (Map.Entry<String, Integer> entry : player.itemAndCounts.entrySet()) {
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
        return item.contains("fish") || item.equals("bass") || item.equals("sunfish") ||
                item.equals("salmon") || item.equals("catfish") || item.equals("pike") ||
                item.equals("anglerfish") || item.equals("sturgeon") || item.equals("giant squid");
    }

    private void smallLake(Player player) {
        if (!player.hasItem("basic rod") &&
                (!player.hasItem("super rod")) &&
                (!player.hasItem("legendary rod"))) {

            System.out.println(RED + "You need a basic Rod to fish here!" + RESET);
            return;
        }
        if (player.getItemCount("basic bait") <= 0) {
            System.out.println(RED + "You need basic Bait to fish here!" + RESET);
            return;
        }

        System.out.println("How many bait would you like to use? (1-20)");
        int baitChoice;
        try {
            baitChoice = Integer.parseInt(scanner.nextLine().trim());
            if (baitChoice < 1 || baitChoice > 20) {
                System.out.println(RED + "Please choose between 1 and 20 bait." + RESET);
                return;
            }
            if (baitChoice > player.getItemCount("basic bait")) {
                System.out.println(RED + "You don't have that much bait!" + RESET);
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println(RED + "Please enter a valid number." + RESET);
            return;
        }

        for (int i = 0; i < baitChoice; i++) {

            try {
                System.out.println(BLUE + BOLD + "~~~ You cast your rod in the Small Lake ~~~" + RESET);
                player.removeItem("basic bait", 1);

                Map<String, Double> smallLakeItems = new HashMap<>();
                smallLakeItems.put("mythical", 0.0001);     // 0.0001% (1 in 1,000,000)

                smallLakeItems.put("rare", 0.1311);           // 0.1% (1 in 1,000)
                smallLakeItems.put("great", 0.2);             // 0.2% (1 in 500)
                smallLakeItems.put("good", 0.6688);           // Approx 1/150
                smallLakeItems.put("decent", 1.0);            // 1.0% (1 in 100)
                smallLakeItems.put("uncommon", 5.0);          // 5.0% (1 in 20)
                smallLakeItems.put("common", 30.0);           // 30.0% (1 in 3.33)
                smallLakeItems.put("miss", 63.0);             // leftovers



                String itemCaught = getRandomItem(smallLakeItems);
                switch (itemCaught) {
                    case null -> System.out.println(YELLOW + "You didn't even get a bite." + RESET);
                    case "mythical" -> {
                        System.out.println(BLUE + BOLD + UNDERLINE + "This is literally a 1 in a million chance to catch! You found a " + PURPLE + "mythical card!");
                        player.addItem("mythical card", 1);
                    }
                    case "rare" -> {
                        System.out.println("rare catch");
                        player.addItem("rare", 1);
                    }
                    case "great" -> {
                        System.out.println("great catch");
                        player.addItem("great", 1);
                    }
                    case "good" -> {
                        System.out.println("good catch");
                        player.addItem("good", 1);
                    }
                    case "decent" -> {
                        System.out.println("decent catch");
                        player.addItem("decent", 1);
                    }
                    case "uncommon" -> {
                        System.out.println("uncommon catch");
                        player.addItem("uncommon", 1);
                    }
                    case "common" -> {
                        System.out.println("common catch");
                        player.addItem("common", 1);
                    }
                    case "miss" -> {
                        player.addItem("seaweed", 1);
                    }
                    default -> System.out.println("You didn't see any fish");
                    }
                } catch (Exception e) {
                    System.out.println("Error");
            }
        }
    }

    // Similar updates for mediumLake and largeLake methods...
    // [Previous medium and large lake implementations with similar adjustments]
    private void mediumLake(Player player) {
        if (!player.hasItem("super rod") &&
            (!player.hasItem("legendary rod"))) {
            System.out.println(RED + "You need a Super Rod to fish here!" + RESET);
            return;
        }
        if (player.getItemCount("super bait") <= 0) {
            System.out.println(RED + "You need Super Bait to fish here!" + RESET);
            return;
        }

        System.out.println("How many super bait would you like to use? (1-10)");
        int baitChoice;
        try {
            baitChoice = Integer.parseInt(scanner.nextLine().trim());
            if (baitChoice < 1 || baitChoice > 10) {
                System.out.println(RED + "Please choose between 1 and 10 super bait." + RESET);
                return;
            }
            if (baitChoice > player.getItemCount("super bait")) {
                System.out.println(RED + "You don't have that much super bait!" + RESET);
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println(RED + "Please enter a valid number." + RESET);
            return;
        }

        for (int i = 0; i < baitChoice; i++) {
            System.out.println(BLUE + BOLD + "~~~ You cast your rod in the Medium Lake ~~~" + RESET);

            Map<String, Double> mediumLakeFish = new HashMap<>();
            mediumLakeFish.put(null, 40.0);
            mediumLakeFish.put("salmon", 20.0);
            mediumLakeFish.put("catfish", 15.0);
            mediumLakeFish.put("pike", 15.0);
            mediumLakeFish.put("legendary rod", 0.01);
            mediumLakeFish.put("legendary bait", 9.99);

            String itemCaught = getRandomItem(mediumLakeFish);
            player.removeItem("super bait", 1);

            if (itemCaught != null) {
                player.addItem(itemCaught, 1);
                if (itemCaught.equals("legendary rod")) {
                    System.out.println(BOLD + YELLOW + "INCREDIBLE! You found a " + PURPLE +
                            "Legendary Rod" + YELLOW + "!" + RESET);
                            player.itemAndCounts.put("legendary rod", player.itemAndCounts.get("legendary rod") + 1);
                } else if (itemCaught.equals("legendary bait")) {
                    System.out.println(BOLD + YELLOW + "Amazing! You found some " + PURPLE +
                            "Legendary Bait" + YELLOW + "!" + RESET);
                            player.itemAndCounts.put("legendary bait", player.itemAndCounts.get("legendary bait") + 1);
                } else {
                    System.out.println(GREEN + "You caught a " + PURPLE + itemCaught +
                            GREEN + "!" + RESET);
                }
            } else {
                System.out.println(YELLOW + "The fish got away!" + RESET);
            }
        }
    }

    private void largeLake(Player player) {
        if (!player.hasItem("legendary rod")) {
            System.out.println(RED + "You need a Legendary Rod to fish here!" + RESET);
            return;
        }
        if (player.getItemCount("legendary bait") <= 0) {
            System.out.println(RED + "You need Legendary Bait to fish here!" + RESET);
            return;
        }

        System.out.println("How many legendary bait would you like to use? (1-5)");
        int baitChoice;
        try {
            baitChoice = Integer.parseInt(scanner.nextLine().trim());
            if (baitChoice < 1 || baitChoice > 5) {
                System.out.println(RED + "Please choose between 1 and 5 legendary bait." + RESET);
                return;
            }
            if (baitChoice > player.getItemCount("legendary bait")) {
                System.out.println(RED + "You don't have that much legendary bait!" + RESET);
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println(RED + "Please enter a valid number." + RESET);
            return;
        }

        for (int i = 0; i < baitChoice; i++) {
            System.out.println(BLUE + BOLD + "\n~~~ You cast your Legendary rod deep in to Mythical Lake ~~~" + RESET);

            Map<String, Double> largeLakeItems = new HashMap<>();
            largeLakeItems.put(null, 30.0);
            largeLakeItems.put("legendary fish", 5.0);
            largeLakeItems.put("giant squid", 20.0);
            largeLakeItems.put("anglerfish", 24.0);
            largeLakeItems.put("sturgeon", 15.0);
            largeLakeItems.put("legendary bait", 1.0);

            String itemCaught = getRandomItem(largeLakeItems);
            player.removeItem("legendary bait", 1);

            if (itemCaught != null) {
                player.addItem(itemCaught, 1);
                if (itemCaught.equals("legendary fish")) {
                    System.out.println(BOLD + YELLOW + "EXTRAORDINARY! You caught a " + PURPLE +
                            "LEGENDARY FISH" + YELLOW + "! ★★★" + RESET);
                } else if (itemCaught.equals("legendary bait")) {
                    System.out.println(BOLD + YELLOW + "Fortune smiles upon you! You found 10 " +
                            PURPLE + "Legendary Bait" + YELLOW + "!" + RESET);
                    player.itemAndCounts.put("legendary bait", player.itemAndCounts.get("legendary bait") + 10);
                } else {
                    System.out.println(BOLD + GREEN + "You caught a " + PURPLE + itemCaught +
                            GREEN + "!" + RESET);
                }
            } else {
                System.out.println(YELLOW + "The mysterious creature evaded your line!" + RESET);
            }
        }
    }
    private String getRandomItem(Map<String, Double> itemMap) {
        double totalWeight = itemMap.values().stream().mapToDouble(d -> d).sum();
        double random = Math.random() * totalWeight;
        double cumulative = 0.0;

        for (Map.Entry<String, Double> entry : itemMap.entrySet()) {
            cumulative += entry.getValue();
            if (random < cumulative) {
                return entry.getKey();
            }
        }
        return null;
    }
}