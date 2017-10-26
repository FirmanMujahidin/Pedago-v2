package com.pedago2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.pedago2.Base.BasesActivity;

import butterknife.BindView;

/**
 * Created by firma on 13-Oct-17.
 */

public class WebViewActivity extends BasesActivity {

    @BindView(R.id.webView)
    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.web_view_open);

        String url = getIntent().getStringExtra("url");
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}
