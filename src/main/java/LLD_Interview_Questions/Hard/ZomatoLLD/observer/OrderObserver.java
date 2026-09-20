package LLD_Interview_Questions.Hard.ZomatoLLD.observer;

import LLD_Interview_Questions.Hard.ZomatoLLD.order.Order;

public interface OrderObserver {
    void onUpdate(Order order);
}
