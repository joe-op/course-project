package exam.question;

import java.util.*;

/**
 * Question Pool Class
 */


public class QuestionPool {

    // Each chapter has its own list
    List<Question> questionList;

    // default constructor
    public QuestionPool() { questionList = new ArrayList<>(); }

    // Getter
    public List<Question> getQuestionList() { return questionList; }

    // Add question
    public void addQuestion(Question question) {
        questionList.add(question);
        Collections.sort(questionList);
    }

    // Get questions from a range of chapters.
    // Returns a list of questions to be randomized.
    public List<Question> chapterRange(int min, int max) throws IllegalArgumentException {
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
    public List<Question> shuffleRange(int min, int max) {
        List<Question> shuffledQuestions = chapterRange(min, max);
        Collections.shuffle(shuffledQuestions);
        return shuffledQuestions;
    }

    // Return a randomized list of questions with a limit on the number of questions
    public List<Question> cullShuffleRange(int min, int max, int noQuestions) {
        return shuffleRange(min, max).subList(0, noQuestions);
    }

}
