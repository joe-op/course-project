package exam.ui;

import exam.build.ParseQuestionPoolFile;
import exam.question.QuestionPool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Provides a window to enter the location of a question pool file
 * and verify that the file is valid
 */
public class QuestionPoolWindow extends JFrame {

    private static final int WIDTH = 480;
    private static final int HEIGHT = 250;
    private static final int ELEMENT_WIDTH = 300;

    private JLabel fileL;
    private JTextField fileTF;

    private JButton checkB;

    private JTextArea log;

    private QuestionPool questionPool;

    public QuestionPoolWindow() {

        questionPool = new QuestionPool();

        setTitle("Question Pool");
        setSize(WIDTH, HEIGHT);

        fileL = new JLabel("Location of Question Pool File:");
        fileTF = new JTextField(10);

        checkB = new JButton("Check File");

        log = new JTextArea(6, 30);

        Container pane = getContentPane();
        pane.setLayout(null);

        // set locations and sizes for the elements
        fileL.setLocation(90, 15);
        fileL.setSize(ELEMENT_WIDTH, 30);
        fileTF.setLocation(90, 65);
        fileTF.setSize(ELEMENT_WIDTH, 30);
        checkB.setLocation(90, 105);
        checkB.setSize(300, 35);
        log.setLocation(90, 145);
        log.setSize(300, 30);

        // add listener to button
        CheckButtonHandler cbHandler = new CheckButtonHandler();
        checkB.addActionListener(cbHandler);

        // add elements to pane
        pane.add(fileL);
        pane.add(fileTF);
        pane.add(checkB);
        pane.add(log);


        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        QuestionPoolWindow qpw = new QuestionPoolWindow();
    }

    private class CheckButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            String filename = fileTF.getText();
            try {
                if (questionPool.load(filename)) {
                    setVisible(false);
                    ExamWindow examWindow = new ExamWindow(questionPool);
                } else {
                    log.setText("File not valid!");
                }
            } catch(FileNotFoundException e) {
                log.setText("File not found!");
            }
        }
    }

}
