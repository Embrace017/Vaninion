package Vaninion_Main.combat;

import Vaninion_Main.Player;
import Vaninion_Main.foodAndPotions.Potion;
import Vaninion_Main.monsters.*;
import org.w3c.dom.ls.LSOutput;

import java.util.*;

import static Vaninion_Main.ColoredConsole.*;

public class Combat {
    private final Scanner scanner = new Scanner(System.in);
    Potion potion = new Potion();

    // Monster getter
        public Monster getMonsterAndFight(Player player) {
            if (player.getHealth() <=1) {
                System.out.println(RED + "You are far too weak to fight,\n" +
                        " perhaps you should spend the night at the Dojo."+ RESET);
            }
            Monster selectedMonster = npc();
            if (selectedMonster != null) {
                fight(player, selectedMonster);
            }
            if (player.getHealth() < 1) {
            player.setHealth(1);
                System.out.println(BOLD + RED + UNDERLINE + "\nYou have narrowly escaped certain death, skill point deducted." + RESET);
                player.setSkillPoints(player.getSkillPoints() - 1);
            }
            potion.potionStatReset(player);
            return selectedMonster;
        }
        public Monster npc() {
            while (true) {
                System.out.println("\nWhat would you like to fight?");
                System.out.println("1. Goblin");
                System.out.println("2. Skeleton");
                System.out.println("* " + RED + "Back" + RESET);

                String choice = scanner.nextLine().toLowerCase().trim();
                return switch (choice) {
                    case "1", "goblin" -> new Goblin();
                    case "2", "skeleton" -> new Skeleton();
                    case "*", "back", "exit", "leave" -> null;
                    default -> {
                        System.out.println(RED + "Invalid choice! Please try again." + RESET);
                        yield null;
                    }
                };
            }
        }

    public void fight(Player player, Monster monster) {
        if (monster == null) {
            System.out.println(RED + "No monster selected!" + RESET);
            return;
        }
        monster.reset();

        System.out.println(BOLD + BLUE + "\n========= Combat Started =========" + RESET);
        System.out.println("You are fighting a " + PURPLE + monster.getName() + RESET);

        while (monster.isAlive() && player.getHealth() > 0) {
            // Display current status
            System.out.println("\n" + PURPLE + BOLD + "~*~\n" + RESET);
            System.out.println(GREEN + "Your HP: " + player.getHealth() + RESET + " | " + PURPLE + "Your MP: " + player.getMana() + RESET);
                    System.out.println(RED + monster.getName() + " HP: " + monster.getHealth() + RESET);
            System.out.println(BOLD + BLUE + "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

            // Combat options
            System.out.println(YELLOW + "\nWhat would you like to do?" + RESET);
            System.out.println("1. " + PURPLE + "Attack" + RESET);
            System.out.println("2. " + PURPLE + "Special Attack" + GREEN + " (Uses 20 MP)" + RESET);
            System.out.println("3. " + PURPLE + "Use Potion" + RESET);
            System.out.println("4. " + RED + "Flee" + RESET);

            String choice = scanner.nextLine().toLowerCase().trim();
            boolean playerTurnEnded = false;

            switch (choice) {
                case "1", "attack" -> {
                    player.attack(monster);
                    playerTurnEnded = true;
                }
                case "2", "special" -> {
                    if (player.getMana() >= 20) {
                        int damage = player.getStrength() * 2;
                        monster.setHealth(monster.getHealth() - damage);
                        player.setMana(player.getMana() - 20);
                        System.out.println(BLUE + BOLD + "\n========= Combat Continued =========" + RESET);
                        System.out.println(PURPLE + "You used a special attack for " + damage + " damage!" + RESET);
                        playerTurnEnded = true;
                    } else {
                        System.out.println(" ");
                        System.out.println(BLUE + BOLD + "\n========= Please Choose again =========" + RESET);
                        System.out.println(RED + "Not enough MP!" + RESET);
                    }
                }
                case "3", "potion" -> potion.usePotion(player);
                case "4", "flee" -> {
                    if (new Random().nextDouble() < 0.5) { // 50% chance to flee
                        System.out.println(GREEN + "You successfully fled from combat!" + RESET);
                        return;
                    } else {
                        System.out.println(BLUE + BOLD + "\n========= Combat Continued =========" + RESET);
                        System.out.println(RED + "Failed to flee!" + RESET);
                        playerTurnEnded = true;
                    }
                }
                default -> System.out.println(RED + "Invalid choice!" + RESET);
            }

            if (!playerTurnEnded) {
                continue; // Skip monster's turn if player didn't take action
            }

            // Check if monster died from player's action
            if (!monster.isAlive()) {
                System.out.println(BLUE + BOLD + "\n=================== " + PURPLE + "VICTORY" + BLUE + " ===================" + RESET);
                System.out.println(GREEN + "\nYou defeated the " +
                        PURPLE + monster.getName() + GREEN + "!" + RESET);

                // Handle drops
                Map<String, Integer> drops = monster.generateDrops();
                if (!drops.isEmpty()) {
                    System.out.println(YELLOW + "The monster dropped:" + RESET);
                    for (Map.Entry<String, Integer> drop : drops.entrySet()) {
                        String item = drop.getKey();
                        int quantity = drop.getValue();
                        player.addItem(item, quantity);
                        System.out.println(PURPLE + "- " + quantity + "x " + item + RESET);
                    }
                }

                // Action after monster defeat
                player.setMoney(player.getMoney() + player.getCharisma() + player.getWisdom());
                player.gainExperience(monster.getExp() + player.getWisdom());

                // Reset
                System.out.println(BLUE + BOLD + "\n === Next Battle with next " + monster.getName() + " begins === " + RESET);
                monster.reset();
            }

            // Monster's turn
            monster.attack(player);
            if (player.getHealth() <= 0) {
                System.out.println(RED + "\nYou have been defeated!" + RESET);
                return;
            }
        }
        if (!monster.isAlive()) {
            monster.reset();
            npc();
        }
    }
    }