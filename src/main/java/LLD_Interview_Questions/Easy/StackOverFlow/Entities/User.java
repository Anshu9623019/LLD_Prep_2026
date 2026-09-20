package LLD_Interview_Questions.Easy.StackOverFlow.Entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private AtomicInteger reputation;

    public User(String name){
        this.name = name;
    }

    User(String name, String email){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.reputation = new AtomicInteger(0);
    }

    public void updateReputation(int change){
        this.reputation.addAndGet(change);
    }

}
