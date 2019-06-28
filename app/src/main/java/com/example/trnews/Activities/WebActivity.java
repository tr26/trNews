package com.example.trnews.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.trnews.R;

public class WebActivity extends AppCompatActivity {

    private WebView mWebView;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Bundle bundle = getIntent().getExtras();
        String toto = bundle.getString("url");

        //url = (String) intent.getExtras().get("url");


        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl(toto);
    }
}
