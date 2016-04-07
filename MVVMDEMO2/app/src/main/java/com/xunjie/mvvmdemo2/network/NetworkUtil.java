package com.xunjie.mvvmdemo2.network;


import com.squareup.okhttp.OkHttpClient;
import com.xunjie.mvvmdemo2.model.Repository;

import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by melvin on 2015/12/19.
 */
public class NetworkUtil {

    private static Retrofit sRetrofit;
    //定义接口
    public interface FromNetwork {
        @GET("users/{username}/repos")
        Observable<List<Repository>> publicRepositories(@Path("username") String username);
    }

    public static Retrofit getRetrofit() {
        if(sRetrofit == null){
            sRetrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return sRetrofit;


    /*    Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(API_URL)
                .build();
        return retrofit;*/

    }
}



