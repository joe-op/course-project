package exam;

import exam.build.ParseQuestionPoolFile;
import exam.question.Question;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Exam class with QuestionPool
 * Provides methods for validating a question pool file, loading a question pool file,
 * and writing an exam & key to file
 */
public class Exam {

    private List<Question> questions;

    public Exam() {
        questions = new ArrayList<Question>();
    }

    public Exam(List<Question> questions) {
        this.questions = questions;
    }
    /* TODO
        Refactor WriteExam into this class
        Refactor Exam to contain list of questions
        Change TextUI to: Load questions into question pool variable
                          Use question pool to make exam variable
        Let QuestionPool have methods related to validating & loading the file
     */
    // write exam and key to file
    // returns -1 if not enough questions

    public int getPoints() {
        int points = 0;
        for (Question q : questions)
            points += q.getPoints();
        return points;
    }

    public void write(String examFile, String keyFile) throws FileNotFoundException {
        writeExam(examFile);
        writeKey(keyFile);
    }

    public static boolean validateFile(String filename) throws FileNotFoundException {
        return ParseQuestionPoolFile.validateFile(filename);
    }

    private void writeExam(String file) throws FileNotFoundException {
        PrintWriter outExam = new PrintWriter(file);
        int questionNo = 1;
        for(Question q : questions) {
            outExam.print(String.format("%2d. %s%n", questionNo, q));
            questionNo++;
        }
        outExam.close();
    }

    private void writeKey(String file) throws FileNotFoundException {
        PrintWriter outKey = new PrintWriter(file);
        int questionNo = 1;
        for(Question q : questions) {
            // Add 4 spaces in front of any additional lines in answer
            String[] answer = q.getAnswer().split("\n");
            outKey.print(String.format("%2d. %s%n", questionNo, answer[0]));
            for(int i=1; i<answer.length; i++)
                outKey.println("    " + answer[i]);
            questionNo++;
        }
        outKey.close();
    }

}
