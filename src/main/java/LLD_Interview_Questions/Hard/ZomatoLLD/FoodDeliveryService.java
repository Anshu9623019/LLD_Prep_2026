//package LLD_Interview_Questions.Hard.ZomatoLLD;
//
//import LLD_Interview_Questions.Hard.ZomatoLLD.entity.*;
//import LLD_Interview_Questions.ZomatoLLD.entity.*;
//import LLD_Interview_Questions.Hard.ZomatoLLD.order.Order;
//import LLD_Interview_Questions.Hard.ZomatoLLD.order.OrderItem;
//import LLD_Interview_Questions.Hard.ZomatoLLD.order.OrderStatus;
//import LLD_Interview_Questions.Hard.ZomatoLLD.search.RestaurantSearchStrategy;
//import LLD_Interview_Questions.Hard.ZomatoLLD.strategy.DeliveryAssignmentStrategy;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//
//
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//public class FoodDeliveryService {
//    private static volatile FoodDeliveryService instance;
//    private final Map<String, Customer> customers = new ConcurrentHashMap<>();
//    private final Map<String, Restaurant> restaurants = new ConcurrentHashMap<>();
//    private final Map<String, DeliveryAgent> deliveryAgents = new ConcurrentHashMap<>();
//    private final Map<String, Order> orders = new ConcurrentHashMap<>();
//    private DeliveryAssignmentStrategy assignmentStrategy;
//
//    public static FoodDeliveryService getInstance() {
//        if (instance == null) {
//            synchronized (FoodDeliveryService.class) {
//                if (instance == null) instance = new FoodDeliveryService();
//            }
//        }
//        return instance;
//    }
//
//    //Registration
//    public Customer registerCustomer(String name, String phone, Address address){
//        Customer customer = new Customer(name,phone,address);
//        customers.put(customer.getId(),customer);
//        return customer;
//    }
//
//    // Register Restaurant
//    public Restaurant registerRestaurant(String name, Address address){
//        Restaurant restaurant = new Restaurant(name,address);
//        restaurants.put(restaurant.getId(),restaurant);
//        return restaurant;
//    }
//
//
//    //Register DeliveryAgent
//    public DeliveryAgent registerDeliveryAgent(String name, String phone, Address initialAddress){
//        DeliveryAgent deliveryAgent = new DeliveryAgent(name,phone,initialAddress);
//        deliveryAgents.put(deliveryAgent.getId(),deliveryAgent);
//        return deliveryAgent;
//    }
//
//    //Place Order
//    public Order placeOrder(String customerId, String restaurantId, List<OrderItem> items) throws NoSuchFieldException {
//        Customer customer =  customers.get(customerId);
//        Restaurant restaurant =  restaurants.get(restaurantId);
//        if(customer==null || restaurant==null) throw new NoSuchFieldException("Customer or Restaurant not found");
//
//        Order order = new Order(customer,restaurant,items);
//
//        orders.put(order.getId(),order);
//        customer.addToHistory(order);
//        System.out.printf("Order %s placed by %s at %s \n", order.getId(), customer.getName(), restaurant.getName());
//        order.setStatus(OrderStatus.PENDING);
//        return order;
//    }
//
//    public void updateOrderStatus(String orderId, OrderStatus newStatus) throws NoSuchFieldException {
//        Order order = orders.get(orderId);
//        if(order==null){
//            throw new NoSuchFieldException("Order Not Found");
//        }
//        order.setStatus(newStatus);
//        if(newStatus == OrderStatus.READY_FOR_PICKUP){
//            assignDelivery(order);
//        }
//    }
//
//    public void cancelOrder(String orderId){
//        Order order = orders.get(orderId);
//        if(order==null){
//            throw new new NoSuchFieldException("Order Not Found");
//        }
//        if(order.cancel()){
//            System.out.println("Success : order" + orderId + "has been successfully canceled");
//        }else{
//            System.out.println("Failed :  order" + orderId + "could not be canceled. its status is :"+ order.getStatus());
//        }
//    }
//
//    private void assignDelivery(Order order){
//        List<DeliveryAgent> availableAgents = new ArrayList<>(deliveryAgents.values());
//
//        assignmentStrategy.findAgent(order, availableAgents).ifPresentOrElse(
//                agent ->{
//                    order.assignDeliveryAgent(agent);
//                    System.out.println("Agent %s (dist : %.2f) assigned to order %s. \n", agent.getName(),
//                            agent.getConcurrentLocation().distanceTo(order.getRestaurant().getAddress()),
//                            order.getId());
//                    order.setStatus(OrderStatus.OUR_FOR_DELIVERY);
//                },
//                ()-> System.out.println("No available delivery agents found for order" + order.getId());
//        )
//    }
//
//    public List<Restaurant> searchRestaurant(List<RestaurantSearchStrategy> strategies){
//        List<Restaurant> results = new ArrayList<>(restaurants.values());
//
//
//        //Sequentially apply each filter strategy
//        for(RestaurantSearchStrategy strategy : strategies){
//            results = strategy.filter(results);
//        }
//        return results;
//    }
//
//    public Menu getRestaurantMenu(String restaurantId) throws NoSuchFieldException {
//        Restaurant restaurant = restaurants.get(restaurantId);
//        if(restaurant==null){
//            throw  new NoSuchFieldException("Restaurant with ID"+restaurantId+"No Found");
//        }
//        return restaurant.getMenu();
//    }
//
//
//
//
//
//}
