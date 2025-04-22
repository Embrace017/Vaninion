package Vaninion_Main.foodAndPotions;

import java.util.Scanner;
import Vaninion_Main.Player;
import static Vaninion_Main.ColoredConsole.*;


public class Potion {
    private final Scanner scanner = new Scanner(System.in);

    public void usePotion(Player player) {

        boolean playerTurnEnded = false;

        System.out.println(YELLOW + "\nWhat would you like to use?" + RESET);
        System.out.println("1. " + PURPLE + "Health Potion" + RESET);
        System.out.println("2. " + PURPLE + "Mana Potion" + RESET);
        System.out.println("3. " + PURPLE + "Strength Potion" + RESET);
        System.out.println("4. " + PURPLE + "Defence Potion" + RESET);

        System.out.println("* " + RED + "Back" + RESET);
        String choice = scanner.nextLine().toLowerCase().trim();
        switch (choice) {
            case "1", "health potion", "health" -> {
                choice = "health potion";
                if (player.hasItem(choice)) {
                    player.removeItem(choice, 1);
                    player.setHealth(player.getHealth() + 50);
                    drinkPotion(player, choice);
                }
            }
            case "2", "mana potion", "mana" -> {
                choice = "mana potion";
                if (player.hasItem(choice)) {
                    player.removeItem(choice, 1);
                    player.setMana(player.getMana() + 50);
                    drinkPotion(player, choice);
                }
            }
            case "3", "strength potion", "strength" -> {
                choice = "strength potion";
                if (player.hasItem(choice)) {
                    player.removeItem(choice, 1);
                    player.setStrength(player.getStrength() + 10);
                    drinkPotion(player, choice);
                }
                System.out.println("Stregth: " + player.getStrength());
            }
            case "4", "defence potion", "defence" -> {
                choice = "defence potion";
                if (player.hasItem(choice)) {
                    player.removeItem(choice, 1);
                    player.setDefence(player.getDefence() + 1);
                    drinkPotion(player, choice);
                }
                System.out.println("Defence: " + player.getDefence());
            }
            case "*", "back", "exit", "leave" -> System.out.println("Not thirsty?");
            default -> System.out.println(RED + "Invalid choice!" + RESET);
        }
    }

    public void drinkPotion(Player player, String potion) {
        if (player.hasItem(potion)) {
            System.out.println(" ");
            System.out.println(BLUE + BOLD + "\n====== Combat Continued ======" + RESET);
            System.out.println(GREEN + "You used a " + potion + RESET);
        } else {
            System.out.println(BLUE + BOLD + "\n====== Please Choose Again ======" + RESET);
            System.out.println(RED + "No " + potion + "'s available." + RESET);
        }
    }
}