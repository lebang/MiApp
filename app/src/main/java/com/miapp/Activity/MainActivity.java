package com.miapp.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.miapp.Fragment.FragmentAbout;
import com.miapp.Fragment.FragmentHome;
import com.miapp.Fragment.FragmentList;
import com.miapp.Heart.HeartView;
import com.miapp.R;

import java.util.Observable;

public class MainActivity extends BaseActivity implements View.OnClickListener {

//    private TextView mTabHome, mTabList, mTabAbout;
//    private Button mSubmitBtn;

    HeartView mHeartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String activityName = this.getActivityName();
        mHeartView = $(R.id.surfaceView);
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
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mHeartView.reDraw();
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
//            default:
//                break;
        }

//        ft.commit();
    }

    @Override
    public void onHomePressed() {
        super.onHomePressed();
        Log.d("homepress:","main activity home press");
    }
}

