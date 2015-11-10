package exam.build;

import exam.question.*;
import org.junit.*;

public class ParseQuestionsTest {

    private static String multiple, shortAnswer, longAnswer;

    @Before
    public void setUp() {
        multiple = "multiple@1@2@Which header file must be included to use an object of the string class?@5@b@namespace std@string@string.h@cstring@iostream";
        shortAnswer = "short@1@10@In inheritance, what is the technical term for the \"parent\" class?@base class";
        longAnswer = "long@5@7@Describe Jar Jar Binks.@Hated by many, Jar Jar may turn out to be a super villain.@We'll see this December.";
    }


    @Test
    public void testParseMultiple() throws Exception {
        Assert.assertEquals("multiple", ParseQuestions.getType(multiple));
        MultipleChoiceQuestion multipleQ = ParseQuestions.parseMultiple(multiple);
        Assert.assertEquals(1, multipleQ.getPoints());
        Assert.assertEquals(2, multipleQ.getChapter());
        Assert.assertEquals("Which header file must be included to use an object of the string class?", multipleQ.getPrompt());
        String[] answers = multipleQ.getChoices();
        Assert.assertEquals(5, answers.length);
        Assert.assertEquals("namespace std", answers[0]);
        Assert.assertEquals("string", answers[1]);
        Assert.assertEquals("string.h", answers[2]);
        Assert.assertEquals("cstring", answers[3]);
        Assert.assertEquals("iostream", answers[4]);
        Assert.assertEquals("b", multipleQ.getAnswer());
    }

    @Test
    public void testParseShort() throws Exception {
        Assert.assertEquals("short", ParseQuestions.getType(shortAnswer));
        ShortAnswerQuestion shortQ = ParseQuestions.parseShort(shortAnswer);
        Assert.assertEquals(1, shortQ.getPoints());
        Assert.assertEquals(10, shortQ.getChapter());
        Assert.assertEquals("In inheritance, what is the technical term for the \"parent\" class?", shortQ.getPrompt());
        String[] answers = shortQ.getAnswers();
        Assert.assertEquals(1, answers.length);
        Assert.assertEquals("base class", answers[0]);
    }

    @Test
    public void testParseLong() throws Exception {
        Assert.assertEquals("long", ParseQuestions.getType(longAnswer));
        LongAnswerQuestion longQ = ParseQuestions.parseLong(longAnswer);
        Assert.assertEquals(5, longQ.getPoints());
        Assert.assertEquals(7, longQ.getChapter());
        Assert.assertEquals("Describe Jar Jar Binks.", longQ.getPrompt());
        Assert.assertEquals("Hated by many, Jar Jar may turn out to be a super villain.\nWe'll see this December.", longQ.getAnswer());

    }


}