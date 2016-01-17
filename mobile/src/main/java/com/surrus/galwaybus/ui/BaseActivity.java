package com.surrus.galwaybus.ui;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.surrus.galwaybus.GalwayBusApplication;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

public class BaseActivity extends AppCompatActivity {

    @Inject
    EventBus bus;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Perform dagger injection
        ((GalwayBusApplication) getApplication()).inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);
    }
}
