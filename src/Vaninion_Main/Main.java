package Vaninion_Main;

import Vaninion_Main.adventure.Adventure;
import Vaninion_Main.adventure.Mining;
import Vaninion_Main.adventure.Riddle;
import Vaninion_Main.combat.Combat;
import Vaninion_Main.dojo.Dojo;
import Vaninion_Main.monsters.*;
import Vaninion_Main.player.Human;
import Vaninion_Main.player.Ork;
import Vaninion_Main.player.Player;
import Vaninion_Main.player.Viking;

import java.util.Scanner;
import static Vaninion_Main.ColoredConsole.*;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Shop shop = new Shop();
    private static final Mining mining = new Mining();
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
        player.addItem("gold coin", 100);
        player.addItem("health potion", 1);

        player.setSkillPoints(3);

        // MUST REMOVE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

        // Welcome message
        System.out.println(GREEN + "\nWelcome, " + PURPLE + player.getName() +
                GREEN + " the " + PURPLE + player.getClass().getSimpleName() +
                GREEN + "!" + RESET);
        boolean running = true;
        while (running) {
            // Create a decorative header
            System.out.println(BLUE + BOLD + "\n╔════════════════════════════════╗");
            System.out.println("║" + YELLOW + "            MAIN MENU           " + BLUE + "║");
            System.out.println("╠════════════════════════════════╣" + RESET);

            // Combat & Adventure section
            System.out.println(BLUE + "║" + PURPLE + " Combat & Adventure:" + BLUE + "            ║");
            System.out.println("║ " + PURPLE + "1. Attack" + BLUE + "                      ║");
            System.out.println("║ " + PURPLE + "2. Adventure" + BLUE + "                   ║");
            System.out.println("║ " + PURPLE + "3. Riddle" + BLUE + "                      ║");

            // Skills section
            System.out.println("╟────────────────────────────────╢");
            System.out.println("║" + PURPLE + " Skills & Activities:" + BLUE + "           ║");
            System.out.println("║ " + PURPLE + "4. Fishing" + BLUE + "                     ║");
            System.out.println("║ " + PURPLE + "5. Mining" + BLUE + "                      ║");
            System.out.println("║ " + PURPLE + "6. Dojo" + BLUE + "                        ║");

            // Character Management section
            System.out.println("╟────────────────────────────────╢");
            System.out.println("║" + PURPLE + " Character Management:" + BLUE + "          ║");
            System.out.println("║ " + PURPLE + "7. Stats" + BLUE + "                       ║");
            System.out.println("║ " + PURPLE + "8. Level Up" + BLUE + "                    ║");
            System.out.println("║ " + PURPLE + "9. Inventory" + BLUE + "                   ║");

            // Shop & Exit section
            System.out.println("╟────────────────────────────────╢");
            System.out.println("║ " + PURPLE + "S. Shop" + BLUE + "                        ║");
            System.out.println("║ " + RED + "E. Exit Game" + BLUE + "                   ║");
            System.out.println("╚════════════════════════════════╝" + RESET);

            // Quick commands hint
            System.out.println(CYAN + "Quick commands: 'reg' for regular inventory, 'res' for resources" + RESET);

            String choice = scanner.nextLine().toLowerCase().trim();
            switch (choice) {
                case "1", "attack" -> combat.getMonsterAndFight(player);
                case "2", "adventure" -> System.out.println("Adventure not yet implemented.");
                case "3", "riddle" -> riddle.playRiddle(player);
                case "4", "fishing" -> fishing.fish(player);
                case "5", "mining", "mine" -> mining.mine(player);
                case "6", "dojo" -> new Dojo().enterDojo(player);
                case "7", "stats" -> player.stats();
                case "8", "level", "level up" -> player.useSkillPoint();
                case "9", "inventory", "inv", "i" -> player.displayInventory();
                case "s", "shop" -> shop.shop(player);
                case "e", "*", "exit", "quit" -> {
                    System.out.println(YELLOW + "Are you sure you want to exit? (yes/no)" + RESET);
                    String confirm = scanner.nextLine().toLowerCase().trim();
                    if (confirm.equals("yes")) {
                        System.out.println(GREEN + "Thank you for playing! Goodbye!" + RESET);
                        running = false;
                    }
                }
                // Quick access commands
                case "reg", "regular" -> player.displayRegularInventory();
                case "res", "resource" -> player.displayResourceInventory();
                default -> System.out.println(RED + "Invalid choice! Please try again." + RESET);
            }
        }
    }
}