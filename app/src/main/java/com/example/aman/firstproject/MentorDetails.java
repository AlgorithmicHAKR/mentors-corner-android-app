package com.example.aman.firstproject;

public class MentorDetails {
    private String name;
    private String category;
    private String yoe;
    private String currentlyworkingin;
    private String rating;
    private String id;
    private String key;
    private String image;
    private String password;
    public MentorDetails(String name, String category, String yoe, String currentlyworkingin,String id,String image,String password,String key) {
        this.name = name;
        this.category = category;this.key=key;
        this.yoe = yoe;this.id=id;
        this.currentlyworkingin = currentlyworkingin;
        this.image=image;this.password=password;
    }
    public MentorDetails(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getYoe() {
        return yoe;
    }

    public void setYoe(String yoe) {
        this.yoe = yoe;
    }

    public String getCurrentlyworkingin() {
        return currentlyworkingin;
    }

    public void setCurrentlyworkingin(String currentlyworkingin) {
        this.currentlyworkingin = currentlyworkingin;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
