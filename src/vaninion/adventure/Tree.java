package vaninion.adventure;

import vaninion.players.Player;


import java.util.Random;
import java.util.Scanner;

import static vaninion.ColoredConsole.*;

public class Tree {

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    public void tree(Player player) {      // Begin Tree encounter
        System.out.println("You stumble upon a tree that is begging to be cut down.");
        System.out.println(PURPLE + "Do you want to cut this tree down? (Axe required)" + RESET);
        String choice = scanner.nextLine().trim().toLowerCase();
        switch (choice) {
            case "yes" -> cutTree(player);
            case "no" -> gnomeDoor(player); // Special secret prizes
        }
    }


    private void cutTree(Player player) { // Checks for axe and gives wood if you have one
        int woodAmount = player.getWisdom();
        if (!player.hasItem("axe")) {
            System.out.println(RED + "You don't have the axe in your inventory");
        } else if (player.hasItem("axe")) {
            player.addItem("wood", woodAmount);
            System.out.println("You cut down the tree and received " + woodAmount + " wood!");
        }
    }


    private void gnomeDoor(Player player) { // checks for levels and responses
        System.out.println(BLUE + "You see a little door carved into the base of the tree..");
        System.out.println("Do you want to open the door?");

        String choice2 = scanner.nextLine().trim().toLowerCase();

        if (Math.random() < 1.1 && choice2.equals("yes")) {

            System.out.println(BRIGHT_RED + "You spot a gnome running around inside!!!");
            System.out.println("Do you chase it? (10 Strength, Wisdom, and a bit of luck required)");

            String choice3 = scanner.nextLine().trim().toLowerCase();
            double roll = Math.random();
            if (choice3.equals("yes") && player.getStrength() >= 10 && player.getWisdom() >= 10 && roll < 0.5) {
                System.out.println(BRIGHT_CYAN + BOLD + "You caught the gnome and begin shaking him by his ankles immediately!!!");
                shakeGnome(player);  // Initiates looting the gnome

            } else if (choice3.equals("yes") && player.getStrength() < 10) {
                System.out.println(BRIGHT_RED + "You weren't strong enough!!");
            } else if (choice3.equals("yes") && player.getWisdom() < 10) {
                System.out.println(BRIGHT_RED + "Your mind wasn't quick enough!!");
            } else if (roll >= 0.5) {
                System.out.println(BRIGHT_RED + "You just barely missed the bugger!!!");
            }

        } else {
            System.out.println(RESET + " ");
            System.out.println(BRIGHT_BLACK_BACKGROUND  + BRIGHT_BLACK + "         IT'S        " + RESET); // Secret text that you can see if highlighted
            System.out.println(BRIGHT_BLACK + "Must be nothing...?");
            System.out.println(BRIGHT_BLACK_BACKGROUND + BRIGHT_BLACK + "     NOT NOTHING     " + RESET);
        }
    }

    public void shakeGnome(Player player) { // Actual looting of the gnome - if checks all pass in gnomeDoor()
        try {
            boolean running = true;
            while (running) {
                System.out.println("\n" + BRIGHT_GREEN);
                int res = random.nextInt(10);
                switch (res) {
                    case 0 -> {
                        System.out.println("Some personal life experience fall out of his pockets!!");
                        player.gainExperience(500);
                    }
                    case 1 -> {
                        System.out.println("A HUNDRED gold coins fall out of his pockets!!");
                        player.addItem("gold coin", 100);
                    }
                    case 2 -> {
                        System.out.println("A few Vaninite ore fall out of his pockets!!");
                        player.addItem("vaninite ore", 5);
                    }
                    case 3 -> {
                        System.out.println("A few legendary bait fall out of his pockets!!");
                        player.addItem("legendary bait", 10);
                    }
                    case 4 -> {
                        System.out.println("A few strength potions fall out of his pockets!!");
                        player.addItem("strength potion", 3);
                    }
                    case 5 -> {
                        System.out.println("A few health potions fall out of his pockets!!");
                        player.addItem("health potion", 3);
                    }
                    case 6 -> {
                        System.out.println("A few mana potions fall out of his pockets!!");
                        player.addItem("mana potion", 3);
                    }
                    case 7 -> {
                        System.out.println("A few defence potions fall out of his pockets!!");
                        player.addItem("defence potion", 3);
                    }
                    case 8 -> {
                        System.out.println("A thousand bucks fall out of his pockets!!");
                        player.setMoney(player.getMoney() + 1000);
                    }
                    case 9 -> {
                        System.out.println("Some sticks fall out of his pockets!!");
                        player.addItem("wood", 1);
                    }
                    default -> System.out.println("Something went wrong!");
                }

                if (Math.random() > 0.5) {
                    running = false;
                    System.out.println(RED + "The little bugger bit you and you dropped him. He gets away!" + RESET); // Randomly ends or continues
                } else {
                    System.out.println(BRIGHT_PURPLE + "You continue shaking him upside down!" + RESET);
                    Thread.sleep(2000);
                }

            }
        } catch (InterruptedException e) {
            System.out.println("interrupted");
            Thread.currentThread().interrupt();
        }
    }
}

