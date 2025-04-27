package vaninion.combat;

//Add special to combat interface






import vaninion.monsters.*;
import vaninion.players.player.Ork;
import vaninion.players.Player;
import vaninion.players.player.Viking;
import vaninion.foodAndPotions.Potion;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import static vaninion.ColoredConsole.*;

public class Combat {
    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);
    private final Potion potion = new Potion();

    private String currentBattleCondition = "normal";
    private int comboCounter = 0;
    private boolean comboInitiatedMsg = false;
    private int maxCombo = 0;  // Tracks highest combo achieved
    private int basePower = 0;

    // Available spells for the player to cast
    private final Spell[] availableSpells = {
        Spell.FIREBALL,
        Spell.ICE_SPIKE,
        Spell.LIGHTNING_BOLT,
        Spell.SOUL_DRAIN,
        Spell.HEALING_TOUCH,
        Spell.REJUVENATION,
        Spell.IRON_SKIN,
        Spell.ARCANE_INTELLECT,
        Spell.WEAKEN,
        Spell.VULNERABILITY
    };

    public Monster getMonsterAndFight(Player player) {
        if (player.getHealth() <= 10) {
            System.out.println(RED + "You are far too weak to fight,\n" +
                    " perhaps you should spend the night at the Dojo." + RESET);
            return null;
        }

        Monster selectedMonster = npc();
        if (selectedMonster != null) {
            // Set random battle condition
            String[] battleConditions = {
                    "normal", "stormy", "foggy", "sunny", "volcanic", "frozen", "magical", "cursed"
            };
            currentBattleCondition = battleConditions[random.nextInt(battleConditions.length)];

            displayBattleCondition();
            fight(player, selectedMonster);
        }

        handlePostBattle(player);
        return selectedMonster;
    }

    private void displayBattleCondition() {
        System.out.println(BLUE + "\n╔═══════════ BATTLE CONDITIONS ═══════════╗");
        switch (currentBattleCondition) {
            case "stormy" ->
                    System.out.println("║ " + CYAN + "A storm rages! Lightning might strike!" + BLUE);
            case "foggy" ->
                    System.out.println("║ " + GREY + "Dense fog reduces accuracy!" + BLUE);
            case "sunny" ->
                    System.out.println("║ " + YELLOW + "The sun's energy empowers you!" + BLUE);
            case "volcanic" ->
                    System.out.println("║ " + RED + "Volcanic heat burns! Fire attacks enhanced!" + BLUE);
            case "frozen" ->
                    System.out.println("║ " + CYAN + "Freezing cold slows movement! Defense increased!" + BLUE);
            case "magical" ->
                    System.out.println("║ " + PURPLE + "Magical energy fills the air! Mana regenerates!" + BLUE);
            case "cursed" ->
                    System.out.println("║ " + RED + "A curse lingers! Random status effects may occur!" + BLUE);
            default ->
                    System.out.println("║ " + GREEN + "Normal conditions" + BLUE);
        }
        System.out.println("╚═════════════════════════════════════════╝" + RESET);
    }

    private void playerAttack(Player player, Monster target) {
        boolean isCritical = random.nextDouble() < (0.15 + (player.getWisdom() * 0.01));

        // Base damage calculation
        basePower = random.nextInt(player.getStrength() + player.getLevel());

        // Combo damage calculation
        int comboDamage = Math.min(basePower * comboCounter, player.getWisdom() * 2);
        if (basePower == 0)
            comboDamage = 0;
        // Miss chance in fog
        if (currentBattleCondition.equals("foggy") && random.nextDouble() < 0.3 - player.getWisdom() * 0.01) {
            System.out.println(GREY + "The fog causes you to miss!" + RESET);
            return;
        }

        // Calculate total damage

        int totalDamage = Math.max(0, basePower + comboDamage - target.getDefence());


        // Apply critical hit
        if (totalDamage != 0){
            if (isCritical) {
                totalDamage *= 2;
            }
        }


        // Apply sunny bonus
        if (currentBattleCondition.equals("sunny")) {
            totalDamage += player.getWisdom() * 2;
            player.setMana(player.getMaxMana());
        }

        // Apply the damage
        target.setHealth(target.getHealth() - totalDamage);

        // Display combat information
        displayAttackInfo(player, target, basePower, comboDamage, totalDamage, isCritical);

        // Handle class-specific effects
        handleClassEffects(player, target);
    }

    private void displayAttackInfo(Player player, Monster target, int baseDamage, int comboDamage, int totalDamage, boolean isCritical) {
        String[] attackMessages = {
                " strikes at ", " lunges toward ", " swings their weapon at ",
                " charges at ", " attacks "
        };
        String attackMessage = attackMessages[random.nextInt(attackMessages.length)];
        System.out.println("\n".repeat(20));
        System.out.println(BLUE + "╔════════════════ ATTACK ════════════════╗");
        System.out.println("║ " + GREEN + player.getName() + YELLOW + attackMessage + RED + target.getName() + BLUE);

        System.out.println("║ " +  BOLD + BRIGHT_GREEN + "Power: " + basePower + BLUE);
        if (comboCounter == 1) {
            if (!comboInitiatedMsg) {
                System.out.println("║ " + BRIGHT_CYAN + "Combo Initiated." + BLUE);
                comboInitiatedMsg = true;
            }
        }

        if (comboDamage > 0) {
            System.out.println("║ " + CYAN + "Combo bonus: +" + comboDamage + BLUE);
        }

        if (totalDamage != 0) {
            if (isCritical) {
                System.out.println("║ " + BRIGHT_YELLOW + "CRITICAL HIT!" + BLUE);
            }
        }


        System.out.println("║ " + PURPLE + "Total damage: " + totalDamage + RESET + BOLD +" (After enemy defence)" + BLUE);
        System.out.println("╚════════════════════════════════════════╝" + RESET);
    }

    private void handleClassEffects(Player player, Monster target) {
        // Viking effects
        if (player instanceof Viking viking) {
            // Call onAttack to potentially gain berserk stacks
            viking.onAttack();

            // Apply berserk damage bonus if stacks exist
            if (viking.getBerserkStacks() > 0) {
                int bonusDamage = viking.getBerserkStacks() * 2;

                // Apply battle fury bonus if available
                if (viking.getBattleFury() > 0) {
                    bonusDamage += viking.getBattleFury() * viking.getBerserkStacks();
                }

                target.setHealth(target.getHealth() - bonusDamage);
                System.out.println(PURPLE + "Berserk bonus damage: " + bonusDamage + "!" + RESET);
            }
        } 
        // Ork effects
        else if (player instanceof Ork ork) {
            ork.increaseRage(10);
        }
        // Human effects - no combat effects to apply
    }

    public void fight(Player player, Monster monster) {

        if (monster == null) {
            System.out.println(RED + "No monster selected!" + RESET);
            return;
        }

        monster.reset();
        comboCounter = 0;
        comboInitiatedMsg = false;
        displayCombatStart(monster);

        boolean playerGuarding = false;

        while (monster.isAlive() && player.getHealth() > 0) {
            handleBattleConditions(player, monster);
            displayBattleStatus(player, monster);
            String choice = scanner.nextLine().toLowerCase().trim();

// Handle flee separately
            if (choice.equals("5") || choice.equals("flee")) {
                if (handleFlee()) {
                    return;  // Exit combat if flee was successful
                }
                continue;  // Skip the rest of this loop iteration if flee failed
            }

            boolean playerTurnEnded = handlePlayerTurn(choice, player, monster, playerGuarding);

            if (!playerTurnEnded) {
                continue;
            }

            if (!monster.isAlive()) {
                handleVictory(player, monster);
                // Reset monster to fight again instead of breaking out of combat
                // Note: monster.reset() is already called in handleVictory
                // Reset combo-related variables for the next fight
                maxCombo = 0;
                System.out.println(YELLOW + "\nAnother " + monster.getName() + " appears! Get ready to fight again!" + RESET);
                displayCombatStart(monster);
                continue;
            }

            handleMonsterTurn(player, monster, playerGuarding);


            if (player.getHealth() <= 0) {
                System.out.println(RED + "\nYou have been defeated!" + RESET);
                return;
            }
        }
    }

    private void displayCombatStart(Monster monster) {
        System.out.println(BLUE + BOLD + "\n╔═══════════════════════════════════════╗");
        System.out.println("║" + YELLOW + "           COMBAT INITIATED            " + BLUE + "║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║ " + PURPLE + "Opponent: " + monster.getName() + RED + " Strength: " + monster.getStrength()+ BLUE);
        System.out.println("║ " + RED + "Defense: " + monster.getDefence() + GREEN + " Health: " + monster.getHealth()  + BLUE);
        System.out.println("╚═══════════════════════════════════════╝" + RESET);
    }

    private void displayBattleStatus(Player player, Monster monster) {
        System.out.println(BLUE + BOLD + "\n╔═══════════════════════════════════════╗");
        System.out.println("║" + YELLOW + "               BATTLE                  " + BLUE + "║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║ " + GREEN + "Your HP: " + player.getHealth() + "/" + player.getMaxHealth() + PURPLE + " Your MP: " + player.getMana() + "/" + player.getMaxMana()+ BLUE);
        System.out.println("║ " + RED + monster.getName() + " HP Remaining: " + BRIGHT_WHITE + monster.getHealth() + BLUE);
        if (comboCounter > 0) {
            System.out.println("║ " + CYAN + "Combo Counter: " + comboCounter + BLUE);
        }
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║" + YELLOW + " Actions:" + BLUE + "                              ║");
        System.out.println("║ " + PURPLE + "1. Attack" + BLUE + "                             ║");
        System.out.println("║ " + PURPLE + "2. Cast Spell" + BLUE + "                         ║");
        System.out.println("║ " + PURPLE + "3. Use Potion" + BLUE + "                         ║");
        System.out.println("║ " + PURPLE + "4. Guard" + BLUE + "                              ║");
        System.out.println("║ " + RED + "5. Flee" + BLUE + "                               ║");
        System.out.println("╚═══════════════════════════════════════╝" + RESET);
    }

    private void handleVictory(Player player, Monster monster) {
        System.out.println(BLUE + BOLD + "\n=================== " + PURPLE + "VICTORY" + BLUE + " ===================" + RESET);
        System.out.println(GREEN + "\nYou defeated the " + PURPLE + monster.getName() + GREEN + "!" + RESET);

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

        // Victory rewards with bonuses
        int moneyBonus = player.getCharisma() + player.getWisdom();
        int expBonus = monster.getExp() + player.getWisdom();

        // Bonus rewards based on max combo achieved
        if (maxCombo >= 3) {
            moneyBonus += maxCombo;
            expBonus += maxCombo * 2;
            System.out.println(YELLOW + "Combo Bonus: +" + maxCombo + " gold and +" + (maxCombo * 2) + " exp!" + RESET);
        }

        // Sunny condition bonus
        if (currentBattleCondition.equals("sunny")) {
            moneyBonus += 5;
            expBonus += 10;
            System.out.println(YELLOW + "Sunny Day Bonus: +5 gold and +10 exp!" + RESET);
        }

        player.setMoney(player.getMoney() + moneyBonus);
        player.gainExperience(expBonus);

        System.out.println(YELLOW + "Total Rewards: " + moneyBonus + " gold and " + expBonus + " experience!" + RESET);
        System.out.println(BRIGHT_PURPLE_BACKGROUND + BOLD + BLACK + player.getExperience() + "/" + 100 * player.getLevel() + BRIGHT_PURPLE_BACKGROUND + " Level exp" + RESET);
        if (player.getSkillPoints() == 1) {
            System.out.println(YELLOW + "Unused skill point!" + RESET);
        } else if (player.getSkillPoints() > 1) {
            System.out.println(YELLOW + "Unused skill points!" + RESET);
        }
        // Reset for next battle
        monster.reset();
        comboCounter = 0;
        comboInitiatedMsg = false;
        maxCombo = 0;
    }


    private void handleMonsterTurn(Player player, Monster monster, boolean playerGuarding) {
        if (!monster.isAlive()) {
            return;
        }

        if (playerGuarding) {
            int reducedDamage = Math.max(1, monster.getStrength() / 2);
            player.setHealth(player.getHealth() - reducedDamage);
            System.out.println(GREEN + "Your guard reduced the damage to " + reducedDamage + "!" + RESET);
            // Guard can maintain combo if you're skilled!
            if (random.nextDouble() < 0.3 + player.getWisdom() * 0.1) { // 30% chance to maintain combo + wisdom
                System.out.println(YELLOW + "Perfect guard! Combo maintained!" + RESET);
            } else {
                comboCounter = 0;
            }
        } else {
            monster.attack(player);
        }
    }

    // ... [Previous helper methods remain the same]

    private void handleBattleConditions(Player player, Monster monster) {
        // Stormy condition - random lightning strikes
        if (currentBattleCondition.equals("stormy") && random.nextDouble() < 0.15 - player.getWisdom() * 0.01) {
            int lightningDamage = random.nextInt(10) + player.getLevel();
            if (random.nextBoolean()) {
                player.setHealth(player.getHealth() - lightningDamage);
                System.out.println(CYAN + "⚡ Lightning strikes you for " + lightningDamage + " damage! ⚡" + RESET);
            } else {
                monster.setHealth(monster.getHealth() - lightningDamage);
                System.out.println(CYAN + "⚡ Lightning strikes " + monster.getName() + " for " + lightningDamage + " damage! ⚡" + RESET);
            }
        }

        // Volcanic condition - periodic burn damage and fire attack boost
        else if (currentBattleCondition.equals("volcanic") && random.nextDouble() < 0.2) {
            int burnDamage = random.nextInt(5) + 3;
            player.setHealth(player.getHealth() - burnDamage);
            System.out.println(RED + "🔥 The volcanic heat burns you for " + burnDamage + " damage! 🔥" + RESET);

            // Fire attack boost for special attacks
            if (random.nextDouble() < 0.3) {
                player.addEffect("fire_boost", 2, 5); // Name, duration, strength
                System.out.println(YELLOW + "The volcanic energy empowers your next attack!" + RESET);
            }
        }

        // Frozen condition - movement slowed, defense increased
        else if (currentBattleCondition.equals("frozen") && random.nextDouble() < 0.15) {
            // Temporary defense boost
            player.setTempStrength(player.getTempStrength() - 1); // Reduce strength temporarily
            player.setDefense(player.getDefense() + 2); // Increase defense temporarily
            System.out.println(CYAN + "❄ The freezing cold slows you but hardens your defenses! ❄" + RESET);
        }

        // Magical condition - mana regeneration
        else if (currentBattleCondition.equals("magical")) {
            int manaRegen = random.nextInt(3) + 2;
            player.setMana(Math.min(player.getMaxMana(), player.getMana() + manaRegen));
            if (manaRegen > 0) {
                System.out.println(PURPLE + "✨ Magical energy restores " + manaRegen + " mana! ✨" + RESET);
            }
        }

        // Cursed condition - random status effects
        else if (currentBattleCondition.equals("cursed") && random.nextDouble() < 0.15) {
            String[] effects = {"confusion", "weakness", "strength", "wisdom"};
            String effect = effects[random.nextInt(effects.length)];
            int duration = random.nextInt(2) + 1;

            switch (effect) {
                case "confusion" -> {
                    player.applyEffect("confusion", duration);
                    System.out.println(PURPLE + "👻 A curse confuses you for " + duration + " turns! 👻" + RESET);
                }
                case "weakness" -> {
                    player.setTempStrength(player.getTempStrength() - 2);
                    System.out.println(RED + "👻 A curse weakens you! 👻" + RESET);
                }
                case "strength" -> {
                    player.setTempStrength(player.getTempStrength() + 2);
                    System.out.println(GREEN + "👻 A curse unexpectedly strengthens you! 👻" + RESET);
                }
                case "wisdom" -> {
                    player.addEffect("wisdom", duration, 2);
                    System.out.println(BLUE + "👻 A curse grants you temporary wisdom! 👻" + RESET);
                }
            }
        }
    }

    private boolean handlePlayerTurn(String choice, Player player, Monster monster, boolean playerGuarding) {
        return switch (choice) {
            case "1", "attack" -> {
                if (random.nextDouble() < 0.1 + player.getWisdom() * 0.05 )
                    comboCounter++;


                maxCombo = Math.max(maxCombo, comboCounter);
                playerAttack(player, monster);
                yield true;
            }
            case "2", "spell", "cast" -> handleSpellCasting(player, monster);
            case "3", "potion" -> {
                potion.usePotion(player);
                yield false;
            }
            case "4", "guard" -> {
                System.out.println(YELLOW + player.getName() + " takes a defensive stance!" + RESET);
                yield true;
            }
            case "5", "flee" -> true;

                default -> {
                System.out.println(RED + "Invalid choice!" + RESET);
                yield false;
            }
        };
    }

    private boolean handleSpellCasting(Player player, Monster monster) {
        // Display available spells
        System.out.println(PURPLE + "\n=== Available Spells ===" + RESET);
        for (int i = 0; i < availableSpells.length; i++) {
            Spell spell = availableSpells[i];
            String spellInfo = String.format("%d. %s (%d MP) - %s", 
                i + 1, 
                spell.getName(), 
                spell.getManaCost(),
                spell.getDescription());

            // Color the spell name based on type
            String coloredInfo = switch(spell.getType()) {
                case DAMAGE -> RED + spellInfo + RESET;
                case HEAL -> GREEN + spellInfo + RESET;
                case BUFF -> BLUE + spellInfo + RESET;
                case DEBUFF -> PURPLE + spellInfo + RESET;
            };

            System.out.println(coloredInfo);
        }
        System.out.println(YELLOW + "0. Back" + RESET);

        // Get player's choice
        System.out.print("Choose a spell to cast (0-" + availableSpells.length + "): ");
        String input = scanner.nextLine().trim();

        try {
            int choice = Integer.parseInt(input);

            if (choice == 0) {
                return false; // Player chose to go back
            }

            if (choice < 1 || choice > availableSpells.length) {
                System.out.println(RED + "Invalid spell choice!" + RESET);
                return false;
            }

            // Cast the selected spell
            Spell selectedSpell = availableSpells[choice - 1];
            return selectedSpell.cast(player, monster);

        } catch (NumberFormatException e) {
            System.out.println(RED + "Please enter a number!" + RESET);
            return false;
        }
    }

    private boolean handleFlee() {
        if (random.nextDouble() < 0.5) {
            System.out.println(GREEN + "You successfully fled from combat!" + RESET);
            return true;
        } else {
            System.out.println(RED + "Failed to escape!" + RESET);
            return false;
        }
    }

    private void handlePostBattle(Player player) {
        if (player.getHealth() < 1) {
            player.setHealth(1);
            System.out.println(RED + "\nYou barely survived..." + RESET);
            if (player.getSkillPoints() > 0) {
                player.setSkillPoints(player.getSkillPoints() - 1);
                System.out.println(RED + "Lost 1 skill point." + RESET);
            }
        }
        potion.potionStatReset(player);
    }

    // ... [Rest of the helper methods remain the same]

    public Monster npc() {
        while (true) {
            System.out.println(YELLOW + "\nChoose your opponent:" + RESET);
            System.out.println("1. " + GREEN + "Goblin" + RESET);
            System.out.println("2. " + GREEN + "Skeleton" + RESET);
            System.out.println("3. " + GREEN + "Werewolf" + RESET);
            System.out.println("4. " + GREEN + "Giant Tarantula" + RESET);
            System.out.println("5. " + RED + BOLD + "DRAGON LORD (BOSS)" + RESET);
            System.out.println("*. " + RED + "Back" + RESET);

            String choice = scanner.nextLine().toLowerCase().trim();
            return switch (choice) {
                case "1", "goblin" -> new Goblin();
                case "2", "skeleton" -> new Skeleton();
                case "3", "werewolf" -> new Werewolf();
                case "4", "giant tarantula" -> new Tarantula();
                case "5", "dragon lord", "boss", "dragon" -> new DragonLord();
                case "*", "back", "exit", "leave" -> null;
                default -> {
                    System.out.println(RED + "Invalid choice!" + RESET);
                    yield null;
                }
            };
        }
    }
}
