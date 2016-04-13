package com.weezlabs.realmexample.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.weezlabs.realmexample.R;
import com.weezlabs.realmexample.adapters.RssRealmAdapter;
import com.weezlabs.realmexample.models.ChannelRealmModel;
import com.weezlabs.realmexample.models.RssRealmModel;
import com.weezlabs.realmexample.repositories.ChannelsRepository;
import com.weezlabs.realmexample.repositories.RssRepository;
import com.weezlabs.realmexample.webapi.RssFeedUpdateRequest;

import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmResults;

public class RssListActivity extends AppCompatActivity {

    private Realm mRealm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RealmResults<RssRealmModel> rssList = RssRepository.getRssFeed();

        RssRealmAdapter adapter = new RssRealmAdapter(this, rssList);

        RealmRecyclerView rssRecyclerView = (RealmRecyclerView) findViewById(R.id.realm_recycler_view);
        rssRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        RealmResults<ChannelRealmModel> channels = ChannelsRepository.getChannels(true);

        for (ChannelRealmModel channel : channels) {
            Intent refreshRssIntent = new Intent(this, RssFeedUpdateRequest.class);
            refreshRssIntent.setData(Uri.parse(channel.getLink()));

            startService(refreshRssIntent);
        }
    }

    @Override
    protected void onDestroy() {
        mRealm.close();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_channels) {
            startActivity(new Intent(this, ChannelsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
