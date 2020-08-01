package com.example.aman.firstproject;

public class usersmentorsinfo {
    private String name, password;

    public usersmentorsinfo(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public usersmentorsinfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
