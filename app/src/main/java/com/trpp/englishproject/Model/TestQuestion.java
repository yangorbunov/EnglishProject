package com.trpp.englishproject.Model;
import java.util.HashMap;

/**
 * @author Yan Gorbunov
 * @version 1.0
 * Model class of questions, which contains question, map of answers and correct answer.
 */
public class TestQuestion {
    private String q;
    private HashMap<String,String> ans;
    private String ca;

    public TestQuestion() {
    }

    public TestQuestion(String text, HashMap<String,String> wAnswers, String cAnswer) {
        this.q = text;
        this.ans = wAnswers;
        this.ca = cAnswer;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public HashMap<String,String> getAns() {
        return ans;
    }

    public void setAns(HashMap<String,String> ans) {
        this.ans = ans;
    }

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }
}
