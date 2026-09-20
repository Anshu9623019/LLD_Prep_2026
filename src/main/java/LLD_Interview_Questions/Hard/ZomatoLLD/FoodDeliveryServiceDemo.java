package LLD_Interview_Questions.Hard.ZomatoLLD;

import LLD_Interview_Questions.Hard.ZomatoLLD.entity.*;
import LLD_Interview_Questions.ZomatoLLD.entity.*;
import LLD_Interview_Questions.Hard.ZomatoLLD.order.OrderItem;
import LLD_Interview_Questions.Hard.ZomatoLLD.order.OrderStatus;
import LLD_Interview_Questions.Hard.ZomatoLLD.search.RestaurantSearchStrategy;
import LLD_Interview_Questions.Hard.ZomatoLLD.search.SearchByCityStrategy;
import LLD_Interview_Questions.Hard.ZomatoLLD.search.SearchByMenuKeywordStrategy;
import LLD_Interview_Questions.Hard.ZomatoLLD.search.SearchByProximityStrategy;
import LLD_Interview_Questions.Hard.ZomatoLLD.strategy.NearestAvailableAgentStrategy;

import java.util.List;

public class FoodDeliveryServiceDemo {

    public static void main(String[] args) throws NoSuchFieldException {
        // setUp the system
        FoodDeliveryService service = new FoodDeliveryService();
        service.setAssignmentStrategy(new NearestAvailableAgentStrategy());

        //2.Define addresses

        Address aliceAddress = new Address("123 Maple st","SpringField","12345",40.7128,-74.0060);
        Address pizzaAddress = new Address("123 Maple st","SpringField","12345",40.7128,-74.0060);
        Address burgerAddress = new Address("123 Maple st","SpringField","12345",40.7128,-74.0060);
        Address tacoAddress = new Address("123 Maple st","SpringField","12345",40.7128,-74.0060);


        //3. Register Entities
        Customer alice  = service.registerCustomer("ALICE","123-4456-193",aliceAddress);
        Restaurant pizzaPlace  = service.registerRestaurant("Pizza Place",aliceAddress);
        Restaurant burgerBarn  = service.registerRestaurant("Burger Barn",aliceAddress);
        Restaurant tacoTown  = service.registerRestaurant("Taco Town",aliceAddress);


        //4. Setup Menu
        pizzaPlace.toAddMenu(new MenuItem("P001","Magarita Pizza",12.99));
        pizzaPlace.toAddMenu(new MenuItem("P001","Magarita Pizza",16.99));
        burgerBarn.toAddMenu(new MenuItem("B001","Burger Classic",6.99));
        tacoTown.toAddMenu(new MenuItem("T001","Cruncy Taco",3.50));


        // 5. Demonstrate search Functionality

        System.out.println("\n 1.Searching for Restaurant...");

        // (A) Search by City
        System.out.println("\n(A) Restaurants in 'Springfield':");
        List<RestaurantSearchStrategy> citySearch = List.of(new SearchByCityStrategy("Springfield"));
        List<Restaurant> springfieldRestaurants = service.searchRestaurants(citySearch);
        springfieldRestaurants.forEach(r -> System.out.println("  - " + r.getName()));

        // (B) Search for restaurants near Alice
        System.out.println("\n(B) Restaurants near Alice (within 0.01 distance units):");
        List<RestaurantSearchStrategy> proximitySearch = List.of(new SearchByProximityStrategy(aliceAddress, 0.01));
        List<Restaurant> nearbyRestaurants = service.searchRestaurants(proximitySearch);
        nearbyRestaurants.forEach(r -> System.out.printf("  - %s (Distance: %.4f)\n", r.getName(), aliceAddress.distanceTo(r.getAddress())));

        // (C) Search for restaurants that serve 'Pizza'
        System.out.println("\n(C) Restaurants that serve 'Pizza':");
        List<RestaurantSearchStrategy> menuSearch = List.of(new SearchByMenuKeywordStrategy("Pizza"));
        List<Restaurant> pizzaRestaurants = service.searchRestaurants(menuSearch);
        pizzaRestaurants.forEach(r -> System.out.println("  - " + r.getName()));

        // (D) Combined Search: Find restaurants near Alice that serve 'Burger'
        System.out.println("\n(D) Burger joints near Alice:");
        List<RestaurantSearchStrategy> combinedSearch = List.of(
                new SearchByProximityStrategy(aliceAddress, 0.01),
                new SearchByMenuKeywordStrategy("Burger")
        );
        List<Restaurant> burgerJointsNearAlice = service.searchRestaurants(combinedSearch);
        burgerJointsNearAlice.forEach(r -> System.out.println("  - " + r.getName()));

        // 6. Demonstrate Browsing a Menu
        System.out.println("\n--- 2. Browsing a Menu ---");
        System.out.println("\nMenu for 'Pizza Palace':");
        Menu pizzaMenu = service.getRestaurantMenu(pizzaPalace.getId());
        pizzaMenu.getItems().values().forEach(item ->
                System.out.printf("  - %s: $%.2f\n", item.getName(), item.getPrice())
        );

        // 7. Alice places an order from a searched restaurant
        System.out.println("\n--- 3. Placing an Order ---");
        if (!pizzaRestaurants.isEmpty()) {
            Restaurant chosenRestaurant = pizzaRestaurants.get(0);
            MenuItem chosenItem = chosenRestaurant.getMenu().getItem("P001");

            System.out.printf("\nAlice is ordering '%s' from '%s'.\n", chosenItem.getName(), chosenRestaurant.getName());
            var order = service.placeOrder(alice.getId(), chosenRestaurant.getId(), List.of(new OrderItem(chosenItem, 1)));

            System.out.println("\n--- Restaurant starts preparing the order ---");
            service.updateOrderStatus(order.getId(), OrderStatus.PREPARING);

            System.out.println("\n--- Order is ready for pickup ---");
            System.out.println("System will now find the nearest available delivery agent...");
            service.updateOrderStatus(order.getId(), OrderStatus.READY_FOR_PICKUP);

            System.out.println("\n--- Agent delivers the order ---");
            service.updateOrderStatus(order.getId(), OrderStatus.DELIVERED);
        }



    }
}
