package exam.question;

import exam.Exam;
import exam.build.ParseQuestionPoolFile;

import javax.xml.bind.ParseConversionEvent;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Question Pool Class
 */


public class QuestionPool {

    List<Question> questionList;

    // default constructor
    public QuestionPool() { questionList = new ArrayList<>(); }

    // Getter
    public List<Question> getQuestionList() { return questionList; }

    // load questions from file
    public boolean load(String filename) throws FileNotFoundException {
        if(ParseQuestionPoolFile.validateFile(filename)) {
            questionList = ParseQuestionPoolFile.parseFile(filename);
            return true;
        } else {
            return false;
        }
    }

    // Add question
    public void addQuestion(Question question) {
        questionList.add(question);
        Collections.sort(questionList);
    }

    // Get questions from a range of chapters.
    // Returns a list of questions to be randomized.
    private List<Question> chapterRange(int min, int max) throws IllegalArgumentException {
        if(min > 0 && min <= max) {
            List<Question> selectedChapterQuestions = new ArrayList<>();
            for(Question q : questionList) {
                if (q.getChapter() >= min && q.getChapter() <= max)
                    selectedChapterQuestions.add(q);
            }
            return selectedChapterQuestions;
        } else {
            throw new IllegalArgumentException("Invalid range");
        }
    }

    // Return a randomized list of questions
    private List<Question> shuffleRange(int min, int max) {
        List<Question> shuffledQuestions = chapterRange(min, max);
        Collections.shuffle(shuffledQuestions);
        return shuffledQuestions;
    }

    // Return a randomized list of questions with a limit on the number of questions
    // If there are not enough questions, return as many questions as possible
    private List<Question> cullShuffleRange(int min, int max, int noQuestions) {
        List<Question> range = shuffleRange(min, max);
        if(noQuestions <= range.size()) {
            return range.subList(0, noQuestions);
        } else {
            return range;
        }
    }

    public Exam makeExam(int minChapter, int maxChapter, int noQuestions) {
        return new Exam(cullShuffleRange(minChapter, maxChapter, noQuestions));
    }

}
