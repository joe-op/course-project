package exam.build;

import exam.question.QuestionPool;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by Joe on 11/7/2015.
 */
public class ParseQuestionPoolFile {

    public static QuestionPool parseFile(String fileName) throws FileNotFoundException {
        Scanner inFile = new Scanner(new FileReader(fileName));
        QuestionPool questions = new QuestionPool();
        while(inFile.hasNext()) {
            String question = inFile.nextLine();
            String questionType = ParseQuestions.getType(question);
            if(questionType.equals("multiple")) {
                questions.addQuestion(ParseQuestions.parseMultiple(question));
            } else if(questionType.equals("short")) {
                questions.addQuestion(ParseQuestions.parseShort(question));
            } else if(questionType.equals("long")) {
                questions.addQuestion(ParseQuestions.parseLong(question));
            }
        }
        return questions;
    }


}
