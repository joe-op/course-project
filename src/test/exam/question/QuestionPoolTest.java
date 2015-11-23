package exam.question;

import org.junit.*;
import java.util.List;
import static org.junit.Assert.*;

/*
 * Test QuestionPool class
 */


public class QuestionPoolTest {

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
        questions2 = new QuestionPool();

    }

    @Test
    public void testAddQuestion() {
        questions2.addQuestion(multipleChoice1);
        assert(questions2.getQuestionList().contains(multipleChoice1));
        questions2.addQuestion(longAnswer1);
        assert(questions2.getQuestionList().contains(longAnswer1));
        questions2.addQuestion(shortAnswer2);
        assert(questions2.getQuestionList().contains(shortAnswer2));
    }

    @Test
    public void testMakeExam() {
        List<Question> chapter1Exam = questions.makeExam(1,1,2).getQuestions();
        assertEquals(2, chapter1Exam.size());
        assert(chapter1Exam.contains(shortAnswer1));
        assert(chapter1Exam.contains(shortAnswer2));
        List<Question> chapter1to3Exam = questions.makeExam(1,3,4).getQuestions();
        assertEquals(4, chapter1to3Exam.size());
        assert(!chapter1to3Exam.contains(multipleChoice2));
        List<Question> finalExam = questions.makeExam(1,4,6).getQuestions();
        assertEquals(6, finalExam.size());
        assert(finalExam.contains(multipleChoice1));
        assert(finalExam.contains(multipleChoice2));
        assert(finalExam.contains(longAnswer1));
        assert(finalExam.contains(longAnswer2));
        assert(finalExam.contains(shortAnswer1));
        assert(finalExam.contains(shortAnswer2));
    }


}