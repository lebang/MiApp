package com.miapp.Activity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.miapp.Adapter.TopStoryAdapter;
import com.miapp.Model.TopStory;
import com.miapp.MyAIDLService;
import com.miapp.R;
import com.miapp.Service.MyService;
import com.miapp.Util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lebang on 16-7-4.
 */
public class DrawerActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView sidemenu, mTopStoryListView;
    private LinearLayout mSidebar;
    private String[] lvs = {"List Item 01", "List Item 02", "List Item 03", "List Item 04"};
    private ArrayAdapter arrayAdapter;

    private RequestQueue mQueue;
    private String NEWS_URL = "http://news-at.zhihu.com/api/4/news/latest";
    private ArrayList<TopStory> mTopStoryArr = new ArrayList<TopStory>();
    private TopStoryAdapter mTopStoryAdapter;

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

        mQueue = Volley.newRequestQueue(DrawerActivity.this);
        getTopStoryData();
    }

    private void getTopStoryData(){
        JsonObjectRequest newsData = new JsonObjectRequest(NEWS_URL, null ,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        topStore(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DrawerActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        mQueue.add(newsData);
    }

    private void topStore(String data){
        JSONObject objData = null;
        try {
            objData = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray topData = objData.optJSONArray("top_stories");
        int len = topData.length();
        if (len < 1) return;

        for (int i = 0 ; i < len; i++) {
            if(topData.isNull(i)){ return; }
            String id = null;
            String title = null;
            String image = null;
            try {
                id = topData.getJSONObject(i).optString("id");
                title = topData.getJSONObject(i).optString("title");
                image = topData.getJSONObject(i).optString("image");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(title) && !TextUtils.isEmpty(image)){
                TopStory mItem = new TopStory(id, title, image);
                mTopStoryArr.add(mItem);
            }
        }

        mTopStoryAdapter = new TopStoryAdapter(DrawerActivity.this, mTopStoryArr);
        mTopStoryListView.setAdapter(mTopStoryAdapter);
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.topbar);
        mToolbar.setTitle("Toolbar");//设置Toolbar标题
        mToolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        mToolbar.inflateMenu(R.menu.base_toolbar_menu);//设置右上角的填充菜单

        mDrawerLayout = (DrawerLayout) findViewById(R.id.container);
        mSidebar = (LinearLayout) findViewById(R.id.sidebar);
        sidemenu = (ListView) findViewById(R.id.sidemenu);

        mTopStoryListView = (ListView) findViewById(R.id.top_stories_list);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            default:
                break;
        }
    }

}
