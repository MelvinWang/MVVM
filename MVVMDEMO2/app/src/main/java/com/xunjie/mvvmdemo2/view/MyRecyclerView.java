package com.xunjie.mvvmdemo2.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.liuguangqiang.support.widgets.recyclerview.LinearRecyclerView;
import com.liuguangqiang.support.widgets.recyclerview.OnPageListener;
import com.liuguangqiang.support.widgets.recyclerview.OnTopPageListener;
import com.liuguangqiang.support.widgets.recyclerview.adapter.Bookends;
import com.xunjie.mvvmdemo2.adapter.MyAdapter;

/**
 * Created by Administrator on 2016/3/30.
 */
public class MyRecyclerView extends LinearRecyclerView implements LinearRecyclerView.OnScrollPositionListener {

    private View loadingFooter;

    private boolean hasAttachedFooter = false;
    private boolean pageEnable = true;
    private boolean isLoading = false;
    private Bookends bookends;
    private OnPageListener onPageListener;
    private OnTopPageListener onTopPageListener;

    public MyRecyclerView(Context context) {
        this(context, null);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setPageEnable(boolean pageEnable) {
        this.pageEnable = pageEnable;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean b) {
        isLoading = b;
    }

    private void init() {
        setOnScrollPositionListener(this);
    }

    public void setOnPageListener(OnPageListener pageListener) {
        this.onPageListener = pageListener;
    }

    public void setOnTopPageListener(OnTopPageListener onTopPageListener) {
        this.onTopPageListener = onTopPageListener;
    }

    @Override
    public void onScrollToTop() {
        if (pageEnable && onTopPageListener != null && !isLoading) {
            isLoading = true;
            onTopPageListener.onTopPage();
            System.out.println("哈哈哈onScrollToTop里面");
        }
        System.out.println("哈哈哈onScrollToTop");
    }

    @Override
    public void onScrollToBottom() {
        if (pageEnable && onPageListener != null && !isLoading) {
            isLoading = true;
            onPageListener.onPage();
            System.out.println("哈哈哈onScrollToBottom里面");
        }
        System.out.println("哈哈哈onScrollToBottom");
    }

    public void notifyDataSetChanged() {
        bookends.notifyDataSetChanged();
    }

    public void notifyItemInserted(int position) {
        bookends.notifyItemInserted(position);
    }

    public void notifyItemRemoved(int position) {
        bookends.notifyItemRemoved(position);
    }

    public void setAdapter(MyAdapter adapter) {
        bookends = new Bookends(adapter);
        super.setAdapter(bookends);
    }

    public void setAdapter(Bookends adapter) {
        super.setAdapter(adapter);
        this.bookends = adapter;
    }

    public Bookends<?> getBookendsAdapter() {
        return bookends;
    }

    /**
     * Add a header view.
     *
     * @param view
     */
    public void addHeader(View view) {
        bookends.addHeader(view);
    }

    /**
     * Add a footer view.
     *
     * @param view
     */
    public void addFooter(View view) {
        bookends.addFooter(view);
    }

    public void setPageFooter(View view) {
        loadingFooter = view;
    }

    public void setPageFooter(int resId) {
        loadingFooter = LayoutInflater.from(getContext()).inflate(resId, null);
    }

    private void attachPageFooter() {
        if (loadingFooter != null && !hasAttachedFooter) {
            hasAttachedFooter = true;
            bookends.addFooter(loadingFooter);
            bookends.setFooterVisibility(loadingFooter, false);
            System.out.println("哈哈哈attachPageFooter");
        }
        System.out.println("哈哈哈attachPageFooter外面");
    }

    /**
     * Remove the footer after load finished.
     */
    public void removePageFooter() {
        if (loadingFooter != null && hasAttachedFooter) {
            hasAttachedFooter = false;
            bookends.removeFooter(loadingFooter);
            System.out.println("哈哈哈 removePageFooter");
        }
        System.out.println("哈哈哈 removePageFooter外面");
    }

    public void showLoadingFooter() {
        if (!hasAttachedFooter) {
            attachPageFooter();
            System.out.println("哈哈哈 !hasAttachedFooter");
        }

        if (loadingFooter != null){
            bookends.setFooterVisibility(loadingFooter, true);
            System.out.println("哈哈哈 loadingFooter != null");
        }

    }


}