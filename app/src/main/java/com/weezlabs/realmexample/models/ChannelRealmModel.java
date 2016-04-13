package com.weezlabs.realmexample.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class ChannelRealmModel extends RealmObject {
    public static final String FIELD_IS_CHECKED = "isChecked";
    public static final String FIELD_LINK = "link";

    @PrimaryKey
    private String title;
    @Required
    private String link;
    private boolean isChecked;
    private RealmList<RssRealmModel> rssList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public RealmList<RssRealmModel> getRssList() {
        return rssList;
    }

    public void setRssList(RealmList<RssRealmModel> rssList) {
        this.rssList = rssList;
    }
}
