package com.example.tma_pxl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class Napoveda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_napoveda);

        Intent intent_data = getIntent();
        Bundle bundle = intent_data.getExtras();
        String www_url = bundle.getString("WEB_URL");

        WebView www_opener = findViewById(R.id.WebView_blabla);

        www_opener.loadUrl(www_url);
        www_opener.getSettings().setJavaScriptEnabled(true);

    }
}