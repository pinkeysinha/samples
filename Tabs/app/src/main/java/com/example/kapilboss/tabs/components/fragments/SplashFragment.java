package com.example.kapilboss.tabs.components.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.kapilboss.tabs.MyApplication;
import com.example.kapilboss.tabs.R;
import com.example.kapilboss.tabs.activity.LoginActivity;
import com.example.kapilboss.tabs.ui.ScreenEvent;
import com.example.kapilboss.tabs.utilities.rx.EventBus;


import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by kapilsharma on 11/05/16.
 */
public class SplashFragment extends BaseFragment {
    @Inject
    EventBus eventBus;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().component().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.splash, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addViewDelay();
    }

    void addViewDelay() {
        Observable.just(true)
                .delay(4, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    switchToLoginScreen("switch");
                });
    }

    void switchToLoginScreen(String data) {
        ScreenEvent<String> screenEvent = new ScreenEvent<>(LoginActivity.LOGIN_SCREEN, data);
        eventBus.publish(screenEventQueue(), screenEvent);
        compositeSubscription.unsubscribe();
    }
}
