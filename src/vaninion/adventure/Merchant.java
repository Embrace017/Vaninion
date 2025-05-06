package vaninion.adventure;

import java.util.Random;

public class Merchant {
    private boolean bait = false;

    Random random = new Random();

    public Merchant() {
        if (random.nextInt(10) == 0) ;


    }

    public void trade() {

    }

    public void createMerchant() {
        int x = 0;
        int i = 0;
        while (i < 10) {
            i++;
            x = random.nextInt(2);
            //System.out.println(x);
            switch (x) {
                case 0 -> addMerchantItem();

                case 1 -> System.out.println("You have found a fish!");

                default -> System.out.println("Something went wrong!");

            }
        }
    }
    private void addMerchantItem() {
        boolean bait = false;
        boolean fish = false;
        boolean meat = false;
        boolean potions = false;
        boolean sword = false;
        boolean wool = false;
        boolean wood = false;
        boolean rocks = false;
        boolean gorganiteSword = false;
        boolean dragonSword  = false;



    }
}