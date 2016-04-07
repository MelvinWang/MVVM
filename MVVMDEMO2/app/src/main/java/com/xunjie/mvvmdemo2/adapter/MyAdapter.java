package com.xunjie.mvvmdemo2.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xunjie.mvvmdemo2.R;
import com.xunjie.mvvmdemo2.databinding.ItemRepoBinding;
import com.xunjie.mvvmdemo2.model.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/3/29.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.BindingHolder> {
    private List<Repository> repositories;
    private Context context;

    public MyAdapter(Context context, List<Repository> repositories) {
        this.repositories = repositories;
        this.context = context;
    }

    @Override
    public MyAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRepoBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_repo,
                parent,
                false);
        BindingHolder holder = new BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        Repository repository =repositories.get(position);
        holder.getBinding().setVariable(com.xunjie.mvvmdemo2.BR.repository, repository);
//        holder.getBinding().setRepository(repository);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }


    /**
     * Holder
     */
    public class BindingHolder extends RecyclerView.ViewHolder {
        private ItemRepoBinding binding;

        public BindingHolder(View v) {
            super(v);
        }

        public ItemRepoBinding getBinding() {
            return binding;
        }

        public void setBinding(ItemRepoBinding binding) {
            this.binding = binding;
        }
    }
}

