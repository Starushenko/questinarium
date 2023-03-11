package repository;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QuestionRepositoryImplTest {
    private String user = "postgres";
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private Connection connection;
    @Before
    public void init() throws SQLException {
        connection = DriverManager.getConnection(url,user,"");
    }
    @Test
    public void getTest(){
        QuestionRepositoryImpl questionRepository = new QuestionRepositoryImpl(connection);
        System.out.println(questionRepository.get(2));
    }

    @Test
    public void getByTopicTest(){
        QuestionRepositoryImpl questionRepository = new QuestionRepositoryImpl(connection);
        System.out.println(questionRepository.getByTopic("OOP"));
    }





}