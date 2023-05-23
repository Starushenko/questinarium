package main;

import model.Question;
import service.QuestionService;

import java.util.Scanner;

public class userMenu {
    public static int userMenu(Scanner reader) {
        System.out.println("choose your action"
                           + "\n\nSave new question - enter 1"
                           + "\nDelete question - enter 2"
                           + "\nGet random question - enter 3"
                           + "\nGet random question by topic - enter 4"
                           + "\n\nFor EXIT - enter 0");
        return reader.nextInt();
    }
    public static void saveAction(Scanner reader, QuestionService questionService) {
        System.out.println("Enter question id: ");
        Question saveQuestion = new Question();
        saveQuestion.setId(reader.nextInt());
        System.out.println("Enter question text: ");
        saveQuestion.setText(reader.next());
        System.out.println("Enter question topic: ");
        saveQuestion.setTopic(reader.next());
        questionService.saveQuestion(saveQuestion);
        System.out.println("saved");
    }
    public static void deleteAction(Scanner reader, QuestionService questionService) {
        System.out.println("Enter question id for delete: ");
        questionService.deleteQuestion(reader.nextInt());
        System.out.println("deleted");
    }

    public static void getRandomQuestionByTopic(Scanner reader, QuestionService questionService) {
        System.out.println("Enter question topic for get random question: ");
        System.out.println(questionService.getRandomQuestionByTopic(reader.next()).getText());
        System.out.println("question for you:");
    }

    public static void getRandomQuestion(QuestionService questionService) {
        System.out.println("Get random questios action");
        System.out.println(questionService.getRnd().getText());
        System.out.println("question for you:");
    }
}
