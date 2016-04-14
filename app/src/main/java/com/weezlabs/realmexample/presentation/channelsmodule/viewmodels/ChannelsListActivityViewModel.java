package com.weezlabs.realmexample.presentation.channelsmodule.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.weezlabs.realmexample.models.plain.Channel;
import com.weezlabs.realmexample.presentation.channelsmodule.activities.CreateChannelActivity;
import com.weezlabs.realmexample.presentation.channelsmodule.adapters.ChannelsAdapter;
import com.weezlabs.realmexample.presentation.channelsmodule.interactors.ChannelsInteractor;
import com.weezlabs.realmexample.presentation.channelsmodule.interactors.ChannelsInteractorInput;
import com.weezlabs.realmexample.presentation.channelsmodule.interactors.ChannelsInteractorOutput;

import java.util.List;

public class ChannelsListActivityViewModel implements ChannelsInteractorOutput {
    private ChannelsInteractorInput mChannelsInteractor;

    private ChannelsAdapter mAdapter;

    public ChannelsListActivityViewModel() {
        mChannelsInteractor = ChannelsInteractor.getInstance();
        mChannelsInteractor.addOutputListener(this);
    }

    @Override
    public void channelsReady(List<Channel> channels) {
        if (mAdapter != null) {
            mAdapter.refreshAll(channels);
        }
    }

    @Override
    public void channelAdded(Channel channel) {
        mAdapter.addChannel(channel);
    }

    public void onFabClick(View v) {
        Context c = v.getContext();
        c.startActivity(new Intent(c, CreateChannelActivity.class));
    }

    public void putAdapter(ChannelsAdapter adapter) {
        mAdapter = adapter;
    }

    public void initUi() {
        mChannelsInteractor.obtainLocalChannels(false);
    }
}
