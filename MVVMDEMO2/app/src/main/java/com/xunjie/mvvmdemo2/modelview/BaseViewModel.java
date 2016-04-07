package com.xunjie.mvvmdemo2.modelview;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.LinearLayoutManager;


import com.xunjie.mvvmdemo2.BR;
import com.xunjie.mvvmdemo2.databinding.DBRecyclerView;
import com.xunjie.mvvmdemo2.model.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/29.
 */
public class BaseViewModel extends BaseObservable {
    public LinearLayoutManager linearLayoutManager;
    @Bindable
    public List<Repository> data = new ArrayList<>();
    @Bindable
    public int footerStatus = DBRecyclerView.SHOW_FOOTER;
    @Bindable
    public boolean loading = false;
    public BaseViewModel(Context context) {
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    }
    public int getFooterStatus() {
        return footerStatus;
    }
    public List<Repository> getData() {
        return data;
    }

    public boolean getLoading() {
        return loading;
    }

    public LinearLayoutManager getLinearLayoutManager() {
        return linearLayoutManager;
    }


    public void onRequestFinished() {
        loading = false;
        footerStatus = DBRecyclerView.HIDE_FOOTER;
        notifyPropertyChanged(BR.loading);
        notifyPropertyChanged(BR.footerStatus);
    }

    public void onRequestSuccess(List<Repository> list) {
        data.addAll(list);
        notifyPropertyChanged(BR.data);
    }

    public void requestData() {
        loading = true;
        footerStatus = DBRecyclerView.SHOW_FOOTER;
        notifyPropertyChanged(BR.loading);
        notifyPropertyChanged(BR.footerStatus);
    }


}
