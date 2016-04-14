package com.weezlabs.realmexample.businesslogic.repositories;

import com.weezlabs.realmexample.models.plain.Channel;

public interface ChannelsRepositoryInput {
    void addOutputListener(ChannelsRepositoryOutput output);
    void obtainChannels(boolean onlyChecked);
    void saveChannel(Channel channel);
    void toggleChannel(Channel channel);
}
