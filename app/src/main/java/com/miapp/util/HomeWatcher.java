package com.miapp.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by lebang on 16-4-12.
 */
public class HomeWatcher {
    static final String TAG = "HomeWatcher";
    private Context mComtext;
    private IntentFilter mFilter;
    private OnHomePressedListener mListener;
    private InnerRecevier mRecevier;

    public interface OnHomePressedListener{
        public void onHomePressed();
        public void onHomeLongPressed();
    }

    public HomeWatcher(Context context){
        mComtext = context;
        mRecevier = new InnerRecevier();
        mFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
    }

    public void setOnHomePressedListener(OnHomePressedListener listener){
        mListener = listener;
    }

    public void startWatch(){
        if(mRecevier != null){
            mComtext.registerReceiver(mRecevier, mFilter);
        }
    }

    public void stopWatch(){
        if(mRecevier != null){
            mComtext.unregisterReceiver(mRecevier);
        }
    }

    public class InnerRecevier extends BroadcastReceiver {
        final String SYSTEM_DIALOG_REASON_KEY = "reason";
        final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
        final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)){
                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                if(reason != null){
                    if (mListener != null){
                        if(reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)){
                            mListener.onHomePressed();
                        }else if(reason.equals(SYSTEM_DIALOG_REASON_RECENT_APPS)){
                            mListener.onHomeLongPressed();
                        }
                    }
                }
            }
        }
    }
}
