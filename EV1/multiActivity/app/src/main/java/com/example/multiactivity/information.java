package com.example.multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class information extends AppCompatActivity {

    Spinner spin;
    Button btnAceptar;
    DisplayMetrics dspm = new DisplayMetrics();
    int maxW, maxH;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        spin = findViewById(R.id.mySpin);
        String []opts={"Salir","salir","sAlir","saLir"};
        spinInit(opts);
        btnAceptar = findViewById(R.id.aceptarSalir);
        getWindowManager().getDefaultDisplay().getMetrics(dspm);
        maxW = dspm.widthPixels;
        maxH = dspm.heightPixels;
    }

    public void mostarAceptar(View view) {
        int left, top;
        left = (int) (Math.random()*maxW);
        top = (int) (Math.random()*maxH);

        if (left==maxW){left-=btnAceptar.getLeft();}
        if (top==maxH){top-=btnAceptar.getTop();}
        btnAceptar.setVisibility(View.VISIBLE);
        btnAceptar.setX(left);
        btnAceptar.setY(top);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                btnAceptar.setVisibility(View.INVISIBLE);
            }
        },350);


    }

    public void closeInfoScr(View view){
        finish();
    }

    public void spinInit(String [] options){
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options);
        spin.setAdapter(adaptador);
    }


}