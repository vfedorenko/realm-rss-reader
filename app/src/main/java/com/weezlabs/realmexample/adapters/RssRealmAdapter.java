package com.weezlabs.realmexample.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weezlabs.realmexample.BR;
import com.weezlabs.realmexample.R;
import com.weezlabs.realmexample.databinding.ItemRssBinding;
import com.weezlabs.realmexample.models.RssRealmModel;
import com.weezlabs.realmexample.viewmodels.RssViewModel;

import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

public class RssRealmAdapter extends RealmBasedRecyclerViewAdapter<RssRealmModel, RssRealmAdapter.BindingHolder> {
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

    public RssRealmAdapter(Context context, RealmResults<RssRealmModel> realmResults) {
        super(context, realmResults, true, true);
    }

    @Override
    public BindingHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rss, viewGroup, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindRealmViewHolder(BindingHolder viewHolder, int i) {
        RssRealmModel rss = realmResults.get(i);

        viewHolder.getBinding().setVariable(BR.rss, new RssViewModel(rss));
        viewHolder.getBinding().executePendingBindings();
    }
}
