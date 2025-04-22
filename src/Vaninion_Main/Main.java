package Vaninion_Main;

import Vaninion_Main.combat.Combat;
import Vaninion_Main.monsters.*;

import java.util.Scanner;
import static Vaninion_Main.ColoredConsole.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Shop shop = new Shop();
    private static final Fishing fishing = new Fishing();
    private static final Monster goblin = new Goblin();
    private static final Combat combat = new Combat();

    public static void main(String[] args) {

        // Get player name
        System.out.println(YELLOW + "Enter your character name: " + RESET);
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

        player.itemAndCounts.put("basic rod", 1);
        player.itemAndCounts.put("basic bait", 100);
        player.itemAndCounts.put("health potion", 10);
        player.itemAndCounts.put("mana potion", 10);
        player.itemAndCounts.put("strength potion", 10);
        player.itemAndCounts.put("defence potion", 10);


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
            System.out.println("* " + RED + "Exit Game" + RESET);


            //Game start
            String choice = scanner.nextLine().toLowerCase().trim();
            switch (choice) {

                case "1", "shop" -> shop.shop(player);
                case "2", "fishing" -> fishing.fish(player);
                case "3", "inventory", "inv", "i" ->  player.displayInventory();
                case "4", "attack" -> { Monster selectedMonster = combat.npc();
                    if (selectedMonster != null) {
                        combat.fight(player, selectedMonster);
                    }
                }
                case "*", "leave", "quit", "exit", "exit game" -> {
                    System.out.println(YELLOW + "Are you sure you want to exit? (yes/no)" + RESET);
                    String confirm = scanner.nextLine().toLowerCase().trim();
                    if (confirm.equals("yes")) {
                        System.out.println(GREEN + "Thank you for playing! Goodbye!" + RESET);
                        running = false;
                    }
                }
                default -> {
                    System.out.println(RED + "Invalid choice! Please try again." + RESET);

                }
            }
        }
    }
}