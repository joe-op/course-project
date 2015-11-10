package exam;

import exam.build.ParseQuestionPoolFile;
import exam.build.WriteExam;
import exam.question.Question;
import exam.question.QuestionPool;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Exam class with QuestionPool
 * Provides methods for validating a question pool file, loading a question pool file,
 * and writing an exam & key to file
 */
public class Exam {

    private QuestionPool questions;

    public Exam() {
        questions = new QuestionPool();
    }

    public Exam(QuestionPool questions) {
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
    public int write(int minChapter, int maxChapter, int noQuestions, String examFile, String keyFile) throws FileNotFoundException {
        List<Question> selectedQuestions;
        try {
            selectedQuestions = questions.cullShuffleRange(minChapter, maxChapter, noQuestions);
        } catch(IndexOutOfBoundsException e) {
            return -1;
        }
        WriteExam.write(selectedQuestions, examFile, keyFile);
        int points = 0;
        for(Question q : selectedQuestions)
            points += q.getPoints();
        return points;
    }

    public void load(String filename) throws FileNotFoundException {
        questions = ParseQuestionPoolFile.parseFile(filename);
    }

    public static boolean validateFile(String filename) throws FileNotFoundException {
        return ParseQuestionPoolFile.validateFile(filename);
    }

}
