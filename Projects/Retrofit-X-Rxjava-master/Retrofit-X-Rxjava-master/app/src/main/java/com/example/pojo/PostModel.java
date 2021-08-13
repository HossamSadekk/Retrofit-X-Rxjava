package com.example.pojo;

public class PostModel {
    private int userId;
    private int id;
    private String title;
    private String body;

    public PostModel(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }


}
