package org.ppvis.view;

public interface MainLoop {
    void run();

    void stop();

    void addCommand(Command command);
}
