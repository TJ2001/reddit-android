package com.example.guest.ourreddit.models;

import org.parceler.Parcel;

@Parcel
public class Category {
    private String name;
    private String imageUrl;
    private String pushId;
    private String userId;

    public Category(){}
    public Category(String name, String imageUrl, String userId) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getUserId() {
        return userId;
    }

}
