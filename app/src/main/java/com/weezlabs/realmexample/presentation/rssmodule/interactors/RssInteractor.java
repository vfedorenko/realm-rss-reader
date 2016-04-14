package com.weezlabs.realmexample.presentation.rssmodule.interactors;

import android.content.Intent;

import com.weezlabs.realmexample.App;
import com.weezlabs.realmexample.businesslogic.repositories.RssRepository;
import com.weezlabs.realmexample.businesslogic.repositories.RssRepositoryInput;
import com.weezlabs.realmexample.businesslogic.repositories.RssRepositoryOutput;
import com.weezlabs.realmexample.businesslogic.webapi.RssFeedUpdateRequest;
import com.weezlabs.realmexample.models.plain.Channel;
import com.weezlabs.realmexample.models.plain.Rss;
import com.weezlabs.realmexample.presentation.channelsmodule.interactors.ChannelsInteractor;
import com.weezlabs.realmexample.presentation.channelsmodule.interactors.ChannelsInteractorInput;
import com.weezlabs.realmexample.presentation.channelsmodule.interactors.ChannelsInteractorOutput;

import java.util.ArrayList;
import java.util.List;

public class RssInteractor implements RssInteractorInput, ChannelsInteractorOutput, RssRepositoryOutput {
    private static RssInteractor sInstance;

    private List<RssInteractorOutput> mOutputs = new ArrayList<>();
    private ChannelsInteractorInput mChannelsInteractor;
    private RssRepositoryInput mRssRepository;

    public static RssInteractor getInstance() {
        if (sInstance == null) {
            sInstance = new RssInteractor();
        }
        return sInstance;
    }

    private RssInteractor() {
        mChannelsInteractor = ChannelsInteractor.getInstance();
        mChannelsInteractor.addOutputListener(this);

        mRssRepository = RssRepository.getInstance();
        mRssRepository.addOutputListener(this);
    }

    @Override
    public void addOutputListener(RssInteractorOutput output) {
        mOutputs.add(output);
    }

    @Override
    public void obtainLocalFeed() {
        mRssRepository.obtainFeed();
    }

    @Override
    public void syncWithWeb() {
        mChannelsInteractor.obtainLocalChannels(true);
    }

    @Override
    public void channelsReady(List<Channel> channels) {
        ArrayList<String> links = new ArrayList<>();

        for (Channel channel : channels) {
            links.add(channel.getLink());
        }

        Intent refreshRssIntent = new Intent(App.getAppContext(), RssFeedUpdateRequest.class);
        refreshRssIntent.putStringArrayListExtra(RssFeedUpdateRequest.EXTRA_CHANNELS_LINKS, links);

        App.getAppContext().startService(refreshRssIntent);
    }

    @Override
    public void channelAdded(Channel channel) {
        // Nothing to do
    }

    @Override
    public void rssReady(List<Rss> feed) {
        for (RssInteractorOutput output : mOutputs) {
            output.rssFeedReady(feed);
        }
    }

    @Override
    public void feedChanged() {
        mRssRepository.obtainFeed();
    }
}
