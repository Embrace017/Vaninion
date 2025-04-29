package vaninion.players.player;

import vaninion.players.Player;
import static vaninion.ColoredConsole.*;

/**
 * Viking class with enhanced racial abilities
 * Vikings are hardy warriors who can enter a berserk state to deal more damage
 */
public class Viking extends Player {
    private int berserkStacks;
    private static final int MAX_BERSERK_STACKS = 5;
    private int battleFury;
    private int vikingResilience;

    public Viking(String name) {
        super(name);
        this.berserkStacks = 0;
        this.battleFury = 0;
        this.vikingResilience = 0;

        // Set Viking-specific stats
        setHealth(getHealth() + 15);      // Vikings are hardy
        setMaxHealth(getMaxHealth() + 15); // Increase max health too
        setStrength(getStrength() + 2);   // Vikings are strong
    }


    /**
     * Vikings can enter a berserk state to deal more damage
     */
    public void useRacialAbility() {
        if (berserkStacks < MAX_BERSERK_STACKS) {
            this.berserkStacks++;
            System.out.println(BLUE + getName() + " enters a berserk state! (Stacks: " + berserkStacks + ")" + RESET);

            // Apply effects based on battle fury level
            if (battleFury > 0) {
                int strengthBonus = battleFury;
                setStrength(getStrength() + strengthBonus);
                System.out.println(BLUE + "Battle fury grants +" + strengthBonus + " strength!" + RESET);
            }

            // Remove defense bonuses from vikingResilience
            if (vikingResilience > 0) {
                System.out.println(BLUE + "Viking resilience grants increased healing!" + RESET);
            }
        } else {
            // When at max stacks, unleash a powerful attack
            System.out.println(RED + getName() + " unleashes the fury of Valhalla!" + RESET);
            int healthBonus = 5 * vikingResilience;
            heal(healthBonus);
            System.out.println(GREEN + "Healed for " + healthBonus + " health!" + RESET);

            // Keep one stack after unleashing
            this.berserkStacks = 1;
        }
    }

    /**
     * Vikings have a chance to gain berserk stacks when attacking
     */
    public void onAttack() {
        // Chance to gain a berserk stack based on battle fury
        double chance = 0.1 + (battleFury * 0.05);
        if (Math.random() < chance && berserkStacks < MAX_BERSERK_STACKS) {
            berserkStacks++;
            System.out.println(BLUE + getName() + "'s battle prowess grants an additional berserk stack! (Stacks: " + berserkStacks + ")" + RESET);
        }
    }

    /**
     * Vikings have increased resilience when at low health
     */
    @Override
    public void setHealth(int health) {
        super.setHealth(health);

        // When below 30% health, gain healing instead of defense
        if (getHealth() <= getMaxHealth() * 0.3) {
            int healAmount = vikingResilience * 2;
            if (healAmount > 0) {
                heal(healAmount);
                System.out.println(BLUE + getName() + "'s viking resilience activates! Healed for " + healAmount + " health!" + RESET);
            }
        }
    }

    /**
     * Vikings improve their abilities as they level up
     */
    @Override
    public void gainExperience(int expGained) {
        super.gainExperience(expGained);

        // Increase battle fury when player levels up
        if (getLevel() % 4 == 0 && battleFury < 5) {
            battleFury++;
            System.out.println(BLUE + "Your battle fury has increased! Battle fury: " + battleFury + RESET);
        }

        // Increase viking resilience when player levels up
        if (getLevel() % 5 == 0 && vikingResilience < 5) {
            vikingResilience++;
            System.out.println(BLUE + "Your viking resilience has increased! Resilience: " + vikingResilience + RESET);
        }
    }

    /**
     * Get the current berserk stacks
     */
    public int getBerserkStacks() {
        return berserkStacks;
    }

    /**
     * Get the current battle fury level
     */
    public int getBattleFury() {
        return battleFury;
    }

    /**
     * Get the current viking resilience level
     */
    public int getVikingResilience() {
        return vikingResilience;
    }

    /**
     * Reset berserker stacks after combat
     */
    public void resetBerserkStacks() {
        if (berserkStacks > 0) {
            System.out.println(BLUE + getName() + "'s berserker rage subsides. (Stacks reset from " + berserkStacks + " to 0)" + RESET);
            this.berserkStacks = 0;
        }
    }
}