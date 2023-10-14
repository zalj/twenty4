package designpattern.command.module;

public class GarageDoor {
    public void up() {
        System.out.println("Garage door Open");
    }

    public void down() {
        System.out.println("Garage door Close");
    }

    public void stop() {
        System.out.println("Garage door Stop");
    }

    public void lightOn() {
        System.out.println("Garage door light On");
    }

    public void lightOff() {
        System.out.println("Garage door light Off");
    }
}
