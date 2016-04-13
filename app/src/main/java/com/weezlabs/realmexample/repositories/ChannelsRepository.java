package com.weezlabs.realmexample.repositories;

import com.weezlabs.realmexample.models.ChannelRealmModel;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public final class ChannelsRepository {
    public static RealmResults<ChannelRealmModel> getChannels(boolean onlyChecked) {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<ChannelRealmModel> query = realm.where(ChannelRealmModel.class);

        if (onlyChecked) {
            query.equalTo(ChannelRealmModel.FIELD_IS_CHECKED, true);
        }

        RealmResults<ChannelRealmModel> results = query.findAll();
        realm.close();

        return results;
    }
}
