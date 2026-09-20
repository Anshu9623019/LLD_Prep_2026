package LLD_Interview_Questions.Easy.StackOverFlow.Entities;

import LLD_Interview_Questions.Easy.StackOverFlow.Enum.EventType;
import LLD_Interview_Questions.Easy.StackOverFlow.Enum.VoteType;
import LLD_Interview_Questions.Easy.StackOverFlow.Observer.PostObserver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post extends Content {
    private final AtomicInteger voteCount = new AtomicInteger(0);
    private final Map<String, VoteType> voters = new ConcurrentHashMap<>();
    private final List<Comment> comments = new CopyOnWriteArrayList<>();
    private final List<PostObserver> observers = new CopyOnWriteArrayList<>();

    public  Post(String id, String body, User author){
        super(id, body, author);
    }
    public void addObserver(PostObserver observer) {
        this.observers.add(observer);
    }

    public void notifyObservers(Event event){
        observers.forEach(o-> o.onPostEvent(event));
    }


    public synchronized void vote(User user, VoteType voteType){
        String userId = user.getId();


        if(voters.get(userId)==voteType) return;

        int scoreChange = 0;

        if(voters.containsKey(userId)){
            scoreChange = (voteType== VoteType.UPVOTE) ? 2 : -2;
        }else {
            scoreChange = (voteType == VoteType.UPVOTE) ? 1 : -1;
        }

        voters.put(userId, voteType);
        voteCount.addAndGet(scoreChange);

        EventType eventType = EventType.UPVOTE_QUESTION;


        if (this instanceof Question) {
            eventType = (voteType == VoteType.UPVOTE ? EventType.UPVOTE_QUESTION : EventType.DOWNVOTE_QUESTION);
        } else {
            eventType = (voteType == VoteType.UPVOTE ? EventType.UPVOTE_ANSWER : EventType.DOWNVOTE_ANSWER);
        }

        notifyObservers(new Event(eventType,user,this));

    }
}
