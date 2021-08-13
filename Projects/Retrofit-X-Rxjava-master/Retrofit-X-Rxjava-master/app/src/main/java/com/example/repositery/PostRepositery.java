package com.example.repositery;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.network.PostInterface;
import com.example.pojo.PostModel;
import com.example.network.PostsClient;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PostRepositery {
    private static final String TAG = "PostRepositery";
    private PostInterface postInterface;

    public PostRepositery() {
        postInterface = PostsClient.getInstance().create(PostInterface.class);
    }



    public LiveData<List<PostModel>> getPosts()
    {
        MutableLiveData<List<PostModel>> data = new MutableLiveData<>();
        Single<List<PostModel>> observable = postInterface.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
         SingleObserver singleObserver = new SingleObserver() {
             @Override
             public void onSubscribe(Disposable d) {

             }

             @Override
             public void onSuccess(Object o) {
                data.setValue((List<PostModel>) o);
             }

             @Override
             public void onError(Throwable e) {
                Log.d("PostRepositery","error-->"+e);
             }
         };
         observable.subscribe(singleObserver);
                return data;
    }
}
