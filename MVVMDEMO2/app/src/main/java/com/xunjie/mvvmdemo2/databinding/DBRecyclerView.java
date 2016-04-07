package com.xunjie.mvvmdemo2.databinding;

import android.databinding.BindingAdapter;
import android.util.Log;

import com.xunjie.mvvmdemo2.R;
import com.xunjie.mvvmdemo2.adapter.MyAdapter;
import com.xunjie.mvvmdemo2.model.Repository;
import com.xunjie.mvvmdemo2.view.MyRecyclerView;

import java.util.List;


/**
 * Custom binding for RecyclerView.
 * <p/>
 * Created by Eric on 15/6/23.
 */
public class DBRecyclerView {

    public static final String  TAG="DBRecyclerView";
    public static int SHOW_FOOTER = 0;

    public static int HIDE_FOOTER = 1;

    @BindingAdapter({"bind:adapter"})
    public static void bindAdapter(MyRecyclerView recyclerView, MyAdapter adapter) {
        recyclerView.setAdapter(adapter);
        recyclerView.setPageFooter(R.layout.layout_loading_footer);
        System.out.println("哈哈哈bind:adapter" );
    }
    @BindingAdapter({"bind:data"})
    public static void bindData(MyRecyclerView recyclerView, List<Repository> repositories) {
        recyclerView.notifyDataSetChanged();
        recyclerView.setIsLoading(false);
        System.out.println("哈哈哈bind:data");
    }

    @BindingAdapter({"bind:isLoading"})
    public static void isLoading(MyRecyclerView recyclerView, boolean isLoading) {
        recyclerView.setIsLoading(isLoading);
        Log.i(TAG, "哈哈哈isLoading"+isLoading);
    }

    @BindingAdapter({"bind:footerStatus"})
    public static void footerStatus(MyRecyclerView recyclerView, int footerStatus) {
        if (footerStatus == SHOW_FOOTER) {
            recyclerView.setPageEnable(true);
            recyclerView.showLoadingFooter();
        } else {
            recyclerView.setPageEnable(true);
            recyclerView.removePageFooter();
        }
        Log.i(TAG, "哈哈哈footerStatus"+footerStatus);
    }



}
