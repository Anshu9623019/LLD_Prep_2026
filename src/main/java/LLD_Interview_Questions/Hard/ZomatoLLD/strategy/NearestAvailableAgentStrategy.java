package LLD_Interview_Questions.Hard.ZomatoLLD.strategy;

import LLD_Interview_Questions.Hard.ZomatoLLD.entity.Address;
import LLD_Interview_Questions.Hard.ZomatoLLD.entity.DeliveryAgent;
import LLD_Interview_Questions.Hard.ZomatoLLD.order.Order;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class NearestAvailableAgentStrategy implements DeliveryAssignmentStrategy{


    @Override
    public  Optional<DeliveryAgent> findAgent(Order order, List<DeliveryAgent> availableAgent){
        Address restaurantAddress = order.getRestaurant().getAddress();
        Address customerAddress =  order.getCustomer().getAddress();

        return availableAgent.stream().filter(DeliveryAgent :: i).min(
                Comparator.comparingDouble(agent->calculateTotalDistance(agent,restaurantAddress,customerAddress))
        );
    }

    private double calculateTotalDistance(DeliveryAgent agent, Address restaurantAddress, Address customerAddress){
        double agentToRestaurantDist = agent.getCurrentLocation().distanceTo(restaurantAddress);
        double restaurantToCustomerDist = restaurantAddress.distanceTo(customerAddress);
        return agentToRestaurantDist + restaurantToCustomerDist;
    }
}
