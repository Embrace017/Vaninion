package Vaninion_Main.adventure;

import Vaninion_Main.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static Vaninion_Main.ColoredConsole.*;

public class Area {
    private final Scanner scanner = new Scanner(System.in);
    private final Riddle riddle = new Riddle();

    public void Areas(Player player) {

        Map<String, Double> areaRates = new HashMap<>();
        areaRates.put("mythical", 0.0001);     // 0.0001% (1 in 1,000,000)
        areaRates.put("legendary", 0.001);     // 0.001% (1 in 100,000)
        areaRates.put("extreme", 0.01);        // 0.01% (1 in 10,000)
        areaRates.put("very rare", 0.02);      // 0.02% (1 in 5,000)
        areaRates.put("rare", 0.1);            // 0.1% (1 in 1,000)
        areaRates.put("great", 0.2);           // 0.2% (1 in 500)
        areaRates.put("good", 0.6689);         // Approx 1/150
        areaRates.put("decent", 1.0);          // 1.0% (1 in 100)
        areaRates.put("uncommon", 5.0);        // 5.0% (1 in 20)
        areaRates.put("common", 30.0);         // 30.0% (1 in 3.33)
        areaRates.put("miss", 62.0);        // leftovers



        System.out.println(YELLOW + "Welcome to the adventure! Are you ready to explore?" + RESET);

        String choice;
        do {
            // Print area stats
            System.out.println(BOLD + GREEN + "Areas discovered:" + RESET);
            // Print area stats

            // Show current adventure common items
            System.out.println(CYAN + "Gold coin: " + player.getItemCount("gold coin") + RESET);
            //System.out.println(CYAN + "Super Bait: " + player.getItemCount("super bait") + RESET);
            //System.out.println(CYAN + "Legendary Bait: " + player.getItemCount("legendary bait") + RESET);

            System.out.println(YELLOW + "Where would you like to go?" + RESET);
            System.out.println("1. " + PURPLE + "Forest" + RESET + " (Requires: 1x Gold coin)");
            System.out.println("2. " + PURPLE + "Mountains" + RESET + " (Requires: 10x Gold coin.)");
            System.out.println("3. " + PURPLE + "South pole" + RESET + " (Requires: 100x Gold coin)");
            System.out.println("4. " + RED + "Leave" + RESET);
            System.out.print(CYAN + "Enter your choice: " + RESET);

            choice = scanner.nextLine().toLowerCase().trim();

            switch (choice) {
                case "1", "forest" -> forest(player);
                case "2", "mountains" -> mountains(player);
                case "3", "southpole" -> southPole(player);
                case "4", "back", "leave", "exit" -> {
                    System.out.println(YELLOW + "Thanks for fishing!" + RESET);
                    return;
                }
                default -> System.out.println(RED + "Invalid choice." + RESET);
            }
        } while (true);
    }
    private void forest(Player player) {
        if (player.getItemCount("gold coin") <= 0) {
            System.out.println("You don't have enough Gold coins!");
            return;
        }
        try { // Begin forest adventure
            System.out.println("Your venture deep in to the Lethal Forest begins..");


            // Implement adventureRng
            Map<String, Double> areaRates = new HashMap<>();
            areaRates.put("mythical", 0.0001);     // 0.0001% (1 in 1,000,000)
            areaRates.put("legendary", 0.001);     // 0.001% (1 in 100,000)
            areaRates.put("extreme", 0.01);        // 0.01% (1 in 10,000)
            areaRates.put("very rare", 0.02);      // 0.02% (1 in 5,000)
            areaRates.put("rare", 0.1);            // 0.1% (1 in 1,000)
            areaRates.put("great", 0.2);           // 0.2% (1 in 500)
            areaRates.put("good", 0.6689);         // Approx 1/150
            areaRates.put("decent", 1.0);          // 1.0% (1 in 100)
            areaRates.put("uncommon", 5.0);        // 5.0% (1 in 20)
            areaRates.put("common", 30.0);         // 30.0% (1 in 3.33)
            areaRates.put("miss", 63.0);           // leftovers

            String areaRate = player.mapRng(areaRates);

            switch (areaRate) {
                case "monster" -> System.out.println("fight");
                case "boss" -> System.out.println("boss fight"); //more rare
                case "riddle" -> riddle.playRiddle(player);
                case "wildAnimal" -> System.out.println("kill ? tame?");
                case "lake" -> System.out.println("move fishing here? special fishing?");
                case "tree" -> System.out.println("cut wood. crafting. bonfire for boosts?");
                case "ore rocks" -> System.out.println("mining");
                case "abandon lookout" -> System.out.println("climb for view? better area?");
                case "camp" -> System.out.println("loot it? sleep for health?");
                case "randomStranger" -> System.out.println("charisma check. recruit? rob(charisma * wisdom)?");
                case "merchant" -> System.out.println("rare items or better prices");
                case "scavengerHunt" -> System.out.println("clues, searching");
                case "work" -> System.out.println("do a job for someone/thing");
                case "land for sale" -> System.out.println("but land, makes passive income,");
                case "training dojo" -> System.out.println("work on lvls and exp");

                //case "" -> System.out.println("");
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    private void mountains(Player player) {

    }

    private void southPole(Player player) {

    }
}