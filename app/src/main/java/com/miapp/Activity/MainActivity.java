package com.miapp.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.miapp.Heart.HeartView;
import com.miapp.Model.TopStory;
import com.miapp.MyAIDLService;
import com.miapp.R;
import com.miapp.Service.MyService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {

//    private TextView mTabHome, mTabList, mTabAbout;
//    private Button mSubmitBtn;

    HeartView mHeartView;

    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    private Button mStartBtn, mStopBtn, mBindBtn;

    private MyAIDLService mMyAIDLService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMyAIDLService = MyAIDLService.Stub.asInterface(service);
            try {
                int result = mMyAIDLService.plus(3, 5);
                String upperStr = mMyAIDLService.tuUpperCase("hello world");
                Toast.makeText(MainActivity.this, "result:"+result, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "upperStr:"+upperStr, Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String activityName = this.getActivityName();
//        mHeartView = $(R.id.surfaceView);
//        Toast.makeText(this,activityName,Toast.LENGTH_SHORT).show();

//        mTabHome = (TextView) findViewById(R.id.tab_home);
//        mTabList = (TextView) findViewById(R.id.tab_list);
//        mTabAbout = (TextView) findViewById(R.id.tab_about);
//
//        mTabHome.setOnClickListener(this);
//        mTabList.setOnClickListener(this);
//        mTabAbout.setOnClickListener(this);

//        mSubmitBtn = $(R.id.button);
//        mSubmitBtn.setOnClickListener(this);


//        mReactRootView = new ReactRootView(this);
//        mReactInstanceManager = ReactInstanceManager.builder()
//                .setApplication(getApplication())
//                .setBundleAssetName("index.android.bundle")
//                .setJSMainModuleName("index.android")
//                .addPackage(new MainReactPackage())
//                .setUseDeveloperSupport(BuildConfig.DEBUG)
//                .setInitialLifecycleState(LifecycleState.RESUMED)
//                .build();
//        mReactRootView.startReactApplication(mReactInstanceManager, "MyAwesomeApp", null);
//        setContentView(mReactRootView);

        mStartBtn = $(R.id.btn_start_service);
        mStopBtn = $(R.id.btn_stop_service);
        mBindBtn = $(R.id.btn_bind_service);
        mStartBtn.setOnClickListener(this);
        mStopBtn.setOnClickListener(this);
        mBindBtn.setOnClickListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        mHeartView.reDraw();
        return super.onTouchEvent(event);
    }

    public void reDraw(View v) {
        mHeartView.reDraw();
    }

    @Override
    public void onClick(View v) {

//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();

        switch (v.getId()){
//            case R.id.tab_home:
//                ft.replace(R.id.main_wrapper, new FragmentHome());
//                break;
//            case R.id.tab_list:
//                ft.replace(R.id.main_wrapper, new FragmentList());
//                break;
//            case R.id.tab_about:
//                ft.replace(R.id.main_wrapper, new FragmentAbout());
//                break;
//            case R.id.button:
//                MyObserverable.getObserverable().setMessage("aa");
//                break;

                case R.id.btn_start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.btn_stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.btn_bind_service:
                Intent bindIntent = new Intent("com.miapp.MyAIDLService");
                bindIntent.setPackage("com.miapp");//显式intent
//                final Intent eintent = new Intent(Util.getExplicitIntent(this,bindIntent));
//                bindService(bindIntent, mConnection, BIND_AUTO_CREATE);
                break;
            default:
                break;
        }

//        ft.commit();
    }

    @Override
    public void onHomePressed() {
        super.onHomePressed();
        Log.d("homepress:","main activity home press");
    }
}

