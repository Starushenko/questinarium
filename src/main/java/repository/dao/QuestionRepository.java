package repository.dao;

import model.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionRepository {
    Question getRnd();
    void save(Question saveQuestion);
    void delete(int id);


}
