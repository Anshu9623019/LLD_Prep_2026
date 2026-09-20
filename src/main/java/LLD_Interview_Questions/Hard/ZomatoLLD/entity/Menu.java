package LLD_Interview_Questions.Hard.ZomatoLLD.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    HashMap<String,MenuItem> items = new HashMap<>();

    public MenuItem getItem(String id){
        return items.get(id);
    }
    public void addItems(MenuItem item){
        items.put(item.getId(),item);
    }
}
