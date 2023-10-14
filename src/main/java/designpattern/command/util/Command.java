package designpattern.command.util;

public interface Command {
    void execute();

    default void undo() {};
}
