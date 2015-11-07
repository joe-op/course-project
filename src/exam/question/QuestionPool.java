package exam.question;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Question Pool Class
 */

// TODO implement Comparable in questions and use it here

public class QuestionPool {

    // Each chapter has its own list
    List<Question> questionList;

    // default constructor
    public QuestionPool() { questionList = new ArrayList<Question>(); }

    // Getter
    public List<Question> getQuestionList() { return questionList; }

    // Add question
    public void addQuestion(Question question) {
        questionList.add(question);
    }

    // Get questions from a range of chapters.
    // Returns a *flat* list of questions to be randomized.
    // TODO add public methods that use this (questions will need to be randomized and culled before returned)
    // TODO make sure empty catch doesn't do anything
    private List<Question> chapterRange(int min, int max) throws IllegalArgumentException {
        if(min > 0 && min <= max) {
            List<Question> selectedChapterQuestions = new ArrayList<Question>();
            // TODO
            return selectedChapterQuestions;
        } else {
            throw new IllegalArgumentException("Invalid range");
        }
    }

    // Sort by chapter
    // Not necessary if already sorted into lists but just in case
    // See http://stackoverflow.com/questions/8432581/how-to-sort-a-listobject-alphabetically-using-object-name-field

}
