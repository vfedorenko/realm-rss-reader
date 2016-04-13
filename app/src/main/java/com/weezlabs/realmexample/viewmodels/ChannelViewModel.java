package com.weezlabs.realmexample.viewmodels;

import android.databinding.ObservableBoolean;
import android.text.Editable;
import android.view.View;

import com.weezlabs.realmexample.models.ChannelRealmModel;
import com.weezlabs.realmexample.repositories.RssRepository;

import io.realm.Realm;

public class ChannelViewModel {
    public ObservableBoolean isChecked = new ObservableBoolean();

    private ChannelRealmModel mChannelModel;

    public ChannelViewModel() {
        mChannelModel = new ChannelRealmModel();
    }

    public ChannelViewModel(ChannelRealmModel model) {
        mChannelModel = model;

        isChecked.set(mChannelModel.isChecked());
    }

    public String getTitle() {
        return mChannelModel.getTitle();
    }

    public void onChannelClick(View v) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        mChannelModel.setChecked(!mChannelModel.isChecked());

        realm.commitTransaction();
        realm.close();

        isChecked.set(mChannelModel.isChecked());

        if (!mChannelModel.isChecked()) {
            RssRepository.clearRssInChannel(mChannelModel.getLink());
        }
    }

    public void onTitleChanged(Editable str) {
        mChannelModel.setTitle(str.toString());
    }

    public void onLinkChanged(Editable str) {
        mChannelModel.setLink(str.toString());
    }

    public void saveModel() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.copyToRealm(mChannelModel);

        realm.commitTransaction();
        realm.close();
    }
}
