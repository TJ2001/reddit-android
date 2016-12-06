package com.example.guest.ourreddit.models;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private String title;
    private String body;
    private String userName;
    private List <Comment> comments;

    public Post(String title, String body, String userName, List<Comment> comments) {
        this.title = title;
        this.body = body;
        this.userName = userName;
        this.comments = comments;
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

    public List<Comment> getComments() {
        return comments;
    }
}
