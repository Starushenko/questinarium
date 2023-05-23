package service;


import model.Question;
import repository.dao.QuestionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionService {
    private final QuestionRepository questionRepository;
    private final Map<String, List<Question>> questionsByTopic = new HashMap<>();

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public Question getRandomQuestionByTopic(String topic){
        Question randomQuestion=questionRepository.getRnd();
        while (!randomQuestion.getTopic().equals(topic)){
            randomQuestion =this.questionRepository.getRnd();
        }
        return randomQuestion;
    }
    public Question getRnd(){
        return this.questionRepository.getRnd();
    }
public void saveQuestion(Question saveQuestion){
        this.questionRepository.save(saveQuestion);
    }
    public void deleteQuestion(int id){
        this.questionRepository.delete(id);
    }





}
