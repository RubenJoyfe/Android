package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LinearLayout colores_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        colores_layout = findViewById(R.id.colores);
        getColores();
    }

    private void getColores(){
        int tama = colores_layout.getChildCount();
        int cont=0;
        View childrens [] = new View[8];
        for(int index = 0; index < (tama); index++) {
            if (colores_layout.getChildAt(index) instanceof ImageView) {
                childrens[cont] = colores_layout.getChildAt(index);
                cont++;
            }
        }
//        childrens[0].setBackground(getDrawable(R.drawable.a_yellowpc));
        Toast.makeText(getApplicationContext(), "a", Toast.LENGTH_LONG).show();
    }

}