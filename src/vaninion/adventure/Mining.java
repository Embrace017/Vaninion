package vaninion.adventure;

import vaninion.players.Player;
import java.util.*;

import static vaninion.ColoredConsole.*;

public class Mining {
    private Random random = new Random();
    private Map<String, Double> ores = new HashMap<>();

    public Mining() {
        // Initialize ores map in constructor
        ores.put("stone", 20.0);
        ores.put("iron ore", 20.0);
        ores.put("coal", 20.0);
        ores.put("copper ore", 10.0);
        ores.put("crystal", 10.0);
        ores.put("gold ore", 10.0);
        ores.put("gorganite ore", 10.0);
        ores.put("diamond", 1.0);
        ores.put("vaninite ore", 0.5);
    }

    // New method to handle mining operation
    public void mine(Player player) {
        if (!player.hasItem("gold coin")) {
            System.out.println("You need at least one Gold Coin to enter the mine");
            return;
        }
        player.removeItem("gold coin", 1);

        String ore = player.mapRng(ores);
        switch (ore) {
            case "stone", "iron ore", "coal" -> mineOre(player, ore, 10, 2);
            case "crystal", "gold ore", "gorganite ore" -> mineOre(player, ore, 5, 10);
            case "diamond" -> mineOre(player, ore, 3, 50);
            case "vaninite ore" -> mineOre(player, ore, 1, 250);
            default -> {
                System.out.println("You found 5 gold coins on the ground!");
                player.addItem("gold coin", 5);
            }
        }
    }
    public void mineOre(Player player, String ore, int amount, int expMultplier) {
        int oreAmount = Math.max(1, random.nextInt(amount + player.getMiningLevel()));
        int expAmount = (oreAmount + player.getWisdom()) * expMultplier;
        player.setMiningExp(player.getMiningExp() + expAmount);
        System.out.println(YELLOW + BOLD + "\n~~~ You spot " + RESET + oreAmount + BOLD + PURPLE + " " + ore + YELLOW + " in a nearby vein! ~~~" + RESET);
        player.addItem(ore, oreAmount);
        System.out.println(GREEN + "You gained " + RESET + expAmount + GREEN + " mining experience!" + RESET);
        System.out.println(GREEN + "Your mining experience is now " + RESET + player.getMiningExp() + "/"+ 500 * player.getMiningLevel());
        if (player.getMiningExp() >= 500 * player.getMiningLevel() && player.getMiningLevel() < 20) {
            player.setMiningLevel(player.getMiningLevel() + 1);
            System.out.println(GREEN + "You gained a mining level!" + RESET);
            System.out.println(GREEN + "Your mining level is now " + player.getMiningLevel() + RESET);
            player.setMiningExp(0);
            if (player.getMiningLevel() == 20) {
                System.out.println(GREEN + "You have reached the maximum mining level!" + RESET);
                System.out.println(GREEN + "You are now eligible for the perfect gold ore!" + RESET);
                mineRareOre(player);
            }
        }
        System.out.println(player.getItemCount("gold coin") + YELLOW + " Gold coins remaining" + RESET);
        // Add math to check levels to maybe roll again?
    }
    public void mineUncommonOre(String ore) {

    }
    public void mineRareOre(Player player) {
        System.out.println(GREEN + "You found a rare ore! You can now mine it for extra gold!" + RESET);
        player.addItem("gold coin", random.nextInt(100) + 50);
    }

}
