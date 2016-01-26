package com.surrus.galwaybus;


import android.support.test.espresso.IdlingResource;
import android.util.Log;

import rx.plugins.RxJavaPlugins;

public class BetterIdlingResource implements IdlingResource, BetterExecutionInterface {

    private static final boolean isLogged = false;
    private IdlingResource.ResourceCallback cb;
    private Integer idler = 0;

    public BetterIdlingResource() {
        Log.e("inaki", "xxxdddddddxxxx");

        try {
            RxJavaPlugins.getInstance().registerObservableExecutionHook(new BetterExecutionHook(this));
        } catch (Exception e) {
        }
    }

    @Override
    public String getName() {
        Log.e("inaki", "xxxccccccxxxx");

        return this.getClass().getSimpleName();
    }

    @Override
    public boolean isIdleNow() {

        synchronized (idler) {
            Log.e("inaki","ssssss");
            return idler == 0;
        }
    }

    @Override
    public void registerIdleTransitionCallback(IdlingResource.ResourceCallback resourceCallback) {
        Log.e("inaki", "yyyy");
        this.cb = resourceCallback;
    }

    @Override
    public void onStart() {
Log.e("inaki", "xxxxxxx");
        synchronized (idler) {
            idler++;
        }
    }

    @Override
    public void onError() {

        synchronized (idler) {
            idler--;
            if (idler == 0 && cb != null) {
                cb.onTransitionToIdle();
            }
        }
    }

    @Override
    public void onEnd() {

        synchronized (idler) {
            idler--;
            if (idler == 0 && cb != null) {
                cb.onTransitionToIdle();
            }
        }
    }
}