package com.example.visorweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    String direccion = "www.google.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void accederWeb(View view){
        Intent i = new Intent(this, WebVisor.class);
        i.putExtra("webDir",direccion);
        startActivity(i);
    }

}