package com.miapp.Util;

import android.view.View;

/**
 * Created by lebang on 16/5/2.
 */
public abstract class OnClickEvent implements View.OnClickListener {

    public static long lastTime;
    public abstract void singleClick(View v);

    @Override
    public void onClick(View v) {
        if(onDoubleClick()){
            return;
        }
        singleClick(v);
    }

    public boolean onDoubleClick(){
        boolean flag = false;
        long time = System.currentTimeMillis() - lastTime;

        if (time < 500){
            flag = true;
        }
        lastTime = System.currentTimeMillis();
        return  flag;
    }
}

/*
* mClickView.setOnClickListener(new OnClickEvent() {
    @Override
    public void singleClick(View v) {

    }
  });
* */
