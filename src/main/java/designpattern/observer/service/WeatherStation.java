package designpattern.observer.service;

import designpattern.observer.service.impl.CurrentConditionsDisplay;
import designpattern.observer.service.impl.ForecastDisplay;
import designpattern.observer.service.impl.StatisticsDisplay;
import designpattern.observer.service.impl.WeatherData;

import javax.swing.*;
import java.awt.*;

public class WeatherStation {
    public static void main(String[] args) {
        new WeatherStation().testObservable();
    }

    public void testObservable() {
        WeatherData weatherData = new WeatherData();

        new CurrentConditionsDisplay(weatherData);
        new StatisticsDisplay(weatherData);
        new ForecastDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }

    static class SwingObserverExample {
        JFrame frame;

        public void go() {
            frame = new JFrame();

            JButton button = new JButton("干不干?");
            button.addActionListener(e -> System.out.println("三思而后行!"));
            button.addActionListener(e -> System.out.println("干就完了!"));
            frame.getContentPane().add(BorderLayout.CENTER, button);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            frame.setSize(500, 500);
        }
    }
}
