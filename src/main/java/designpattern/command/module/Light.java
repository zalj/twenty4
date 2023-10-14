package designpattern.command.module;

public class Light {
    private String room;

    public Light(String room) {
        this.room = room;
    }

    public void on() {
        System.out.println("Light is On");
    }

    public void off() {
        System.out.println("Light is Off");
    }
}
