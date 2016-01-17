package com.surrus.galwaybus;


import com.surrus.galwaybus.service.GalwayBusService;
import com.surrus.galwaybus.ui.RoutesFragment;
import com.surrus.galwaybus.ui.StopsActivity;
import com.surrus.galwaybus.ui.StopsMapActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.EventBusBuilder;

@Module(
        library = true,
        injects = {
                GalwayBusApplication.class ,
                RoutesFragment.class,
                GalwayBusService.class,
                StopsActivity.class,
                StopsActivity.StopsFragment.class,
                StopsMapActivity.class
        }
)
public class AppModule {

    @Provides
    @Singleton
    public EventBus providesBus() {
        EventBusBuilder builder = EventBus.builder();
        return builder.build();
    }
}