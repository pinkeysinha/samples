package com.example.kapilboss.tabs.dagger;

import android.app.usage.UsageEvents;

import com.example.kapilboss.tabs.activity.LoginActivity;
import com.example.kapilboss.tabs.components.fragments.SigninFragment;
import com.example.kapilboss.tabs.components.fragments.SplashFragment;
import com.example.kapilboss.tabs.utilities.MainModule;
import com.example.kapilboss.tabs.utilities.rx.EventBus;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kapilsharma on 11/05/16.
 */
@Singleton
@Component(modules = {MainModule.class})
public interface NetworkServiceComponent {

    void inject(LoginActivity activity);
    void inject(SplashFragment fragment);
    void inject(SigninFragment fragment);

  EventBus getEventBus();
}
