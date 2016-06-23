package com.miapp.Activity;

import com.miapp.R;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by lebang on 16-6-14.
 */
public class GuideActivity extends BaseActivity implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {
    private ViewPager mViewPager;
    private MediaPlayer mPlayer;

    private ArrayList<View> mViews;
    private View view1, view2, view3;
    private SurfaceHolder holder1, holder2, holder3;
    private SurfaceView sf1, sf2, sf3;

    // 目录
    private String Path = Environment.getExternalStorageDirectory().getPath()
            + "/1.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        // 初始化控件
        initView();

        // 获取SurfaceHolder实例
        holder1 = sf1.getHolder();
        holder2 = sf2.getHolder();
        holder3 = sf3.getHolder();
    }

    private void initView() {
        mViewPager = $(R.id.guide_viewpager);
        mViews = new ArrayList<View>();

        view1 = View.inflate(this, R.layout.guide_view1, null);
        view2 = View.inflate(this, R.layout.guide_view2, null);
        view3 = View.inflate(this, R.layout.guide_view3, null);

        sf1 = $(view1, R.id.sf1);
        sf2 = $(view2, R.id.sf2);
        sf3 = $(view3, R.id.sf3);

        mViews.add(view1);
        mViews.add(view2);
        mViews.add(view3);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mPlayer = new MediaPlayer();
        mPlayer.setDisplay(holder);
        mPlayer.setOnPreparedListener(this);
        mPlayer.setLooping(true);

        if (holder1.equals(holder)) {
            Path = Environment.getExternalStorageDirectory().getPath() + "/1.mp4";
        }
        if (holder2.equals(holder)) {
            Path = Environment.getExternalStorageDirectory().getPath() + "/2.mp4";
        }
        if (holder3.equals(holder)) {
            Path = Environment.getExternalStorageDirectory().getPath() + "/3.mp4";
        }

        try {
            mPlayer.setDataSource(Path);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 准备播放
        try {
            mPlayer.prepareAsync();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        // 开始播放
        mp.start();
    }
}
