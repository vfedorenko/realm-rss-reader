package com.weezlabs.realmexample.presentation.channelsmodule.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import com.weezlabs.realmexample.R;
import com.weezlabs.realmexample.databinding.ActivityChannelsBinding;
import com.weezlabs.realmexample.presentation.channelsmodule.adapters.ChannelsAdapter;
import com.weezlabs.realmexample.presentation.channelsmodule.viewmodels.ChannelsListActivityViewModel;

public class ChannelsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChannelsListActivityViewModel viewModel = new ChannelsListActivityViewModel();

        ActivityChannelsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_channels);
        binding.setViewModel(viewModel);

        setSupportActionBar(binding.toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ChannelsAdapter adapter = new ChannelsAdapter();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        viewModel.putAdapter(adapter);
        viewModel.initUi();
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
}
