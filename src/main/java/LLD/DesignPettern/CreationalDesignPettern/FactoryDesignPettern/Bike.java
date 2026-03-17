package LLD.DesignPettern.CreationalDesignPettern.FactoryDesignPettern;

public class Bike implements Vehicle {

    @Override
    public void start() {
        System.out.println("start the bike");
    }

    @Override
    public void stop() {
        System.out.println("Stop the bike");
    }
}
