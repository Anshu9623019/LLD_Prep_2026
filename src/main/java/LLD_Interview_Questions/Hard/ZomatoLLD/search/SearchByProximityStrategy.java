//package LLD_Interview_Questions.Hard.ZomatoLLD.search;
//
//import LLD_Interview_Questions.Hard.ZomatoLLD.entity.Address;
//import LLD_Interview_Questions.Hard.ZomatoLLD.entity.Restaurant;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class SearchByProximityStrategy implements RestaurantSearchStrategy{
//    private  Address userLocation;
//    private double maxDistance;
//
//
//    @Override
//    public List<Restaurant> filter(List<Restaurant> allRestaurants){
//        return allRestaurants.stream().filter(r->userLocation.distanceTo(r.getAddress() <=
//                maxDistance).sorted(Comparator.comparingDouble(r ->
//                userLocation.distanceTo(r.getAddress()))).collect(Collectors.toList());
//        )
//    }
//}
