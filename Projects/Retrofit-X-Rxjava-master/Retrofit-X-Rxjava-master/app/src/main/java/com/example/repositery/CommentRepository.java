package com.example.repositery;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.network.PostInterface;
import com.example.network.PostsClient;
import com.example.pojo.Comment;
import com.example.pojo.PostModel;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CommentRepository {
    private PostInterface postInterface;

    public CommentRepository() {
        postInterface = PostsClient.getInstance().create(PostInterface.class);
    }

    public LiveData<List<Comment>> getComments(int ID)
    {
        MutableLiveData<List<Comment>> data = new MutableLiveData<>();
        Single<List<Comment>> observable = postInterface.getComments(ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        SingleObserver singleObserver = new SingleObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Object o) {
                data.setValue((List<Comment>) o);
                Log.d("PostRepositery",""+((List<Comment>) o).get(2).getName());
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
