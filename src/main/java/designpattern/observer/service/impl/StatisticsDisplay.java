package designpattern.observer.service.impl;

import designpattern.observer.service.DisplayElement;

import java.util.Observable;
import java.util.Observer;

public class StatisticsDisplay implements Observer, DisplayElement {
    private Observable observable;
    // 0: min, 1: avg, 2: max, 3: sum
    private final int MIN = 0;
    private final int AVG = 1;
    private final int MAX = 2;
    private final int SUM = 3;

    private final float[] temperatureStatistics;
    int count;

    public StatisticsDisplay(Observable observable) {
        this.observable = observable;
        this.temperatureStatistics = new float[5];
        this.count = 0;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) o;
            changeStatistics(temperatureStatistics, weatherData.getTemperature());
            display();
        }
    }

    @Override
    public void display() {
        System.out.printf("Avg/Max/Min temperature = %.1f/%.1f/%.1f\n",
                temperatureStatistics[AVG],
                temperatureStatistics[MAX],
                temperatureStatistics[MIN]);
    }

    private void changeStatistics(float[] statistics, float current) {
        if (count == 0 || statistics[MIN] > current) {
            statistics[MIN] = current;
        }
        if (count == 0 || statistics[MAX] < current) {
            statistics[MAX] = current;
        }
        count++;
        statistics[SUM] += current;
        statistics[AVG] = statistics[SUM] / count;
    }
}
