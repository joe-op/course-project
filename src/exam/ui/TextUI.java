package exam.ui;

import exam.build.ParseQuestionPoolFile;
import exam.question.Question;
import exam.question.QuestionPool;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Joe on 10/30/2015.
 */
public class TextUI {

    private static Scanner console = new Scanner(System.in);
    private static QuestionPool questions;

    public static void main(String[] args) throws FileNotFoundException {

        // Prompt for file containing question pool
        boolean validFile = false;
        String filename;
        do {
            System.out.println("Enter the name of the file containing exam questions: ");
            filename = console.nextLine();
            try {
                validFile = ParseQuestionPoolFile.validateFile(filename);
                if(!validFile)
                    System.out.println("File does not contain valid data.");
            } catch(FileNotFoundException e) {
                System.out.println("File was not found.");
            }
        } while(!validFile);
        questions = ParseQuestionPoolFile.parseFile(filename);

        // Get number of questions and range of chapters

        boolean validExam = false;
        int noQuestions, minChapter, maxChapter;
        List<Question> examQuestions = new ArrayList<Question>();
        do {
            noQuestions = getInt("Enter the number of questions:", 0);
            minChapter = getInt("Enter the minimum chapter to take questions from:", 1);
            maxChapter = getInt("Enter the maximum chapter to take questions from:", minChapter);
            try {
                examQuestions = questions.cullShuffleRange(minChapter, maxChapter, noQuestions);
                validExam = true;
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Not enough questions.");
            }
        } while(!validExam);

        // Print exam & key
        PrintWriter outExam = new PrintWriter("exam.txt");
        PrintWriter outKey = new PrintWriter("key.txt");
        int questionNo = 1;
        for(Question el : examQuestions) {
            outExam.print(String.format("%2d. %s%n", questionNo, el));
            outKey.print(String.format("%2d. %s%n", questionNo, el.getAnswer()));
            questionNo++;
        }
        outExam.close();
        outKey.close();
        // Display total number of points
        int points = 0;
        for(Question el: examQuestions)
            points += el.getPoints();
        System.out.print(
                String.format("Total number of points: %d%n", points));



    }

    // get integer input within a certain range
    private static int getInt(String prompt, int min) {
        boolean validInput = false;
        int input;
        do {
            System.out.println(prompt);
            input = console.nextInt();
            validInput = (input >= min);
            if (!validInput)
                System.out.print(String.format("%d is not a valid entry.%n%s%n", input, prompt));
        } while (!validInput);
        return input;
    }



}
