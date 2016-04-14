package com.weezlabs.realmexample.presentation.channelsmodule.interactors;

import com.weezlabs.realmexample.businesslogic.repositories.ChannelsRepository;
import com.weezlabs.realmexample.businesslogic.repositories.ChannelsRepositoryInput;
import com.weezlabs.realmexample.businesslogic.repositories.ChannelsRepositoryOutput;
import com.weezlabs.realmexample.models.plain.Channel;

import java.util.ArrayList;
import java.util.List;

public class ChannelsInteractor implements ChannelsInteractorInput, ChannelsRepositoryOutput {
    private static ChannelsInteractor sInstance;

    private List<ChannelsInteractorOutput> mOutputs = new ArrayList<>();
    private ChannelsRepositoryInput mChannelsRepository;

    public static ChannelsInteractor getInstance() {
        if (sInstance == null) {
            sInstance = new ChannelsInteractor();
        }

        return sInstance;
    }

    private ChannelsInteractor() {
        mChannelsRepository = ChannelsRepository.getInstance();
        mChannelsRepository.addOutputListener(this);
    }

    @Override
    public void addOutputListener(ChannelsInteractorOutput output) {
        mOutputs.add(output);
    }

    @Override
    public void obtainLocalChannels(boolean onlyChecked) {
        mChannelsRepository.obtainChannels(onlyChecked);
    }

    @Override
    public void toggleChannel(Channel channel) {
        mChannelsRepository.toggleChannel(channel);
    }

    @Override
    public void saveChannel(Channel channel) {
        mChannelsRepository.saveChannel(channel);
    }

    @Override
    public void channelsReady(List<Channel> channels) {
        for (ChannelsInteractorOutput output : mOutputs) {
            output.channelsReady(channels);
        }
    }

    @Override
    public void channelAdded(Channel channel) {
        for (ChannelsInteractorOutput output : mOutputs) {
            output.channelAdded(channel);
        }
    }
}
