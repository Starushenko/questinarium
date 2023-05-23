package main;

import repository.ConnectionSingleton;
import repository.QuestionRepositoryImpl;
import service.QuestionService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        System.out.println("Hi");
        QuestionService questionService = new QuestionService(new QuestionRepositoryImpl(ConnectionSingleton.getConnection()));
        Scanner scanner = new Scanner(System.in);
        do{
            int action = userMenu.userMenu(scanner);
            switch (action){
                case 0 :
                    System.out.println("good buy");
                    System.exit(1);
                    break;
                case 1 :
                    userMenu.saveAction(scanner,questionService);
                    break;
                case 2 :
                    userMenu.deleteAction(scanner,questionService);
                    break;
                case 3 :
                    userMenu.getRandomQuestion(questionService);
                    break;
                case 4 :
                    userMenu.getRandomQuestionByTopic(scanner,questionService);
                    break;
            }
        }while (true);
    }
}
