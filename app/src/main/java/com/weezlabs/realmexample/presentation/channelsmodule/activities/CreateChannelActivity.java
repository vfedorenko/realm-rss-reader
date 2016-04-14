package com.weezlabs.realmexample.presentation.channelsmodule.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.weezlabs.realmexample.R;
import com.weezlabs.realmexample.databinding.ActivityCreateChannelBinding;
import com.weezlabs.realmexample.presentation.channelsmodule.viewmodels.ChannelViewModel;

public class CreateChannelActivity extends AppCompatActivity implements CreateChannelActivityInput {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChannelViewModel viewModel = new ChannelViewModel();
        viewModel.setActivityInput(this);

        ActivityCreateChannelBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_create_channel);
        binding.setChannel(viewModel);
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
