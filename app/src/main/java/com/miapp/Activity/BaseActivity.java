package com.miapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.miapp.Util.HomeWatcher;
import com.miapp.Util.ViewUtils;

import butterknife.ButterKnife;

/**
 * Created by lebang on 16-4-8.
 */
public class BaseActivity extends AppCompatActivity implements HomeWatcher.OnHomePressedListener {

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

    public <E extends View> E $(int resId){
        return ViewUtils.findViewById(this, resId);
    }
    public <E extends View> E $(View view, int resId){
        return ViewUtils.findViewById(view, resId);
    }
}
