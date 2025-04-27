package vaninion.combat;

import vaninion.players.Player;
import vaninion.monsters.Monster;
import static vaninion.ColoredConsole.*;

/**
 * Represents a magical spell that can be cast by players
 */
public class Spell {
    private final String name;
    private final String description;
    private final int manaCost;
    private final SpellType type;
    private final int power;
    
    public enum SpellType {
        DAMAGE,
        HEAL,
        BUFF,
        DEBUFF
    }
    
    public Spell(String name, String description, int manaCost, SpellType type, int power) {
        this.name = name;
        this.description = description;
        this.manaCost = manaCost;
        this.type = type;
        this.power = power;
    }
    
    /**
     * Cast the spell, applying its effects based on the spell type
     * @param caster The player casting the spell
     * @param target The monster target (for damage/debuff spells)
     * @return True if the spell was cast successfully, false otherwise
     */
    public boolean cast(Player caster, Monster target) {
        // Check if player has enough mana
        if (caster.getMana() < manaCost) {
            System.out.println(RED + "Not enough mana to cast " + name + "!" + RESET);
            return false;
        }
        
        // Deduct mana cost
        caster.setMana(caster.getMana() - manaCost);
        
        // Apply spell effects based on type
        switch (type) {
            case DAMAGE -> {
                // Calculate damage based on spell power and player's wisdom
                int damage = power + (caster.getWisdom() * 2);
                target.setHealth(target.getHealth() - damage);
                System.out.println(PURPLE + "You cast " + name + " for " + damage + " damage!" + RESET);
                
                // Special effects for specific spells
                if (name.equals("Fireball")) {
                    System.out.println(RED + "🔥 The fireball explodes in a burst of flames! 🔥" + RESET);
                } else if (name.equals("Ice Spike")) {
                    System.out.println(CYAN + "❄ The ice spike freezes your target! ❄" + RESET);
                } else if (name.equals("Lightning Bolt")) {
                    System.out.println(YELLOW + "⚡ Lightning courses through your target! ⚡" + RESET);
                } else if (name.equals("Soul Drain")) {
                    int healthGain = damage / 3;
                    caster.setHealth(Math.min(caster.getMaxHealth(), caster.getHealth() + healthGain));
                    System.out.println(PURPLE + "You drain " + healthGain + " health from your target!" + RESET);
                }
            }
            case HEAL -> {
                // Calculate healing based on spell power and player's wisdom
                int healing = power + caster.getWisdom();
                caster.setHealth(Math.min(caster.getMaxHealth(), caster.getHealth() + healing));
                System.out.println(GREEN + "You cast " + name + " and heal for " + healing + " health!" + RESET);
            }
            case BUFF -> {
                // Apply buffs to the player
                if (name.equals("Iron Skin")) {
                    caster.setTempStrength(caster.getTempStrength() + power);
                    System.out.println(BLUE + "Your skin hardens, increasing your strength by " + power + "!" + RESET);
                } else if (name.equals("Arcane Intellect")) {
                    caster.addEffect("wisdom", 3, power);
                    System.out.println(PURPLE + "Your mind sharpens, increasing your wisdom!" + RESET);
                } else {
                    System.out.println(GREEN + "You feel empowered by " + name + "!" + RESET);
                }
            }
            case DEBUFF -> {
                // Apply debuffs to the monster
                System.out.println(RED + "You cast " + name + " on " + target.getName() + "!" + RESET);
                if (name.equals("Weaken")) {
                    target.setStrength(target.getStrength() - power);
                    System.out.println(PURPLE + target.getName() + "'s strength is reduced by " + power + "!" + RESET);
                } else if (name.equals("Vulnerability")) {
                    target.setDefence(target.getDefence() - power);
                    System.out.println(PURPLE + target.getName() + "'s defense is reduced by " + power + "!" + RESET);
                }
            }
        }
        
        return true;
    }
    
    // Static spell definitions
    public static final Spell FIREBALL = new Spell("Fireball", "Launches a ball of fire at the target", 15, SpellType.DAMAGE, 20);
    public static final Spell ICE_SPIKE = new Spell("Ice Spike", "Impales the target with a spike of ice", 20, SpellType.DAMAGE, 25);
    public static final Spell LIGHTNING_BOLT = new Spell("Lightning Bolt", "Strikes the target with lightning", 25, SpellType.DAMAGE, 30);
    public static final Spell SOUL_DRAIN = new Spell("Soul Drain", "Drains life from the target to heal yourself", 30, SpellType.DAMAGE, 35);
    
    public static final Spell HEALING_TOUCH = new Spell("Healing Touch", "Heals your wounds", 15, SpellType.HEAL, 20);
    public static final Spell REJUVENATION = new Spell("Rejuvenation", "Restores a significant amount of health", 30, SpellType.HEAL, 40);
    
    public static final Spell IRON_SKIN = new Spell("Iron Skin", "Hardens your skin, increasing strength", 20, SpellType.BUFF, 3);
    public static final Spell ARCANE_INTELLECT = new Spell("Arcane Intellect", "Sharpens your mind, increasing wisdom", 25, SpellType.BUFF, 2);
    
    public static final Spell WEAKEN = new Spell("Weaken", "Reduces the target's strength", 20, SpellType.DEBUFF, 5);
    public static final Spell VULNERABILITY = new Spell("Vulnerability", "Reduces the target's defense", 25, SpellType.DEBUFF, 5);
    
    // Getters
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getManaCost() {
        return manaCost;
    }
    
    public SpellType getType() {
        return type;
    }
    
    public int getPower() {
        return power;
    }
    
    @Override
    public String toString() {
        return name + " (" + manaCost + " mana) - " + description;
    }
}