package com.example.aman.firstproject;

public class QuestionFromMentor {
    private String question,userpassword,key;

    public QuestionFromMentor(String question, String userpassword,String key) {
        this.question = question;this.key=key;
        this.userpassword = userpassword;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public QuestionFromMentor() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


}
