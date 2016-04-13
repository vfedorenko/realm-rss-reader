package com.weezlabs.realmexample.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RssRealmModel extends RealmObject {
    public static final String FIELD_DATE = "date";
    public static final String FIELD_CHANNEL = "channel";

    @PrimaryKey
    private long date;

    private String title;
    private String text;
    private String link;

    private ChannelRealmModel channel;

    //region Getters+Setters
    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ChannelRealmModel getChannel() {
        return channel;
    }

    public void setChannel(ChannelRealmModel channel) {
        this.channel = channel;
    }
    //endregion
}
