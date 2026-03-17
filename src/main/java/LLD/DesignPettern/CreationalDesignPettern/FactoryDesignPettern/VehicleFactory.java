package LLD.DesignPettern.CreationalDesignPettern.FactoryDesignPettern;

public class VehicleFactory {

    public static  Vehicle getFactory(String type) {
        if(type.equals("Car")){
           return new Car();
        }else if(type.equals("Bike")){
            return  new Bike();
        } else if (type.equals("Bus")) {
            return new Bus();
        }else {
            throw  new IllegalArgumentException("Unknown vehicle type");
        }
    }
}
