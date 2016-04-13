package com.weezlabs.realmexample.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weezlabs.realmexample.BR;
import com.weezlabs.realmexample.R;
import com.weezlabs.realmexample.databinding.ItemChannelBinding;
import com.weezlabs.realmexample.models.ChannelRealmModel;
import com.weezlabs.realmexample.viewmodels.ChannelViewModel;

import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

public class ChannelsRealmAdapter extends RealmBasedRecyclerViewAdapter<ChannelRealmModel, ChannelsRealmAdapter.BindingHolder> {
    public class BindingHolder extends RealmViewHolder {
        private ItemChannelBinding mBinding;

        public BindingHolder(View itemView) {
            super(itemView);

            mBinding = DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding() {
            return mBinding;
        }
    }

    public ChannelsRealmAdapter(Context context, RealmResults<ChannelRealmModel> realmResults) {
        super(context, realmResults, true, true);
    }

    @Override
    public BindingHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_channel, viewGroup, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindRealmViewHolder(BindingHolder viewHolder, int i) {
        ChannelRealmModel item = realmResults.get(i);

        viewHolder.getBinding().setVariable(BR.channel, new ChannelViewModel(item));
        viewHolder.getBinding().executePendingBindings();
    }
}
