package com.weezlabs.realmexample.models.plain;

import com.weezlabs.realmexample.models.realm.ChannelRealmModel;

public class Channel {
    private String mTitle;
    private String mLink;
    private boolean mIsChecked;

    //region Getters+Setters
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public boolean isChecked() {
        return mIsChecked;
    }

    public void setChecked(boolean checked) {
        mIsChecked = checked;
    }
    //endregion

    public ChannelRealmModel toRealmObject() {
        ChannelRealmModel realmModel = new ChannelRealmModel();
        realmModel.setTitle(mTitle);
        realmModel.setLink(mLink);
        realmModel.setChecked(mIsChecked);

        return realmModel;
    }
}
