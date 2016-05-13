package com.example.kapilboss.tabs.utilities;

import com.example.kapilboss.tabs.utilities.rx.DefaultEventBus;
import com.example.kapilboss.tabs.utilities.rx.EventBus;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.schedulers.Schedulers;

/**
 * Created by kapilsharma on 11/05/16.
 */
@Module
public class MainModule {

    @Provides
    @Singleton
    public EventBus eventBus() {
        return new DefaultEventBus(Schedulers.computation());
    }

}
