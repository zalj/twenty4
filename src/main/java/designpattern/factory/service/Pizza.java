package designpattern.factory.service;

public class Pizza {
    public final void prepare() {
        System.out.println("prepare...");
    }

    public final void bake() {
        System.out.println("bake...");
    }

    public final void cut() {
        System.out.println("cut...");
    }

    public final void box() {
        System.out.println("box...");
    }
}

class CheesePizza extends Pizza {
    public CheesePizza() {
        System.out.println("Cheese Pizza");
    }
}

class GreekPizza extends Pizza {
    public GreekPizza() {
        System.out.println("Greek Pizza");
    }
}

class PepperoniPizza extends Pizza {
    public PepperoniPizza() {
        System.out.println("Pepperoni Pizza");
    }
}

class VeggiePizza extends Pizza {
    public VeggiePizza() {
        System.out.println("Veggie Pizza");
    }
}
