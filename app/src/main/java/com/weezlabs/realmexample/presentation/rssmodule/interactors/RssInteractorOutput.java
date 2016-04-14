package com.weezlabs.realmexample.presentation.rssmodule.interactors;

import com.weezlabs.realmexample.models.plain.Rss;

import java.util.List;

public interface RssInteractorOutput {
    void rssFeedReady(List<Rss> feed);
}
