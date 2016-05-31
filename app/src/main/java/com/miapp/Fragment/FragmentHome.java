package com.miapp.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miapp.Activity.MyObserverable;
import com.miapp.R;
import com.miapp.Util.ViewUtils;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by lebang on 16-4-8.
 */
public class FragmentHome extends Fragment implements Observer{

    private TextView mTextView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
//        mTextView = (TextView) view.findViewById(R.id.text1);
        mTextView = $(view,R.id.text1);
        // 注册观察者
        MyObserverable.getObserverable().addObserver(this);
        return view;
    }

    @Override
    public void update(Observable observable, Object data) {
        mTextView.setText(data.toString());
    }

    public <E extends View> E $(View v,int resId){
        return ViewUtils.findViewById(v, resId);
    }
}
