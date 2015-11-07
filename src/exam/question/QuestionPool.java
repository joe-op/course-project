package exam.question;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Question Pool Class
 */


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
        Collections.sort(questionList);
    }

    // Get questions from a range of chapters.
    // Returns a *flat* list of questions to be randomized.
    // TODO add public methods that use this (questions will need to be randomized and culled before returned)
    // TODO make sure empty catch doesn't do anything
    public List<Question> chapterRange(int min, int max) throws IllegalArgumentException {
        if(min > 0 && min <= max) {
            List<Question> selectedChapterQuestions = new ArrayList<Question>();
            // get first and last index of matching elements
            //TODO find a better way to do this
            int firstIndex = firstChapterIndex(min);
            int lastIndex = lastChapterIndex(max);
            for(int i=firstIndex; i<=lastIndex; i++) {
                selectedChapterQuestions.add(i-firstIndex, questionList.get(i));
            }
            return selectedChapterQuestions;
        } else {
            throw new IllegalArgumentException("Invalid range");
        }
    }

    public List<Question> shuffleRange(int min, int max) throws IllegalArgumentException {
        List<Question> shuffledQuestions = chapterRange(min, max);
        Collections.shuffle(shuffledQuestions);
        return shuffledQuestions;
    }

    // find first item matching chapter
    private int firstChapterIndex(int chapter) {
        int i;
        Iterator<Question> questionIterator = questionList.iterator();
        while(questionIterator.hasNext()) {
            Question q = questionIterator.next();
            if (q.getChapter() == chapter)
                return questionList.indexOf(q);
        }
        return -1;
    }
    // find last item matching chapter
    private int lastChapterIndex(int chapter) {
        int i = -1;
        boolean found = false;
        Iterator<Question> questionIterator = questionList.iterator();
        while (questionIterator.hasNext()) {
            Question q = questionIterator.next();
            if (found && q.getChapter() != chapter) {
                return i;
            }
            if (q.getChapter() == chapter) {
                found = true;
                i = questionList.indexOf(q);
            }

        }
        return i;
    }

}
