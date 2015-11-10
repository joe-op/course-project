package exam.ui;

import exam.Exam;
import exam.question.QuestionPool;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Takes a file containing a pool of exam questions, a number of questions to use,
 * and a minimum & maximum chapter to take questions from.
 * Writes shuffled questions & key to file.
 * Displays total number of points in generated exam.
 */
public class TextUI {

    private static Scanner console = new Scanner(System.in);
    private static QuestionPool questionPool = new QuestionPool();
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
                validFile = questionPool.load(filename);
                if(!validFile)
                    System.out.println("File does not contain valid data.");
            } catch(FileNotFoundException e) {
                System.out.println("File was not found.");
            }
        } while(!validFile);

        // Get number of questions and range of chapters

        int noQuestions, minChapter, maxChapter;
        boolean validExam = false;
        do {
            noQuestions = getInt("Enter the number of questions:", 1);
            minChapter = getInt("Enter the minimum chapter to take questions from:", 1);
            maxChapter = getInt("Enter the maximum chapter to take questions from:", minChapter);
            //points = exam.write(minChapter, maxChapter, noQuestions, EXAM_FILE, KEY_FILE);
            try {
                exam = questionPool.makeExam(minChapter, maxChapter, noQuestions);
                validExam = true;
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Not enough questions");
            }
        } while(!validExam);
        exam.write(EXAM_FILE, KEY_FILE);
        System.out.print(String.format("Total number of points: %d%n", exam.getPoints()));

    }

    // get integer input with minimum value
    private static int getInt(String prompt, int min) {
        String inputStr;
        int input = min - 1;
        do {
            System.out.println(prompt);
            inputStr = console.next();
            try {
                input = Integer.parseInt(inputStr);
            } catch(NumberFormatException e) {
                System.out.println(inputStr + " is not a valid entry.");
            }
        } while (input <= min);
        return input;
    }

}
