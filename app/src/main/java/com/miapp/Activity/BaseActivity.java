package com.miapp.Activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by lebang on 16-4-8.
 */
public class BaseActivity extends Activity {

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
}
