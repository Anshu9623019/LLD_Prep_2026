package LLD_Interview_Questions.ParkingLot;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Vehicle {
    private  String licencePlate; // store the vehicle licence plateNumber
    private String vehicleType; // Stores the types of vehicles(car,bike,bus etc..)
    private ParkingFeeStrategy parkingFeeStrategy;  // Strategy for calculating parking fees

    public Vehicle(String licencePlate,String  vehicleType,ParkingFeeStrategy parkingFeeStrategy){
        this.licencePlate = licencePlate;
        this.vehicleType = vehicleType;
        this.parkingFeeStrategy = parkingFeeStrategy;
    }

    public double calculateFee(int duration,DurationType durationType){
        return parkingFeeStrategy.calculateFee(vehicleType,duration,durationType);
    }


}
