package org.ppvis.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainLoopImpl implements MainLoop {
    private final String EXIT_WORD = "quit";
    private final String GREETINGS = "> ";
    private final String UNDEFINED_COMMAND = "Undefined command";
    private final List<Command> commands = new ArrayList<>();
    private final Command startQuestion;
    private volatile boolean doContinue = true;

    public MainLoopImpl(Command startQuestion) {
        this.startQuestion = startQuestion;
    }

    @Override
    public void run() {
        doContinue = true;

        try {
            displayStartQuestion();
        } catch (IOException e) {
            System.out.println("Error at start command");
        }

        System.out.println("Available commands and arguments:");
        for (int i = 0; i < commands.size(); i++) {
            Command command = commands.get(i);
            System.out.println(i + ") " + command.getCommandFormat());
        }
        System.out.println();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String command = "";

        while (doContinue) {
            System.out.print(GREETINGS);

            try {
                command = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (command.equals(EXIT_WORD))
                stop();
            else if (!command.isEmpty()) {
                boolean found = false;

                for (Command candidate : commands) {
                    if (candidate.checkCommand(command)) {
                        found = true;
                        candidate.run(command);

                        break;
                    }
                }

                if (!found)
                    System.out.println(UNDEFINED_COMMAND);
            }
            System.out.println();
        }
    }

    @Override
    public void stop() {
        doContinue = false;
    }

    @Override
    public void addCommand(Command command) {
        commands.add(command);
    }

    private void displayStartQuestion() throws IOException {
        System.out.println(startQuestion.getCommandFormat());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String command = bufferedReader.readLine();
        startQuestion.run(command);
    }
}
