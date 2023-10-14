package designpattern.command;

import designpattern.command.module.Light;

import java.util.LinkedHashMap;

public class RemoteControlTest {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");

        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
    }
}
