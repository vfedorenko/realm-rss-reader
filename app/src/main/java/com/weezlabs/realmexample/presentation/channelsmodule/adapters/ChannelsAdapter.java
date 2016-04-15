package com.weezlabs.realmexample.presentation.channelsmodule.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weezlabs.realmexample.BR;
import com.weezlabs.realmexample.R;
import com.weezlabs.realmexample.databinding.ItemChannelBinding;
import com.weezlabs.realmexample.models.plain.Channel;
import com.weezlabs.realmexample.presentation.channelsmodule.viewmodels.ChannelViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChannelsAdapter extends RecyclerView.Adapter<ChannelsAdapter.BindingHolder> {
    public class BindingHolder extends RecyclerView.ViewHolder {
        private ItemChannelBinding mBinding;

        public BindingHolder(View itemView) {
            super(itemView);

            mBinding = DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding() {
            return mBinding;
        }
    }

    private List<Channel> mChannels = new ArrayList<>();

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_channel, viewGroup, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(BindingHolder viewHolder, int i) {
        Channel item = mChannels.get(i);

        viewHolder.getBinding().setVariable(BR.channel, new ChannelViewModel(item));
        viewHolder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mChannels.size();
    }

    public void refreshAll(List<Channel> channels) {
        mChannels.clear();
        mChannels.addAll(channels);
        notifyDataSetChanged();
    }

    public void addChannel(Channel channel) {
        mChannels.add(channel);
        notifyItemInserted(mChannels.indexOf(channel));
    }
}
