package vaninion.monsters;

import vaninion.players.Player;
import static vaninion.ColoredConsole.*;
import java.util.Random;

/**
 * DragonLord - A powerful boss monster that players can fight
 * Has special abilities and drops unique loot
 */
public class DragonLord extends Monster {
    private int specialAttackCooldown;
    private boolean enraged;
    private final int MAX_HEALTH = 300; // Store max health as a constant
    private final int maxStr = 30;

    // Getter for maxHealth
    private int getMaxHealth() {
        return MAX_HEALTH;
    }

    public DragonLord() {
        super("Dragon Lord", 500, 300, 20, 30);
        this.specialAttackCooldown = 0;
        this.enraged = false;

        // Add unique drops with their drop rates and prices
        addPossibleDrop("gold coin", 100, 100);
        addPossibleDrop("dragon scale", 100.0, 500);  // Guaranteed drop
        addPossibleDrop("dragon heart", 50.0, 1000);
        addPossibleDrop("dragon fang", 50.0, 750);
        addPossibleDrop("dragon blood", 50.0, 800);
        addPossibleDrop("dragon staff", 10.0, 2000);
    }

    @Override
    public void attack(Player player) {
        // Check if it's time for a special attack
        if (specialAttackCooldown <= 0) {
            specialAttack(player);
            specialAttackCooldown = 3; // Reset cooldown
        } else {
            // Regular attack with potential for higher damage when enraged
            int damageMultiplier = enraged ? 2 : 1;
            int damageRange = Math.max(0, this.getStrength() * damageMultiplier - player.getDefense());
            int damage = damageRange > 1 ? new Random().nextInt(damageRange) : 0;

            player.setHealth(player.getHealth() - damage);

            System.out.println("\n" + RED + BOLD + this.getName() + " attacks " + player.getName() + " for " + damage + " damage!");
            System.out.println(BOLD + BLUE + "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

            // Decrease special attack cooldown
            specialAttackCooldown--;
        }
    }

    /**
     * Special attack that does one of several powerful effects
     */
    private void specialAttack(Player player) {
        Random random = new Random();
        int attackType = random.nextInt(3);

        switch (attackType) {
            case 0: // Fire breath
                int fireDamage = 20 + random.nextInt(20);
                player.setHealth(player.getHealth() - fireDamage);
                System.out.println(RED + BOLD + getName() + " breathes a torrent of fire for " + fireDamage + " damage!" + RESET);
                break;

            case 1: // Wing blast
                int wingDamage = 10 + random.nextInt(15);
                player.setHealth(player.getHealth() - wingDamage);
                // Also reduces player's mana
                int manaReduction = random.nextInt(Math.max(player.getMana(), 15));
                player.setMana(player.getMana() - manaReduction);
                System.out.println(CYAN + BOLD + getName() + " creates a powerful gust with its wings for " +
                        wingDamage + " damage and drains " + manaReduction + " mana!" + RESET);
                break;

            case 2: // Tail swipe
                int tailDamage = 15 + random.nextInt(15);
                player.setHealth(player.getHealth() - tailDamage);
                // Also applies a confusion effect
                player.applyEffect("confusion", 2);
                System.out.println(PURPLE + BOLD + getName() + " swipes its massive tail for " +
                        tailDamage + " damage and confuses you!" + RESET);
                break;
        }
    }

    /**
     * When the Dragon Lord's health drops below 30%, it becomes enraged
     */
    @Override
    public void setHealth(int health) {
        super.setHealth(health);

        // Check if health is below 30% and dragon isn't already enraged
        if (health <= getMaxHealth() * 0.3 && !enraged) {
            enraged = true;
            setStrength(getStrength() + 10); // Increase strength when enraged
            System.out.println(RED + BOLD + "The Dragon Lord becomes ENRAGED! Its attacks grow more powerful!" + RESET);
        }
    }

    /**
     * Reset the dragon to its initial state
     */
    @Override
    public void reset() {
        super.reset();
        setStrength(getMaxStr());
        this.specialAttackCooldown = 0;
        this.enraged = false;
        // Reset strength to original value if it was increased due to enrage

    }
}
