package com.example.aman.firstproject;

public class Question {
    private String question;
    private String doubtkey;

    public Question(String question,String doubtkey) {
        this.question = question;this.doubtkey=doubtkey;
    }

    public Question() {
    }

    public String getDoubtkey() {
        return doubtkey;
    }

    public void setDoubtkey(String doubtkey) {
        this.doubtkey = doubtkey;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
