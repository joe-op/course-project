package exam.ui;


import exam.Exam;
import exam.question.Question;
import exam.question.QuestionPool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A window that takes a question pool file and lets
 * the user create an exam
 */
public class ExamWindow extends JFrame {

    // Variables for the window
    private static final int WIDTH = 640;
    private static final int HEIGHT = 640;
    private static final int ELEMENT_WIDTH = 275;

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
    private QuestionPool questionPool;
    private Exam exam;
    private final String EXAM_FILE = "exam.txt";
    private final String KEY_FILE = "key.txt";
    private int noQuestions, minChapter, maxChapter;

    public ExamWindow(QuestionPool questionPoolP) {

        questionPool = questionPoolP;

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
        //noQuestions
        noQuestionsL.setLocation(30, 15);
        noQuestionsL.setSize(ELEMENT_WIDTH, 30);
        noQuestionsTF.setLocation(45 + ELEMENT_WIDTH, 15);
        noQuestionsTF.setSize(ELEMENT_WIDTH, 30);
        // minChapter
        minChapterL.setLocation(30, 65);
        minChapterL.setSize(ELEMENT_WIDTH, 30);
        minChapterTF.setLocation(45 + ELEMENT_WIDTH, 65);
        minChapterTF.setSize(ELEMENT_WIDTH, 30);
        // maxChapter
        maxChapterL.setLocation(30, 105);
        maxChapterL.setSize(ELEMENT_WIDTH, 30);
        maxChapterTF.setLocation(45 + ELEMENT_WIDTH, 105);
        maxChapterTF.setSize(ELEMENT_WIDTH, 30);
        // Buttons & Output
        submitB.setLocation(30, 145);
        submitB.setSize(ELEMENT_WIDTH, 35);
        output.setLocation(30, 185);
        output.setSize(ELEMENT_WIDTH * 2 + 15, 400);

        pane.add(noQuestionsL);
        pane.add(noQuestionsTF);
        pane.add(minChapterL);
        pane.add(minChapterTF);
        pane.add(maxChapterL);
        pane.add(maxChapterTF);
        pane.add(submitB);
        pane.add(output);

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    public static void main(String[] args) {
        ExamWindow ew = new ExamWindow(new QuestionPool());
    }

    private class SubmitButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                noQuestions = Integer.parseInt(noQuestionsTF.getText());
                minChapter = Integer.parseInt(minChapterTF.getText());
                maxChapter = Integer.parseInt(maxChapterTF.getText());
                exam = questionPool.makeExam(minChapter, maxChapter, noQuestions);
                if(exam.getQuestions().size() == noQuestions) {

                }
            } catch(NumberFormatException e) {
                output.setText("Invalid input!");
            }

        }
    }


}
