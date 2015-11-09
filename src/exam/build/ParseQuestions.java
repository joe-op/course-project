package exam.build;

import exam.question.*;

import java.security.InvalidParameterException;

/**
 * Created by Joe on 11/7/2015.
 */
public class ParseQuestions {

    // Get question type from string
    public static String getType(String str) {
        if(str.indexOf('@') != -1) {
            return str.substring(0, str.indexOf('@'));
        } else {
            return "";
        }
    }

    // Split string with values separated by "@"
    private static String[] splitString(String str) { return str.split("@"); }

    // create MultipleChoiceQuestion from string
    public static MultipleChoiceQuestion parseMultiple(String str) {
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
            throw new InvalidParameterException(e.getMessage());
        }
        return new MultipleChoiceQuestion(points, chapter, prompt, choices, answer);
    }

    // create ShortAnswerQuestion from string
    public static ShortAnswerQuestion parseShort(String str) {
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
            throw new InvalidParameterException(e.getMessage());
        }
        return new ShortAnswerQuestion(points, chapter, prompt, answers);
    }

    // create LongAnswerQuestion from string
    //TODO question pool may contain a multiple line answer
    public static LongAnswerQuestion parseLong(String str) {
        String[] strs = splitString(str);
        int points, chapter;
        String prompt, answer;
        try {
            points = Integer.parseInt(strs[1]);
            chapter = Integer.parseInt(strs[2]);
            prompt = strs[3];
            answer = strs[4];
        } catch(Exception e) {
            throw new InvalidParameterException(e.getMessage());
        }
        return new LongAnswerQuestion(points, chapter, prompt, answer);
    }





}
