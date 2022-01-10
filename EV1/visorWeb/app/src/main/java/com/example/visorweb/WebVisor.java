package com.example.visorweb;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebVisor extends AppCompatActivity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_visor);
        urlCharge();
    }

    private void urlCharge(){
        web = findViewById(R.id.webView);
        Bundle bnd = getIntent().getExtras();
        String url = bnd.getString("webDir");
        web.loadUrl("https://"+url);
    }
}