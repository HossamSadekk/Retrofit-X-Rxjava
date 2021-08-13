package com.example.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pojo.PostModel;
import com.example.repositery.PostRepositery;

import java.util.List;

public class PostViewModel extends ViewModel {
    private PostViewModel instance;
    private PostRepositery postRepositery;

    public PostViewModel getInstance() {
        if (instance == null) {
            instance = new PostViewModel();
        }
        return instance;
    }

    public PostViewModel() {
        postRepositery = new PostRepositery();
    }

    public LiveData<List<PostModel>> getPosts() {
        return postRepositery.getPosts();
    }


}
