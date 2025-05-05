package vaninion.adventure;

import vaninion.players.Player;

import java.util.Scanner;

import static vaninion.ColoredConsole.*;
import static vaninion.ColoredConsole.RED;
import static vaninion.ColoredConsole.RESET;

public class Animal {
    Scanner scanner = new Scanner(System.in);
    public void wildAnimal(Player player) {
        System.out.println(RED + "You found a sheep wandering around!" + RESET);
        System.out.println(RED + "What are you going to do to it??" + RESET);
        System.out.println("1. Kill it (20 Strength)");
        System.out.println("2. Tame it (20 wisdom)");
        System.out.print(CYAN + "Enter your choice: " + RESET);
        String choice = scanner.nextLine().toLowerCase().trim();
        switch (choice) {
            case "1", "kill" -> {

                if (player.getStrength() >= 20) {
                    player.inventory.put("golden fleece", 1);
                    System.out.println(RED + "You killed the sheep and received its fleece!" + RESET);
                } else {
                    System.out.println(RED + "You aren't strong enough to kill the sheep!" + RESET);
                }

            }

            case "2", "tame" -> {

                if (player.getWisdom() >= 20) {
                    addSheep(player);
                    player.displayAnimalInventory();
                } else {
                    System.out.println(RED + "You aren't wise enough to tame the sheep!" + RESET);
                }

            }
            default -> System.out.println(RED + "Invalid choice." + RESET);
        }
    }
    public void addSheep(Player player) {
        System.out.println(RED + "You tamed the sheep!" + RESET);
        int numberOfSheepToAdd = 1; // For example
        int currentSheep = player.animalInventory.getOrDefault("sheep", 0);
        player.animalInventory.put("sheep", currentSheep + numberOfSheepToAdd);
    }
    public static void animalRewards(Player player) {
        if (!player.animalInventory.isEmpty()) {
            int totalAnimals = 0;
            for (int count : player.animalInventory.values()) {
                totalAnimals += count;
            }
            int profit = totalAnimals * 100;
            player.setMoney(player.getMoney() + profit);


            System.out.println("Profit from barn: " + profit);
            System.out.println("Money: " + player.getMoney());
        }
    }
}


























