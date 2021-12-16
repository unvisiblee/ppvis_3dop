package org.ppvis.view;

import org.ppvis.controller.AddProductController;

public class AddProductsCommand implements Command {
    private static final String COMMAND = "add_product";
    private final AddProductController controller;
    private final String UNDEFINED_COMMAND = "The subcommand is undefined";
    private final String WRONG_FORMAT = "Wrong format of the command";

    public AddProductsCommand(AddProductController controller) {
        this.controller = controller;
    }

    @Override
    public String getCommandFormat() {
        return COMMAND + " *product_name* *count*";
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
