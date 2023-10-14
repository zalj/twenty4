package designpattern.command.module;

public class Stereo {
    private int volume;

    private MOD mod = MOD.NORMAL;

    public void on() {
        System.out.println("Stereo is On");
    }

    public void setCD() {
        mod = MOD.CD;
        System.out.println("Stereo set CD");
    }

    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("Stereo's volume set to " + volume);
    }

    public void off() {
        System.out.println("Stereo is Off");
    }

    enum MOD {
        CD, NORMAL;
    }
}
