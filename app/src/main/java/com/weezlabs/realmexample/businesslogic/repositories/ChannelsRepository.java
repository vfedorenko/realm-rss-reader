package com.weezlabs.realmexample.businesslogic.repositories;

import com.weezlabs.realmexample.models.plain.Channel;
import com.weezlabs.realmexample.models.realm.ChannelRealmModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public final class ChannelsRepository implements ChannelsRepositoryInput{
    private static ChannelsRepository sInstance;

    private List<ChannelsRepositoryOutput> mOutputs = new ArrayList<>();

    public static ChannelsRepository getInstance() {
        if (sInstance == null) {
            sInstance = new ChannelsRepository();
        }

        return sInstance;
    }

    private ChannelsRepository() {

    }

    @Override
    public void addOutputListener(ChannelsRepositoryOutput output) {
        mOutputs.add(output);
    }

    @Override
    public void obtainChannels(boolean onlyChecked) {
        final Realm realm = Realm.getDefaultInstance();
        RealmQuery<ChannelRealmModel> query = realm.where(ChannelRealmModel.class);

        if (onlyChecked) {
            query.equalTo(ChannelRealmModel.FIELD_IS_CHECKED, true);
        }

        final RealmResults<ChannelRealmModel> realmResults = query.findAllAsync();
        realmResults.addChangeListener(new RealmChangeListener() {
            @Override
            public void onChange() {
                ListMapper<Channel, ChannelRealmModel> mapper = new ListMapper<>();
                List<Channel> channels = mapper.realmToPlain(realmResults);

                realm.close();

                for (ChannelsRepositoryOutput output : mOutputs) {
                    output.channelsReady(channels);
                }
            }
        });
    }

    @Override
    public void saveChannel(Channel channel) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.copyToRealm(channel.toRealmObject());

        realm.commitTransaction();
        realm.close();

        for (ChannelsRepositoryOutput output : mOutputs) {
            output.channelAdded(channel);
        }
    }

    @Override
    public void toggleChannel(Channel channel) {
        Realm realm = Realm.getDefaultInstance();

        ChannelRealmModel realmChannel = realm.where(ChannelRealmModel.class).equalTo(ChannelRealmModel.FIELD_LINK, channel.getLink()).findFirst();

        realm.beginTransaction();

        channel.setChecked(!channel.isChecked());
        realmChannel.setChecked(channel.isChecked());

        realm.commitTransaction();
        realm.close();
    }
}
