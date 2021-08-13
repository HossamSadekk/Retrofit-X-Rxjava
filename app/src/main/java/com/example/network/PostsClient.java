package com.example.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import com.example.pojo.PostModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostsClient {
    private static final String TAG = "PostsClient";
    MutableLiveData<List<PostModel>> posts;

    private static PostsClient instance;

    // getInstance() method
    public static PostsClient getInstance()
    {
        if(instance==null)
        {
            instance = new PostsClient();
        }
        return instance;
    }


    private PostsClient()
    {
        posts = new MutableLiveData<>();
        MakeApiCall();
    }

    public MutableLiveData<List<PostModel>> getPosts()
    {
        return posts;
    }

    public void MakeApiCall()
    {
        PostInterface postInterface = Service.getPostApi();

        Single<List<PostModel>> observable = postInterface.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(o->posts.setValue(o),e->Log.d(TAG,"getPost"+e));
    }


}
