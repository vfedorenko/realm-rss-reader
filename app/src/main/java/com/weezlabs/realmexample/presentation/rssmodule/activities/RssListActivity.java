package com.weezlabs.realmexample.presentation.rssmodule.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.weezlabs.realmexample.R;
import com.weezlabs.realmexample.databinding.ActivityRssListBinding;
import com.weezlabs.realmexample.presentation.rssmodule.adapters.RssRealmAdapter;
import com.weezlabs.realmexample.presentation.rssmodule.viewmodels.RssListActivityViewModel;

public class RssListActivity extends AppCompatActivity {

    private RssListActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new RssListActivityViewModel();

        ActivityRssListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_rss_list);
        binding.setViewModel(viewModel);

        setSupportActionBar(binding.toolbar);

        RssRealmAdapter adapter = new RssRealmAdapter();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        viewModel.putAdapter(adapter);
        viewModel.initUi();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.refreshUi();
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
            viewModel.onChannelsClick(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
