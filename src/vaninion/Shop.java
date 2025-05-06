package vaninion;

import java.util.*;

import static vaninion.ColoredConsole.*;

import vaninion.players.Player;

public class Shop {

    Scanner scanner = new Scanner(System.in);
    public static Map<String, Integer> shopItems = new HashMap<>();

    public void shop(Player player) {

        customSellItems();

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

            // Iron Gear
            System.out.println(BLUE + "\n=== Iron Gear ===" + RESET);
            System.out.println("1. " + PURPLE + "iron Helmet" + RESET + " - $" + GREEN + 100 + RESET + (player.inventory.getOrDefault("iron helmet", 0) > 0 ? " (" + player.inventory.get("iron helmet") + " Owned)" : ""));
            System.out.println("2. " + PURPLE + "iron chestplate" + RESET + " - $" + GREEN + 150 + RESET + (player.inventory.getOrDefault("iron chestplate", 0) > 0 ? " (" + player.inventory.get("iron chestplate") + " Owned)" : ""));
            System.out.println("3. " + PURPLE + "iron leggings" + RESET + " - $" + GREEN + 100 + RESET + (player.inventory.getOrDefault("iron leggings", 0) > 0 ? " (" + player.inventory.get("iron leggings") + " Owned)" : ""));
            System.out.println("4. " + PURPLE + "iron Shield" + RESET + " - $" + GREEN + 100 + RESET + (player.inventory.getOrDefault("iron shield", 0) > 0 ? " (" + player.inventory.get("iron shield") + " Owned)" : ""));
            System.out.println("5. " + PURPLE + "iron Sword" + RESET + " - $" + GREEN + 150 + RESET + (player.inventory.getOrDefault("iron sword", 0) > 0 ? " (" + player.inventory.get("iron sword") + " Owned)" : ""));
            System.out.println("6. " + PURPLE + "Iron Armour Package (Helmet, Body, Legs, Shield, Sword)" + RESET + " - $" + GREEN + 500 + RESET);

            // Mage Gear
            System.out.println(BLUE + "\n=== Mage Gear ===" + RESET);
            System.out.println("7. " + PURPLE + "Mage Hat" + RESET + " - $" + GREEN + 5000 + RESET + (player.inventory.getOrDefault("mage hat", 0) > 0 ? " (" + player.inventory.get("mage hat") + " Owned)" : ""));
            System.out.println("8. " + PURPLE + "Mage Robe" + RESET + " - $" + GREEN + 8000 + RESET + (player.inventory.getOrDefault("mage robe", 0) > 0 ? " (" + player.inventory.get("mage robe") + " Owned)" : ""));
            System.out.println("9. " + PURPLE + "Mage Pants" + RESET + " - $" + GREEN + 5000 + RESET + (player.inventory.getOrDefault("mage pants", 0) > 0 ? " (" + player.inventory.get("mage pants") + " Owned)" : ""));
            System.out.println("10. " + PURPLE + "Mage Amulet" + RESET + " - $" + GREEN + 10000 + RESET + (player.inventory.getOrDefault("mage amulet", 0) > 0 ? " (" + player.inventory.get("mage amulet") + " Owned)" : ""));
            System.out.println("11. " + PURPLE + "Fire Staff" + RESET + " - $" + GREEN + 12000 + RESET + (player.inventory.getOrDefault("fire staff", 0) > 0 ? " (" + player.inventory.get("fire staff") + " Owned)" : ""));
            System.out.println("12. " + PURPLE + "Ice Staff" + RESET + " - $" + GREEN + 12000 + RESET + (player.inventory.getOrDefault("ice staff", 0) > 0 ? " (" + player.inventory.get("ice staff") + " Owned)" : ""));
            System.out.println("13. " + PURPLE + "Lightning Staff" + RESET + " - $" + GREEN + 12000 + RESET + (player.inventory.getOrDefault("lightning staff", 0) > 0 ? " (" + player.inventory.get("lightning staff") + " Owned)" : ""));
            System.out.println("14. " + PURPLE + "Mage Package (Hat, Robe, Pants, Amulet, Staff of choice)" + RESET + " - $" + GREEN + 35000 + RESET);

            // Dragon Gear (Premium)
            System.out.println(RED + "\n=== Dragon Gear (Premium) ===" + RESET);
            System.out.println("15. " + PURPLE + "Dragon Helmet" + RESET + " - $" + GREEN + 20000 + RESET + (player.inventory.getOrDefault("dragon helmet", 0) > 0 ? " (" + player.inventory.get("dragon helmet") + " Owned)" : ""));
            System.out.println("16. " + PURPLE + "Dragon Chestplate" + RESET + " - $" + GREEN + 30000 + RESET + (player.inventory.getOrDefault("dragon chestplate", 0) > 0 ? " (" + player.inventory.get("dragon chestplate") + " Owned)" : ""));
            System.out.println("17. " + PURPLE + "Dragon Leggings" + RESET + " - $" + GREEN + 20000 + RESET + (player.inventory.getOrDefault("dragon leggings", 0) > 0 ? " (" + player.inventory.get("dragon leggings") + " Owned)" : ""));
            System.out.println("18. " + PURPLE + "Dragon Shield" + RESET + " - $" + GREEN + 20000 + RESET + (player.inventory.getOrDefault("dragon shield", 0) > 0 ? " (" + player.inventory.get("dragon shield") + " Owned)" : ""));
            System.out.println("19. " + PURPLE + "Dragon Sword" + RESET + " - $" + GREEN + 30000 + RESET + (player.inventory.getOrDefault("dragon sword", 0) > 0 ? " (" + player.inventory.get("dragon sword") + " Owned)" : ""));
            System.out.println("20. " + PURPLE + "Dragon Armour Package (Helmet, Body, Legs, Shield, Sword)" + RESET + " - $" + GREEN + 10000 + RESET);

            System.out.println("0. " + RED + "Back to Shop Categories" + RESET);
            System.out.print(CYAN + BOLD + "Enter the " + RESET + "number " + CYAN + BOLD + "of the item you wish to purchase: " + RESET);

            String armourChoice = scanner.nextLine().trim();

            try {
                switch (armourChoice) {
                    // Iron Gear
                    case "1":
                        buyArmour(player, "iron helmet", 100);
                        break;
                    case "2":
                        buyArmour(player, "iron chestplate", 150);
                        break;
                    case "3":
                        buyArmour(player, "iron leggings", 100);
                        break;
                    case "4":
                        buyArmour(player, "iron shield", 100);
                        break;
                    case "5":
                        buyArmour(player, "iron sword", 150);
                        break;
                    case "6": // Iron Armour Package
                        buyArmourPackage(player);
                        break;

                    // Mage Gear
                    case "7":
                        buyArmour(player, "mage hat", 5000);
                        break;
                    case "8":
                        buyArmour(player, "mage robe", 8000);
                        break;
                    case "9":
                        buyArmour(player, "mage pants", 5000);
                        break;
                    case "10":
                        buyArmour(player, "mage amulet", 10000);
                        break;
                    case "11":
                        buyArmour(player, "fire staff", 12000);
                        break;
                    case "12":
                        buyArmour(player, "ice staff", 12000);
                        break;
                    case "13":
                        buyArmour(player, "lightning staff", 12000);
                        break;
                    case "14": // Mage Package
                        buyMagePackage(player);
                        break;

                    // Dragon Gear
                    case "15":
                        buyArmour(player, "dragon helmet", 20000);
                        break;
                    case "16":
                        buyArmour(player, "dragon chestplate", 30000);
                        break;
                    case "17":
                        buyArmour(player, "dragon leggings", 20000);
                        break;
                    case "18":
                        buyArmour(player, "dragon shield", 20000);
                        break;
                    case "19":
                        buyArmour(player, "dragon sword", 30000);
                        break;
                    case "20": // Dragon Armour Package
                        buyDragonPackage(player);
                        break;

                    case "0", "back", "leave", "exit": // Back
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
        int packagePrice = 500;
        if (player.getMoney() >= packagePrice) {
            player.setMoney(player.getMoney() - packagePrice);
            player.addItem("iron helmet", 1);
            player.addItem("iron chestplate", 1);
            player.addItem("iron leggings", 1);
            player.addItem("iron shield", 1);
            player.addItem("iron sword", 1);
            System.out.println(GREEN + "You bought the Iron Armour Package for $" + YELLOW + packagePrice + GREEN + ". You now have:" + RESET);
            System.out.println(YELLOW + "  - iron Helmet" + GREEN + ": " + player.getItemCount("iron helmet"));
            System.out.println(YELLOW + "  - iron chestplate" + GREEN + ": " + player.getItemCount("iron chestplate"));
            System.out.println(YELLOW + "  - iron leggings" + GREEN + ": " + player.getItemCount("iron leggings"));
            System.out.println(YELLOW + "  - iron Shield" + GREEN + ": " + player.getItemCount("iron shield"));
            System.out.println(YELLOW + "  - iron  Sword" + GREEN + ": " + player.getItemCount("iron sword") + RESET);
            System.out.println(GREEN + "Your remaining cash: $" + YELLOW + player.getMoney() + RESET);
        } else {
            System.out.println(RED + "Not enough money for the Iron Armour Package!" + RESET);
        }
    }

    private void buyMagePackage(Player player) {
        int packagePrice = 35000;
        if (player.getMoney() >= packagePrice) {
            player.setMoney(player.getMoney() - packagePrice);
            player.addItem("mage hat", 1);
            player.addItem("mage robe", 1);
            player.addItem("mage pants", 1);
            player.addItem("mage amulet", 1);

            // Ask which staff they want
            System.out.println(YELLOW + "Which staff would you like with your Mage Package?" + RESET);
            System.out.println("1. " + PURPLE + "Fire Staff" + RESET);
            System.out.println("2. " + PURPLE + "Ice Staff" + RESET);
            System.out.println("3. " + PURPLE + "Lightning Staff" + RESET);

            String staffChoice = scanner.nextLine().trim();
            String staffName = switch(staffChoice) {
                case "1" -> "fire staff";
                case "2" -> "ice staff";
                case "3" -> "lightning staff";
                default -> {
                    System.out.println(YELLOW + "Invalid choice. You'll receive a Fire Staff by default." + RESET);
                    yield "fire staff";
                }
            };

            player.addItem(staffName, 1);

            System.out.println(GREEN + "You bought the Mage Package for $" + YELLOW + packagePrice + GREEN + ". You now have:" + RESET);
            System.out.println(YELLOW + "  - Mage Hat" + GREEN + ": " + player.getItemCount("mage hat"));
            System.out.println(YELLOW + "  - Mage Robe" + GREEN + ": " + player.getItemCount("mage robe"));
            System.out.println(YELLOW + "  - Mage Pants" + GREEN + ": " + player.getItemCount("mage pants"));
            System.out.println(YELLOW + "  - Mage Amulet" + GREEN + ": " + player.getItemCount("mage amulet"));
            System.out.println(YELLOW + "  - " + staffName + GREEN + ": " + player.getItemCount(staffName) + RESET);
            System.out.println(GREEN + "Your remaining cash: $" + YELLOW + player.getMoney() + RESET);
        } else {
            System.out.println(RED + "Not enough money for the Mage Package!" + RESET);
        }
    }

    private void buyDragonPackage(Player player) {
        int packagePrice = 100000;
        if (player.getMoney() >= packagePrice) {
            player.setMoney(player.getMoney() - packagePrice);
            player.addItem("dragon helmet", 1);
            player.addItem("dragon chestplate", 1);
            player.addItem("dragon leggings", 1);
            player.addItem("dragon shield", 1);
            player.addItem("dragon sword", 1);
            System.out.println(GREEN + "You bought the Dragon Armour Package for $" + YELLOW + packagePrice + GREEN + ". You now have:" + RESET);
            System.out.println(YELLOW + "  - Dragon Helmet" + GREEN + ": " + player.getItemCount("dragon helmet"));
            System.out.println(YELLOW + "  - Dragon Chestplate" + GREEN + ": " + player.getItemCount("dragon chestplate"));
            System.out.println(YELLOW + "  - Dragon Leggings" + GREEN + ": " + player.getItemCount("dragon leggings"));
            System.out.println(YELLOW + "  - Dragon Shield" + GREEN + ": " + player.getItemCount("dragon shield"));
            System.out.println(YELLOW + "  - Dragon Sword" + GREEN + ": " + player.getItemCount("dragon sword") + RESET);
            System.out.println(GREEN + "Your remaining cash: $" + YELLOW + player.getMoney() + RESET);
        } else {
            System.out.println(RED + "Not enough money for the Dragon Armour Package!" + RESET);
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
            System.out.println("2. " + PURPLE + "Basic Rod" + RESET + " - $" + GREEN + 2000 + RESET + (player.inventory.containsKey("basic rod") ? " (Owned)" : ""));
            System.out.println("3. " + PURPLE + "Basic bait" + RESET + " - $" + GREEN + 50 + RESET + (player.inventory.getOrDefault("basic bait", 0) > 0 ? " (" + player.inventory.get("basic bait") + " Owned)" : ""));
            System.out.println("4. " + PURPLE + "Super bait" + RESET + " - $" + GREEN + 125 + RESET + (player.inventory.getOrDefault("super bait", 0) > 0 ? " (" + player.inventory.get("super bait") + " Owned)" : ""));
            System.out.println("5. " + PURPLE + "axe" + RESET + " - $" + GREEN + 5000 + RESET + (player.inventory.getOrDefault("axe", 0) > 0 ? " (" + player.inventory.get("axe") + " Owned)" : ""));
            System.out.println("*. " + RED + "Back to Shop Categories" + RESET);
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
                        buyBait(player, "basic bait", 50);
                        break;
                    case "4":
                        buyBait(player, "super bait", 125);
                        break;
                    case "5":
                        buyAxe(player, 5000);
                        break;

                    case "*", "exit", "back", "leave": // Back
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
    private void buyAxe(Player player, int price) {
        if (!player.hasItem("axe")) {
            if (player.getMoney() >= price) {
                player.setMoney(player.getMoney() - price);
                player.addItem("axe", 1);
                System.out.println(GREEN + "You purchased an " + PURPLE + "axe" + GREEN + " for $" + YELLOW + price + GREEN + ". You now have $" + YELLOW + player.getMoney() + GREEN + "." + RESET);
            } else {
                System.out.println(RED + "Not enough money for the axe!" + RESET);
            }
        } else {
            System.out.println(YELLOW + "You already own an axe." + RESET);
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
    private void customSellItems() {
        shopItems.putIfAbsent("copper wire", 175); // Manual shop items
        shopItems.putIfAbsent("starter helmet", 10);
        shopItems.putIfAbsent("seaweed", 2);

    }
    private void sell(Player player) {

        customSellItems();


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
