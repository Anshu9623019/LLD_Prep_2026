package LLD_Interview_Questions.Easy.StackOverFlow.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Content {
    protected  String id;
    protected  String body;
    protected User author;
    protected  LocalDateTime creationTime;

    public Content(String id, String body, User author){
        this.id = id;
        this.body = body;
        this.author = author;
        this.creationTime = LocalDateTime.now();
    }


}
