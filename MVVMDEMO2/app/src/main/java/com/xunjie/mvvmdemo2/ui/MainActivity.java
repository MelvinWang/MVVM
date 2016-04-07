package com.xunjie.mvvmdemo2.ui;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xunjie.mvvmdemo2.R;
import com.xunjie.mvvmdemo2.databinding.ActivityMainBinding;
import com.xunjie.mvvmdemo2.modelview.MainViewModel;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    ActivityMainBinding binding;
    private SwipeRefreshLayout mSwipeLayout;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this, R.layout.activity_main);
        mSwipeLayout=binding.refresh;
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mainViewModel = new MainViewModel(MainActivity.this);
        mainViewModel.requestData(mSwipeLayout);
        binding.setViewModel(mainViewModel);
    }

    @Override
    public void onRefresh() {
        mainViewModel.requestData(mSwipeLayout);
//        mSwipeLayout.setRefreshing(false);
    }
}
