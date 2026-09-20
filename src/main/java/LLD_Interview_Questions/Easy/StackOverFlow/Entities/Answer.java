package LLD_Interview_Questions.Easy.StackOverFlow.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answer extends Post {
    private boolean isAccepted = false;

    public Answer(String body, User author) {
        super(UUID.randomUUID().toString(), body, author);
    }

}
