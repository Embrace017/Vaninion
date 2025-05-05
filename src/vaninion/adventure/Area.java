package vaninion.adventure;

import vaninion.Fishing;
import vaninion.combat.Combat;
import vaninion.players.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import static vaninion.ColoredConsole.*;
import static vaninion.combat.Combat.*;

public class Area {
    private final Scanner scanner = new Scanner(System.in);
    private final Riddle riddle = new Riddle();
    private final Random random = new Random();
    private final Combat combat = new Combat();
    private final Fishing fishing = new Fishing();
    private final Mining mining = new Mining();
    private final Animal animal = new Animal();
    private final Camp camp = new Camp();

    public void areas(Player player) {

        System.out.println(YELLOW + "Welcome to the adventure! Are you ready to explore?" + RESET);

        String choice;
        do {
            // Print area stats
            //System.out.println(BOLD + GREEN + "Areas discovered:" + RESET);
            // Print area stats

            // Show current adventure common items
            System.out.println(CYAN + "Gold coin: " + player.getItemCount("gold coin") + RESET);
            //System.out.println(CYAN + "Super Bait: " + player.getItemCount("super bait") + RESET);
            //System.out.println(CYAN + "Legendary Bait: " + player.getItemCount("legendary bait") + RESET);

            System.out.println(YELLOW + "Where would you like to go?" + RESET);
            System.out.println("1. " + PURPLE + "Forest" + RESET + " (Requires: 1x Gold coin)");
            //System.out.println("2. " + PURPLE + "Mountains" + RESET + " (Requires: 10x Gold coin.)");
            //System.out.println("3. " + PURPLE + "South pole" + RESET + " (Requires: 100x Gold coin)");
            System.out.println("* " + RED + "Leave" + RESET);
            System.out.print(CYAN + "Enter your choice: " + RESET);

            choice = scanner.nextLine().toLowerCase().trim();

            switch (choice) {
                case "1", "forest" -> forest(player);
                case "2", "mountains" -> mountains(player);
                case "3", "southpole" -> southPole(player);
                case "*", "back", "leave", "exit" -> {
                    System.out.println(YELLOW + "You decide to stay home." + RESET);
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
            System.out.println(RED + BOLD + UNDERLINE + "Your venture deep in to the Lethal Forest begins.................." + RESET);


            // Implement adventureRng
            Map<String, Double> areaRates = new HashMap<>();
            areaRates.put("boss", 1.0);
            areaRates.put("abandon lookout", 2.0);
            areaRates.put("land for sale", 2.0);
            areaRates.put("scavenger hunt", 5.0);
            areaRates.put("merchant", 5.0);
            areaRates.put("random stranger", 5.0);
            areaRates.put("camp", 5.0);
            areaRates.put("wild dojo", 5.0);
            areaRates.put("wild animal", 5.0);
            areaRates.put("work", 5.0);
            areaRates.put("riddle", 5.0);
            areaRates.put("tree", 15.0);
            areaRates.put("fish", 15.0);
            areaRates.put("mine", 15.0);
            areaRates.put("fight", 10.0);

            String areaRate = player.mapRng(areaRates);

            switch (areaRate) {
                case "fight" -> combat.getMonsterAndFight(player);
                case "mine" -> mining.mine(player);
                case "fish" -> fishing.fish(player);
                case "riddle" -> riddle.playRiddle(player);
                case "wild dojo" -> wildDojo(player);

                case "wild animal" -> animal.wildAnimal(player);
                case "camp" -> camp.camp(player);

                case "tree" -> System.out.println("cut wood. crafting. bonfire for boosts?");
                case "random stranger" -> System.out.println("charisma check. recruit? rob(charisma * wisdom)?");
                case "merchant" -> System.out.println("rare items or better prices");
                case "scavenger hunt" -> System.out.println("clues, searching");
                case "work" -> System.out.println("do a job for someone/thing");
                case "land for sale" -> System.out.println("but land, makes passive income,");
                case "abandon lookout" -> System.out.println("climb for view? better area?");

                case "boss" -> System.out.println("boss fight"); //more rare

            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    private void mountains(Player player) {
    }

    private void southPole(Player player) {
    }

    public void wildDojo(Player player) {

        System.out.println(BRIGHT_PURPLE + "You've found a training dojo!" + RESET);
        System.out.println(BRIGHT_PURPLE + "Pay the trainers $1000 in hopes of gaining some valuable skills?" + RESET);
        System.out.println("yes/no .. 'info'");


        int roll = random.nextInt(Math.max(50, 100 - player.getWisdom()));
        String choice = scanner.nextLine().toLowerCase().trim();
        switch (choice) {
            case "y", "yes" -> {
                if (player.getMoney() >= 1000) {
                    player.setMoney(player.getMoney() - 1000);
                    if (roll <= 50) {
                        player.setSkillPoints(player.getSkillPoints() + 1);
                        System.out.println(GREEN + "You have actually managed to learn something!! " + RESET + "(+1 skill point)");
                        System.out.println(GREEN + "You rolled a " + roll + "!" + RESET);
                    } else {
                        System.out.println(RED + "You didn't listen to anything they said!" + RESET);
                        System.out.println(RED + "You rolled a " + roll + "!" + RESET);
                    }
                } else {
                    System.out.println(RED + "You don't have enough money!" + RESET);
                }
            }
            case "n", "no" ->
                    System.out.println(RED + "You leave the Dojo. Just as inexperienced as you came!" + RESET);
            case "info" -> {
                System.out.println(RED + "You have a 50% chance of gaining a skill point. (Boosted with wisdom)");
                wildDojo(player);
            }
            default -> System.out.println(RED + "Invalid choice." + RESET);

        }
    }
}
