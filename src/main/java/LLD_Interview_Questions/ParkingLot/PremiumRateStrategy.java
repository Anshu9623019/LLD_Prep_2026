package LLD_Interview_Questions.ParkingLot;

public class PremiumRateStrategy implements ParkingFeeStrategy {

    public double calculateFee(String vehicleType, int duration, DurationType durationType){

        switch (vehicleType.toLowerCase()){
            case "car" :
                return durationType == DurationType.HOURS ? duration * 15.0 : duration * 15.0 * 24;

            case "bike" :
                return durationType == DurationType.HOURS ? duration * 8.0 : duration * 8.0 * 24;

            case "auto" :
                return durationType == DurationType.HOURS ? duration * 12.0 : duration * 12.0 * 24;

            default:
                return durationType == DurationType.HOURS ? duration * 20.0 : duration * 20.0 * 24;
        }
    }

}
