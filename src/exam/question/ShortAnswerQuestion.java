package exam.question;

/**
 * ShortAnswerQuestion
 * Has an array of possible answers.
 */
public class ShortAnswerQuestion extends Question {

    private String[] answers;

    public ShortAnswerQuestion(int points, int chapter, String prompt, String[] answers) {
        super(points, chapter, prompt);
        this.answers = answers;
    }

    public String[] getAnswers() {
        return answers;
    }
    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\n\n";
    }
}
