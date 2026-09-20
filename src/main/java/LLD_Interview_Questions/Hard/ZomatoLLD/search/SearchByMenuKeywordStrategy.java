package LLD_Interview_Questions.Hard.ZomatoLLD.search;

import LLD_Interview_Questions.Hard.ZomatoLLD.entity.Restaurant;

import java.util.List;
import java.util.stream.Collectors;

public class SearchByMenuKeywordStrategy implements RestaurantSearchStrategy {
    private String keyword;

    @Override
    public List<Restaurant> filter(List<Restaurant> allRestaurants){
        return allRestaurants.stream().filter(r-> r.getMenu().getItems().values().stream()
                .anyMatch(item -> item.getName().toLowerCase().contains(keyword)))
                .collect(Collectors.toList());
    }

}