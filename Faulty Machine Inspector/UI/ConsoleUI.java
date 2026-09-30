package UI;

import Machines.CoolingMachine;
import Machines.Machine;
import java.util.Scanner;
import Machines.PowerMachine;
import Machines.HeatingMachine;
import java.util.concurrent.ThreadLocalRandom;

public class ConsoleUI {
    public static void mainMenu(Scanner scanner) {
        Machine machine = new CoolingMachine();

        boolean running = true;
        int userChoice = 0;
        int machineSelectionNum;

        while (running) {
            System.out.println("Welcome to The Faulty Machine Inspector v0.2.0");
            System.out.println("\nThis is a game where you need to inspect machines and figure out what is wrong with them!");
            System.out.println("What do you wish to do?\n\n1. Play Game\n2. Learn How to Play\n3. Future Features\n4. Exit Game");

            while (true) {
                if (scanner.hasNextInt()) {
                    userChoice = scanner.nextInt();
                    if (userChoice >= 1 && userChoice <= 4) {
                        scanner.nextLine();
                        break;
                    }
                } else {
                    scanner.next();
                }

                System.out.println("You did not select a valid input. Enter 1, 2, 3, or 4.");
            }

            switch (userChoice) {
                case 1 -> {
                    for (int i = 0; i < machine.setMaxGameRounds(); i++) {
                    machineSelectionNum = ThreadLocalRandom.current().nextInt(0, 26);

                    if (machineSelectionNum < 8) {
                        machine = new CoolingMachine();
                        System.out.println("A faulty cooling machine needs your attention.\n");
                    } else if (machineSelectionNum < 16) {
                        machine = new PowerMachine();
                        System.out.println("A faulty power machine needs your attention.\n");
                    } else {
                        machine = new HeatingMachine();
                        System.out.println("A faulty heating machine needs your attention\n");
                    }

                    machine.setHintLimit();
                    machine.setMaxScore();
                    machine.setPointsForRepair();

                    System.out.println("--- Inspection Report ---");
                    System.out.println(machine.getInspectionReport());
                    System.out.println("-------------------------");
                    System.out.println("Type 'quit' if you wish to quit the game.");

                    while (!machine.isOperational()) {
                        String input = scanner.nextLine().trim().toLowerCase();

                        if (input.equals("help") && machine.getHintUse() < machine.getHintLimit()) {
                            machine.helpCommand();
                            machine.setHelpPointPenalty();
                            machine.increaseHintUse();
                            machine.resetCurrentStreak();

                            System.out.println("Hint Limit: " + machine.getHintLimit());
                            System.out.println("Current Hint: " + machine.getHintUse());
                        }
                        else if (input.equals("quit")) {
                            System.out.println("Thanks for playing!");
                            return;
                        }
                        else if (input.equals("help") && machine.getHintUse() >= machine.getHintLimit()) {
                            System.out.println("You have reached the hint limit...");
                        } 
                        else if (!input.equals(machine.getBadPart())) {
                            System.out.println("That is not the component that needs repaired.");
                            
                            machine.setWrongCompPenalty();
                        } 
                        else if (input.equals(machine.getBadPart())) {
                            machine.repairComponent(input);

                            machine.incrementScore();
                            machine.setCurrentStreak();

                            machine.incrementLevel();
                            System.out.println("Your current level: " + machine.getCurrentLevel());
                            System.out.println("Your score: " + machine.getCurrentScore() + "\nMax possible score: " + machine.getMaxScore());
                            System.out.println("Your streak: " + machine.getCurrentStreak());

                            if (i >= machine.setMaxGameRounds()) {
                                System.out.println("Congrats, the game is over!");
                            }
                        }
                    }

                    }
                    running = false;
                }
                case 2 -> {
                    LearnHowPlay.howToPlay();
                }
                case 3 -> {
                    UpcomingFeatures.newFeatures();
                }
                case 4 -> {
                    System.out.println("You are now exiting the game...");
                    running = false;
                }
            }
        }
    }
}