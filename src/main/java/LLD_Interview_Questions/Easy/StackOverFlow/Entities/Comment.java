package LLD_Interview_Questions.Easy.StackOverFlow.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment extends Content {

    Comment(String body, User author){
        super(UUID.randomUUID().toString(),body,author);
    }

}
