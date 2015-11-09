package exam.build;

import exam.question.Question;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Write exam & key to file.
 */
public class WriteExam {

    public static void write(List<Question> questions, String examFile, String keyFile) throws FileNotFoundException {
        writeExam(questions, examFile);
        writeKey(questions, keyFile);
    }

    public static void writeExam(List<Question> questions, String file) throws FileNotFoundException {
        PrintWriter outExam = new PrintWriter(file);
        int questionNo = 1;
        for(Question q : questions) {
            outExam.print(String.format("%2d. %s%n", questionNo, q));
            questionNo++;
        }
        outExam.close();
    }

    public static void writeKey(List<Question> questions, String file) throws FileNotFoundException {
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
