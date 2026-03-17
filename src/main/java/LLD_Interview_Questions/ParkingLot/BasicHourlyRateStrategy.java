package LLD_Interview_Questions.ParkingLot;

public class BasicHourlyRateStrategy  implements  ParkingFeeStrategy{
    @Override
    public double calculateFee(String vehicleType, int duration,DurationType durationType){

        switch (vehicleType.toLowerCase()){
            case "car" :
                return durationType == DurationType.HOURS ?  duration * 10.0 : duration * 10 * 24;
            case "bike":
                return durationType == DurationType.HOURS ? duration * 5.0 : duration * 5.0 * 24;

            case "auto" :
                return durationType == DurationType.HOURS ? duration * 8.0 : duration * 8.0 * 24;

            default:
                return durationType == DurationType.HOURS ? duration * 15.0 : duration * 15.0 * 24;
        }
    }
}
