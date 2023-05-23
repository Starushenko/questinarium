package service;

import model.Question;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.QuestionRepositoryImplementation;

import java.util.List;

public class QuestionServiceTest {
    private QuestionRepositoryImplementation mockQuestionRepository;
    private QuestionService questionService;
    private final Question saveQuestion = Question.builder()
            .id(13)
            .text("saveTest")
            .topic("saveTest")
            .build();


    @Before
    public void init() {
        questionService = new QuestionService(mockQuestionRepository);
    }
    @Test
    public void getRandomQuestionByTopic() {
        String topic = "OOP";
        Question randomQuestion = questionService.getRandomQuestionByTopic(topic);
        Assert.assertTrue(mockQuestionRepository.getTempStorage().contains(randomQuestion));
    }

    @Test
    public void getRandomQuestion() {
        Question randomQuestion = questionService.getRnd();
        Assert.assertTrue(mockQuestionRepository.getTempStorage().contains(randomQuestion));
    }

    @Test
    public void saveQuestion() {
        questionService.saveQuestion(saveQuestion);
        Assert.assertTrue(mockQuestionRepository.getTempStorage().contains(saveQuestion));
    }

    @Test
    public void deleteQuestion() {
        questionService.saveQuestion(saveQuestion);
        questionService.deleteQuestion(1);
        Assert.assertFalse(mockQuestionRepository.getTempStorage().contains(saveQuestion));
    }


}
