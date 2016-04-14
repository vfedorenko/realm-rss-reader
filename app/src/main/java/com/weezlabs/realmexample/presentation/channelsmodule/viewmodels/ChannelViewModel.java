package com.weezlabs.realmexample.presentation.channelsmodule.viewmodels;

import android.databinding.ObservableBoolean;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.weezlabs.realmexample.businesslogic.repositories.RssRepository;
import com.weezlabs.realmexample.models.plain.Channel;
import com.weezlabs.realmexample.presentation.channelsmodule.activities.CreateChannelActivity;
import com.weezlabs.realmexample.presentation.channelsmodule.activities.CreateChannelActivityInput;
import com.weezlabs.realmexample.presentation.channelsmodule.interactors.ChannelsInteractor;
import com.weezlabs.realmexample.presentation.channelsmodule.interactors.ChannelsInteractorInput;

import java.lang.ref.WeakReference;

public class ChannelViewModel {
    public ObservableBoolean isChecked = new ObservableBoolean();

    private Channel mChannel;

    private ChannelsInteractorInput mChannelsInteractor;
    private WeakReference<CreateChannelActivityInput> mWeakActivityInput;

    public ChannelViewModel() {
        this(new Channel());
    }

    public ChannelViewModel(Channel model) {
        mChannelsInteractor = ChannelsInteractor.getInstance();

        mChannel = model;

        isChecked.set(mChannel.isChecked());
    }

    public String getTitle() {
        return mChannel.getTitle();
    }

    public void onChannelClick(View v) {
        mChannelsInteractor.toggleChannel(mChannel);

        isChecked.set(mChannel.isChecked());

        if (!mChannel.isChecked()) {
            RssRepository.getInstance().clearRssInChannel(mChannel.getLink());
        }
    }

    public void onDoneClick(View v) {
        saveChannel();
    }

    public boolean onDoneClick(TextView v, int actionId, KeyEvent event) {
        if (event != null && event.getAction() == KeyEvent.ACTION_DOWN) {
            saveChannel();
        }
        return false;
    }

    public void onTitleChanged(Editable str) {
        mChannel.setTitle(str.toString());
    }

    public void onLinkChanged(Editable str) {
        mChannel.setLink(str.toString());
    }

    public void setActivityInput(CreateChannelActivity activityInput) {
        mWeakActivityInput = new WeakReference<CreateChannelActivityInput>(activityInput);
    }

    private void saveChannel() {
        mChannelsInteractor.saveChannel(mChannel);

        if (mWeakActivityInput != null && mWeakActivityInput.get() != null) {
            mWeakActivityInput.get().finishActivity();
        }
    }
}
