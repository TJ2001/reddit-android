package com.example.guest.ourreddit.models;

import org.parceler.Parcel;

@Parcel
public class Post {
    private String title;
    private String body;
    private String userName;
    private String categoryId;
    private String userId;
    private String pushId;
    private String imageUrl;

    public Post(){};
    public Post(String title, String body, String userName, String categoryId, String userId, String imageUrl) {
        this.title = title;
        this.body = body;
        this.userName = userName;
        this.categoryId = categoryId;
        this.userId = userId;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPushId() {
        return pushId;
    }
}
