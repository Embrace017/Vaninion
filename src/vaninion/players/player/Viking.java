package vaninion.players.player;

import vaninion.combat.Combat;
import vaninion.monsters.Monster;
import vaninion.players.Player;
import static vaninion.ColoredConsole.*;

/**
 * Viking class with enhanced racial abilities
 * Vikings are hardy warriors who can enter a berserk state to deal more damage
 */
public class Viking extends Player {
    private int berserkStacks;
    private int MAX_BERSERK_STACKS;
    private int battleFury;
    private int vikingResilience;

    public Viking(String name) {
        super(name);
        this.berserkStacks = 0;
        this.MAX_BERSERK_STACKS = 5; // Adjusted max stacks for faster build-up
        this.battleFury = 0;
        this.vikingResilience = 1; // Starting with some resilience

        // Set Viking-specific stats
        setStrength(5); // Increased base strength




    }

    /**
     * Vikings can attempt to enter a berserk state.
     */
    public void useRacialAbility(Monster monster) {
        if (berserkStacks < MAX_BERSERK_STACKS) {
            // Increased base chance and wisdom scaling for berserk
            double chance = 0.3 + (getWisdom() * 0.05);
            if (Math.random() < chance) {
                this.berserkStacks++;
                System.out.println(BLUE + getName() + " enters a berserk state! (Stacks: " + berserkStacks + RESET);

                // Apply battle fury bonus PER STACK GAINED
                if (battleFury > 0) {
                    int strengthBonus = battleFury;
                    setStrength(getStrength() + strengthBonus);
                    System.out.println(BLUE + "Battle fury grants +" + strengthBonus + " strength!" + RESET);
                }
            } else {
                System.out.println(YELLOW + getName() + " attempts to enhance berserk state but fails." + RESET);
            }
        } else {
            // Unleash fury at max stacks
            System.out.println(RED + getName() + " unleashes a furious attack at max berserk!" + RESET);
            // Damage bonus based on stacks and battle fury
            int damageBonus = berserkStacks * getLevel() + battleFury * berserkStacks;
            monster.setHealth(monster.getHealth() - damageBonus); // Apply bonus for the current attack. fix?
            System.out.println(BRIGHT_RED + "Unleashed fury grants +" + damageBonus + " damage!" + RESET);
            this.berserkStacks = 0; // Reset stacks after unleashing
        }
    }


    /**
     * Vikings have a chance to gain berserk stacks when attacking.
     */
    public void onAttack() {
        double chance = 0.1 + (battleFury * 0.05); // Slightly reduced scaling
        if (Math.random() < chance && berserkStacks < MAX_BERSERK_STACKS) {
            berserkStacks++;
            System.out.println(BLUE + getName() + "'s battle prowess grants an additional berserk stack! (Stacks: " + berserkStacks + ")" + RESET);
        }
    }

    /**
     * Vikings have increased resilience when at low health.
     */
    @Override
    public void setHealth(int health) {
        super.setHealth(health);

        if (getHealth() <= getMaxHealth() * 0.3) {
            int healAmount = vikingResilience * getWisdom();
            if (healAmount > 0) {
                heal(healAmount);
                System.out.println(BLUE + getName() + "'s viking resilience activates! Healed for " + healAmount + " health!" + RESET);
            }
        }
    }

    /**
     * Vikings improve their abilities as they level up.
     */
    @Override
    public void gainExperience(int expGained) {
        super.gainExperience(expGained);

        if (getLevel() % 3 == 0 && battleFury < 5) { // Increased frequency
            battleFury++;
            System.out.println(BLUE + "Your battle fury has increased! Battle fury: " + battleFury + RESET);
        }

        if (getLevel() % 4 == 0 && vikingResilience < 5) { // Increased frequency
            vikingResilience++;
            System.out.println(BLUE + "Your viking resilience has increased! Resilience: " + vikingResilience + RESET);
        }
    }

    public int getBerserkStacks() {
        return berserkStacks;
    }

    public int getMaxBerserkStacks() {
        return MAX_BERSERK_STACKS;
    }

    public int getBattleFury() {
        return battleFury;
    }

    public int getVikingResilience() {
        return vikingResilience;
    }

    public void resetBerserkStacks() {
        if (berserkStacks > 0) {
            System.out.println(BLUE + getName() + "'s berserker rage subsides. (Stacks reset from " + berserkStacks + " to 0)" + RESET);
            this.berserkStacks = 0;
        }
    }

    public void setBerserkStacks(int i) {
        this.berserkStacks = i;
    }
}