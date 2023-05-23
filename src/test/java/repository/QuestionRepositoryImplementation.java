package repository;

import model.Question;
import repository.dao.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class QuestionRepositoryImplementation implements QuestionRepository {
    private final List<Question> tempStorage= new ArrayList<>();


    public List<Question> getTempStorage() {
        tempStorage.add(Question.builder().id(1).text("text1").topic("OOP").build());
        tempStorage.add(Question.builder().id(2).text("text2").topic("java").build());
        tempStorage.add(Question.builder().id(3).text("text3").topic("OOP").build());
        tempStorage.add(Question.builder().id(4).text("text4").topic("OOP").build());
        tempStorage.add(Question.builder().id(5).text("text5").topic("OOP").build());
        tempStorage.add(Question.builder().id(6).text("text6").topic("java").build());
        return tempStorage;
    }
    @Override
    public Question getRnd() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, tempStorage.size());
        return tempStorage.get(randomNum);
    }

    @Override
    public void save(Question saveQuestion) {
        tempStorage.add(saveQuestion);
    }

    @Override
    public void delete(int id) {
        tempStorage.remove(id);
    }
}
