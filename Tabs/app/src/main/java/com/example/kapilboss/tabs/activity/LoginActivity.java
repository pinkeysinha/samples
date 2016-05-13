package com.example.kapilboss.tabs.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;


import com.example.kapilboss.tabs.MyApplication;
import com.example.kapilboss.tabs.R;
import com.example.kapilboss.tabs.components.fragments.SigninFragment;
import com.example.kapilboss.tabs.components.fragments.SplashFragment;
import com.example.kapilboss.tabs.ui.ScreenEvent;
import com.example.kapilboss.tabs.utilities.rx.EventBus;
import com.example.kapilboss.tabs.utilities.rx.Queue;


import javax.inject.Inject;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by kapilsharma on 10/05/16.
 */
public class LoginActivity extends BaseActivity {
    private static final String MY_FRAGMENT_STACK = "myFragmentStack";
    public static final int LOGIN_SCREEN = 1;
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Inject
    EventBus eventBus;
    public final Queue<ScreenEvent> SCREEN_TRANSITION_EVENT_QUEUE = Queue.of(ScreenEvent.class).get();
    private CompositeSubscription lifeCycle;

    private class ScreenTransitionSubscriber extends Subscriber<ScreenEvent> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

            e.printStackTrace();
        }

        @Override
        public void onNext(ScreenEvent screenEvent) {
            switchScreen(screenEvent);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        MyApplication.getInstance().component().inject(this);

        lifecycle().subscribe(activityEvent -> {
            switch (activityEvent) {
                case START:
                    lifeCycle = new CompositeSubscription();
                    lifeCycle.add(eventBus.subscribeOnMain(SCREEN_TRANSITION_EVENT_QUEUE, new ScreenTransitionSubscriber()));
                    break;
                case STOP:
                    lifeCycle.unsubscribe();
                    break;
            }
        });

        addSplashFragment();

    }

    private void addSplashFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(R.id.screen_placeholder) == null) {
            SplashFragment splashFragment = new SplashFragment();
            fragmentManager.beginTransaction().add(R.id.screen_placeholder, splashFragment).commitAllowingStateLoss();
        }
    }

    //@Override
    public Queue<ScreenEvent> screenEventQueue() {
        return SCREEN_TRANSITION_EVENT_QUEUE;
    }

    private void switchScreen(ScreenEvent screenEvent) {
        switch (screenEvent.screen) {
            case LOGIN_SCREEN:
                addSignInScreen(screenEvent);
                break;
        }
    }

    private void addSignInScreen(ScreenEvent<String> screenEvent) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(MY_FRAGMENT_STACK, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        String phoneNumber = "99663327050";
        SigninFragment signInFragment =
                SigninFragment.newInstance(phoneNumber);
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fragment_slide_left_enter,
                        R.anim.fragment_slide_left_exit,
                        R.anim.fragment_slide_right_enter,
                        R.anim.fragment_slide_right_exit)
                .replace(R.id.screen_placeholder, signInFragment)
                .commitAllowingStateLoss();
        Log.d(TAG, "Add Signin");
    }
}
