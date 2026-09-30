package Machines;

public class CoolingMachine extends Machine {

    public CoolingMachine() {
        super("Cooling Machine");
        components.add(new Component("fan", false));
        components.add(new Component("coolant pump", false));
        chooseFaultyComponent();
    }

    @Override
    public void repairComponent(String componentName) {
        if (componentName.equals("fan")) {
            System.out.println("You replaced the fan blades.");
        } else if (componentName.equals("coolant pump")) {
            System.out.println("You flushed and repaired the coolant pump.");
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
                       Machine: Cooling Machine
                       Operator complaint: The room is getting warmer, and the machine is not cooling properly.
                       Observed symptoms:
                       - No air is moving through the cooling vents.
                       - A rattling sound is coming from the lower service panel.
                       - The coolant indicator is fluctuating instead of staying steady.
                       Possible problem areas: fan.""";
            } else {
                return """
                       Machine: Cooling Machine
                       Operator complaint: The room is getting warmer, and the machine is not cooling properly.
                       Observed symptoms:
                       - No air is moving through the cooling vents.
                       - A rattling sound is coming from the lower service panel.
                       - The coolant indicator is fluctuating instead of staying steady.
                       Possible problem areas: coolant pump.""";
            }
        } else {
            return """
                Machine: Cooling Machine
                Operator complaint: The room is getting warmer, and the machine is not cooling properly.
                Observed symptoms:
                - No air is moving through the cooling vents.
                - A rattling sound is coming from the lower service panel.
                - The coolant indicator is fluctuating instead of staying steady.""";
        }
    }
}