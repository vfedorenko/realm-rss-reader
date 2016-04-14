package com.weezlabs.realmexample.presentation.rssmodule.viewmodels;

import com.weezlabs.realmexample.models.plain.Rss;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RssViewModel {
    private Rss mRss;

    public RssViewModel(Rss model) {
        mRss = model;
    }

    public String getTitle() {
        return mRss.getTitle();
    }

    public String getText() {
        return mRss.getText();
    }

    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH);

        return dateFormat.format(new Date(mRss.getDate()));
    }
}
