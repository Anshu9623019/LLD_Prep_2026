package LLD.DesignPettern.CreationalDesignPettern.FactoryDesignPettern;

public class Car implements Vehicle{

    @Override
    public void start() {
        System.out.println("start the Car");
    }

    @Override
    public void stop() {
        System.out.println("Stop the Car");
    }
}
