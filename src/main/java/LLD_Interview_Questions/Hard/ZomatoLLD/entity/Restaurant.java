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
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant implements OrderObserver {

    private String id;
    private String name;
    private Menu menu;
    private Address address;


    public Restaurant(String name, Address address){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.menu = new Menu();
    }

    public void toAddMenu(MenuItem menuItem){
        this.menu.addItems(menuItem);
    }

    @Override
    public void onUpdate(Order order){
        System.out.println("---Notification for restaurant %s \n" +getName());
        System.out.println("Order %s has been updated to %s \n" + order.getId() + order.getStatus());
        System.out.println("...........\n");
    }


}
