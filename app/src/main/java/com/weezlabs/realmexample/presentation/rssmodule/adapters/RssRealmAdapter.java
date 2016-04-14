package com.weezlabs.realmexample.presentation.rssmodule.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weezlabs.realmexample.BR;
import com.weezlabs.realmexample.R;
import com.weezlabs.realmexample.databinding.ItemRssBinding;
import com.weezlabs.realmexample.models.plain.Rss;
import com.weezlabs.realmexample.presentation.rssmodule.viewmodels.RssViewModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmViewHolder;

public class RssRealmAdapter extends RecyclerView.Adapter<RssRealmAdapter.BindingHolder> {
    public class BindingHolder extends RealmViewHolder {
        private ItemRssBinding mBinding;

        public BindingHolder(View itemView) {
            super(itemView);

            mBinding = DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding() {
            return mBinding;
        }
    }

    private List<Rss> mRssList = new ArrayList<>();

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rss, viewGroup, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(BindingHolder viewHolder, int i) {
        Rss rss = mRssList.get(i);

        viewHolder.getBinding().setVariable(BR.rss, new RssViewModel(rss));
        viewHolder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mRssList.size();
    }

    public void refreshAll(List<Rss> feed) {
        mRssList.clear();
        mRssList.addAll(feed);
        notifyDataSetChanged();
    }
}
