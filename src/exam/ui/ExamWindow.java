package exam.ui;

import exam.Exam;
import exam.question.Question;
import exam.question.QuestionPool;

import javax.swing.*;
import java.awt.*;

/**
 * A window that takes a question pool file and lets
 * the user create an exam
 */
public class ExamWindow extends JFrame {

    // Variables for the window
    private static final int WIDTH = 480;
    private static final int HEIGHT = 640;
    private static final int ELEMENT_WIDTH = 300;

    private JLabel noQuestionsL;
    private JTextField noQuestionsTF;

    private JLabel minChapterL;
    private JTextField minChapterTF;

    private JLabel maxChapterL;
    private JTextField maxChapterTF;

    private JButton submitB;
    private JButton toggleExamKeyB;

    private JTextArea output;

    // Variables for the exam
    private Exam exam = new Exam();
    private final String EXAM_FILE = "exam.txt";
    private final String KEY_FILE = "key.txt";
    private int noQuestions, minChapter, maxChapter;

    public ExamWindow(QuestionPool questionPool) {

        setTitle("Exam");
        setSize(WIDTH, HEIGHT);

        noQuestionsL = new JLabel("Enter the number of questions:");
        noQuestionsTF = new JTextField(10);

        minChapterL = new JLabel("Enter the minimum chapter to use:");
        minChapterTF = new JTextField(10);

        maxChapterL = new JLabel("Enter the maximum chapter to use:");
        maxChapterTF = new JTextField(10);

        submitB = new JButton("Submit");
        toggleExamKeyB = new JButton("Check Key");

        output = new JTextArea(30, 30);

        Container pane = getContentPane();
        pane.setLayout(null);

        // set locations and sizes for the elements
        noQuestionsL.setLocation(30, 15);
        noQuestionsL.setSize(ELEMENT_WIDTH, 30);
        noQuestionsTF.setLocation(45 + ELEMENT_WIDTH, 15);
        noQuestionsTF.setSize(ELEMENT_WIDTH, 30);
        minChapterL.setLocation(30, 65);
        minChapterL.setSize(ELEMENT_WIDTH, 30);
        minChapterTF.setLocation(45 + ELEMENT_WIDTH, 65);
        minChapterTF.setSize(ELEMENT_WIDTH, 30);







    }


}
