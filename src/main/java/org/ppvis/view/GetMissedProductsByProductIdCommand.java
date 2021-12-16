package org.ppvis.view;

import org.ppvis.controller.CheckMissedProductsForRecipeController;

import java.util.List;

public class GetMissedProductsByProductIdCommand implements Command {
    private static final String COMMAND = "check_missed_products";
    private final CheckMissedProductsForRecipeController controller;
    private final String UNDEFINED_COMMAND = "The subcommand is undefined";
    private final String WRONG_FORMAT = "Wrong format of the command";

    public GetMissedProductsByProductIdCommand(CheckMissedProductsForRecipeController controller) {
        this.controller = controller;
    }

    @Override
    public String getCommandFormat() {
        return COMMAND+" *number_in_list*";
    }

    @Override
    public boolean checkCommand(String input) {
        return input.split(" ")[0].equalsIgnoreCase(COMMAND);
    }

    @Override
    public void run(String command) {
        String[] commandArgs = command.split(" ");
        if (commandArgs.length == 2) {

            Integer id;
            try {
                id = Integer.parseInt(commandArgs[1]);
                List<String> missed = controller.getMissingProductsForRecipeById(id);
                if (missed.isEmpty())
                    System.out.println("You have all products");
                else{
                    System.out.println("Report:");
                    for (int i = 0; i < missed.size(); i++) {
                        String missedProduct = missed.get(i);
                        System.out.println(i+") " + missedProduct);
                    }
                }
            } catch (Exception e){
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
