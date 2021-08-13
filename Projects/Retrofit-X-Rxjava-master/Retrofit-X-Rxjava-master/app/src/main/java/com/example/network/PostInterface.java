package com.example.network;

import com.example.pojo.Comment;
import com.example.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostInterface {

    @GET("posts")
    Single<List<PostModel>> getPosts();

    @GET("posts/{id}/comments")
    Single<List<Comment>> getComments(@Path("id") int commentID);

}
