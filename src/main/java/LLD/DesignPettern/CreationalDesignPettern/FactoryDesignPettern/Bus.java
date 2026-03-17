package LLD.DesignPettern.CreationalDesignPettern.FactoryDesignPettern;

public class Bus implements Vehicle {

    @Override
    public void start() {
        System.out.println("Start the Bus");
    }

    @Override
    public void stop() {
        System.out.println("Stop the Bus");
    }
}
