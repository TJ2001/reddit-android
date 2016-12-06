package com.example.guest.ourreddit.models;

public class Post {
    private String title;
    private String body;
    private String userName;
    private String categoryId;
    private String userId;
    private String pushId;

    public Post(String title, String body, String userName, String categoryId, String userId) {
        this.title = title;
        this.body = body;
        this.userName = userName;
        this.categoryId = categoryId;
        this.userId = userId;
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

    public String getUserId() { return userId; }

    public String getCategoryId() { return categoryId; }
}
