package LLD_Interview_Questions.Hard.ZomatoLLD.entity;

import LLD_Interview_Questions.Hard.ZomatoLLD.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends User {
    private Address address;
    private final List<Order> orderHistory = new ArrayList<>();
    public Customer(String name, String phone, Address address){
        super(name,phone);
        this.address = address;
    }


    public void addToHistory(Order order){
        orderHistory.add(order);
    }

    @Override
    public void onUpdate(Order order){
        System.out.println("Notification for customer %s \n" + this.getClass().getName());
        System.out.println("Order %s is now %s. \n" + order.getId() + order.getStatus());
        System.out.println("-------------\n");
    }
}
