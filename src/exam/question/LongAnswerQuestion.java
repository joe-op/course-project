package exam.question;

/**
 * LongAnswerQuestion
 * Has one answer.
 * Overrides toString() method to add space for the answer.
 */
public class LongAnswerQuestion extends Question {

    private String answer;

    public LongAnswerQuestion(int points, int chapter, String prompt, String answer) {
        super(points, chapter, prompt);
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\n\n\n\n\n\n\n\n\n";
    }
}
