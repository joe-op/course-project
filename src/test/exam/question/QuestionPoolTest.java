package exam.question;

import junit.framework.TestCase;
import org.junit.BeforeClass;
//import org.junit.*;

/**
 * Created by Joe on 11/5/2015.
 */
public class QuestionPoolTest extends TestCase {

    private static QuestionPool questions;
    private static Question multipleChoice1;
    private static Question multipleChoice2;
    private static Question longAnswer1;
    private static Question longAnswer2;
    private static Question shortAnswer1;
    private static Question shortAnswer2;

    @BeforeClass
    public void setUpClass() {
        Question multipleChoice1 = new MultipleChoiceQuestion(3, 2, "Which is a fruit?",
                new String[] {"Car", "Boat", "Apple", "Kid"}, 2);
        Question multipleChoie2 = new MultipleChoiceQuestion(2, 4, "Which is not a fruit?",
                new String[] {"Car", "Peach", "Watermelon", "Apple"}, 0);
        Question longAnswer1 = new LongAnswerQuestion(10, 2, "Describe fruit", "Fruit is something you eat");
        Question longAnswer2 = new LongAnswerQuestion(8, 3, "Describe vegetable", "Vegetable is something you should eat");
        Question shortAnswer1 = new ShortAnswerQuestion(2, 1, "Name a red fruit",
                new String[] {"Cherry", "Strawberry", "Apple"});
        Question shortAnswer2 = new ShortAnswerQuestion(2, 1, "Name a blue fruit",
                new String[] {"Blueberry"});
        QuestionPool questions = new QuestionPool();

    }

    public void testAddQuestion() throws Exception {
        questions.addQuestion(multipleChoice1);
        assert(questions.getQuestionList().contains(multipleChoice1));
        questions.addQuestion(longAnswer1);
        assert(questions.getQuestionList().contains(longAnswer1));
        questions.addQuestion(shortAnswer2);
        assert(questions.getQuestionList().contains(shortAnswer2));
    }

    public void testGetQuestionList() throws Exception {

    }


}