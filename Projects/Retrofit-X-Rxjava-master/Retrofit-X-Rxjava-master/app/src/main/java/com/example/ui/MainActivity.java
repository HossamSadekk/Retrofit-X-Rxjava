package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.adapters.PostsAdapter;
import com.example.pojo.PostModel;
import com.example.retrofit_mvvm.R;
import com.example.viewmodels.PostViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
PostViewModel postViewModel;
RecyclerView recyclerView;
PostsAdapter postsAdapter;
ProgressBar progressBar;
Button search;
EditText commentid_ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doInitialize();

        commentid_ed = findViewById(R.id.commentid);
        search = findViewById(R.id.search_button);
        
        if (!commentid_ed.getText().toString().isEmpty()) {
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!commentid_ed.toString().isEmpty()) {
                        Intent intent = new Intent(getBaseContext(), CommentActivity.class);
                        intent.putExtra("id", commentid_ed.getText().toString());
                        startActivity(intent);
                    }
                }
            });
        }


    }

    private void doInitialize() {
        recyclerView = findViewById(R.id.recycler);
        postsAdapter = new PostsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postsAdapter);

        //movieListViewModel = new ViewModelProvider(MainActivity.this).get(MovieListViewModel.class);
        postViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(PostViewModel.class);
        getPosts();
    }

    private void getPosts(){
        progressBar = findViewById(R.id.progress_circular);
        progressBar.setVisibility(View.VISIBLE);
        postViewModel.getInstance().getPosts().observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                progressBar.setVisibility(View.INVISIBLE);
                postsAdapter.setList(postModels);
            }
        });
    }


}