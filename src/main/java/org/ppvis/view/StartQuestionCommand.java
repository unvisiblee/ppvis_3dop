package org.ppvis.view;

import org.ppvis.controller.StartWindowController;

public class StartQuestionCommand implements Command {
    private final StartWindowController controller;

    public StartQuestionCommand(StartWindowController controller) {
        this.controller = controller;
    }

    @Override
    public String getCommandFormat() {
        return "Hello. Please, choose one of the options:\n" +
                "0) Continue previous session\n" +
                "1) Default menu";
    }

    @Override
    public boolean checkCommand(String input) {
        return true;
    }

    @Override
    public void run(String command) {
        Integer option;
        try {
            option = Integer.parseInt(command);
            switch (option) {
                case 0: {
                    System.out.println(controller.loadLastRecipe());
                    break;
                }
                case 1:
                    break;
                default:
                    System.out.println("Invalid option. The program will continue from the default option ");
            }
        } catch (Exception e) {
            System.out.println("Format error. The program will continue from the default option ");
        }
    }

}
