package Vaninion_Main;

import Vaninion_Main.monsters.Monster;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Map;

import static Vaninion_Main.ColoredConsole.*;
import Vaninion_Main.monsters.Monster;

public class Shop {

    Scanner scanner = new Scanner(System.in);

    public void shop(Player player) {
        System.out.println(YELLOW + "Welcome " + player.getName() + ", to the Rusty Caboose." + RESET);

        String choice;
        do {
            System.out.println(YELLOW + "current cash: $" + player.getMoney() + RESET);
            System.out.println(YELLOW + "What category would you like to browse?" + RESET);
            System.out.println("1. " + PURPLE + "Armour" + RESET);
            System.out.println("2. " + PURPLE + "Food" + RESET);
            System.out.println("3. " + PURPLE + "Misc" + RESET);
            System.out.println("4. " + PURPLE + "Info" + RESET);
            System.out.println("5. " + PURPLE + "Sell" + RESET);
            System.out.println("* " + RED + "Exit Shop" + RESET);
            System.out.print(CYAN + "Enter your choice: " + RESET);

            choice = scanner.nextLine().toLowerCase().trim();

            switch (choice) {
                case "1", "armour" -> browseArmour(player);
                case "2", "food" -> browseFood(player);
                case "3", "misc" -> browseMisc(player);
                case "4", "info" -> shopInfo(player);
                case "5", "sell" -> sell();
                case "*", "exit shop", "exit", "back", "leave" -> System.out.println(YELLOW + "Thanks for visiting the Rusty Caboose!" + RESET);
                default -> System.out.println(RED + "Invalid category choice." + RESET);
            }
        } while (true);
    }

    private void shopInfo(Player player) {
        System.out.println(YELLOW + "What would you like to know?" + RESET);
        scanner.nextLine();
        System.out.println(RED + BOLD + UNDERLINE +
                "You want to know too much...Get out!" + RESET);
    }

    private void browseArmour(Player player) {
        System.out.println(YELLOW + "\n--- Armour ---" + RESET);
        while (true) {
            System.out.println(YELLOW + "Your current cash: $" + player.getMoney() + RESET);

            System.out.println("1. " + PURPLE + "Steel Helmet" + RESET + " - $" + GREEN + 300 + RESET + (player.itemAndCounts.getOrDefault("steel helmet", 0) > 0 ? " (" + player.itemAndCounts.get("steel helmet") + " Owned)" : ""));
            System.out.println("2. " + PURPLE + "Steel Platebody" + RESET + " - $" + GREEN + 800 + RESET + (player.itemAndCounts.getOrDefault("steel platebody", 0) > 0 ? " (" + player.itemAndCounts.get("steel platebody") + " Owned)" : ""));
            System.out.println("3. " + PURPLE + "Steel Platelegs" + RESET + " - $" + GREEN + 700 + RESET + (player.itemAndCounts.getOrDefault("steel platelegs", 0) > 0 ? " (" + player.itemAndCounts.get("steel platelegs") + " Owned)" : ""));
            System.out.println("4. " + PURPLE + "Steel Shield" + RESET + " - $" + GREEN + 400 + RESET + (player.itemAndCounts.getOrDefault("steel shield", 0) > 0 ? " (" + player.itemAndCounts.get("steel shield") + " Owned)" : ""));
            System.out.println("5. " + PURPLE + "Steel Long Sword" + RESET + " - $" + GREEN + 600 + RESET + (player.itemAndCounts.getOrDefault("steel long sword", 0) > 0 ? " (" + player.itemAndCounts.get("steel long sword") + " Owned)" : ""));
            System.out.println("6. " + PURPLE + "Armour Package (Helmet, Body, Legs, Shield, Sword)" + RESET + " - $" + GREEN + 2500 + RESET);
            System.out.println("7. " + RED + "Back to Shop Categories" + RESET);
            System.out.print(CYAN + BOLD + "Enter the " + RESET + "number " + CYAN + BOLD + "of the item you wish to purchase: " + RESET);

            String armourChoice = scanner.nextLine().trim();

            try {
                switch (armourChoice) {
                    case "1": // Steel Helmet
                        buyArmour(player, "steel helmet", 300);
                        break;
                    case "2": // Steel Platebody
                        buyArmour(player, "steel platebody", 800);
                        break;
                    case "3": // Steel Platelegs
                        buyArmour(player, "steel platelegs", 700);
                        break;
                    case "4": // Steel Shield
                        buyArmour(player, "steel shield", 400);
                        break;
                    case "5": // Steel Long Sword
                        buyArmour(player, "steel long sword", 600);
                        break;
                    case "6": // Armour Package
                        buyArmourPackage(player);
                        break;
                    case "7", "back", "leave", "exit": // Back
                        return;
                    default:
                        System.out.println(RED + "Invalid choice." + RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid input. Please enter a number." + RESET);
            }
        }
    }

    private void buyArmourPackage(Player player) {
        int packagePrice = 2500;
        if (player.getMoney() >= packagePrice) {
            player.setMoney(player.getMoney() - packagePrice);
            player.addItem("steel helmet", 1);
            player.addItem("steel platebody", 1);
            player.addItem("steel platelegs", 1);
            player.addItem("steel shield", 1);
            player.addItem("steel long sword", 1);
            System.out.println(GREEN + "You bought the Armour Package for $" + YELLOW + packagePrice + GREEN + ". You now have:" + RESET);
            System.out.println(YELLOW + "  - Steel Helmet" + GREEN + ": " + player.getItemCount("steel helmet"));
            System.out.println(YELLOW + "  - Steel Platebody" + GREEN + ": " + player.getItemCount("steel platebody"));
            System.out.println(YELLOW + "  - Steel Platelegs" + GREEN + ": " + player.getItemCount("steel platelegs"));
            System.out.println(YELLOW + "  - Steel Shield" + GREEN + ": " + player.getItemCount("steel shield"));
            System.out.println(YELLOW + "  - Steel Long Sword" + GREEN + ": " + player.getItemCount("steel long sword") + RESET);
            System.out.println(GREEN + "Your remaining cash: $" + YELLOW + player.getMoney() + RESET);
        } else {
            System.out.println(RED + "Not enough money for the Armour Package!" + RESET);
        }
    }

    private void buyArmour(Player player, String itemName, int price) {
        String normalizedItemName = itemName.toLowerCase();
        if (player.getMoney() >= price) {
            player.setMoney(player.getMoney() - price);
            player.addItem(normalizedItemName, 1);
            System.out.println(GREEN + "You bought a " + PURPLE + itemName + GREEN + " for $" + YELLOW + price + GREEN + ". You now have $" + YELLOW + player.getMoney() + GREEN + "." + RESET);
        } else {
            System.out.println(RED + "Not enough money for the " + itemName + "!" + RESET);
        }
    }


    private void browseFood(Player player) {
        System.out.println(YELLOW + "\n--- Food ---" + RESET);
        while (true) {
            System.out.println(YELLOW + "Your current cash: $" + player.getMoney() + RESET);
            System.out.println("1. " + PURPLE + "Beans" + RESET + " - $" + GREEN + 100 + RESET + (player.itemAndCounts.getOrDefault("beans", 0) > 0 ? " (" + player.itemAndCounts.get("beans") + " Owned)" : ""));
            System.out.println("2. " + PURPLE + "Tacos" + RESET + " - $" + GREEN + 100 + RESET + (player.itemAndCounts.getOrDefault("tacos", 0) > 0 ? " (" + player.itemAndCounts.get("tacos") + " Owned)" : ""));
            System.out.println("3. " + RED + "Back to Shop Categories" + RESET);
            System.out.print(CYAN + BOLD + "Enter the number of the food you wish to purchase: " + RESET);

            String foodChoice = scanner.nextLine().trim().toLowerCase();

            try {
                switch (foodChoice) {
                    case "1": // Beans
                        buyItem(player, "beans", 100);
                        break;
                    case "2": // Tacos
                        buyItem(player, "tacos", 100);
                        break;
                    case "3", "back", "leave", "exit":
                        return;
                    default:
                        System.out.println(RED + "Invalid choice." + RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid input. Please enter a number." + RESET);
            }
        }
    }

    private void buyItem(Player player, String itemName, int price) {
        System.out.println("How many " + itemName + " would you like to buy?");
        try {
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
            int cost = quantity * price;
            if (player.getMoney() >= cost) {
                player.setMoney(player.getMoney() - cost);
                player.addItem(itemName.toLowerCase(), quantity);
                System.out.println(GREEN + "You bought " + YELLOW + quantity + GREEN + " " + PURPLE + itemName + "(s) for " + RESET + "$" + cost + GREEN + ". You now have" + RESET + " $" + player.getMoney() + GREEN + "." + RESET);
            } else {
                System.out.println(RED + "Not enough money!" + RESET);
            }
        } catch (InputMismatchException e) {
            System.out.println(RED + "Invalid input." + RESET);
            scanner.nextLine(); // Consume the invalid input
        }
    }


    private void browseMisc(Player player) {
        System.out.println(YELLOW + "\n--- Miscellaneous ---" + RESET);
        while (true) {
            System.out.println(YELLOW + "Your current cash: $" + player.getMoney() + RESET);

            System.out.println("1. " + PURPLE + "Calculator" + RESET + " - $" + GREEN + 2000 + RESET + (player.itemAndCounts.containsKey("calculator") ? " (Owned)" : ""));
            System.out.println("2. " + PURPLE + "Basic Rod" + RESET + " - $" + GREEN + 1000 + RESET + (player.itemAndCounts.containsKey("basic rod") ? " (Owned)" : ""));
            System.out.println("3. " + PURPLE + "Basic bait" + RESET + " - $" + GREEN + 50 + RESET + (player.itemAndCounts.getOrDefault("basic bait", 0) > 0 ? " (" + player.itemAndCounts.get("basic bait") + " Owned)" : ""));
            System.out.println("4. " + PURPLE + "Super bait" + RESET + " - $" + GREEN + 100 + RESET + (player.itemAndCounts.getOrDefault("super bait", 0) > 0 ? " (" + player.itemAndCounts.get("super bait") + " Owned)" : ""));
            System.out.println("5. " + RED + "Back to Shop Categories" + RESET);
            System.out.print(CYAN + "Enter the number of the item you wish to purchase: " + RESET);

            String miscChoice = scanner.nextLine().trim().toLowerCase();

            try {
                switch (miscChoice) {
                    case "1": // Calculator
                        buyCalculator(player, 2000);
                        break;
                    case "2":
                        buyRod(player, 1000);
                        break;
                    case "3":
                        buyBait(player, "basic bait", 500);
                        break;
                    case "4":
                        buyBait(player, "super bait", 100);
                        break;

                    case "5", "exit", "back", "leave": // Back
                        return;
                    default:
                        System.out.println(RED + "Invalid choice." + RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid input. Please enter a number." + RESET);
            }
        }
    }

    private void buyCalculator(Player player, int price) {
        if (!player.hasItem("calculator")) {
            if (player.getMoney() >= price) {
                player.setMoney(player.getMoney() - price);
                player.addItem("calculator", 1);
                System.out.println(GREEN + "You purchased a " + PURPLE + "Fart jump calc" + GREEN + " for $" + YELLOW + price + GREEN + ". You now have $" + YELLOW + player.getMoney() + GREEN + ". Jump without consequences!!" + RESET);
            } else {
                System.out.println(RED + "Not enough money for the calculator!" + RESET);
            }
        } else {
            System.out.println(YELLOW + "You already own the calculator." + RESET);
        }
    }


    private void buyRod(Player player, int price) {
        if (!player.hasItem("basic rod")) {
            if (player.getMoney() >= price) {
                player.setMoney(player.getMoney() - price);
                player.addItem("basic rod", 1);
                System.out.println(GREEN + "You purchased a " + PURPLE + "basic Rod" + GREEN + " for $" + YELLOW + price + GREEN + ". You now have $" + YELLOW + player.getMoney() + GREEN + "." + RESET);
            } else {
                System.out.println(RED + "Not enough money for the rod!" + RESET);
            }
        } else {
            System.out.println(YELLOW + "You already own a Basic Rod." + RESET);
        }
    }


    private void buyBait(Player player, String itemName, int price) {
        System.out.println("How many " + itemName + " would you like to buy?");
        try {
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
            int cost = quantity * price;
            if (player.getMoney() >= cost) {
                player.setMoney(player.getMoney() - cost);
                player.addItem(itemName, quantity);
                System.out.println(GREEN + "You bought " + YELLOW + quantity + GREEN + " " + PURPLE + itemName + "(s) for " + RESET + "$" + cost + GREEN + ". You now have" + RESET + " $" + player.getMoney() + GREEN + "." + RESET);
            } else {
                System.out.println(RED + "Not enough money!" + RESET);
            }
        } catch (InputMismatchException e) {
            System.out.println(RED + "Invalid input." + RESET);
            scanner.nextLine(); // Consume the invalid input
        }
    }
    private void sell() {
        //for (String drop : possibleDrops);
    }
}