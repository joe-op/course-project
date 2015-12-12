package exam.ui;


import exam.Exam;
import exam.question.Question;
import exam.question.QuestionPool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * A window that takes a question pool file and lets
 * the user create an exam
 */
public class ExamWindow extends JFrame {

    // Variables for the window
    private static final int WIDTH = 800;
    private static final int HEIGHT = 700;
    private static final int ELEMENT_WIDTH = 275;
    private static final int INDENT = 30;
    private static final int SPACE = 15;
    private static final int COL_2_INDENT = SPACE + ELEMENT_WIDTH;
    private static final int OUTPUT_WIDTH = INDENT * 4 + ELEMENT_WIDTH * 2 + SPACE;

    private JLabel noQuestionsL;
    private JTextField noQuestionsTF;

    private JLabel minChapterL;
    private JTextField minChapterTF;

    private JLabel maxChapterL;
    private JTextField maxChapterTF;

    private JButton submitB;
    private SubmitButtonHandler sbHandler;
    private JButton toggleExamKeyB;
    private static final String CHECK_KEY_LABEL = "Check Key";
    private static final String CHECK_EXAM_LABEL = "Check Exam";

    private JTextArea output;
    private JScrollPane outputScroll;

    // Variables for the exam
    private QuestionPool questionPool;
    private Exam exam;
    private final String EXAM_FILE = "exam.txt";
    private final String KEY_FILE = "key.txt";
    private int noQuestions, minChapter, maxChapter;

    public ExamWindow(QuestionPool questionPoolP) {

        questionPool = questionPoolP;
        // TODO remove this
        try {
            questionPool.load("question-pool.txt");
        } catch(FileNotFoundException e)
        {}

        setTitle("Exam");
        setSize(WIDTH, HEIGHT);

        noQuestionsL = new JLabel("Enter the number of questions:");
        noQuestionsTF = new JTextField(10);

        minChapterL = new JLabel("Enter the minimum chapter to use:");
        minChapterTF = new JTextField(10);

        maxChapterL = new JLabel("Enter the maximum chapter to use:");
        maxChapterTF = new JTextField(10);

        // submit action
        submitB = new JButton("Submit");
        sbHandler = new SubmitButtonHandler();
        submitB.addActionListener(sbHandler);
        noQuestionsTF.addActionListener(sbHandler);
        minChapterTF.addActionListener(sbHandler);
        maxChapterTF.addActionListener(sbHandler);

        // toggle exam/key action
        toggleExamKeyB = new JButton("Check Key");
        ToggleKeyExamButtonHandler tekbHandler = new ToggleKeyExamButtonHandler();
        toggleExamKeyB.addActionListener(tekbHandler);
        toggleExamKeyB.setEnabled(false);

        output = new JTextArea(30, 30);
        // output.setEnabled(false); // can't select text
        outputScroll = new JScrollPane(output);

        Container pane = getContentPane();
        pane.setLayout(null);

        // set locations and sizes for the elements
        //noQuestions
        noQuestionsL.setLocation(INDENT, 15);
        noQuestionsL.setSize(ELEMENT_WIDTH, 30);
        noQuestionsTF.setLocation(COL_2_INDENT, 15);
        noQuestionsTF.setSize(ELEMENT_WIDTH-30, 30);
        // minChapter
        minChapterL.setLocation(INDENT, 65);
        minChapterL.setSize(ELEMENT_WIDTH, 30);
        minChapterTF.setLocation(COL_2_INDENT, 65);
        minChapterTF.setSize(ELEMENT_WIDTH-30, 30);
        // maxChapter
        maxChapterL.setLocation(INDENT, 105);
        maxChapterL.setSize(ELEMENT_WIDTH, 30);
        maxChapterTF.setLocation(COL_2_INDENT, 105);
        maxChapterTF.setSize(ELEMENT_WIDTH-30, 30);
        // Buttons & Output
        submitB.setLocation(INDENT, 145);
        submitB.setSize(ELEMENT_WIDTH - 30, 35);
        outputScroll.setLocation(INDENT, 185);
        outputScroll.setSize(OUTPUT_WIDTH, 400);
        toggleExamKeyB.setLocation(INDENT, 600);
        toggleExamKeyB.setSize(ELEMENT_WIDTH - 30, 35);

        pane.add(noQuestionsL);
        pane.add(noQuestionsTF);
        pane.add(minChapterL);
        pane.add(minChapterTF);
        pane.add(maxChapterL);
        pane.add(maxChapterTF);
        pane.add(submitB);
        pane.add(toggleExamKeyB);
        pane.add(outputScroll);

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
                    exam.write(EXAM_FILE, KEY_FILE);
                    displayExam();
                    toggleExamKeyB.setEnabled(true);
                } else {
                    output.setText(
                            String.format("Not enough questions!%nNumber of questions in that range: %d.",
                                    exam.getQuestions().size()));
                    toggleExamKeyB.setEnabled(false);
                }
            } catch(NumberFormatException e) {
                output.setText("Invalid input!");
                toggleExamKeyB.setEnabled(false);
            } catch(Exception e) {
                toggleExamKeyB.setEnabled(false);
                output.setText(e.getMessage());
            }

        }
    }

    private class ToggleKeyExamButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if(toggleExamKeyB.getText().equals(CHECK_KEY_LABEL)) {
                displayKey();
            } else if(toggleExamKeyB.getText().equals(CHECK_EXAM_LABEL)) {
                displayExam();
            }
        }
    }

    private void displayExam() {
        int noQuestions = exam.getQuestions().size();
        int points = exam.getPoints();
        output.setText(
                String.format(
                        "Built exam with %d questions and %d points:%n%n%s",
                        noQuestions, points, exam.toString()));
        output.setCaretPosition(0);
        toggleExamKeyB.setText("Check Key");
    }
    private void displayKey() {
        output.setText(exam.keyToString());
        output.setCaretPosition(0);
        toggleExamKeyB.setText("Check Exam");
    }


}
