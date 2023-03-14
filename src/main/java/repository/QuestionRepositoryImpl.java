package repository;

import model.Question;
import repository.dao.QuestionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryImpl implements QuestionRepository {

    private Connection connection;

    public QuestionRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    private String findById =
            """
                    select * from question where id = ?
            """;

    private String findByTopic =
            """
                    select * from question where topic = ?
            """;

    private String save =
            """
                    INSERT INTO "public".question(
                    text, 
                    topic)
                           VALUES (?, ?)
            """;
    private String update =
            """
                    update question set           
                    text = ?, 
                    topic = ?           
                    where id = ?
            """;

    private String deleteById =
            """
                 delete from question where id = ?
            """;
    @Override
    public Question get(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findById);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return Question.builder()
                    .id(resultSet.getInt("id"))
                    .text(resultSet.getString("text"))
                    .topic(resultSet.getString("topic"))
                    .build();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Question> getByTopic(String topic) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findByTopic);
            preparedStatement.setString(1, topic);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            List<Question> questions = new ArrayList<>();
            while (resultSet.next()){
                questions.add(Question.builder()
                        .id(resultSet.getInt("id"))
                        .text(resultSet.getString("text"))
                        .topic(resultSet.getString("topic"))
                        .build()
                );
            }
            return questions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteById);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            System.out.println("elem No " +id + " was deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Question question) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1, question.getText());
            preparedStatement.setString(2, question.getTopic());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Question question) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, question.getText());
            preparedStatement.setString(2, question.getTopic());
            preparedStatement.setInt(3, question.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }






}
