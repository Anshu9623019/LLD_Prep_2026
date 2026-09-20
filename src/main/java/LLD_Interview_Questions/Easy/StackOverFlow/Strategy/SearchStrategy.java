package LLD_Interview_Questions.Easy.StackOverFlow.Strategy;

import LLD_Interview_Questions.Easy.StackOverFlow.Entities.Question;

import java.util.List;

public interface SearchStrategy {
    List<Question> filter(List<Question> questions);

}
