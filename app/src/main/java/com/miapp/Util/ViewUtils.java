package com.miapp.Util;

import android.app.Activity;
import android.view.View;

/**
 * Created by lebang on 16-5-12.
 */
public final class ViewUtils {

    public static <E extends View> E findViewById (Activity activity, int resId){
        return (E) activity.findViewById(resId);
    }

    public static <E extends View> E findViewById (View view, int resId){
        return (E) view.findViewById(resId);
    }
}
