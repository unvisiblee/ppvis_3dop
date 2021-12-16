package org.ppvis.view;

public interface Command {
    String getCommandFormat();
    boolean checkCommand(String input);
    void run(String command);
}
