package Vaninion_Main;

import Vaninion_Main.monsters.Monster;

import java.util.*;

import static Vaninion_Main.ColoredConsole.*;
import Vaninion_Main.monsters.Monster;

public class Shop {

    Scanner scanner = new Scanner(System.in);
    public static Map<String, Integer> shopItems = new HashMap<>();

    public void shop(Player player) {

        Map<String, Integer> shopItems = new HashMap<>();
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
                case "5", "sell" -> sell(player);
                case "*", "exit shop", "exit", "back", "leave" -> {
                    System.out.println(YELLOW + "Thanks for visiting the Rusty Caboose!" + RESET);
                    return;
                }
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

            System.out.println("1. " + PURPLE + "Steel Helmet" + RESET + " - $" + GREEN + 300 + RESET + (player.inventory.getOrDefault("steel helmet", 0) > 0 ? " (" + player.inventory.get("steel helmet") + " Owned)" : ""));
            System.out.println("2. " + PURPLE + "Steel Platebody" + RESET + " - $" + GREEN + 800 + RESET + (player.inventory.getOrDefault("steel platebody", 0) > 0 ? " (" + player.inventory.get("steel platebody") + " Owned)" : ""));
            System.out.println("3. " + PURPLE + "Steel Platelegs" + RESET + " - $" + GREEN + 700 + RESET + (player.inventory.getOrDefault("steel platelegs", 0) > 0 ? " (" + player.inventory.get("steel platelegs") + " Owned)" : ""));
            System.out.println("4. " + PURPLE + "Steel Shield" + RESET + " - $" + GREEN + 400 + RESET + (player.inventory.getOrDefault("steel shield", 0) > 0 ? " (" + player.inventory.get("steel shield") + " Owned)" : ""));
            System.out.println("5. " + PURPLE + "Steel Long Sword" + RESET + " - $" + GREEN + 600 + RESET + (player.inventory.getOrDefault("steel long sword", 0) > 0 ? " (" + player.inventory.get("steel long sword") + " Owned)" : ""));
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
            System.out.println("1. " + PURPLE + "Beans" + RESET + " - $" + GREEN + 100 + RESET + (player.inventory.getOrDefault("beans", 0) > 0 ? " (" + player.inventory.get("beans") + " Owned)" : ""));
            System.out.println("2. " + PURPLE + "Tacos" + RESET + " - $" + GREEN + 100 + RESET + (player.inventory.getOrDefault("tacos", 0) > 0 ? " (" + player.inventory.get("tacos") + " Owned)" : ""));
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

            System.out.println("1. " + PURPLE + "Calculator" + RESET + " - $" + GREEN + 2000 + RESET + (player.inventory.containsKey("calculator") ? " (Owned)" : ""));
            System.out.println("2. " + PURPLE + "Basic Rod" + RESET + " - $" + GREEN + 1000 + RESET + (player.inventory.containsKey("basic rod") ? " (Owned)" : ""));
            System.out.println("3. " + PURPLE + "Basic bait" + RESET + " - $" + GREEN + 50 + RESET + (player.inventory.getOrDefault("basic bait", 0) > 0 ? " (" + player.inventory.get("basic bait") + " Owned)" : ""));
            System.out.println("4. " + PURPLE + "Super bait" + RESET + " - $" + GREEN + 100 + RESET + (player.inventory.getOrDefault("super bait", 0) > 0 ? " (" + player.inventory.get("super bait") + " Owned)" : ""));
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
    private void sell(Player player) {
        while (true) {
            System.out.println(YELLOW + "\n--- Sell Items ---" + RESET);
            System.out.println("Your current cash: $" + player.getMoney());

            // Show player's inventory with prices
            boolean hasItems = false;
            for (Map.Entry<String, Integer> entry : player.inventory.entrySet()) {
                String item = entry.getKey();
                int quantity = entry.getValue();
                if (quantity > 0 && shopItems.containsKey(item)) {
                    hasItems = true;
                    int price = shopItems.get(item);
                    System.out.println(PURPLE + item + RESET + " (x" + quantity + ") - Worth: $" + GREEN + price + RESET + " each");
                }
            }

            if (!hasItems) {
                System.out.println(RED + "You have no items to sell!" + RESET);
                return;
            }

            System.out.println("\n" + YELLOW + "Enter item name or 'sell all' (or 'back' to exit):" + RESET);
            String choice = scanner.nextLine().toLowerCase().trim();
            if (choice.equals("sell all")) {
                sellAll(player);
                return;
            }
            if (choice.equals("back") || choice.equals("exit")) {
                return;
            }

            if (player.hasItem(choice) && shopItems.containsKey(choice)) {
                System.out.println("How many would you like to sell?");
                try {
                    int quantity = Integer.parseInt(scanner.nextLine().trim());
                    if (quantity > 0 && quantity <= player.getItemCount(choice)) {
                        int price = shopItems.get(choice) * quantity;
                        player.removeItem(choice, quantity);
                        player.setMoney(player.getMoney() + price);
                        System.out.println(GREEN + "Sold " + quantity + " " + choice + " for $" + price +
                                ". You now have $" + player.getMoney() + RESET);
                    } else {
                        System.out.println(RED + "Invalid quantity!" + RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(RED + "Invalid number!" + RESET);
                }
            } else {
                System.out.println(RED + "Invalid item or you don't have any to sell!" + RESET);
            }
        }
    }
    public void sellAll(Player player) {
        int totalSale = 0;
        List<String> itemsToRemove = new ArrayList<>();  // Track items to remove after iteration

        // Go through player's inventory
        for (Map.Entry<String, Integer> entry : player.getInventory().entrySet()) {
            String item = entry.getKey();
            int quantity = entry.getValue();

            if (shopItems.containsKey(item)) {  // Check if shop buys this item
                int pricePerItem = shopItems.get(item);
                int goldForItems = pricePerItem * quantity;
                totalSale += goldForItems;
                itemsToRemove.add(item);
            }
        }

        // Remove sold items and give gold to player
        for (String item : itemsToRemove) {
            player.getInventory().remove(item);
        }
        player.setMoney(player.getMoney() + totalSale);

        // Inform player
        if (totalSale > 0) {
            System.out.println(RED + "Sold all items for " + RESET + "$"  + GREEN + totalSale + RESET);
        } else {
            System.out.println("No items to sell!");
        }
    }

}