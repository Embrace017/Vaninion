package vaninion;

import vaninion.players.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import static vaninion.ColoredConsole.*;


public class Fishing {
    private final Scanner scanner = new Scanner(System.in);
    public final Map<String, Double> catchRates = new HashMap<>();
    Random random = new Random();


    public Fishing() {
        initializeCatchRates();


    }

    public void fish(Player player) {

        System.out.println(YELLOW + "Welcome to fishing!" + RESET);
        // Show current fish in inventory
        System.out.println(BOLD + GREEN + "Your current catches:" + RESET);
        displayFishInventory(player);
        // Show current bait
        System.out.println(CYAN + "Basic Bait: " + player.getItemCount("basic bait") + RESET);
        System.out.println(CYAN + "Super Bait: " + player.getItemCount("super bait") + RESET);
        System.out.println(CYAN + "Legendary Bait: " + player.getItemCount("legendary bait") + RESET);
        smallLake(player);

    }
    public double calculateTotalCatchRate() {
        double total = 0.0;
        for (Double rate : catchRates.values()) {
            total += rate;
        }
        return total;
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

        System.out.println(BRIGHT_BLACK_BACKGROUND + "How many bait would you like to use? (1-20)" + RESET);
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
                int res = Math.max(1, random.nextInt(player.getFishingLevel()));
                String itemCaught = player.mapRng(catchRates);
                switch (itemCaught) {
                    case null -> System.out.println(YELLOW + "You didn't even get a bite." + RESET);
                    case "Abyssal Leviathan" -> {
                        System.out.println(BLUE + BOLD + UNDERLINE + "This is literally a 1 in a million chance to catch! You found a " + PURPLE + "mythical card!");
                        player.addItem("mythical card", 1);
                        // Maybe initialize combat?
                    }
                    case "Spectral Kraken" -> {
                        player.addItem("raw spectral kraken", res);
                        // Maybe initialize combat?
                    }
                    case "Thunder Eel" -> {
                        player.addItem("raw thunder eel", res);
                    }
                    case "Frost Barracuda" -> {
                        player.addItem("raw frost barracuda", res);
                    }
                    case "Magma Ray" -> {
                        player.addItem("raw magma ray", res);
                    }
                    case "Shadow Pike" -> {
                        player.addItem("raw shadow pike", res);
                    }
                    case "Crystal Bass" -> {
                        player.addItem("raw crystal bass", res);
                    }
                    case "Steel Snapper" -> {
                        player.addItem("raw steel snapper", res);
                    }
                    case "River Perch" -> {
                        player.addItem("raw river perch", res);
                    }
                    case "Lake Minnow" -> {
                        player.addItem("raw lake minnow", res);
                    }
                    case "seaweed" -> {
                        player.addItem("seaweed", res);
                        player.setFishingExp(player.getFishingExp() + 10);
                    }
                    default -> System.out.println("You didn't see any fish");
                }
                System.out.println("You caught " + player.getFishingLevel() + " " + itemCaught + "!\n");
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
    }
}
