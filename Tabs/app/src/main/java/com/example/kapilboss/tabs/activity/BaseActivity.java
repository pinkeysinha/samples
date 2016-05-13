package com.example.kapilboss.tabs.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.kapilboss.tabs.R;
import com.example.kapilboss.tabs.activity.common.BaseActionBarActivity;
import com.example.kapilboss.tabs.ui.ScreenEvent;
import com.example.kapilboss.tabs.utilities.rx.Queue;

/**
 * Created by kapilboss on 08/04/16.
 */
public class BaseActivity extends BaseActionBarActivity {
    private static final String TAG=BaseActivity.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public Queue<ScreenEvent> screenEventQueue() {

        throw new IllegalArgumentException("Must implement this screenEventQueue method in your activity");
    }
}
