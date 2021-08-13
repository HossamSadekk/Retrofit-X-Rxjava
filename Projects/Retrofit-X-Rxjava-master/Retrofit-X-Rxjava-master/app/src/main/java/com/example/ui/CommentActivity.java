package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.adapters.CommentAdapter;
import com.example.adapters.PostsAdapter;
import com.example.pojo.Comment;
import com.example.retrofit_mvvm.R;
import com.example.viewmodels.CommentViewModel;
import com.example.viewmodels.PostViewModel;

import java.util.List;

public class CommentActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private CommentAdapter commentAdapter;
private CommentViewModel commentViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
    doInitialize();
    }

    private void doInitialize() {
        recyclerView = findViewById(R.id.recyclercomment);
        commentAdapter = new CommentAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commentAdapter);
        commentViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(CommentViewModel.class);
        commentViewModel.getComments(Integer.parseInt(getIntent().getStringExtra("id"))).observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                commentAdapter.setList(comments);
                Log.d("list",""+comments.get(2).getName());
            }
        });
    }
}