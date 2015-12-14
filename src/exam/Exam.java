package exam;

import exam.build.ParseQuestionPoolFile;
import exam.question.Question;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Joe Opseth & Bel Sahn
 * Course Project CS235
 * 23 November 2015
 *
 *
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

    // getters
    public int getPoints() {
        int points = 0;
        for (Question q : questions)
            points += q.getPoints();
        return points;
    }
    public List<Question> getQuestions() {
        return questions;
    }

    // write exam & key to file
    public void write(String examFile, String keyFile) throws FileNotFoundException {
        writeExam(examFile);
        writeKey(keyFile);
    }

    public static boolean validateFile(String filename) throws FileNotFoundException {
        return ParseQuestionPoolFile.validateFile(filename);
    }

    // toString
    public String toString() {
        String str = "";
        int questionNo = 1;
        for(Question q : questions) {
            str += String.format("%2d. %s%n", questionNo, q);
            questionNo++;
        }
        return str;
    }

    public String keyToString() {
        String str = "";
        int questionNo = 1;
        for(Question q : questions) {
            String[] answer = q.getAnswer().split("\n");
            str += String.format("%2d. %s%n", questionNo, answer[0]);
            for (int i = 1; i < answer.length; i++)
                str += String.format("    %s%n", answer[i]);
            questionNo++;
        }
        return str;
    }

    // write exam to file
    private void writeExam(String file) throws FileNotFoundException {
        PrintWriter outExam = new PrintWriter(file);
        outExam.print(toString());
        outExam.close();
    }

    // write key to file
    private void writeKey(String file) throws FileNotFoundException {
        PrintWriter outKey = new PrintWriter(file);
        outKey.print(keyToString());
        outKey.close();
    }

}
