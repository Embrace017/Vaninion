package Vaninion_Main.combat;

import Vaninion_Main.player.Ork;
import Vaninion_Main.player.Player;
import Vaninion_Main.player.Viking;
import Vaninion_Main.foodAndPotions.Potion;
import Vaninion_Main.monsters.*;

import java.util.*;

import static Vaninion_Main.ColoredConsole.*;

public class Combat {
    Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);
    Potion potion = new Potion();

    // Monster getter
    public Monster getMonsterAndFight(Player player) {
        if (player.getHealth() <= 20) {
            System.out.println(RED + "You are far too weak to fight,\n" +
                    " perhaps you should spend the night at the Dojo." + RESET);
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
            System.out.println(YELLOW + "\nWhat would you like to fight?" + RESET);
            System.out.println(PURPLE + "1. " + GREEN + "Goblin" + RESET);
            System.out.println(PURPLE + "2. " + GREEN + "Skeleton" + RESET);
            System.out.println(PURPLE + "* " + RED + "Back" + RESET);

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
    public void playerAttack(Player player, Monster target) {
        int number = random.nextInt(player.getStrength() + player.getLevel() + 1);
        int damage = Math.max(0, number - target.getDefence());
        target.setHealth(target.getHealth() - damage);

        // Display base attack
        System.out.println(BLUE + "╔════════════════ ATTACK ════════════════╗");
        System.out.println("║ " + GREEN + player.getName() + " attacks " + target.getName() +
                " for " + damage + " damage!" + BLUE);

        // Handle class-specific bonus effects
        if (player instanceof Viking) {
            Viking viking = (Viking) player;
            if (viking.getBerserkStacks() > 0) {
                int bonusDamage = viking.getBerserkStacks() * 2;
                target.setHealth(target.getHealth() - bonusDamage);
                System.out.println("║ " + PURPLE + "Berserk bonus damage: " + bonusDamage + "!" + BLUE);
            }
        } else if (player instanceof Ork) {
            Ork ork = (Ork) player;
            // Handle Ork's rage mechanic here
            ork.increaseRage(10);
        }

        System.out.println("╚════════════════════════════════════════╝" + RESET);
    }

    public void fight(Player player, Monster monster) {
        if (monster == null) {
            System.out.println(RED + "No monster selected!" + RESET);
            return;
        }
        monster.reset();

        // Combat start banner
        System.out.println(BLUE + BOLD + "\n╔═══════════════════════════════════════╗");
        System.out.println("║" + YELLOW + "           COMBAT INITIATED            " + BLUE + "║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║ " + PURPLE + "Opponent: " + monster.getName() + BLUE);
        System.out.println("╚═══════════════════════════════════════╝" + RESET);


        while (monster.isAlive() && player.getHealth() > 0) {
            // Status display
            System.out.println(BLUE + BOLD + "\n╔═══════════════════════════════════════╗");
            System.out.println("║" + YELLOW + "               BATTLE                  " + BLUE + "║");
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.println("║ " + GREEN + "Your HP: " + player.getHealth() + "/" + player.getMaxHealth() + BLUE);
            System.out.println("║ " + PURPLE + "Your MP: " + player.getMana() + "/" + player.getMaxMana() + BLUE);
            System.out.println("║ " + RED + monster.getName() + " HP: " + monster.getHealth() + BLUE);
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.println("║" + YELLOW + " Actions:" + BLUE + "                              ║");
            System.out.println("║ " + PURPLE + "1. Attack" + BLUE + "                             ║");
            System.out.println("║ " + PURPLE + "2. Special Attack " + GREEN + "(20 MP)" + BLUE + "             ║");
            System.out.println("║ " + PURPLE + "3. Use Potion" + BLUE + "                         ║");
            System.out.println("║ " + RED + "4. Flee" + BLUE + "                               ║");
            System.out.println("╚═══════════════════════════════════════╝" + RESET);


            String choice = scanner.nextLine().toLowerCase().trim();
            boolean playerTurnEnded = false;

            switch (choice) {
                case "1", "attack" -> {
                    playerAttack(player, monster);
                    playerTurnEnded = true;
                }
                case "2", "special" -> {
                    if (player.getMana() >= 20) {
                        int damage = player.getStrength() + player.getLevel() + player.getWisdom();
                        monster.setHealth(monster.getHealth() - damage);
                        player.setMana(player.getMana() - 20);
                        System.out.println(BLUE + "╔═══════════ SPECIAL ATTACK ═══════════╗");
                        System.out.println("║ " + PURPLE + "Defense ignored! " + BLUE);
                        System.out.println("║ " + PURPLE + "Damage dealt: " + damage + BLUE);
                        System.out.println("╚══════════════════════════════════════╝" + RESET);

                        playerTurnEnded = true;
                    } else {
                        System.out.println(RED + "⚠ Not enough MP! ⚠" + RESET);
                    }
                }
                case "3", "potion" -> potion.usePotion(player);
                case "4", "flee" -> {
                    if (new Random().nextDouble() < 0.5) {
                        System.out.println(GREEN + "\n╔══════════════ ESCAPED ══════════════╗");
                        System.out.println("║  You successfully fled from combat! ║");
                        System.out.println("╚═════════════════════════════════════╝" + RESET);
                        return;
                    } else {
                        System.out.println(RED + "\n╔══════════════ FAILED ═════════════╗");
                        System.out.println("║      Failed to escape combat!     ║");
                        System.out.println("╚═══════════════════════════════════╝" + RESET);
                        playerTurnEnded = true;
                    }
                }
                default -> System.out.println(RED + "Invalid choice!" + RESET);
            }

            // Rest of your combat logic...

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