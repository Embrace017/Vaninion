package vaninion.adventure;

import vaninion.players.Player;

import java.util.Random;
import java.util.Scanner;

import static vaninion.ColoredConsole.*;
import static vaninion.ColoredConsole.GREEN;
import static vaninion.ColoredConsole.RED;
import static vaninion.ColoredConsole.RESET;


public class WildDojo {


    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();


    public void wildDojo(Player player) {

        System.out.println(BRIGHT_PURPLE + "You've found a training dojo!" + RESET);
        System.out.println(BRIGHT_PURPLE + "Pay the trainers $1000 in hopes of gaining some valuable skills?" + RESET);
        System.out.println("yes/no .. 'info'");


        int roll = random.nextInt(Math.max(50, 100 - player.getWisdom()));
        String choice = scanner.nextLine().toLowerCase().trim();
        switch (choice) {
            case "y", "yes" -> {
                if (player.getMoney() >= 1000) {
                    player.setMoney(player.getMoney() - 1000);
                    if (roll <= 50) {
                        player.setSkillPoints(player.getSkillPoints() + 1);
                        System.out.println(GREEN + "You have actually managed to learn something!! " + RESET + "(+1 skill point)");
                        System.out.println(GREEN + "You rolled a " + roll + "!" + RESET);
                    } else {
                        System.out.println(RED + "You didn't listen to anything they said!" + RESET);
                        System.out.println(RED + "You rolled a " + roll + "!" + RESET);
                    }
                } else {
                    System.out.println(RED + "You don't have enough money!" + RESET);
                }
            }
            case "n", "no" ->
                    System.out.println(RED + "You leave the Dojo. Just as inexperienced as you came!" + RESET);
            case "info" -> {
                System.out.println(RED + "You have a 50% chance of gaining a skill point. (Boosted with wisdom)");
                wildDojo(player);
            }
            default -> System.out.println(RED + "Invalid choice." + RESET);

        }
    }
}
