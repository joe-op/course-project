package exam.question;

/**
 * Created by Joe on 10/28/2015.
 */
public class TestQuestion {

    public static void main(String[] args) {
        Question mpq = new MultipleChoiceQuestion(3, 3, "What is your name?", new String[]{"Bob", "Ruxin", "Steve", "Bill"},2);
        System.out.println(mpq.getPoints());
    }
}
