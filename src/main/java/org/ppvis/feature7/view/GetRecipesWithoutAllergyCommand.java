package org.ppvis.feature7.view;

import org.ppvis.feature7.controller.GetRecipesByMealTimeAndRequiredComponentWithoutAllergyController;
import org.ppvis.model.Recipe;
import org.ppvis.view.Command;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class GetRecipesWithoutAllergyCommand implements Command {

    private static final String COMMAND = "get_recipe";
    private final GetRecipesByMealTimeAndRequiredComponentWithoutAllergyController controller;
    private final String UNDEFINED_COMMAND = "The subcommand is undefined";
    private final String WRONG_FORMAT = "Wrong format of the command";

    public GetRecipesWithoutAllergyCommand(GetRecipesByMealTimeAndRequiredComponentWithoutAllergyController controller) {
        this.controller = controller;
    }

    @Override
    public String getCommandFormat() {
        return COMMAND + " *meal_time* *required_ingredient* *allergy*";
    }

    @Override
    public boolean checkCommand(String input) {
        return input.split(" ")[0].equalsIgnoreCase(COMMAND);
    }

    @Override
    public void run(String command) {
        String[] commandArgs = command.split(" ");
        if (commandArgs.length == 4) {
            List<Recipe> found = controller.findRecipes(commandArgs[1], commandArgs[2], commandArgs[3]);
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
