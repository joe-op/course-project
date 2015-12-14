package exam.build;

/**
 * Created by Joe on 12/13/2015.
 */
public class InvalidQuestionException extends Exception {

    public InvalidQuestionException() {
        super("Invalid data!");
    }
    public InvalidQuestionException(String message) {
        super(message);
    }
}
