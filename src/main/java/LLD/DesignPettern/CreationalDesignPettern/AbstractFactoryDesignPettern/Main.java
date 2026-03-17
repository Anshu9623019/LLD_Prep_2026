package LLD.DesignPettern.CreationalDesignPettern.AbstractFactoryDesignPettern;

// Abstract Products
interface Bus {
    void pay(double amount);
}

interface Car {
    void pay(double amount);
}

// Stripe Implementations
class BMWBus implements Bus {
    public void pay(double amount) {
        System.out.println("BMW Bus Payment of $" + amount);
    }
}

class BMWCar implements Car {
    public void pay(double amount) {
        System.out.println(" BMW Car UPI Payment of $" + amount);
    }
}

// Razorpay Implementations
class HondaBus implements Bus {
    public void pay(double amount) {
        System.out.println("Honda Bus Payment of $" + amount);
    }
}

class HondaCAr implements Car {
    public void pay(double amount) {
        System.out.println("Honda CAr UPI Payment of $" + amount);
    }
}

// Abstract Factory
interface VehicleBrandFactory {
    Car createCar();
    Bus createBus();
}

// Concrete Factories
class BMWFactory implements VehicleBrandFactory {

    @Override
    public Bus createBus() {
        return new BMWBus();
    }

    @Override
    public Car createCar() {
        return new BMWCar();
    }
}

class HondaFactory implements VehicleBrandFactory {
    @Override
    public Bus createBus() {
        return new HondaBus();
    }

    @Override
    public Car createCar() {
        return new HondaCAr();
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        // Switch between payment gateways dynamically
        VehicleBrandFactory bmwfactory = new BMWFactory(); // or RazorpayPaymentFactory

        Bus bus = bmwfactory.createBus();
        bus.pay(1200.50);

        Car car = bmwfactory.createCar();
        car.pay(850.00);

        VehicleBrandFactory hondaFactory = new HondaFactory();

        Bus bus1 = hondaFactory.createBus();
        bus1.pay(1200.50);

        Car  car1 = hondaFactory.createCar();
        car1.pay(850.00);
    }
}
