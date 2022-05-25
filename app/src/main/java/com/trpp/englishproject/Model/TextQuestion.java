package com.trpp.englishproject.Model;


public class TextQuestion {
    String q;
    String ans;

    public TextQuestion() {
    }

    
    public TextQuestion(String ans, String q) {
        this.q = q;
        this.ans = ans;
    }

    
    public String getAns() {
        return ans;
    }

    
    public void setAns(String ans) {
        this.ans = ans;
    }

    
    public String getQ() {
        return q;
    }

    
    public void setQ(String q) {
        this.q = q;
    }
}
