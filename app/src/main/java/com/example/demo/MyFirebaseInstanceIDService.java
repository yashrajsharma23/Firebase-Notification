package com.example.demo;

import android.content.SharedPreferences;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("FCM ", "Instance token: " + refreshedToken);
        System.out.println("FCM Token :: "+ refreshedToken);

        SharedPreferences.Editor editor =
                getSharedPreferences("Pref_demo", MODE_PRIVATE).edit();
        editor.putString("token",refreshedToken);
        editor.apply();
    }

}
