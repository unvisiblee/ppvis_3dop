package org.ppvis.view;

import org.ppvis.controller.AllRecipesController;

public class DisplayAllRecipesCommand implements Command {
    private static final String COMMAND = "get_all";
    private final AllRecipesController controller;

    public DisplayAllRecipesCommand(AllRecipesController controller) {
        this.controller = controller;
    }

    @Override
    public String getCommandFormat() {
        return COMMAND;
    }

    @Override
    public boolean checkCommand(String input) {
        return input.trim().equalsIgnoreCase(COMMAND);
    }

    @Override
    public void run(String command){
        System.out.println(controller.getAllRecipes());
    }

}
