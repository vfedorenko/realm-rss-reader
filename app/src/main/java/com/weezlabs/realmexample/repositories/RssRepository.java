package com.weezlabs.realmexample.repositories;

import android.util.Log;

import com.weezlabs.realmexample.models.RssRealmModel;

import java.util.List;

import io.realm.Realm;

public final class RssRepository {
    public static void updateFeedInChannel(final List<RssRealmModel> rssList, String channelLink) {
        clearRssInChannel(channelLink);

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(rssList);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d("REALM", "All done updating.");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // transaction is automatically rolled-back, do any cleanup here
            }
        });
        realm.close();
    }

    public static void clearRssInChannel(String channelLink) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.where(RssRealmModel.class).equalTo("channel.link", channelLink).findAll().clear();

        realm.commitTransaction();
        realm.close();
    }
}
