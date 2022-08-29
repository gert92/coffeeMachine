package org.example;

public enum CoffeeTypes {
    ESPRESSO(1, "Espresso",0.0, 1, 6, 3),
    LATTE(2, "Latte", 0.7, 0.3, 2, 5),
    MILK_COFFEE(3, "Coffee with Milk", 0.4, 0.6, 4, 2),
    AMERICANO(4, "Americano",0.0, 1, 3, 2);

    private final int id;

    private final String name;
    private final double milk;
    private final double water;
    private final int beans;
    private final double price;
    CoffeeTypes(int id, String name, double milk, double water, int beans, double price){
        this.id = id;
        this.name = name;
        this.milk = milk;
        this.water = water;
        this.beans = beans;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMilk() {
        return milk;
    }

    public double getWater() {
        return water;
    }

    public int getBeans() {
        return beans;
    }

    public double getPrice() {
        return price;
    }
}
