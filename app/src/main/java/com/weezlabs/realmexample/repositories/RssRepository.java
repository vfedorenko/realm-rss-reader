package com.weezlabs.realmexample.repositories;

import com.weezlabs.realmexample.models.RssRealmModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public final class RssRepository {
    public static RealmResults<RssRealmModel> getRssFeed() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<RssRealmModel> results = realm.where(RssRealmModel.class)
                .findAllSortedAsync(RssRealmModel.FIELD_DATE, Sort.DESCENDING);
        realm.close();

        return results;
    }

    public static void updateFeedInChannel(List<RssRealmModel> rssList, String channelLink) {
        clearRssInChannel(channelLink);

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.copyToRealmOrUpdate(rssList);

        realm.commitTransaction();
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
