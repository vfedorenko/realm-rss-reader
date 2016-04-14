package com.weezlabs.realmexample.businesslogic.repositories;

import com.weezlabs.realmexample.models.plain.Channel;

import java.util.List;

public interface ChannelsRepositoryOutput {
    void channelsReady(List<Channel> channels);
    void channelAdded(Channel channel);
}
