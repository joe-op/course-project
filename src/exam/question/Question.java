package exam.question;

/**
 * Question - 10/28/15
 * Has integers points & chapter and String prompt
 * Provides getters, setters, and a default toString() method
 */
public abstract class Question {

    private int points;
    private int chapter;
    private String prompt;

    // constructors
    public Question(int points, int chapter, String prompt) {
        this.points = points;
        this.chapter = chapter;
        this.prompt = prompt;
    }
    public String toString() {
        return String.format("(%s)%s%n%n", pluralize(points), prompt);
    }
    // setters and getters
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    private String pluralize(int points) {
        if (points == 1)
            return String.format("%d point", points);
        else
            return String.format("%d points", points);
    }



}
