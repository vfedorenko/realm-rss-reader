package com.weezlabs.realmexample.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.weezlabs.realmexample.R;
import com.weezlabs.realmexample.databinding.ActivityCreateChannelBinding;
import com.weezlabs.realmexample.viewmodels.ChannelViewModel;

public class CreateChannelActivity extends AppCompatActivity {
    ChannelViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ChannelViewModel();

        ActivityCreateChannelBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_create_channel);
        binding.setChannel(mViewModel);
    }

    public void onDoneClick(View view) {
        mViewModel.saveModel();
        finish();
    }
}
