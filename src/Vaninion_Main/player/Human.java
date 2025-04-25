package Vaninion_Main.player;

public class Human extends Player {
    private String bonusSkill;
    public Human(String name) {
        super(name);
        setHealth(getMaxHealth() + 50);
        bonusSkill = "diplomacy";
    }

    public void useRacialAbility() {
        System.out.println(getName() + " uses their " + bonusSkill + " to influence others.");
        setCharisma(getCharisma() + 1);  // Temporary charisma boost
    }
}
