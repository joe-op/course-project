package exam.question;

import junit.framework.TestCase;
import org.junit.*;

import java.util.List;

/**
 * Created by Joe on 11/5/2015.
 */
public class QuestionPoolTest extends TestCase {

    private static QuestionPool questions;
    private static QuestionPool questions2;
    private static Question multipleChoice1;
    private static Question multipleChoice2;
    private static Question longAnswer1;
    private static Question longAnswer2;
    private static Question shortAnswer1;
    private static Question shortAnswer2;

    @Before
    public void setUp() {
        multipleChoice1 = new MultipleChoiceQuestion(3, 2, "Which is a fruit?",
                new String[] {"Car", "Boat", "Apple", "Kid"}, 'c');
        multipleChoice2 = new MultipleChoiceQuestion(2, 4, "Which is not a fruit?",
                new String[] {"Car", "Peach", "Watermelon", "Apple"}, 'a');
        longAnswer1 = new LongAnswerQuestion(10, 2, "Describe fruit", "Fruit is something you eat");
        longAnswer2 = new LongAnswerQuestion(8, 3, "Describe vegetable", "Vegetable is something you should eat");
        shortAnswer1 = new ShortAnswerQuestion(2, 1, "Name a red fruit",
                new String[] {"Cherry", "Strawberry", "Apple"});
        shortAnswer2 = new ShortAnswerQuestion(2, 1, "Name a blue fruit",
                new String[] {"Blueberry"});
        questions = new QuestionPool();
        questions.addQuestion(multipleChoice1);
        questions.addQuestion(multipleChoice2);
        questions.addQuestion(longAnswer1);
        questions.addQuestion(longAnswer2);
        questions.addQuestion(shortAnswer1);
        questions.addQuestion(shortAnswer2);
        // Use to test addQuestion()
        questions2 = new QuestionPool();

    }

    @Test
    public void testAddQuestion() throws Exception {
        questions2.addQuestion(multipleChoice1);
        assert(questions2.getQuestionList().contains(multipleChoice1));
        questions2.addQuestion(longAnswer1);
        assert(questions2.getQuestionList().contains(longAnswer1));
        questions2.addQuestion(shortAnswer2);
        assert(questions2.getQuestionList().contains(shortAnswer2));
    }

    @Test
    public void testChapterRange() throws Exception {
        List<Question> selectedQuestions = questions.chapterRange(1,2);
        assert(selectedQuestions.contains(multipleChoice1));
        assert(selectedQuestions.contains(longAnswer1));
        assert(selectedQuestions.contains(shortAnswer1));
        assert(selectedQuestions.contains(shortAnswer2));
        assert(!selectedQuestions.contains(multipleChoice2));
        assert(!selectedQuestions.contains(longAnswer2));
    }

    //TODO Chapters 1-9 returns less questions than Chapters 2-9
    @Test
    public void testShuffleRange() throws Exception {

    }

    @Test
    public void testCullRange() throws Exception {

    }


}