package com.miapp.Service;

import com.miapp.Activity.DrawerActivity;
import com.miapp.MyAIDLService;
import com.miapp.R;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by lebang on 16-7-5.
 */
public class MyService extends Service {

    private static final String TAG = "MyService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: exectued");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: exectued");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy:  exectued");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }

    MyAIDLService.Stub mStub = new MyAIDLService.Stub() {
        @Override
        public int plus(int a, int b) throws RemoteException {
            return a+b;
        }

        @Override
        public String tuUpperCase(String str) throws RemoteException {
           if (!TextUtils.isEmpty(str)){
               return str.toUpperCase();
           } else {
               return null;
           }
        }
    };

}
