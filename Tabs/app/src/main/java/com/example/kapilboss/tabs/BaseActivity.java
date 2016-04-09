package com.example.kapilboss.tabs;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by kapilboss on 08/04/16.
 */
public class BaseActivity extends AppCompatActivity {
    private static final String TAG=BaseActivity.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    protected void initilizeToolbar(boolean backBtnEnabled, String title, boolean isElevated){
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(backBtnEnabled);
        if(Build.VERSION.SDK_INT>=21 && isElevated){
            getSupportActionBar().setElevation(10);
        }
    }
}
