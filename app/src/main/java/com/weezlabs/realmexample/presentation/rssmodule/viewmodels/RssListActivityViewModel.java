package com.weezlabs.realmexample.presentation.rssmodule.viewmodels;

import android.content.Context;
import android.content.Intent;

import com.weezlabs.realmexample.models.plain.Rss;
import com.weezlabs.realmexample.presentation.channelsmodule.activities.ChannelsActivity;
import com.weezlabs.realmexample.presentation.rssmodule.adapters.RssRealmAdapter;
import com.weezlabs.realmexample.presentation.rssmodule.interactors.RssInteractor;
import com.weezlabs.realmexample.presentation.rssmodule.interactors.RssInteractorInput;
import com.weezlabs.realmexample.presentation.rssmodule.interactors.RssInteractorOutput;

import java.util.List;

public class RssListActivityViewModel implements RssInteractorOutput {
    private RssInteractorInput mRssInteractor;
    private RssRealmAdapter mRssListAdapter;

    public RssListActivityViewModel() {
        mRssInteractor = RssInteractor.getInstance();
        mRssInteractor.addOutputListener(this);
    }

    @Override
    public void rssFeedReady(List<Rss> feed) {
        if (mRssListAdapter != null) {
            mRssListAdapter.refreshAll(feed);
        }
    }

    public void putAdapter(RssRealmAdapter adapter) {
        mRssListAdapter = adapter;
    }

    public void initUi() {
        mRssInteractor.obtainLocalFeed();
    }

    public void refreshUi() {
        mRssInteractor.syncWithWeb();
    }

    public void onChannelsClick(Context context) {
        context.startActivity(new Intent(context, ChannelsActivity.class));
    }
}
