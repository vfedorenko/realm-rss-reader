package com.weezlabs.realmexample.presentation.rssmodule.interactors;

public interface RssInteractorInput {
    void addOutputListener(RssInteractorOutput output);
    void obtainLocalFeed();
    void syncWithWeb();
}
