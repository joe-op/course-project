package exam.question;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Question Pool Class
 */
public class QuestionPool {

    // Each chapter has its own list
    List<ArrayList<Question>> questionList;

    // default constructor
    public QuestionPool() { questionList = new ArrayList<ArrayList<Question>>(); }

    // Getter
    public List<ArrayList<Question>> getQuestionList() { return questionList; }

    // Add question
    // TODO is there a better way to push?
    public void addQuestion(Question question) {
        int chapter = question.getChapter();
        ArrayList<Question> chapterList;
        try {
            chapterList = questionList.get(chapter);
        } catch(IndexOutOfBoundsException e) {
            questionList.add(chapter, new ArrayList<Question>());
            chapterList = questionList.get(chapter);
        }
        // add at index 'size()' to mimic push method
        chapterList.add(chapterList.size(), question);
    }

    // Get questions from a range of chapters.
    // Returns a *flat* list of questions to be randomized.
    // TODO add public methods that use this (questions will need to be randomized and culled before returned)
    // TODO make sure empty catch doesn't do anything
    private List<Question> chapterRange(int min, int max) throws IllegalArgumentException {
        if(min > 0 && min <= max) {
            List<Question> selectedChapterQuestions = new ArrayList<Question>();
            for(int i=min; i<=max; i++) {
                // catch exception if a chapter does not exist
                try {
                    selectedChapterQuestions.addAll(questionList.get(i));
                } catch(IndexOutOfBoundsException e) {}
            }
            return selectedChapterQuestions;
        } else {
            throw new IllegalArgumentException("Invalid range");
        }
    }

    // Sort by chapter
    // Not necessary if already sorted into lists but just in case
    // See http://stackoverflow.com/questions/8432581/how-to-sort-a-listobject-alphabetically-using-object-name-field

}
