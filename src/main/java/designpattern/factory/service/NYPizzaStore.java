package designpattern.factory.service;

public class NYPizzaStore extends PizzaStore {

    @Override
    Pizza createPizza(String type) {
        SimplePizzaFactory factory = new NYPizzaFactory();
        return factory.createPizza(type);
    }
}
