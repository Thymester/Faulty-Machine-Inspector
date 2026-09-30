package Machines;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Machine {
    protected String name;
    protected ArrayList<Component> components;
    protected int componentToRepair;
    protected String faultyComponent;
    private static final int POINTS_FOR_REPAIR = 100;
    private static int currentLevel = 0;
    private static int userScore = 0;
    private static int maxScore = 0;
    private static int hintLimit = 0;
    private static int hintUse = 0;
    private static int userStreak = 0;
    private static int maxGameRounds = 0;

    public Machine(String name) {
        this.name = name;
        components = new ArrayList<>();
        componentToRepair = 0;
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    protected void chooseFaultyComponent() {
        componentToRepair = ThreadLocalRandom.current().nextInt(components.size());

        for (int i = 0; i < components.size(); i++) {
            components.get(i).setFaulty(i == componentToRepair);
        }

        faultyComponent = components.get(componentToRepair).getName();
    }

    public String getBadPart() {
        return faultyComponent;
    }

    public void helpCommand() {
        System.out.println("It looks like the problem could be relating to a " + faultyComponent + "...");
    }

    public int incrementLevel() {
        return currentLevel += 1;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public int setPointsForRepair() {
        return POINTS_FOR_REPAIR;
    }

    public int incrementScore() {
        return userScore += 100;
    }

    public int setHelpPointPenalty() {
        return userScore -= 20;
    }

    public int setWrongCompPenalty() {
        return userScore -= 5;
    }

    public int getCurrentScore() {
        return userScore;
    }

    public int setMaxScore() {
        if (maxScore != 700) {
            return maxScore = 700;
        }
        return maxScore = 700;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public int setHintLimit() {
        return hintLimit = 3;
    }

    public int getHintLimit() {
        return hintLimit;
    }

    public int increaseHintUse() {
        return hintUse += 1;
    }

    public int getHintUse() {
        return hintUse;
    }

    public int setCurrentStreak() {
        return userStreak += 1;
    }

    public int resetCurrentStreak() {
        return userStreak = 0;
    }

    public int getCurrentStreak() {
        return userStreak;
    }

    public int setMaxGameRounds() {
        return maxGameRounds = 7;
    }

    public int getMaxGameRounds() {
        return maxGameRounds;
    }

    public abstract void repairComponent(String componentName);

    public abstract String getInspectionReport();

    public boolean isOperational() {
        for (Component component : components) {
            if (!component.isWorking()) {
                return false;
            }
        }

        return true;
    }
}