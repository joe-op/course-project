package exam.question;

/**
 * MultipleChoiceQuestion
 * Has an array of choices and an integer indicating the index of the correct answer.
 */
public class MultipleChoiceQuestion extends Question {

    private char answer;
    private String[] choices;

    public MultipleChoiceQuestion(int points, int chapter, String prompt, String[] choices, char answer) {
        super(points, chapter, prompt);
        this.choices = choices;
        this.answer = answer;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }
    public String getAnswer() {
        return String.valueOf(answer);
    }

    public void setAnswer(char answer) {
        this.answer = answer;
    }

    // TODO override toString()

}
