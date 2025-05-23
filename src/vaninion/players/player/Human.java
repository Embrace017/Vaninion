package vaninion.players.player;

import vaninion.players.Player;
import static vaninion.ColoredConsole.*;

/**
 * Human class with enhanced racial abilities
 * Humans are versatile and adaptable, with bonuses to charisma and wisdom
 */
public class Human extends Player {
    private String bonusSkill;
    private int diplomacyLevel;
    private int adaptabilityBonus;

    public Human(String name) {
        super(name);
        setHealth(getMaxHealth() + 10); // Slight health bonus
        setMana(getMana() + 20);
        setWisdom(getWisdom() + 9);     // Humans are wise
        setCharisma(getCharisma() + 9); // Humans are charismatic

        this.bonusSkill = "diplomacy";
        this.diplomacyLevel = 1;
        this.adaptabilityBonus = 0;
    }

    /**
     * Humans can use diplomacy to gain advantages in various situations
     */
    public void useRacialAbility() {
        System.out.println(YELLOW + getName() + " uses their " + bonusSkill + " (Level " + diplomacyLevel + ") to influence others." + RESET);

        // Different effects based on diplomacy level, increased wisdom and charisma effects to compensate for removed defense
        switch(diplomacyLevel) {
            case 1 -> {
                setCharisma(getCharisma() + 2);
                System.out.println(GREEN + "Your charisma is temporarily increased by 2!" + RESET);
            }
            case 2 -> {
                setCharisma(getCharisma() + 3);
                System.out.println(GREEN + "Your charisma is temporarily increased by 3!" + RESET);
            }
            case 3 -> {
                setCharisma(getCharisma() + 3);
                setWisdom(getWisdom() + 2);
                System.out.println(GREEN + "Your charisma is temporarily increased by 3 and wisdom by 2!" + RESET);
            }
            default -> {
                setCharisma(getCharisma() + 4);
                setWisdom(getWisdom() + 3);
                System.out.println(GREEN + "Your charisma is temporarily increased by 4 and wisdom by 3!" + RESET);
            }
        }
    }

    /**
     * Humans gain adaptability bonuses as they level up
     */
    @Override
    public void gainExperience(int expGained) {
        super.gainExperience(expGained);

        // Humans get bonus experience due to adaptability
        int adaptabilityExp = (int)(expGained * 0.1 * (1 + adaptabilityBonus * 0.05));
        if (adaptabilityExp > 0) {
            System.out.println(BLUE + "Human adaptability grants " + adaptabilityExp + " bonus experience!" + RESET);
            super.gainExperience(adaptabilityExp);
        }

        // Increase diplomacy level when player levels up
        if (getLevel() % 5 == 0 && diplomacyLevel < 4) {
            diplomacyLevel++;
            System.out.println(YELLOW + "Your diplomacy skill has improved to level " + diplomacyLevel + "!" + RESET);
        }

        // Increase adaptability bonus when player levels up
        if (getLevel() % 3 == 0) {
            adaptabilityBonus++;
            System.out.println(BLUE + "Your adaptability has increased! You now gain more bonus experience." + RESET);
        }
    }

    /**
     * Humans have a chance to avoid damage through quick thinking
     * Change from damage reduction to health recovery
     */
    @Override
    public void setHealth(int health) {
        int currentHealth = getHealth();
        int damage = currentHealth - health;

        // Instead of reducing damage, have a chance to heal a bit when taking damage
        if (damage > 0 && Math.random() < getWisdom() * 0.03) {
            int healAmount = Math.max(1, getWisdom());
            health += healAmount;
            System.out.println(CYAN + "Your adaptability allows you to quickly recover " + healAmount + " health!" + RESET);
        }

        super.setHealth(health);
    }
}