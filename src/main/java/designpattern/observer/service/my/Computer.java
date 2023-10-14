package designpattern.observer.service.my;

import java.util.Observable;

public class Computer extends Observable {
    private boolean open;

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
        setChanged();
        StringBuilder sb = new StringBuilder();

        notifyObservers(this.open);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "open=" + open +
                '}';
    }
}
