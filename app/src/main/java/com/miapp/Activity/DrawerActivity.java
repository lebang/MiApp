package com.miapp.Activity;

import com.miapp.MyAIDLService;
import com.miapp.R;
import com.miapp.Service.MyService;
import com.miapp.Util.Util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by lebang on 16-7-4.
 */
public class DrawerActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView sidemenu;
    private LinearLayout mSidebar;
    private String[] lvs = {"List Item 01", "List Item 02", "List Item 03", "List Item 04"};
    private ArrayAdapter arrayAdapter;
    private Button mStartBtn, mStopBtn, mBindBtn;

    private MyAIDLService mMyAIDLService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMyAIDLService = MyAIDLService.Stub.asInterface(service);
            try {
                int result = mMyAIDLService.plus(3, 5);
                String upperStr = mMyAIDLService.tuUpperCase("hello world");
                Toast.makeText(DrawerActivity.this, "result:"+result, Toast.LENGTH_SHORT).show();
                Toast.makeText(DrawerActivity.this, "upperStr:"+upperStr, Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_drawer);
        initViews();

        //实现打开/关闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        //设置菜单列表
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lvs);
        sidemenu.setAdapter(arrayAdapter);
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.topbar);
        mToolbar.setTitle("Toolbar");//设置Toolbar标题
        mToolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        mToolbar.inflateMenu(R.menu.base_toolbar_menu);//设置右上角的填充菜单

        mDrawerLayout = (DrawerLayout) findViewById(R.id.container);
        mSidebar = (LinearLayout) findViewById(R.id.sidebar);
        sidemenu = (ListView) findViewById(R.id.sidemenu);

        mStartBtn = $(R.id.btn_start_service);
        mStopBtn = $(R.id.btn_stop_service);
        mBindBtn = $(R.id.btn_bind_service);
        mStartBtn.setOnClickListener(this);
        mStopBtn.setOnClickListener(this);
        mBindBtn.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(mSidebar)) {
            mDrawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
                bindService(bindIntent, mConnection, BIND_AUTO_CREATE);
                break;
            default:
                break;
        }
    }

}
