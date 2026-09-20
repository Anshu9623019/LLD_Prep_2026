package LLD_Interview_Questions.Hard.ZomatoLLD.entity;

import LLD_Interview_Questions.Hard.ZomatoLLD.order.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicBoolean;


@Getter
@Setter
public class DeliveryAgent extends User {
    private final AtomicBoolean isAvailable = new AtomicBoolean(true);
    private final Address currentLocation;

    public DeliveryAgent(String name, String phone ,Address currentLocation){
        super(name,phone);
        this.currentLocation = currentLocation;
    }

    @Override
    public void onUpdate(Order order){
        System.out.println("---Notification for delivery Agent....\n");
        System.out.println("Order %s update: Status is %s \n" + order.getId() + order.getStatus());
        System.out.println("-----------------");
    }



}
