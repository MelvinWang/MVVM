package com.xunjie.mvvmdemo2.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;

import com.xunjie.mvvmdemo2.BR;
import com.xunjie.mvvmdemo2.R;
import com.xunjie.mvvmdemo2.modelview.MainViewModel;
import com.xunjie.mvvmdemo2.network.GithubService;
import com.xunjie.mvvmdemo2.network.NetworkUtil;
import com.xunjie.mvvmdemo2.view.RequestView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Retrofit;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class Repository extends BaseObservable {
    public long id;

    public String name;

    public String description;

    public int forks;

    public int watchers;

    public int stars;

    public String language;

    public String homepage;
    public boolean fork;

    private RequestView requestView;

    public Repository(RequestView requestView) {
        this.requestView = requestView;

    }

    @Bindable
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
        notifyPropertyChanged(BR.forks);
    }

    @Bindable
    public int getWatchers() {
        return watchers;
    }

    public void setWatchers(int watchers) {
        this.watchers = watchers;
        notifyPropertyChanged(BR.watchers);
    }

    @Bindable
    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
        notifyPropertyChanged(BR.stars);
    }

    @Bindable
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
        notifyPropertyChanged(BR.language);
    }

    @Bindable
    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
        notifyPropertyChanged(BR.homepage);
    }

    @Bindable
    public boolean getFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
        notifyPropertyChanged(BR.fork);
    }

    public void getDaily() {
        GithubService  githubService = GithubService.Factory.create();
        githubService.publicRepositories("h")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repository>>() {
                    @Override
                    public void onCompleted() {
                        requestView.onRequestFinished();
                    }

                    @Override
                    public void onError(Throwable error) {

                    }

                    @Override
                    public void onNext(List<Repository> repositories) {
                        requestView.onRequestSuccess(repositories);
                    }
                });
    }

    @Override
    public String toString() {
        return "Repository{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", forks=" + forks +
                ", watchers=" + watchers +
                ", stars=" + stars +
                ", language='" + language + '\'' +
                ", homepage='" + homepage + '\'' +
                ", fork=" + fork +
                ", requestView=" + requestView +
                '}';
    }
}
