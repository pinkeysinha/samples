package com.example.kapilboss.tabs.components.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.kapilboss.tabs.MyApplication;
import com.example.kapilboss.tabs.R;
import com.example.kapilboss.tabs.activity.LoginActivity;
import com.example.kapilboss.tabs.utilities.rx.EventBus;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by kapilsharma on 11/05/16.
 */
public class SigninFragment extends BaseFragment {
    private static final String KEY_MOBILE = "mobile";
    private EditText mobileNumber;
    private CompositeSubscription compositeSubscription;
    @Inject
    EventBus eventBus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signin, container, false);
        mobileNumber = (EditText) view.findViewById(R.id.mobile);
        String phoneNumber = getStringArgument(KEY_MOBILE, "");
        compositeSubscription = new CompositeSubscription();
        MyApplication.getInstance().component().inject(this);

        return view;

    }

    public static SigninFragment newInstance(String phoneNumber) {
        SigninFragment fragment = new SigninFragment();
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(phoneNumber)) {
            bundle.putString(KEY_MOBILE, phoneNumber);
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    protected String getStringArgument(String key, String defaultValue) {
        if (getArguments() == null || !getArguments().containsKey(key) || TextUtils.isEmpty(getArguments().getString(key))) {
            return defaultValue;
        }
        return getArguments().getString(key);
    }
}
