package com.example.pojo;

import com.google.gson.annotations.SerializedName;

public class Comment {


    @SerializedName("body")
    private String body;
    @SerializedName("email")
    private String email;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private int id;
    @SerializedName("postId")
    private int postid;

    public String getBody() {
        return body;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getPostid() {
        return postid;
    }
}

