package com.weezlabs.realmexample.presentation.channelsmodule.interactors;

import com.weezlabs.realmexample.models.plain.Channel;

import java.util.List;

public interface ChannelsInteractorOutput {
    void channelsReady(List<Channel> channels);
    void channelAdded(Channel channel);
}
