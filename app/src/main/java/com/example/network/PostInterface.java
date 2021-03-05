package com.example.network;

import com.example.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostInterface {

    @GET("posts")
    Single<List<PostModel>> getPosts();

}
