package com.xunjie.mvvmdemo2.modelview;

import android.content.Context;
import android.databinding.BaseObservable;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.liuguangqiang.support.widgets.recyclerview.OnPageListener;
import com.xunjie.mvvmdemo2.R;
import com.xunjie.mvvmdemo2.adapter.MyAdapter;
import com.xunjie.mvvmdemo2.model.Repository;
import com.xunjie.mvvmdemo2.network.GithubService;
import com.xunjie.mvvmdemo2.view.RequestView;

import java.util.ArrayList;
import java.util.List;

import retrofit.HttpException;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/4/6.
 */
public class MainViewModel extends BaseViewModel implements RequestView<Repository> {

    private MyAdapter adapter;
    private Context context;
    private Repository repository;

    public MainViewModel(Context context) {
        super(context);
        this.context = context;
        repository = new Repository(this);
        adapter = new MyAdapter(context, getData());
//        requestData();
    }


    //    @Override
    public void requestData(final SwipeRefreshLayout mSwipeLayout) {
        super.requestData();
        GithubService githubService = GithubService.Factory.create();
        githubService.publicRepositories("gha")
                .subscribeOn(Schedulers.io())
                .doOnNext(new Action1<List<Repository>>() {
                    @Override
                    public void call(List<Repository> Daily) {
                        SystemClock.sleep(1000);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repository>>() {
                    @Override
                    public void onCompleted() {
                        onRequestFinished();
                        if (mSwipeLayout != null) {
                            mSwipeLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        if (!isHttp404(error)) {
                            Toast.makeText(context,"服务器有误",Toast.LENGTH_SHORT).show();
                        }
                        onRequestFinished();
                        if (mSwipeLayout != null) {
                            mSwipeLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onNext(List<Repository> repositories) {
                        onRequestSuccess(repositories);
                    }
                });
    }


    public MyAdapter getAdapter() {
        return adapter;
    }

    private static boolean isHttp404(Throwable error) {
        return error instanceof HttpException && ((HttpException) error).code() == 404;
    }


    public OnPageListener getOnPageListener() {
        return new OnPageListener() {
            @Override
            public void onPage() {
                requestData(null);
            }
        };
    }
}
