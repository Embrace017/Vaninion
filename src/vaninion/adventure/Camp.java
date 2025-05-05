package vaninion.adventure;

import vaninion.players.Player;

import java.util.Scanner;

import static vaninion.ColoredConsole.*;

public class Camp {
    Scanner scanner = new Scanner(System.in);
    public void camp(Player player) {
        System.out.println("You are in a camp. You can see a few tents and a warm campfire.");
        System.out.println("What would you like to do?");
        System.out.println("1. Go to a tent");
        System.out.println("2. Go to the campfire");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine().toLowerCase().trim();
        switch (choice) {
            case "1" -> {
                System.out.println("You go to a tent.");
                System.out.println("You enter the tent and find yourself in a small room with a surprisingly comfy looking mattress.");
                System.out.println(BRIGHT_GREEN + "You rest in the bed and get the best nights sleep of your life!.");
                player.setHealth(player.getMaxHealth() * 2);
                player.setMana(player.getMaxMana() * 2);
            }
            case "2" -> {
                System.out.println("You go to the campfire.");
                System.out.println("You look around and see a some glowing food on the ground.");
                System.out.println("Do you eat it???? (Higher wisdom may help)" );
                String choice2 = scanner.nextLine().toLowerCase().trim();
                if (choice2.equals("yes")) {
                    double boost = player.getWisdom() * 0.05;
                    int food = Math.random() < boost ? 1 : 2;
                    if (food == 1) {
                        System.out.println(GREEN + "You eat the food and feel super refreshed!" + RESET);
                        player.setHealth(player.getMaxHealth() * 2);
                        player.setMana(player.getMaxMana() * 2);
                        player.setCharisma(player.getCharisma() + 1);
                    }
                    if (food == 2) {
                        System.out.println(RED + "You eat the food and feel sick." + RESET);
                        player.setHealth(1);
                        player.setMana(0);
                    }
                    System.out.println(RED + "Your chances of success were: " + boost * 100 + "%" + RESET);
                } else {
                    System.out.println("You decide not to eat the food.");
                }

            }

        }
    }

}
