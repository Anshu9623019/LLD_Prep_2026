package LLD_Interview_Questions.Easy.ParkingLot;

public class VehicleFactory {

    public static Vehicle createVehicle(String vehicleType, String licensePlate, ParkingFeeStrategy feeStrategy){
        if (vehicleType.equalsIgnoreCase("Car")){
            return  new CarVehicle(licensePlate,vehicleType,feeStrategy);
        } else if (vehicleType.equalsIgnoreCase("Bike")) {
            return new BikeVehicle(licensePlate,vehicleType,feeStrategy);
        }
        return new OtherVehicle(licensePlate,vehicleType,feeStrategy);
    }

}
