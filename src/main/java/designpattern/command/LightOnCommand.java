package designpattern.command;

import designpattern.command.module.Light;
import designpattern.command.util.Command;

public class LightOnCommand implements Command {

    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
