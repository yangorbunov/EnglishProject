package com.trpp.englishproject.Model;


public class ImageQuestion {
    private String q;
    private String ans;

    public ImageQuestion() {
    }

    public ImageQuestion(String pictureUri, String cAnswer) {
        this.q = pictureUri;
        this.ans = cAnswer;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
