package com.example.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.pojo.PostModel;
import com.example.retrofit_mvvm.R;
import com.example.viewmodels.PostViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
PostViewModel postViewModel;
RecyclerView recyclerView;
PostsAdapter postsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        postsAdapter = new PostsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postsAdapter);

//movieListViewModel = new ViewModelProvider(MainActivity.this).get(MovieListViewModel.class);
        postViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                                    .get(PostViewModel.class);
        postViewModel.getInstance().getPosts().observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                postsAdapter.setList(postModels);
                Log.v("Tag","ss"+postModels);
            }
        });
    }
}