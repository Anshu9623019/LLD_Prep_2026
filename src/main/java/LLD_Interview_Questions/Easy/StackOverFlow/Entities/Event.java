package LLD_Interview_Questions.Easy.StackOverFlow.Entities;

import LLD_Interview_Questions.Easy.StackOverFlow.Enum.EventType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Event {
    private EventType type;
    private User actor;
    private Post targetPost;

    public  Event(EventType type, User actor, Post targerPost){
        this.type = type;
        this.actor = actor;
        this.targetPost = targerPost;
    }



}
