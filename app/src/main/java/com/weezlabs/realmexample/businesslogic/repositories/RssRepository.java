package com.weezlabs.realmexample.businesslogic.repositories;

import com.weezlabs.realmexample.models.plain.Rss;
import com.weezlabs.realmexample.models.realm.RssRealmModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public final class RssRepository implements RssRepositoryInput {
    private static RssRepository sInstance;

    private List<RssRepositoryOutput> mOutputs = new ArrayList<>();

    public static RssRepository getInstance() {
        if (sInstance == null) {
            sInstance = new RssRepository();
        }

        return sInstance;
    }

    private RssRepository() {

    }

    @Override
    public void addOutputListener(RssRepositoryOutput output) {
        mOutputs.add(output);
    }

    @Override
    public void obtainFeed() {
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<RssRealmModel> realmResults = realm.where(RssRealmModel.class)
                .findAllSortedAsync(RssRealmModel.FIELD_DATE, Sort.DESCENDING);
        realmResults.addChangeListener(new RealmChangeListener() {
            @Override
            public void onChange() {
                ListMapper<Rss, RssRealmModel> mapper = new ListMapper<>();
                List<Rss> feed = mapper.realmToPlain(realmResults);

                realm.close();

                for (RssRepositoryOutput output : mOutputs) {
                    output.rssReady(feed);
                }
            }
        });
    }

    @Override
    public void updateFeedInChannel(List<RssRealmModel> rssList, String channelLink) {
        clearRssInChannel(channelLink);

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.copyToRealmOrUpdate(rssList);

        realm.commitTransaction();
        realm.close();

        for (RssRepositoryOutput output : mOutputs) {
            output.feedChanged();
        }
    }

    @Override
    public void clearRssInChannel(String channelLink) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.where(RssRealmModel.class).equalTo("channel.link", channelLink).findAll().clear();

        realm.commitTransaction();
        realm.close();

        for (RssRepositoryOutput output : mOutputs) {
            output.feedChanged();
        }
    }
}
