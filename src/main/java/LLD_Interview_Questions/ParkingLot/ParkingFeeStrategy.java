package LLD_Interview_Questions.ParkingLot;

// Interface for parking fee Calculation Strategy
public interface ParkingFeeStrategy {
    /**
     *  Calculate parking fee based on vehicle type and duration
     *
     * @param vehicleType Type of vehicle being parked
     * @param duration Duration of parking(in hours or days)
     * @param durationType Types od duration(HOURS, DAYS)
     * @return Return calculated parking fee
     */
    double calculateFee(String vehicleType,int duration, DurationType durationType);
}
