package com.weezlabs.realmexample.presentation.channelsmodule.interactors;

import com.weezlabs.realmexample.models.plain.Channel;

public interface ChannelsInteractorInput {
    void addOutputListener(ChannelsInteractorOutput output);
    void obtainLocalChannels(boolean onlyChecked);
    void toggleChannel(Channel channel);
    void saveChannel(Channel channel);
}
