package com.weezlabs.realmexample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.weezlabs.realmexample.R;
import com.weezlabs.realmexample.adapters.ChannelsRealmAdapter;
import com.weezlabs.realmexample.models.ChannelRealmModel;
import com.weezlabs.realmexample.repositories.ChannelsRepository;

import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.RealmResults;

public class ChannelsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channels);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RealmResults<ChannelRealmModel> channels = ChannelsRepository.getChannels(false);

        ChannelsRealmAdapter adapter = new ChannelsRealmAdapter(this, channels);

        RealmRecyclerView rssRecyclerView = (RealmRecyclerView) findViewById(R.id.realm_recycler_view);
        rssRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onFabClick(View view) {
        startActivity(new Intent(this, CreateChannelActivity.class));
    }
}
