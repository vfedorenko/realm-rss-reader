package com.weezlabs.realmexample.businesslogic.repositories;

import com.weezlabs.realmexample.models.plain.Rss;

import java.util.List;

public interface RssRepositoryOutput {
    void rssReady(List<Rss> feed);
    void feedChanged();
}
