package com.weezlabs.realmexample.viewmodels;

import com.weezlabs.realmexample.models.RssRealmModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RssViewModel {
    private RssRealmModel mRssModel;

    public RssViewModel(RssRealmModel model) {
        mRssModel = model;
    }

    public String getTitle() {
        return mRssModel.getTitle();
    }

    public String getText() {
        return mRssModel.getText();
    }

    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH);

        return dateFormat.format(new Date(mRssModel.getDate()));
    }
}
