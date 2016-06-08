package com.miapp.Activity;

import com.miapp.R;

import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by lebang on 16-6-8.
 */
public class WebviewActivity extends BaseActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        mWebView = $(R.id.main_web);
        mWebView.getSettings().setJavaScriptEnabled(true);

    }
}
