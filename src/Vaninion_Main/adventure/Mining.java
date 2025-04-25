package Vaninion_Main.adventure;

import Vaninion_Main.player.Player;
import java.util.*;

import static Vaninion_Main.ColoredConsole.*;

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
            case "stone" -> mineOre(player, ore, 20, 1);
            case "iron ore" -> mineOre(player, ore, 15, 1);
            case "coal" -> mineOre(player, ore, 15, 1);
            case "crystal" -> mineOre(player, ore, 10, 4);
            case "gold ore" -> mineOre(player, ore, 10, 4);
            case "gorganite ore" -> mineOre(player, ore, 10, 4);
            case "diamond" -> mineOre(player, ore, 5, 10);
            case "vaninite ore" -> mineOre(player, ore, 1, 20);
            default -> {
                System.out.println("You found 5 gold coins on the ground!");
                player.addItem("gold coin", 5);
            }
        }
    }
    public void mineOre(Player player, String ore, int amount, int exp) {
        int res = Math.max(1, random.nextInt(amount + player.getMiningLevel()));
        player.setMiningExp(player.getMiningExp() + (res + player.getWisdom()) * exp);
        System.out.println(GREEN + BOLD + "You spotted " + res + " " + ore + " rocks!" + RESET);
        player.addItem(ore, res);
        System.out.println("you now have " + player.getItemCount(ore) + " " + ore + " total" + RESET);
        System.out.println(GREEN + "You gained " + (res + player.getWisdom()) * exp + " mining experience!" + RESET);
        System.out.println(GREEN + player.getItemCount("gold coin") + " Gold coins remaining" + RESET);
        // Add math to check levels to maybe roll again?
    }
    public void mineUncommonOre(String ore) {

    }
    public void mineRareOre(String ore) {

    }
    public void mineMegarareOre(String ore) {

    }
}
