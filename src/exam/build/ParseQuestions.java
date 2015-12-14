package exam.build;

import exam.question.*;

import java.security.InvalidParameterException;

/**
 * Methods for handling a string containing a single question
 */
public class ParseQuestions {

    private static final char DELIMITER = '@';

    // Get question type from string
    public static String getType(String str) {
        if(str.indexOf(DELIMITER) != -1) {
            return str.substring(0, str.indexOf(DELIMITER));
        } else {
            return "";
        }
    }

    // Split string with values separated by DELIMITER
    private static String[] splitString(String str) { return str.split(String.valueOf(DELIMITER)); }

    // create MultipleChoiceQuestion from string
    public static MultipleChoiceQuestion parseMultiple(String str) throws InvalidQuestionException {
        String[] strs = splitString(str);
        int points, chapter;
        String prompt;
        char answer;
        String[] choices;
        try {
            points = Integer.parseInt(strs[1]);
            chapter = Integer.parseInt(strs[2]);
            prompt = strs[3];
            int numChoices = Integer.parseInt(strs[4]);
            answer = strs[5].charAt(0);
            choices = new String[numChoices];
            for (int i = 0; i < numChoices; i++)
                choices[i] = strs[i + 6];
        } catch(Exception e) {
            throw new InvalidQuestionException(e.getMessage());
        }
        return new MultipleChoiceQuestion(points, chapter, prompt, choices, answer);
    }

    // create ShortAnswerQuestion from string
    public static ShortAnswerQuestion parseShort(String str) throws InvalidQuestionException {
        String[] strs = splitString(str);
        int points, chapter;
        String prompt;
        String[] answers;
        try {
            points = Integer.parseInt(strs[1]);
            chapter = Integer.parseInt(strs[2]);
            prompt = strs[3];
            answers = new String[strs.length - 4];
            for (int i = 0; i < answers.length; i++) {
                answers[i] = strs[i + 4];
            }
        } catch(Exception e) {
            throw new InvalidQuestionException(e.getMessage());
        }
        return new ShortAnswerQuestion(points, chapter, prompt, answers);
    }

    // create LongAnswerQuestion from string
    public static LongAnswerQuestion parseLong(String str) throws InvalidQuestionException {
        String[] strs = splitString(str);
        int points, chapter;
        String prompt, answer;
        try {
            points = Integer.parseInt(strs[1]);
            chapter = Integer.parseInt(strs[2]);
            prompt = strs[3];
            answer = strs[4];
            // Add any additional lines to the answer
            for (int i=5; i<strs.length; i++)
                answer += "\n" + strs[i];
        } catch(Exception e) {
            throw new InvalidQuestionException(e.getMessage());
        }
        return new LongAnswerQuestion(points, chapter, prompt, answer);
    }





}
