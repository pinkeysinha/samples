package com.example.kapilboss.tabs.components.fragments;

import android.support.v4.app.Fragment;

import com.example.kapilboss.tabs.activity.BaseActivity;
import com.example.kapilboss.tabs.ui.ScreenEvent;
import com.example.kapilboss.tabs.utilities.rx.Queue;
import com.example.kapilboss.tabs.view.JhampakDialog;
import com.trello.rxlifecycle.components.RxFragment;

/**
 * Created by kapilsharma on 11/05/16.
 */
public class BaseFragment extends Fragment {
    protected JhampakDialog progressDialog;
    protected Queue<ScreenEvent> screenEventQueue() {
        if (getActivity() instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) getActivity();
            return baseActivity.screenEventQueue();
        }

        throw new IllegalArgumentException("Must extend BaseActivity");
    }

}
