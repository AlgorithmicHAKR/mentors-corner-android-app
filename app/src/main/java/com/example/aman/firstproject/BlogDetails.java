package com.example.aman.firstproject;

public class BlogDetails {
    public String topic, description;
    private String image;

    public BlogDetails(String topic, String description, String image) {
        this.topic = topic;
        this.description = description;
        this.image= image;
    }

    public BlogDetails(){

    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
