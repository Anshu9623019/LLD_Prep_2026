package LLD_Interview_Questions.Hard.ZomatoLLD.order;

import LLD_Interview_Questions.Hard.ZomatoLLD.entity.Customer;
import LLD_Interview_Questions.Hard.ZomatoLLD.entity.DeliveryAgent;
import LLD_Interview_Questions.Hard.ZomatoLLD.entity.Restaurant;
import LLD_Interview_Questions.Hard.ZomatoLLD.observer.OrderObserver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private Restaurant restaurant;
    private Customer customer;
    private List<OrderItem> orderItems;
    private OrderStatus status;
    private DeliveryAgent deliveryAgent;
    private final List<OrderObserver> observers = new ArrayList<>();

    public Order(Customer customer, Restaurant restaurant, List<OrderItem> items) {
    }
}
