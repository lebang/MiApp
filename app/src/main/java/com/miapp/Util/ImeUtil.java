package com.miapp.Util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by lebang on 16/5/2.
 */
public class ImeUtil {
    /*
    * 显示和隐藏软键盘
    * view : EditText/TextView
    * isShow : true / false
    * */

    public static void popSoftKeyboard(Context context, View view, boolean isShow){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isShow){
            view.requestFocus();
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }else{
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    /*
    * 显示软键盘
    * */
    public static void showSoftKeyboard(View view){
        Context context = view.getContext();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        view.requestFocus();
        imm.showSoftInput(view, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /*
    * 隐藏软键盘
    * */
    public static void hideSoftKeyboard(View view){
        Context context = view.getContext();
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

}
