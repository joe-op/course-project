package exam.build;

import exam.question.*;

/**
 * Created by Joe on 11/7/2015.
 */
public class ParseQuestions {

    // Get question type from string
    public static String getType(String str) {
        return str.substring(0, str.indexOf('@'));
    }

    // Split string with values separated by "@"
    private static String[] splitString(String str) { return str.split("@"); }

    // create MultipleChoiceQuestion from string
    public static MultipleChoiceQuestion parseMultiple(String str) {
        String[] strs = splitString(str);
        int points = Integer.parseInt(strs[1]);
        int chapter = Integer.parseInt(strs[2]);
        String prompt = strs[3];
        int numChoices = Integer.parseInt(strs[4]);
        char answer = strs[5].charAt(0);
        String[] choices = new String[numChoices];
        for(int i=0; i<numChoices; i++)
            choices[i] = strs[i+6];
        return new MultipleChoiceQuestion(points, chapter, prompt, choices, answer);
    }

    // create ShortAnswerQuestion from string
    public static ShortAnswerQuestion parseShort(String str) {
        String[] strs = splitString(str);
        int points = Integer.parseInt(strs[1]);
        int chapter = Integer.parseInt(strs[2]);
        String prompt = strs[3];
        String[] answers = new String[strs.length - 4];
        for(int i=0; i<answers.length; i++) {
            answers[i] = strs[i+4];
        }
        return new ShortAnswerQuestion(points, chapter, prompt, answers);
    }

    // create LongAnswerQuestion from string
    public static LongAnswerQuestion parseLong(String str) {
        String[] strs = splitString(str);
        int points = Integer.parseInt(strs[1]);
        int chapter = Integer.parseInt(strs[2]);
        String prompt = strs[3];
        String answer = strs[4];
        return new LongAnswerQuestion(points, chapter, prompt, answer);
    }





}
