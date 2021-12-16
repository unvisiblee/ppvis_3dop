package org.ppvis.view;

import org.ppvis.controller.GetRecipesByMealTimeAndRequiredComponentController;
import org.ppvis.model.Recipe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class GetRecipesCommand implements Command {
    private static final String COMMAND = "get_recipe";
    private final GetRecipesByMealTimeAndRequiredComponentController controller;
    private final String UNDEFINED_COMMAND = "The subcommand is undefined";
    private final String WRONG_FORMAT = "Wrong format of the command";

    public GetRecipesCommand(GetRecipesByMealTimeAndRequiredComponentController controller) {
        this.controller = controller;
    }

    @Override
    public String getCommandFormat() {
        return COMMAND + " *meal_time* *required_ingredient*";
    }

    @Override
    public boolean checkCommand(String input) {
        return input.split(" ")[0].equalsIgnoreCase(COMMAND);
    }

    @Override
    public void run(String command) {
        String[] commandArgs = command.split(" ");
        if (commandArgs.length == 3) {
            List<Recipe> found = controller.findRecipes(commandArgs[1], commandArgs[2]);
            if (found == null)
                printUndefinedCommand();
            else {
                if (!found.isEmpty()) {
                    printFoundRecipes(found);
                    System.out.print("Write the id of the selected dish:\n>>");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                    try {
                        Integer id = Integer.parseInt(bufferedReader.readLine());
                        System.out.println(found.get(id));
                        controller.removeUsedProducts(found.get(id));
                    } catch (Exception e) {
                        System.out.println("Invalid id");
                    }
                }

            }
        } else
            printWrongFormat();
    }

    private void printFoundRecipes(List<Recipe> found) {
        for (int i = 0; i < found.size(); i++) {
            Recipe recipe = found.get(i);
            System.out.println(i + ") " + recipe.toString());
        }
    }

    private void printUndefinedCommand() {
        System.out.println(UNDEFINED_COMMAND);
    }

    private void printWrongFormat() {
        System.out.println(WRONG_FORMAT);
    }
}
