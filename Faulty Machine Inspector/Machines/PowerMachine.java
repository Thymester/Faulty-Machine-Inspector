package Machines;

public class PowerMachine extends Machine {

    public PowerMachine() {
        super("Power Machine");
        components.add(new Component("battery", false));
        components.add(new Component("generator", false));
        chooseFaultyComponent();
    }

    @Override
    public void repairComponent(String componentName) {
        if (componentName.equals("battery")) {
            System.out.println("You replaced the battery cells.");
        } else if (componentName.equals("generator")) {
            System.out.println("You rewired the generator.");
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
                       Machine: Power Machine
                       Operator complaint: The machine loses power during operation.
                       Observed symptoms:
                       - The machine shuts down when demand increases.
                       - The power indicator drops unexpectedly.
                       - A clicking sound is coming from the electrical panel.
                       Possible problem areas: battery.""";
            } else {
                return """
                       Machine: Power Machine
                       Operator complaint: The machine loses power during operation.
                       Observed symptoms:
                       - The machine shuts down when demand increases.
                       - The power indicator drops unexpectedly.
                       - A clicking sound is coming from the electrical panel.
                       Possible problem areas: generator.""";
            }
        } else {
            return """
                Machine: Power Machine
                Operator complaint: The machine loses power during operation.
                Observed symptoms:
                - The machine shuts down when demand increases.
                - The power indicator drops unexpectedly.
                - A clicking sound is coming from the electrical panel.""";
        }
    }
}