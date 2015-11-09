package exam.build;

import exam.question.*;
import junit.framework.TestCase;
import org.junit.*;

/**
 * Created by Joe on 11/7/2015.
 */
public class ParseQuestionsTest extends TestCase {

    private static String multiple, shortAnswer, longAnswer;

    @Before
    public void setUp() {
        multiple = "multiple@1@2@Which header file must be included to use an object of the string class?@5@b@namespace std@string@string.h@cstring@iostream";
        shortAnswer = "short@1@10@In inheritance, what is the technical term for the \"parent\" class?@base class";
        longAnswer = "long@5@7@Describe Jar Jar Binks.@Hated by many, Jar Jar may turn out to be a super villain.@We'll see this December.";
    }


    @Test
    public void testParseMultiple() throws Exception {
        assertEquals("multiple", ParseQuestions.getType(multiple));
        MultipleChoiceQuestion multipleQ = ParseQuestions.parseMultiple(multiple);
        assertEquals(1, multipleQ.getPoints());
        assertEquals(2, multipleQ.getChapter());
        assertEquals("Which header file must be included to use an object of the string class?", multipleQ.getPrompt());
        String[] answers = multipleQ.getChoices();
        assertEquals(5, answers.length);
        assertEquals("namespace std", answers[0]);
        assertEquals("string", answers[1]);
        assertEquals("string.h", answers[2]);
        assertEquals("cstring", answers[3]);
        assertEquals("iostream", answers[4]);
        assertEquals("b", multipleQ.getAnswer());
    }

    @Test
    public void testParseShort() throws Exception {
        assertEquals("short", ParseQuestions.getType(shortAnswer));
        ShortAnswerQuestion shortQ = ParseQuestions.parseShort(shortAnswer);
        assertEquals(1, shortQ.getPoints());
        assertEquals(10, shortQ.getChapter());
        assertEquals("In inheritance, what is the technical term for the \"parent\" class?", shortQ.getPrompt());
        String[] answers = shortQ.getAnswers();
        assertEquals(1, answers.length);
        assertEquals("base class", answers[0]);
    }

    @Test
    public void testParseLong() throws Exception {
        assertEquals("long", ParseQuestions.getType(longAnswer));
        LongAnswerQuestion longQ = ParseQuestions.parseLong(longAnswer);
        assertEquals(5, longQ.getPoints());
        assertEquals(7, longQ.getChapter());
        assertEquals("Describe Jar Jar Binks.", longQ.getPrompt());
        assertEquals("Hated by many, Jar Jar may turn out to be a super villain.\nWe'll see this December.", longQ.getAnswer());

    }


}