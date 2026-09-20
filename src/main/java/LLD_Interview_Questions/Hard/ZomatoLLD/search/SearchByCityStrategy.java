package LLD_Interview_Questions.Hard.ZomatoLLD.search;

import LLD_Interview_Questions.Hard.ZomatoLLD.entity.Restaurant;

import java.util.List;
import java.util.stream.Collectors;

public class SearchByCityStrategy implements RestaurantSearchStrategy {
    private String city;

    @Override
    public List<Restaurant> filter(List<Restaurant> allRestaurants){
        return allRestaurants.stream().filter(r-> r.getAddress().getCity()
                .equalsIgnoreCase(this.city)).collect(Collectors.toList());
    }

}
