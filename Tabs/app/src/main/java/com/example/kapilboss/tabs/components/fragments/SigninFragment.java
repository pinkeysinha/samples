package com.example.kapilboss.tabs.components.fragments;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.kapilboss.tabs.MyApplication;
import com.example.kapilboss.tabs.R;
import com.example.kapilboss.tabs.activity.LoginActivity;
import com.example.kapilboss.tabs.broadcastreceiver.IncomingSms;
import com.example.kapilboss.tabs.broadcastreceiver.SMSReceiverCallback;
import com.example.kapilboss.tabs.utilities.rx.EventBus;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by kapilsharma on 11/05/16.
 */
public class SigninFragment extends BaseFragment implements SMSReceiverCallback {
    private final String TAG = SigninFragment.class.getSimpleName();
    private static final String KEY_MOBILE = "mobile";
    private EditText mobileNumber;
    private CompositeSubscription compositeSubscription;
    @Inject
    EventBus eventBus;
    private IncomingSms smsReceiver ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signin, container, false);
        mobileNumber = (EditText) view.findViewById(R.id.mobile);
        String phoneNumber = getStringArgument(KEY_MOBILE, "");
        compositeSubscription = new CompositeSubscription();
        smsReceiver = new IncomingSms();
        smsReceiver.registerCallback(this);
        MyApplication.getInstance().component().inject(this);

        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
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

    protected void registerSmsReceiver() {
        smsReceiver = new IncomingSms();
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        getActivity().registerReceiver(smsReceiver, intentFilter);
    }

    protected void unregisterSmsReceiver() {
        if (smsReceiver == null) {
            return;
        }
        getActivity().unregisterReceiver(smsReceiver);
        smsReceiver = null;
    }

    @Override
    public void onSMSReceived(String data) {
        Log.d(TAG, "SMS data:" + data);
    }
}
