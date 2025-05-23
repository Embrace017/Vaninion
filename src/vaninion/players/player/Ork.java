
package vaninion.players.player;

import vaninion.players.Player;
import static vaninion.ColoredConsole.*;

/**
 * Ork class with enhanced racial abilities
 * Orks are tough and strong, with a rage mechanic that builds up during combat
 */
public class Ork extends Player {
    private int rage;
    private static final int MAX_RAGE = 100;
    private int rageLevel;
    private int berserkerStrength;

    public Ork(String name) {
        super(name);
        this.rage = 0;
        this.rageLevel = 1;
        this.berserkerStrength = 0;

        // Set Ork-specific stats

        setDefense(10);    // Orks have thick skin
    }

    /**
     * Orks can unleash their rage for combat bonuses
     */
    public void useRacialAbility() {
        System.out.println(RED + getName() + " unleashes a mighty roar!" + RESET);

        if (rage >= 50) {
            // Different effects based on rage level, removing defense bonuses
            switch(rageLevel) {
                case 1 -> {
                    System.out.println(RED + "RAGE BONUS ACTIVATED!" + RESET);
                    setStrength(getStrength() + 2);
                    System.out.println(RED + "Strength increased by 2!" + RESET);
                }
                case 2 -> {
                    System.out.println(RED + "ENHANCED RAGE BONUS ACTIVATED!" + RESET);
                    setStrength(getStrength() + 4);
                    System.out.println(RED + "Strength increased by 4!" + RESET);
                }
                case 3 -> {
                    System.out.println(RED + "SUPERIOR RAGE BONUS ACTIVATED!" + RESET);
                    setStrength(getStrength() + 6);
                    heal(10);
                    System.out.println(RED + "Strength increased by 6 and healed 10 HP!" + RESET);
                }
                default -> {
                    System.out.println(RED + "ULTIMATE RAGE BONUS ACTIVATED!" + RESET);
                    setStrength(getStrength() + 8);
                    heal(20);
                    System.out.println(RED + "Strength increased by 8 and healed 20 HP!" + RESET);
                }
            }

            // Add berserker strength bonus
            if (berserkerStrength > 0) {
                int bonus = berserkerStrength * 2;
                setStrength(getStrength() + bonus);
                System.out.println(RED + "Berserker training adds " + bonus + " more strength!" + RESET);
            }

            rage = 0; // Reset rage after using ability
        } else {
            System.out.println(YELLOW + "Not enough rage! Current rage: " + rage + "/" + MAX_RAGE + RESET);
            // Small bonus even without full rage
            setStrength(getStrength() + 1);
            System.out.println(RED + "Strength increased by 1!" + RESET);
        }
    }

    /**
     * Increase rage during combat
     */
    public void increaseRage(int amount) {
        // Berserker strength increases rage gain
        int actualAmount = amount + (int)(amount * berserkerStrength * 0.1);

        rage += actualAmount;
        if (rage > MAX_RAGE) {
            rage = MAX_RAGE;
        }

        System.out.println(RED + getName() + " now has " + rage + " rage!" + RESET);

        // Auto-activate rage if at maximum
        if (rage == MAX_RAGE) {
            System.out.println(RED + "Maximum rage reached! Automatically activating rage ability!" + RESET);
            useRacialAbility();
        }
    }

    /**
     * Orks gain rage when taking damage
     */
    @Override
    public void setHealth(int health) {
        int currentHealth = getHealth();
        int damage = currentHealth - health;

        // If taking damage, gain rage proportional to damage
        if (damage > 0) {
            int rageGain = damage / 2;
            increaseRage(rageGain);
        }

        super.setHealth(health);
    }

    /**
     * Orks improve their rage abilities as they level up
     */
    @Override
    public void gainExperience(int expGained) {
        super.gainExperience(expGained);

        // Increase rage level when player levels up
        if (getLevel() % 5 == 0 && rageLevel < 4) {
            rageLevel++;
            System.out.println(RED + "Your rage has grown more powerful! Rage level: " + rageLevel + RESET);
        }

        // Increase berserker strength when player levels up
        if (getLevel() % 3 == 0) {
            berserkerStrength++;
            System.out.println(RED + "Your berserker training has improved! Berserker strength: " + berserkerStrength + RESET);
        }
    }

    /**
     * Get the current rage value
     */
    public int getRage() {
        return rage;
    }

    /**
     * Get the current rage level
     */
    public int getRageLevel() {
        return rageLevel;
    }
}