package com.weezlabs.realmexample.models.plain;

import com.weezlabs.realmexample.models.realm.RssRealmModel;

public class Rss {
    private long mDate;
    private String mTitle;
    private String mText;
    private String mLink;

    //region Getters+Setters
    public long getDate() {
        return mDate;
    }

    public void setDate(long date) {
        mDate = date;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }
    //endregion

    public RssRealmModel toRealmObject() {
        RssRealmModel realmModel = new RssRealmModel();
        realmModel.setTitle(mTitle);
        realmModel.setText(mText);
        realmModel.setLink(mLink);
        realmModel.setDate(mDate);

        return realmModel;
    }
}
