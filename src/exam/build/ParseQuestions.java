package exam.build;

import exam.question.*;

import java.security.InvalidParameterException;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Joe on 11/7/2015.
 */
public class ParseQuestions {

    /*
    public static Question parse(String str) {
        Question question;
        String[] questionStrs;
        try {
            questionStrs = str.split("@");
        } catch (PatternSyntaxException ex) {
            throw new InvalidParameterException("Not a valid string");
        }    //
        if(questionStrs[0].equals("multiple")) {
            question = parseMultiple(questionStrs);
        } else if(questionStrs[0].equals("short")) {
            question = parseShort(questionStrs);
        } else if(questionStrs[0].equals("long")) {
            question = parseLong(questionStrs);
        } else {
            throw new InvalidParameterException("Not a valid question type");
        }
        return question;
    }
    */
    public static String getType(String str) {
        return str.substring(0, str.indexOf('@'));
    }

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
