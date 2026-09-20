package LLD_Interview_Questions.Easy.StackOverFlow.Entities;

import LLD_Interview_Questions.Easy.StackOverFlow.Enum.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question extends Post {

    private String title;
    private List<Answer> answers = new ArrayList<>();
    private Set<Tag> tags;
    private Answer acceptedAnswer;

    public Question(String title, String body, User author, Set<Tag> tags){
        super(UUID.randomUUID().toString(),body,author);
        this.title = title;
        this.tags = tags;
    }

    public void addAnswer(Answer answer) { this.answers.add(answer); }

    public synchronized void acceptAnswer(Answer answer) {
        if (!this.author.getId().equals(answer.getAuthor().getId()) && this.acceptedAnswer == null) {
            this.acceptedAnswer = answer;
            answer.setAccepted(true);
            notifyObservers(new Event(EventType.ACCEPT_ANSWER, answer.getAuthor(), answer));
        }
    }


}
