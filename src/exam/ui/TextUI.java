package exam.ui;

import exam.build.ParseQuestionPoolFile;
import exam.question.QuestionPool;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
            // Print error message if validFile has been changed to false
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




    }



}
