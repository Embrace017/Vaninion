package Vaninion_Main.adventure;

import Vaninion_Main.Player;
import java.util.*;

public class Mining {


    public Mining(Player player) {

        Map<String, Double> ores = new HashMap<>();
        // Common
        ores.put("stone", 20.0);
        ores.put("iron ore", 20.0);
        ores.put("coal", 20.0);
        // Uncommon
        ores.put("copper ore", 10.0);
        ores.put("crystal", 10.0);
        ores.put("gold ore", 10.0);
        ores.put("gorganite ore", 10.0);
        // Rare
        ores.put("diamond", 1.0);
        //Megarare
        ores.put("vaninite ore", 0.2);


        if (player.hasItem("gold coin")) {
            player.removeItem("gold coin", 1);
        } else {
            System.out.println("You need at least one Gold Coin to enter the mine");
            return;
        }
        String ore = player.mapRng(ores);
        switch (ore) {
            // Common
            case "stone" -> mineCommonOre();
            case "iron" -> mineCommonOre();
            case "coal" -> mineCommonOre();
            // Uncommon
            case "crystal" -> mineUncommonOre();
            case "gold" -> mineUncommonOre();
            case "gorganite" -> mineUncommonOre();
            // Rare
            case "diamond" -> mineRareOre();
            // Megarare
            case "vanite" -> mineMegarareOre();
        }
    }
    public void mineCommonOre() {

    }
    public void mineUncommonOre() {

    }
    public void mineRareOre() {

    }
    public void mineMegarareOre() {

    }
}