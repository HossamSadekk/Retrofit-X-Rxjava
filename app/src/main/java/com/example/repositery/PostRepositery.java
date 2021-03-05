package com.example.repositery;

import androidx.lifecycle.MutableLiveData;

import com.example.pojo.PostModel;
import com.example.network.PostsClient;

import java.util.List;

public class PostRepositery {
    private static PostRepositery instance;
    private PostsClient postsClient;

    public static PostRepositery getInstance()
    {
        if(instance==null)
        {
            instance = new PostRepositery();
        }
        return instance;
    }

    public PostRepositery() {
        postsClient = PostsClient.getInstance();
    }



    public MutableLiveData<List<PostModel>> getPosts()
    {
        return postsClient.getPosts();
    }
}
