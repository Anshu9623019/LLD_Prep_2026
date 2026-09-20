package LLD_Interview_Questions.Hard.ZomatoLLD.entity;

import LLD_Interview_Questions.Hard.ZomatoLLD.observer.OrderObserver;
import LLD_Interview_Questions.Hard.ZomatoLLD.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class User implements OrderObserver {
    private  String id;
    private String name;
    private String phone;

    public User(String name, String phone){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phone  = phone;
    }

    @Override
    public void onUpdate(Order order) {
        System.out.println();
    }
}
