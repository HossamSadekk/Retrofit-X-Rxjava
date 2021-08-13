package com.example.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.pojo.Comment;
import com.example.repositery.CommentRepository;
import com.example.repositery.PostRepositery;

import java.util.List;

public class CommentViewModel extends ViewModel {

    private CommentRepository commentRepository;

    public CommentViewModel() {
        commentRepository = new CommentRepository();
    }

    public LiveData<List<Comment>> getComments(int ID)
    {
        return commentRepository.getComments(ID);
    }
}
