package org.example;

import java.util.*;

public class Machine {
    public Scanner sc = new Scanner(System.in);
    private State state = State.IDLE;
    private double milk = 10;
    private double water = 20;
    private int beans = 30;

    private int cups = 30;
    private double cash = 0;
    private int coffeeCount = 0;


    public State getState() {
        return state;
    }

    public void chooseAction(){
        System.out.println("Select your action!");
        System.out.println("0. Exit");
        System.out.println("1. Make Coffee");
        System.out.println("2. Service");
        System.out.println("3. Statistics");
        int answer =  Integer.parseInt(sc.nextLine());
        switch (answer){
            case 0 -> state = State.EXIT;
            case 1 -> state = State.RUNNING;
            case 2 -> state = State.SERVICE;
            case 3 -> state = State.STATS;
        }
    }

    public void chooseCoffee(){
        System.out.println("Select your coffee!");
        System.out.println("0. Back");
        for (CoffeeTypes types: CoffeeTypes.values()){
            System.out.printf("%d. %s\n", types.getId(), types.getName());
        }
        int answer = Integer.parseInt(sc.nextLine());
        Optional<CoffeeTypes> chosenOne = Arrays.stream(CoffeeTypes.values()).filter(coffeeTypes -> coffeeTypes.getId() == answer).findFirst();
        chosenOne.ifPresent(this::makeCoffee);
        state = State.IDLE;
    }

    public void makeCoffee(CoffeeTypes coffeeType){
        if (isCanMakeCoffee(coffeeType)){
            milk -= coffeeType.getMilk();
            water -= coffeeType.getWater();
            beans -= coffeeType.getBeans();
            cups--;
            cash += coffeeType.getPrice();
            coffeeCount++;
            System.out.printf("Enjoy your %s!\n", coffeeType.getName());
            System.out.println();
            state = State.IDLE;
        }
    }

    public void showStats (){
        System.out.printf("Total amount of coffees made: %d\n", coffeeCount);
        System.out.printf("Total amount of cash: %.2f\n", cash);
        System.out.println();
        System.out.printf("Remaining milk: %.2f \n", milk);
        System.out.printf("Remaining water: %.2f \n", water);
        System.out.printf("Remaining beans: %d \n", beans);
        System.out.printf("Remaining cups: %d \n", cups);
        System.out.println();
        state = State.IDLE;
    }

    public void runService(){
        System.out.println("Choose what you want to refill:");
        System.out.println("0. Back");
        System.out.println("1. Milk");
        System.out.println("2. Water");
        System.out.println("3. Beans");
        System.out.println("4. Cups");
        System.out.println();

        int answer = Integer.parseInt(sc.nextLine());
        if (answer == 0){
            state = State.IDLE;
        } else {
            addValues(answer);
        }
    }

    private boolean isCanMakeCoffee(CoffeeTypes coffeeType){
        List<String> missingItems = new ArrayList<>();
        if (milk - coffeeType.getMilk() < 0) missingItems.add("milk");
        if (water - coffeeType.getWater() < 0) missingItems.add("water");
        if (beans - coffeeType.getBeans() < 0) missingItems.add("beans");
        if (cups - 1 < 0) missingItems.add("cups");

        if (missingItems.size() > 0){
            System.out.printf("Sorry, can't make coffee, no %s \n", missingItems.get(0));
            state = State.IDLE;
            return false;
        }
        return true;
    }

    private void addValues(int item){
        System.out.println("How much you would like to add?");
        double answer = Double.parseDouble(sc.nextLine());
        switch (item) {
            case 1 -> milk += answer;
            case 2 -> water += answer;
            case 3 -> beans += answer;
            case 4 -> cups += answer;
        }
        System.out.println("Done!");
        System.out.println();
        state = State.SERVICE;
    }


}
