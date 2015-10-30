package exam.ui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by Joe on 10/30/2015.
 */
public class TextUI {

    private static Scanner inFile;
    private static Scanner console = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {

        // Prompt for file containing question pool
        System.out.println("Enter the name of the file containing exam questions: ");
        inFile = new Scanner(new FileReader(console.nextLine()));

        /*
         * Load questions using exam.build.ParseQuestions
         *
         */

    }



}
