package LLD.DesignPettern.CreationalDesignPettern.FactoryDesignPettern;

public class Main {
    public static void main(String[] args) {
        Vehicle car = VehicleFactory.getFactory("Car");
        car.start();
        car.stop();
    }
}
