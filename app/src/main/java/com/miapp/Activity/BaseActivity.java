package com.miapp.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.miapp.Util.HomeWatcher;

import butterknife.ButterKnife;

/**
 * Created by lebang on 16-4-8.
 */
public class BaseActivity extends Activity implements HomeWatcher.OnHomePressedListener {

    HomeWatcher mHomeWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    protected String getActivityName(){
        return getClass().getSimpleName();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onResume() {
        mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(this);
        mHomeWatcher.startWatch();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHomeWatcher.setOnHomePressedListener(null);
        mHomeWatcher.stopWatch();
    }

    @Override
    public void onHomePressed() {
        Log.d("homepress","home");
    }

    @Override
    public void onSettingPressed() {

    }
}
