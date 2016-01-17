package com.surrus.galwaybus.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.surrus.galwaybus.GalwayBusApplication;

import javax.inject.Inject;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class BaseFragment extends Fragment {

    @Inject
    EventBus bus;

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Perform dagger injection
        ((GalwayBusApplication) getActivity().getApplication()).inject(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
