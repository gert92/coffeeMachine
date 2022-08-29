package org.example;

public class Main {
    public static void main(String[] args) {
        Machine machine = new Machine();

        while (machine.getState() != State.EXIT){
            switch (machine.getState()){
                case EXIT -> System.exit(0);
                case IDLE -> machine.chooseAction();
                case RUNNING -> machine.chooseCoffee();
                case SERVICE -> machine.runService();
                case STATS -> machine.showStats();
            }
        }
    }
}