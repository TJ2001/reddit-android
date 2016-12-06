package com.example.guest.ourreddit.models;


public class Comment {
    private String title;
    private String body;
    private String userName;

    public Comment(String title, String body, String userName) {
        this.title = title;
        this.body = body;
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getUserName() {
        return userName;
    }
}
