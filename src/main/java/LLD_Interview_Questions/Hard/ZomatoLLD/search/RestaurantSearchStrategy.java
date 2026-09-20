package LLD_Interview_Questions.Hard.ZomatoLLD.search;

import LLD_Interview_Questions.Hard.ZomatoLLD.entity.Restaurant;

import java.util.List;

public interface RestaurantSearchStrategy {
    List<Restaurant> filter(List<Restaurant> allRestaurant);
}
