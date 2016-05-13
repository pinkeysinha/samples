package com.example.kapilboss.tabs.activity.common;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;

import com.example.kapilboss.tabs.R;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by kapilsharma on 10/05/16.
 */
public abstract class BaseActionBarActivity extends RxAppCompatActivity {
    private static final String TAG = BaseActionBarActivity.class.getName();
    private Snackbar snackbar;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        snackbar = Snackbar.make(findViewById(android.R.id.content), "No internet connection", Snackbar.LENGTH_INDEFINITE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //MyEventBus.getInstance().addObserver(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //MyEventBus.getInstance().removeObserver(this);

    }

    protected void initilizeToolbar(boolean backBtnEnabled, String title){
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(backBtnEnabled);
        if(Build.VERSION.SDK_INT>=21){
            getSupportActionBar().setElevation(10);
        }
    }

    protected void setTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
