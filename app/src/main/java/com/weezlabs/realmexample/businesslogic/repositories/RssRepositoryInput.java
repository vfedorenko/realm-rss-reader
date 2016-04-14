package com.weezlabs.realmexample.businesslogic.repositories;

import com.weezlabs.realmexample.models.realm.RssRealmModel;

import java.util.List;

public interface RssRepositoryInput {
    void addOutputListener(RssRepositoryOutput output);
    void obtainFeed();
    void updateFeedInChannel(List<RssRealmModel> rssList, String channelLink);
    void clearRssInChannel(String channelLink);
}
