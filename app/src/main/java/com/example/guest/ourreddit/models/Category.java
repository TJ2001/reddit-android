package com.example.guest.ourreddit.models;

import java.util.List;

public class Category {
    private String name;
    private String imageUrl;
    private List<Post> posts;

    public Category(String name, String imageUrl, List<Post> posts) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.posts = posts;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
