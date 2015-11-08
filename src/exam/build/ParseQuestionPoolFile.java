package exam.build;

import exam.question.QuestionPool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.InvalidParameterException;
import java.util.Scanner;

/**
 * Created by Joe on 11/7/2015.
 */
public class ParseQuestionPoolFile {

    public static QuestionPool parseFile(String filename) throws FileNotFoundException {
        Scanner inFile = new Scanner(new FileReader(filename));
        QuestionPool questions = new QuestionPool();
        int lineNo = 0;
        while(inFile.hasNextLine()) {
            String line = inFile.nextLine();
            lineNo++;
            try {
                String questionType = ParseQuestions.getType(line);
                if (questionType.equals("multiple")) {
                    questions.addQuestion(ParseQuestions.parseMultiple(line));
                } else if (questionType.equals("short")) {
                    questions.addQuestion(ParseQuestions.parseShort(line));
                } else if (questionType.equals("long")) {
                    questions.addQuestion(ParseQuestions.parseLong(line));
                } else {
                    System.out.print(
                            String.format("Invalid question type at line %d.%n%s%n",
                                    lineNo, line));
                }
            } catch(InvalidParameterException e) {
                System.out.print(
                        String.format("Invalid data at line %d.%n%s%n",
                                lineNo, line));
            }
        }
        return questions;
    }

    // Quick check if file appears valid
    public static boolean validateFile(String filename) throws FileNotFoundException {
        Scanner inFile = new Scanner(new FileReader(filename));
        if(inFile.hasNextLine()) {
            String questionType = inFile.nextLine().split("@")[0];
            return (questionType.equals("multiple") || questionType.equals("short") || questionType.equals("long"));
        } else {
            return false;
        }
    }


}
