package Machines;

public class HeatingMachine extends Machine {

    public HeatingMachine() {
        super("Heating Machine");
        components.add(new Component("fan", false));
        components.add(new Component("furnace", false));
        chooseFaultyComponent();
    }

    @Override
    public void repairComponent(String componentName) {
        if (componentName.equals("fan")) {
            System.out.println("You replaced the fan blades.");
        } else if (componentName.equals("furnace")) {
            System.out.println("You touched the furnace and it was cold. So, you replaced the furnace.");
        }

        for (Component component : components) {
            if (component.getName().equals(componentName)) {
                component.repair();
            }
        }
    }

    @Override
    public String getInspectionReport() {
        if (getCurrentLevel() < 3) {
            if (componentToRepair < 1) {
                return """
                       Machine: Heating Machine
                       Operator complaint: The room is getting cooler, and the machine is not warming properly.
                       Observed symptoms:
                       - No air is moving through the heating vents.
                       - A clanking sound is coming from the lower service panel.
                       - The machine temperature is fluctuating instead of staying steady.
                       - The furnace feels cool to the touch.
                       Possible problem areas: fan.""";
            } else {
                return """
                       Machine: Heating Machine
                       Operator complaint: The room is getting cooler, and the machine is not warming properly.
                       Observed symptoms:
                       - No air is moving through the heating vents.
                       - A clanking sound is coming from the lower service panel.
                       - The machine temperature is fluctuating instead of staying steady.
                       - The furnace feels cool to the touch.
                       Possible problem areas: furnace.""";
            }
        } else {
            return """
                Machine: Heating Machine
                Operator complaint: The room is getting cooler, and the machine is not warming properly.
                Observed symptoms:
                - No air is moving through the heating vents.
                - A clanking sound is coming from the lower service panel.
                - The machine temperature is fluctuating instead of staying steady.
                - The furnace feels cool to the touch.""";
        }
    }
}