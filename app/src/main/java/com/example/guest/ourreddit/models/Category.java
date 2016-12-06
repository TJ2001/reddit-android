package com.example.guest.ourreddit.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Category {
    private String name;
    private String imageUrl;
    private String pushId;
    private String userId;
    private List<Post> posts = new ArrayList<>();

    public Category(){}
    public Category(String name, String imageUrl, String userId, ArrayList<Post> posts) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.userId = userId;
        this.posts = null;
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

    public List<Post> getPosts() {
        return posts;
    }
}
