package repository;

import model.Question;
import repository.dao.QuestionRepository;

import javax.tools.ForwardingJavaFileManager;
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
    private String getRnd =
            """
                    select * from question ORDER BY RAND() LIMIT 1 ?
            """;

    private String save =
            """
                    INSERT INTO "public".question(
                    text, 
                    topic)
                           VALUES (?, ?)
            """;

    private String deleteById =
            """
                 delete from question where id = ?
            """;




    @Override
    public Question getRnd() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getRnd);
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








}
