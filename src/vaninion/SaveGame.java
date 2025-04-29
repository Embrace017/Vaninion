package vaninion;

import vaninion.players.Player;
import vaninion.players.player.Human;
import vaninion.players.player.Ork;
import vaninion.players.player.Viking;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static vaninion.ColoredConsole.*;

/**
 * Handles saving and loading game state
 */
public class SaveGame {
    private static final String SAVE_DIRECTORY = "saves";
    private static final String FILE_EXTENSION = ".sav";

    /**
     * Initialize the save system by creating the save directory if it doesn't exist
     */
    public static void initialize() {
        try {
            Files.createDirectories(Paths.get(SAVE_DIRECTORY));
        } catch (IOException e) {
            System.out.println(RED + "Error creating save directory: " + e.getMessage() + RESET);
        }
    }

    /**
     * Save the player's game state to a file
     *
     * @param player The player to save
     * @return true if the save was successful, false otherwise
     */
    public static boolean saveGame(Player player) {
        String fileName = SAVE_DIRECTORY + File.separator + player.getName() + FILE_EXTENSION;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            // Save player class type
            oos.writeObject(player.getClass().getName());

            // Save player name
            oos.writeObject(player.getName());

            // Save player stats
            oos.writeInt(player.getMoney());
            oos.writeInt(player.getLevel());
            oos.writeInt(player.getExperience());
            oos.writeInt(player.getSkillPoints());
            oos.writeInt(player.getHealth());
            oos.writeInt(player.getBaseMaxHealth()); // Save base max health without equipment bonuses
            oos.writeInt(player.getMana());
            oos.writeInt(player.getMaxMana());
            oos.writeInt(player.getBaseStrength()); // Save base strength without equipment bonuses
            oos.writeInt(player.getMaxStrength());
            oos.writeInt(player.getBaseDefense()); // Save base defense without equipment bonuses
            oos.writeInt(player.getMaxDefense());
            oos.writeInt(player.getWisdom());
            oos.writeInt(player.getCharisma());

            // Save skill stats
            oos.writeInt(player.getFishingLevel());
            oos.writeInt(player.getFishingExp());
            oos.writeInt(player.getCookingLevel());
            oos.writeInt(player.getCookingExp());
            oos.writeInt(player.getMiningLevel());
            oos.writeInt(player.getMiningExp());

            // Save inventories
            oos.writeObject(player.inventory);
            oos.writeObject(player.resourceInventory);

            // Save equipment status (as a string for now)
            oos.writeObject(player.getEquipmentStatus());

            System.out.println(GREEN + "Game saved successfully!" + RESET);
            return true;
        } catch (IOException e) {
            System.out.println(RED + "Error saving game: " + e.getMessage() + RESET);
            return false;
        }
    }

    /**
     * Load a player's game state from a file
     *
     * @param saveName The name of the save file to load
     * @return The loaded player, or null if the load failed
     */
    public static Player loadGame(String saveName) {
        String fileName = SAVE_DIRECTORY + File.separator + saveName + FILE_EXTENSION;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            // Load player class type
            String className = (String) ois.readObject();

            // Load player name
            String playerName = (String) ois.readObject();

            // Create a temporary player to clear equipment
            // This prevents health from stacking up when loading a game
            Player tempPlayer = new Human("temp");
            tempPlayer.unequipAllItems();

            // Create player instance based on class type
            Player player;
            switch (className) {
                case "vaninion.players.player.Human" -> player = new Human(playerName);
                case "vaninion.players.player.Ork" -> player = new Ork(playerName);
                case "vaninion.players.player.Viking" -> player = new Viking(playerName);
                default -> throw new ClassNotFoundException("Unknown player class: " + className);
            }

            // Load player stats
            player.setMoney(ois.readInt());
            player.setLevel(ois.readInt());
            player.setExperience(ois.readInt());
            player.setSkillPoints(ois.readInt());
            player.setHealth(ois.readInt());

            // Read and set the base maxHealth (without equipment bonuses)
            int savedMaxHealth = ois.readInt();
            player.setMaxHealth(savedMaxHealth);

            player.setMana(ois.readInt());
            player.setMaxMana(ois.readInt());

            // Read and set the base strength (without equipment bonuses)
            int savedStrength = ois.readInt();
            player.setStrength(savedStrength);

            player.setMaxStrength(ois.readInt());

            // Read and set the base defense (without equipment bonuses)
            int savedDefense = ois.readInt();
            player.setDefense(savedDefense);

            player.setMaxDefense(ois.readInt());
            player.setWisdom(ois.readInt());
            player.setCharisma(ois.readInt());

            // Load skill stats
            player.setFishingLevel(ois.readInt());
            player.setFishingExp(ois.readInt());
            player.setCookingLevel(ois.readInt());
            player.setCookingExp(ois.readInt());
            player.setMiningLevel(ois.readInt());
            player.setMiningExp(ois.readInt());

            // Load inventories
            @SuppressWarnings("unchecked")
            Map<String, Integer> inventory = (Map<String, Integer>) ois.readObject();
            @SuppressWarnings("unchecked")
            Map<String, Integer> resourceInventory = (Map<String, Integer>) ois.readObject();

            player.inventory = inventory;
            player.resourceInventory = resourceInventory;

            // Read equipment status (but we don't use it directly)
            String equipmentStatus = (String) ois.readObject();

            // Re-equip items from inventory
            // Create a copy of the keys to avoid ConcurrentModificationException
            List<String> itemNames = new ArrayList<>(inventory.keySet());
            for (String itemName : itemNames) {
                if (isEquipmentItem(itemName) && inventory.getOrDefault(itemName, 0) > 0) {
                    player.equipItem(itemName);
                    // Note: equipItem already removes the item from inventory
                }
            }

            System.out.println(GREEN + "Game loaded successfully!" + RESET);
            return player;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(RED + "Error loading game: " + e.getMessage() + RESET);
            return null;
        }
    }

    /**
     * Get a list of all available save files
     *
     * @return A list of save file names (without extension)
     */
    public static List<String> getSaveFiles() {
        List<String> saveFiles = new ArrayList<>();

        try {
            Files.createDirectories(Paths.get(SAVE_DIRECTORY));
            Files.list(Paths.get(SAVE_DIRECTORY))
                    .filter(path -> path.toString().endsWith(FILE_EXTENSION))
                    .forEach(path -> {
                        String fileName = path.getFileName().toString();
                        saveFiles.add(fileName.substring(0, fileName.length() - FILE_EXTENSION.length()));
                    });
        } catch (IOException e) {
            System.out.println(RED + "Error listing save files: " + e.getMessage() + RESET);
        }

        return saveFiles;
    }

    /**
     * Check if an item is an equipment item based on its name
     *
     * @param itemName The name of the item to check
     * @return true if the item is an equipment item, false otherwise
     */
    private static boolean isEquipmentItem(String itemName) {
        return itemName.contains("helmet") ||
                itemName.contains("mask") ||
                itemName.contains("hat") ||
                itemName.contains("chestplate") ||
                itemName.contains("leggings") ||
                itemName.contains("shield") ||
                itemName.contains("staff") ||
                itemName.contains("sword");
    }

    /**
     * Delete a save file
     *
     * @param saveName The name of the save file to delete
     * @return true if the deletion was successful, false otherwise
     */
    public static boolean deleteSave(String saveName) {
        String fileName = SAVE_DIRECTORY + File.separator + saveName + FILE_EXTENSION;

        try {
            Files.deleteIfExists(Paths.get(fileName));
            System.out.println(GREEN + "Save file deleted successfully!" + RESET);
            return true;
        } catch (IOException e) {
            System.out.println(RED + "Error deleting save file: " + e.getMessage() + RESET);
            return false;
        }
    }
}
