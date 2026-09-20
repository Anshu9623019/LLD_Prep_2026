package LLD_Interview_Questions.Hard.ZomatoLLD.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    private  String id;
    private  String name;
    private  boolean status;
    private  double price;

    public String getManuItem(){
        return "Name: " + name + "Price: " +price;
    }
}
