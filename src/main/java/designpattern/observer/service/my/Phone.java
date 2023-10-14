package designpattern.observer.service.my;

import java.util.Observable;
import java.util.Observer;

public class Phone implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(o);
        System.out.println(arg);
    }
}
