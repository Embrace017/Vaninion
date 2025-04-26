package vaninion.adventure;

import vaninion.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static vaninion.ColoredConsole.*;

public class Riddle {

    private List<String> riddles;
    private List<String> answers;
    private Random random;

    public Riddle() {
        riddles = new ArrayList<>();
        answers = new ArrayList<>();
        random = new Random();
        loadRiddles();
    }

    private void loadRiddles() {
        // Add your riddles and corresponding answers here
        riddles.add("What has an eye, but cannot see?");
        answers.add("needle");

        riddles.add("What is full of holes but still holds water?");
        answers.add("sponge");

        riddles.add("What question can you never answer yes to?");
        answers.add("are you asleep");

        riddles.add("What has to be broken before you can use it?");
        answers.add("egg");

        riddles.add("What is always in front of you but can’t be seen?");
        answers.add("future");

        riddles.add("What can travel around the world while staying in the same corner?");
        answers.add("stamp");

        riddles.add("What has hands but can’t clap?");
        answers.add("clock");

        riddles.add("What has a head, a tail, is brown, and has no legs?");
        answers.add("penny");

        riddles.add("The more you take, the more you leave behind. What am I?");
        answers.add("footsteps");

        riddles.add("I’m tall when I’m young, and I’m short when I’m old. What am I?");
        answers.add("candle");

        riddles.add("What has to be kept but can never be thrown?");
        answers.add("promise");

        riddles.add("I speak without a mouth and hear without ears. What am I?");
        answers.add("echo");

        riddles.add("What has cities, but no houses; forests, but no trees; and rivers, but no water?");
        answers.add("map");

        riddles.add("What can you keep after giving it to someone?");
        answers.add("your word");

        riddles.add("It belongs to you, but other people use it more than you do. What is it?");
        answers.add("your name");

        riddles.add("What has one eye but cannot see, serves to help you when you need to sew?");
        answers.add("needle");

        riddles.add("What starts with a T, ends with a T, and has T in it?");
        answers.add("teapot");

        riddles.add("What has a neck but no head?");
        answers.add("bottle");

        riddles.add("I shave every day, but my beard stays the same. What am I?");
        answers.add("barber");

        riddles.add("You see me once in June, twice in November, and not at all in May. What am I?");
        answers.add("letter e");

        riddles.add("What comes once in a minute, twice in a moment, but never in a thousand years?");
        answers.add("letter m");

        riddles.add("What is so fragile that saying its name breaks it?");
        answers.add("silence");

        riddles.add("I’m light as a feather, yet the strongest person can’t hold me for very long. What am I?");
        answers.add("breath");

        riddles.add("The person who makes it sells it. The person who buys it never uses it. The person who uses it never knows they’re using it. What is it?");
        answers.add("coffin");

        riddles.add("Where does today come before yesterday?");
        answers.add("dictionary");

        riddles.add("I’m found in the sky, yet I’m part of the sea. What am I?");
        answers.add("cloud");

        riddles.add("I am a word, I contain six letters, and when you remove one letter, I become twelve. What am I?");
        answers.add("dozens");

        riddles.add("What gets sharper the more you use it?");
        answers.add("brain");

        riddles.add("The more of me you take, the more you leave behind. What am I?");
        answers.add("footsteps");

        riddles.add("I come down but never go up. What am I?");
        answers.add("rain");

        riddles.add("The man who invented it doesn’t want it, the man who bought it doesn’t need it, and the man who needs it doesn’t know it. What is it?");
        answers.add("coffin");
    }

    public boolean playRiddle(Player player) {
        if (riddles.isEmpty()) {
            System.out.println("No riddles available right now.");
            return true; // Consider what should happen if no riddles
        }
        try {

            if (player.inventory.get("gold coin") < 10) {
                System.out.println("You need 10 gold coins to attempt this riddle.");
                return false;
            }
        }catch (Exception e){
            System.out.println("You need 10 gold coins to attempt this riddle.");
            return false;
        }


        int randomIndex = random.nextInt(riddles.size());
        String currentRiddle = riddles.get(randomIndex);
        String correctAnswer = answers.get(randomIndex);

        System.out.println("\nHere's a riddle for you, " + player.getName() + ":");
        System.out.println(currentRiddle);
        System.out.print("Your answer: ");

        Scanner scanner = new Scanner(System.in);
        String playerAnswer = scanner.nextLine().trim().toLowerCase();

        if (riddles.isEmpty()) {
            System.out.println(BLUE + BOLD +"You have completed all the riddles! Incredible");
            player.setWisdom(player.getWisdom() + 10);
        }
        if (playerAnswer.equals(correctAnswer.toLowerCase())) {
            System.out.println(GREEN + "Correct! You solved the riddle.\n Wisdom increased!\n 10 Gold coins removed." + RESET);
            player.setWisdom(player.getWisdom() + 1);
            player.removeItem("gold coin", 10);
            riddles.remove(randomIndex);
            answers.remove(randomIndex);
            return true; // Player answered correctly
        } else {
            System.out.println("Incorrect.");
            player.removeItem("gold coin", 1);
            System.out.println(RED + "One gold coin removed. Better luck next time.");
            return false; // Player answered incorrectly
        }
    }
}
