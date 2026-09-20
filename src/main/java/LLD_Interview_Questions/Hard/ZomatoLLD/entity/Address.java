package LLD_Interview_Questions.Hard.ZomatoLLD.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String city;
    private String zipCode;
    private double latitude;
    private double longitude;


    public double distanceTo(Address other){
        double latDiff = this.latitude - other.latitude;
        double longDiff = this.longitude - other.longitude;
        return Math.sqrt(latDiff*latDiff + longDiff*longDiff);
    }
}
