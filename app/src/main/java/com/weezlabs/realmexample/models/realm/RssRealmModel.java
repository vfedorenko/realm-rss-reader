package com.weezlabs.realmexample.models.realm;

import com.weezlabs.realmexample.models.Plainable;
import com.weezlabs.realmexample.models.plain.Rss;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RssRealmModel extends RealmObject implements Plainable<Rss> {
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

    @Override
    public Rss toPlainObject() {
        Rss rss = new Rss();
        rss.setTitle(title);
        rss.setText(text);
        rss.setLink(link);
        rss.setDate(date);

        return rss;
    }
}
