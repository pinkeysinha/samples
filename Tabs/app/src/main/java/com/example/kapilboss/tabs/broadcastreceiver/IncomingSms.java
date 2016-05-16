package com.example.kapilboss.tabs.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kapilsharma on 13/05/16.
 */
public class IncomingSms extends BroadcastReceiver {
    private static final String TAG = IncomingSms.class.getSimpleName();
    private SMSReceiverCallback callback;

    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }
        try {
            final Object[] pdusObj = (Object[]) bundle.get("pdus");
            if (pdusObj == null) return;

            for (int i = 0; i < pdusObj.length; i++) {
                SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                String message = currentMessage.getDisplayMessageBody();
                if (wasOurMessage(phoneNumber)) {
                    message = message.replaceAll("[^0-9]+", " ");
                    List<String> otpText = Arrays.asList(message.trim().split(" "));
                    if (otpText.size() > 0) {
                        Log.d(TAG,"We got message");
                        if (!(callback instanceof SMSReceiverCallback)) {
                            throw new UnsupportedOperationException("callback Not yet implemented!! Implement SMSReceiverCallback interface ");
                        } else {
                            callback.onSMSReceived(otpText.get(0));
                        }
                        //submitOtp(otpText.get(0));
                    } else {
                        Log.e(TAG, "OTP not found in message");
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception while reading otp message.", e);
        }
    }
    public static boolean wasOurMessage(String phoneNumber) {
        return phoneNumber.toLowerCase().contains("kapil") || phoneNumber.toLowerCase().contains("kapilsharma");
    }

    public void registerCallback(SMSReceiverCallback callback) {
        this.callback = callback;
    }
}