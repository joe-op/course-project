package exam.ui;

import exam.Exam;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Takes a file containing a pool of exam questions, a number of questions to use,
 * and a minimum & maximum chapter to take questions from.
 * Writes shuffled questions & key to file.
 * Displays total number of points in generated exam.
 */
public class TextUI {

    private static Scanner console = new Scanner(System.in);
    private static Exam exam = new Exam();
    private static final String EXAM_FILE = "exam.txt";
    private static final String KEY_FILE = "key.txt";

    public static void main(String[] args) throws FileNotFoundException {

        // Prompt for file containing question pool
        boolean validFile = false;
        String filename;
        do {
            System.out.println("Enter the name of the file containing exam questions: ");
            filename = console.nextLine();
            try {
                validFile = Exam.validateFile(filename);
                if(!validFile)
                    System.out.println("File does not contain valid data.");
            } catch(FileNotFoundException e) {
                System.out.println("File was not found.");
            }
        } while(!validFile);
        exam.load(filename);

        // Get number of questions and range of chapters

        int noQuestions, minChapter, maxChapter, points;
        do {
            noQuestions = getInt("Enter the number of questions:", 0);
            minChapter = getInt("Enter the minimum chapter to take questions from:", 1);
            maxChapter = getInt("Enter the maximum chapter to take questions from:", minChapter);
            points = exam.write(minChapter, maxChapter, noQuestions, EXAM_FILE, KEY_FILE);
            if(points == -1)
                System.out.println("Not enough questions.");
        } while(points == -1);
        System.out.print(String.format("Total number of points: %d%n", points));

    }

    // get integer input with minimum value
    private static int getInt(String prompt, int min) {
        boolean validInput;
        int input;
        do {
            System.out.println(prompt);
            input = console.nextInt();
            validInput = (input >= min);
            if (!validInput)
                System.out.print(String.format("%d is not a valid entry.%n", input));
        } while (!validInput);
        return input;
    }

}
