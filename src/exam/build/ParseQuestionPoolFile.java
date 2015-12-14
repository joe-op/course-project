package exam.build;

import exam.question.Question;
import exam.question.QuestionPool;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Methods for handling a question pool file
 */
public class ParseQuestionPoolFile {

    public static List<Question> parseFile(String filename) throws FileNotFoundException, InvalidQuestionException {
        Scanner inFile = new Scanner(new FileReader(filename));
        List<Question> questions = new ArrayList<Question>();
        int lineNo = 0;
        while(inFile.hasNextLine()) {
            String line = inFile.nextLine();
            lineNo++;
            try {
                String questionType = ParseQuestions.getType(line);
                if (questionType.equals("multiple")) {
                    questions.add(ParseQuestions.parseMultiple(line));
                } else if (questionType.equals("short")) {
                    questions.add(ParseQuestions.parseShort(line));
                } else if (questionType.equals("long")) {
                    questions.add(ParseQuestions.parseLong(line));
                } else {
                    throw new InvalidQuestionException(
                            String.format(
                                "Invalid question type at line %d.%n%s",
                                    lineNo, line));
                }
            } catch(InvalidQuestionException e) {
                throw new InvalidQuestionException(
                        String.format(
                                "Invalid data at line %d.%n%s",
                                lineNo, line));

            }
        }
        return questions;
    }

    // Quick check if file appears valid
    public static boolean validateFile(String filename) throws FileNotFoundException {
        Scanner inFile = new Scanner(new FileReader(filename));
        if(inFile.hasNextLine()) {
            String questionType = ParseQuestions.getType(inFile.nextLine());
            return (questionType.equals("multiple") || questionType.equals("short") || questionType.equals("long"));
        } else {
            return false;
        }
    }


}
