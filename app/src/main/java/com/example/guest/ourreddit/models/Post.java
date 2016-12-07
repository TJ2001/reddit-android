package com.example.guest.ourreddit.models;

import org.parceler.Parcel;

@Parcel
public class Post {
    private String title;
    private String body;
    private String categoryId;
    private String userId;
    private String pushId;
    private String imageUrl;

    public Post(){};
    public Post(String title, String body, String categoryId, String userId, String imageUrl) {
        this.title = title;
        this.body = body;
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

    public String getUserId() { return userId; }

    public String getCategoryId() { return categoryId; }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
