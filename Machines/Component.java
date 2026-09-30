package Machines;

public class Component {
    private final String name;
    private boolean faulty;
    private boolean repaired;

    public Component(String name, boolean faulty) {
        this.name = name;
        this.faulty = faulty;
        this.repaired = false;
    }

    public String getName() {
        return name;
    }

    public boolean isFaulty() {
        return faulty;
    }

    public void setFaulty(boolean faulty) {
        this.faulty = faulty;
    }

    public boolean isRepaired() {
        return repaired;
    }

    public void repair() {
        repaired = true;
    }

    public boolean isWorking() {
        return !faulty || repaired;
    }
}