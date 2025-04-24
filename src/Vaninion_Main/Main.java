package Vaninion_Main;

import Vaninion_Main.adventure.Adventure;
import Vaninion_Main.adventure.Riddle;
import Vaninion_Main.combat.Combat;
import Vaninion_Main.dojo.Dojo;
import Vaninion_Main.monsters.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import static Vaninion_Main.ColoredConsole.*;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Shop shop = new Shop();
    private static final Fishing fishing = new Fishing();
    private static final Monster goblin = new Goblin();
    private static final Combat combat = new Combat();
    private static final Adventure adventure = new Adventure();
    private static final Riddle riddle = new Riddle();

    public static void main(String[] args) {
        System.out.println(BOLD + BLUE + "~~~ Welcome to Vaninion ~~~" + RESET);
        System.out.println(GREEN + BOLD + "Best of luck on your adventures!" + RESET);
        // Get player name
        System.out.println(YELLOW + "\nEnter your character name: " + RESET);
        String name = "Ragnorok"; //scanner.nextLine().trim();

        // Character selection
        Player player = null;
        while (player == null) {
            System.out.println(YELLOW + "\nChoose your class:" + RESET);
            System.out.println("1. " + PURPLE + "Human " + GREEN + "(Bonus: Diplomacy)" + RESET);
            System.out.println("2. " + PURPLE + "Ork " + GREEN + "(Bonus: Strength & Health)" + RESET);
            System.out.println("3. " + PURPLE + "Viking " + GREEN + "(Bonus: Berserk Power)" + RESET);

            String choice = "1";   //scanner.nextLine().toLowerCase().trim();

            player = switch (choice) {
                case "1", "human" -> new Human(name);
                case "2", "ork" -> new Ork(name);
                case "3", "viking" -> new Viking(name);
                default -> {
                    System.out.println(RED + "Invalid choice! Please try again." + RESET);
                    yield null;
                }
            };
        }
        // MUST REMOVE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

        player.addItem("basic rod", 1);
        player.addItem("basic bait", 100);
        player.addItem("health potion", 1);

        player.setSkillPoints(3);


        // MUST REMOVE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

        // Welcome message
        System.out.println(GREEN + "\nWelcome, " + PURPLE + player.getName() +
                GREEN + " the " + PURPLE + player.getClass().getSimpleName() +
                GREEN + "!" + RESET);
        boolean running = true;
        while (running) {
            System.out.println(YELLOW + "\nWhat would you like to do?" + RESET);
            System.out.println("1. " + PURPLE + "Shop" + RESET);
            System.out.println("2. " + PURPLE + "Fishing" + RESET);
            System.out.println("3. " + PURPLE + "Inventory" + RESET);
            System.out.println("4. " + PURPLE + "Attack" + RESET);
            System.out.println("5. " + PURPLE + "Adventure" + RESET);
            System.out.println("6. " + PURPLE + "Stats" + RESET);
            System.out.println("7. " + PURPLE + "Riddle" + RESET);
            System.out.println("8. " + PURPLE + "Level Up" + RESET);
            System.out.println("9. " + PURPLE + "Dojo" + RESET);
            System.out.println("* " + RED + "Exit Game" + RESET);
            System.out.println();



            // Game start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            String choice = scanner.nextLine().toLowerCase().trim();
            switch (choice) {


                case "1", "shop" -> shop.shop(player);
                case "2", "fishing" -> fishing.fish(player);
                case "3", "inventory", "inv", "i" ->  player.displayInventory();
                case "4", "attack" -> combat.getMonsterAndFight(player);
                case "5", "adventure" -> {
                    System.out.println("Adventure not yet implemented."); // Break adventure down into categories? fishing/scavenge(riddle)? battle?
                }
                case "6", "stats" -> player.stats();
                case "7", "riddle" -> riddle.playRiddle(player);
                case "8", "level up" -> player.useSkillPoint();
                case "9", "dojo" -> new Dojo().enterDojo(player);
                case "*", "leave", "quit", "exit", "exit game" -> {
                    System.out.println(YELLOW + "Are you sure you want to exit? (yes/no)" + RESET);
                    String confirm = scanner.nextLine().toLowerCase().trim();
                    if (confirm.equals("yes")) {
                        System.out.println(GREEN + "Thank you for playing! Goodbye!" + RESET);
                        running = false;
                    }
                }
                // Extras
                case "regular", "reg" -> player.displayRegularInventory();
                case "resource", "res" -> player.displayResourceInventory();
                default -> {
                    System.out.println(RED + "Invalid choice! Please try again." + RESET);

                }
            }
        }
    }
}