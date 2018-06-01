package exam;

import exam.question.LongAnswerQuestion;
import exam.question.MultipleChoiceQuestion;
import exam.question.Question;
import exam.question.ShortAnswerQuestion;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Test Exam class
 */
public class ExamTest {

    private static Exam myExam;

    @Before
    public void setUp() {
        List<Question> questions = new ArrayList<>();
        questions.add(new ShortAnswerQuestion(3, 1,"",new String[]{}));
        questions.add(new LongAnswerQuestion(6, 1, "", ""));
        questions.add(new MultipleChoiceQuestion(7, 4, "", new String[]{}, 'a'));
        myExam = new Exam(questions);
    }

    @Test
    public void testGetPoints() {
        assertEquals(16, myExam.getPoints());
    }

}
