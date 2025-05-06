package vaninion;

import vaninion.adventure.*;
import vaninion.combat.Combat;
import vaninion.combat.equipment.EquipmentManager;
import vaninion.dojo.Dojo;
import vaninion.monsters.*;
import vaninion.players.*;
import vaninion.players.player.Human;
import vaninion.players.player.Ork;
import vaninion.players.player.Viking;
import java.util.List;

import java.util.Scanner;
import static vaninion.ColoredConsole.*;


public class Game {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Shop shop = new Shop();
    private static final Combat combat = new Combat();
    private static final Area area = new Area();
    private static final Animal animal = new Animal();
    private static final Camp camp = new Camp();
    private static final Tree tree = new Tree();





    public static void main(String[] args) {
        // Initialize save system
        SaveGame.initialize();

        System.out.println(BOLD + BLUE + "~~~ Welcome to Vaninion ~~~" + RESET);

        System.out.println(GREEN + BOLD + "Best of luck on your adventures!" + RESET);
        // Get player name
        System.out.println(YELLOW + "\nEnter your character name: " + RESET);

        String name = "Ragnorok"; //scanner.nextLine().trim();
        //String name = scanner.nextLine();
        if (name.equals("hannah")) {
            while (true) {
                try {
                    System.out.println(BRIGHT_PURPLE + BOLD + "AND THEN YOU LAID AN EGG!!!" + RESET);
                    Thread.sleep(200);
                    System.out.println(BRIGHT_PURPLE_BACKGROUND + BRIGHT_BLACK+ BOLD + "AND THEN YOU LAID AN EGG!!!" + RESET);
                    Thread.sleep(100);
                }catch (Exception e) {}
            }
        }
        // Character selection
        Player player = null;
        while (player == null) {
            System.out.println(YELLOW + "\nChoose your class:" + RESET);
            System.out.println("1. " + PURPLE + "Human " + GREEN + "(Bonus: a few extra brain cells)" + RESET);
            System.out.println("2. " + PURPLE + "Ork " + GREEN + "(Bonus: Thick skin)" + RESET);
            System.out.println("3. " + PURPLE + "Viking " + GREEN + "(Bonus: Berserk Power)" + RESET);

            //String choice = scanner.nextLine().toLowerCase().trim();
            String choice = "3";

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
        player.addItem("starter helmet", 1);

        // MUST REMOVE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


        // MUST REMOVE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

        // Welcome message
        System.out.println(GREEN + "\nWelcome, " + PURPLE + player.getName() +
                GREEN + " the " + PURPLE + player.getClass().getSimpleName() +
                GREEN + "!" + RESET);
        boolean running = true;
        while (running) {
            updateRewards(player);

            System.out.println();
            // Create a decorative header
            System.out.println(BLUE + BOLD + "\n╔════════════════════════════════╗");
            System.out.println("║" + YELLOW + "            MAIN MENU           " + BLUE + "║");
            System.out.println("╠════════════════════════════════╣" + BLUE);

            // Combat & Adventure section
            System.out.println("║ " + PURPLE + "1. Attack" + BLUE + "                      ║");
            System.out.println("║ " + PURPLE + "2. Adventure" + BLUE + "                   ║");
            System.out.println("║ " + PURPLE + "3. Shop" + BLUE + "                        ║");
            System.out.println("║ " + PURPLE + "4. Dojo" + BLUE + "                        ║");

            // Character Management section
            System.out.println("╟────────────────────────────────╢");
            System.out.println("║" + PURPLE + " Character Management:" + BLUE + "          ║");
            System.out.println("║ " + PURPLE + "5. Stats" + BLUE + "                       ║");
            System.out.println("║ " + PURPLE + "6. Level Up" + BLUE + "                    ║");
            System.out.println("║ " + PURPLE + "7. Equipment" + BLUE + "                   ║");

            // Shop & Save/Load section
            System.out.println("╟────────────────────────────────╢");
            System.out.println("║ " + PURPLE + "S. Save Game" + BLUE + "                   ║");
            System.out.println("║ " + PURPLE + "L. Load Game" + BLUE + "                   ║");
            System.out.println("║ " + RED + "E. Exit Game" + BLUE + "                   ║");
            System.out.println("╚════════════════════════════════╝" + RESET);

            // Quick commands hint
            System.out.println(CYAN + "'inv', 'res', 'armour', for inventories" + RESET);

            String choice = scanner.nextLine().toLowerCase().trim();
            switch (choice) {
                case "1", "attack" -> combat.getMonsterAndFight(player);
                case "2", "adventure" -> area.areas(player);
                case "3", "shop" -> shop.shop(player);
                case "4", "dojo" -> new Dojo().enterDojo(player);


                case "5", "stats" -> player.stats();
                case "6", "level", "level up" -> player.useSkillPoint();
                case "732", "equipment", "equip" -> equipMenu(player);

                case "s", "save" -> saveGame(player);
                case "l", "load" -> {
                    Player loadedPlayer = loadGame();
                    if (loadedPlayer != null) {
                        player = loadedPlayer;
                    }
                }
                case "e", "*", "exit", "quit" -> {
                    System.out.println(YELLOW + "Are you sure you want to exit? (yes/no)" + RESET);
                    String confirm = scanner.nextLine().toLowerCase().trim();
                    if (confirm.equals("yes")) {
                        System.out.println(GREEN + "Thank you for playing! Goodbye!" + RESET);
                        running = false;
                    }
                }
                // Quick access commands
                case "inv", "inventory" -> player.displayRegularInventory();
                case "res", "resource" -> player.displayResourceInventory();
                case "armour" -> player.displayArmourInventory();
                case "barn", "animals", "animal" -> player.displayAnimalInventory();


                // Dev commands
                case "t" -> tree.tree(player);
                case "gnome" -> tree.shakeGnome(player);
                case "a" -> animal.addSheep(player);
                case "c" -> camp.camp(player);
                case "money" -> {
                    player.setMoney(player.getMoney() + 1_000_000);
                    System.out.println("money: " + player.getMoney());
                }
                case "items" -> {
                    player.addItem("dragon sword", 1);
                    player.addItem("dragon chestplate", 1);
                    player.addItem("gold coin", 1_000_000);
                    player.addItem("basic rod", 1);
                    player.addItem("basic bait", 1_000_000);
                    player.addItem("legendary bait", 1_000_000);
                    player.addItem("axe", 1);
                }
                case "lvls" -> {
                    player.setWisdom(player.getWisdom() + 10);
                    player.setStrength(player.getStrength() + 10);
                }

                default -> System.out.println(RED + "Invalid choice! Please try again." + RESET);
            }
        }
    }


    /**
     * Save the current game state
     * @param player The player to save
     */
    private static void saveGame(Player player) {
        System.out.println(YELLOW + "\n=== Save Game ===" + RESET);
        System.out.println("Saving game for " + PURPLE + player.getName() + RESET + "...");

        if (SaveGame.saveGame(player)) {
            System.out.println(GREEN + "Game saved successfully!" + RESET);
        } else {
            System.out.println(RED + "Failed to save game." + RESET);
        }
    }

    /**
     * Load a saved game
     * @return The loaded player, or null if loading failed
     */
    private static Player loadGame() {
        System.out.println(YELLOW + "\n=== Load Game ===" + RESET);

        List<String> saveFiles = SaveGame.getSaveFiles();

        if (saveFiles.isEmpty()) {
            System.out.println(RED + "No save files found." + RESET);
            return null;
        }

        System.out.println("Available save files:");
        for (int i = 0; i < saveFiles.size(); i++) {
            System.out.println((i + 1) + ". " + PURPLE + saveFiles.get(i) + RESET);
        }
        System.out.println("*. " + RED + "Back" + RESET);

        System.out.print("Enter the number of the save file to load: ");
        String choice = scanner.nextLine().trim();

        if (choice.equals("*") || choice.equalsIgnoreCase("back")) {
            return null;
        }

        try {
            int index = Integer.parseInt(choice) - 1;
            if (index >= 0 && index < saveFiles.size()) {
                String saveName = saveFiles.get(index);
                System.out.println("Loading save file: " + PURPLE + saveName + RESET);
                return SaveGame.loadGame(saveName);
            } else {
                System.out.println(RED + "Invalid save file number." + RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(RED + "Please enter a valid number." + RESET);
        }

        return null;
    }

    public static void equipMenu(Player player) {
        boolean equipMenu = true;
        while (equipMenu) {
            System.out.println(YELLOW + "\n=== Equipment Menu ===" + RESET);
            System.out.println("1. " + PURPLE + "Equip Items" + RESET);
            System.out.println("2. " + PURPLE + "View Equipment Status" + RESET);
            System.out.println("*. " + RED + "Back" + RESET);

            String equipChoice = scanner.nextLine().toLowerCase().trim();
            switch (equipChoice) {
                case "1", "equip" -> {
                    boolean equippingItems = true;
                    while (equippingItems) {
                        player.displayArmourInventory();
                        System.out.println(GREEN + "Enter the name of the item to equip (or 'back' to return):" + RESET);
                        String itemToEquip = scanner.nextLine().toLowerCase().trim();
                        if (itemToEquip.equals("back")) {
                            equippingItems = false;
                        } else {
                            player.equipItem(itemToEquip);
                            System.out.println(YELLOW + "Would you like to equip another item? (y/n)" + RESET);
                            String continueEquipping = scanner.nextLine().toLowerCase().trim();
                            if (!continueEquipping.equals("y")) {
                                equippingItems = false;
                            }
                        }
                    }
                }
                case "2", "status" -> System.out.println(player.getEquipmentStatus());
                case "*", "back" -> equipMenu = false;
                default -> System.out.println(RED + "Invalid choice!" + RESET);
            }
        }
    }
    public static void updateRewards(Player player) {

        System.out.println(GREEN + BOLD + "\nrewards");
        Animal.animalRewards(player);




    }

}
