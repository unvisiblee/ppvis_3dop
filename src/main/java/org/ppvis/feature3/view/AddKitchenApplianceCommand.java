package org.ppvis.feature3.view;

import org.ppvis.feature3.controller.AddKitchenApplianceController;
import org.ppvis.view.Command;

public class AddKitchenApplianceCommand implements Command {
    private static final String COMMAND = "add_appliance";
    private final AddKitchenApplianceController controller;
    private final String UNDEFINED_COMMAND = "The subcommand is undefined";
    private final String WRONG_FORMAT = "Wrong format of the command";

    public AddKitchenApplianceCommand(AddKitchenApplianceController controller) {
        this.controller = controller;
    }

    @Override
    public String getCommandFormat() {
        return COMMAND + " *appliance* *count*";
    }

    @Override
    public boolean checkCommand(String input) {
        return input.split(" ")[0].equalsIgnoreCase(COMMAND);
    }

    @Override
    public void run(String command) {
        String[] commandArgs = command.split(" ");
        if (commandArgs.length == 3) {
            try {
                Integer count = Integer.parseInt(commandArgs[2]);
                controller.addProduct(commandArgs[1], count);
            } catch (Exception e) {
                printUndefinedCommand();
            }
        } else
            printWrongFormat();
    }

    private void printUndefinedCommand() {
        System.out.println(UNDEFINED_COMMAND);
    }

    private void printWrongFormat() {
        System.out.println(WRONG_FORMAT);
    }
}
