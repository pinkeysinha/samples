package com.example.kapilboss.tabs;

import android.app.Application;

import com.example.kapilboss.tabs.dagger.DaggerNetworkServiceComponent;
import com.example.kapilboss.tabs.dagger.NetworkServiceComponent;
import com.example.kapilboss.tabs.utilities.rx.EventBus;


/**
 * Created by kapilsharma on 11/05/16.
 */
public class MyApplication extends Application {
    private static MyApplication instance;
    private NetworkServiceComponent component;

    public static MyApplication getInstance() {
        return instance;
    }
    public NetworkServiceComponent component() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        component = DaggerNetworkServiceComponent.builder()
                .build();
    }
}
