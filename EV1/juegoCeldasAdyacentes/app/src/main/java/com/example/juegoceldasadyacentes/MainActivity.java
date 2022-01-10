package com.example.juegoceldasadyacentes;

import static android.graphics.Color.rgb;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4;
    int est_btn1 = 0, est_btn2 = 0, est_btn3 = 0, est_btn4 = 0;
    TextView pntcTxt;
    int puntuacion = 0;
    int clrs[] = {Color.parseColor("#4085F4"), Color.parseColor("#F44640")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pntcTxt = findViewById(R.id.puntuacion);
        btnInit();
        pntcTxt.setText(String.valueOf(puntuacion));
    }

    public void btnInit(){
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
    }

    public void btnClick(View view){
        puntuacion++;
        pntcTxt.setText(String.valueOf(puntuacion));
        changeBgCl(view);
    }

    public void comprobarGanar(){
        if (est_btn1 == 1 && est_btn2 == 1 && est_btn3 == 1 && est_btn4 == 1) {
            Toast.makeText(MainActivity.this, "¡HAS GANADO!", Toast.LENGTH_LONG).show();
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    reestartGame();
                }
            }, 2000);

        }
    }

    public void reestartGame(){
        puntuacion = 0;
        est_btn1 = 0; est_btn2 = 0; est_btn3 = 0; est_btn4 = 0;
        pntcTxt.setText(String.valueOf(puntuacion));
        btn1.setBackgroundColor(clrs[est_btn1]);
        btn2.setBackgroundColor(clrs[est_btn2]);
        btn3.setBackgroundColor(clrs[est_btn3]);
        btn4.setBackgroundColor(clrs[est_btn4]);
    }

    public void changeBgCl(View view){
        String btnid = String.valueOf(view.getResources().getResourceName(view.getId()));
        char btnNum = btnid.charAt(btnid.length()-1);
//      Toast.makeText(MainActivity.this, , Toast.LENGTH_LONG).show();
        switch (btnNum){
            case '1':
            {
                if (est_btn1 == 0){est_btn1++;}
                else {est_btn1--;}
                if (est_btn2 == 0){est_btn2++;}
                else {est_btn2--;}
                if (est_btn3 == 0){est_btn3++;}
                else {est_btn3--;}
                view.setBackgroundColor(clrs[est_btn1]);
                btn2.setBackgroundColor(clrs[est_btn2]);
                btn3.setBackgroundColor(clrs[est_btn3]);
                break;
            }
            case '2':
            {
                if (est_btn1 == 0){est_btn1++;}
                else {est_btn1--;}
                if (est_btn2 == 0){est_btn2++;}
                else {est_btn2--;}
                if (est_btn4 == 0){est_btn4++;}
                else {est_btn4--;}
                view.setBackgroundColor(clrs[est_btn2]);
                btn1.setBackgroundColor(clrs[est_btn1]);
                btn4.setBackgroundColor(clrs[est_btn4]);
                break;
            }
            case '3':
            {
                if (est_btn1 == 0){est_btn1++;}
                else {est_btn1--;}
                if (est_btn4 == 0){est_btn4++;}
                else {est_btn4--;}
                if (est_btn3 == 0){est_btn3++;}
                else {est_btn3--;}
                view.setBackgroundColor(clrs[est_btn3]);
                btn1.setBackgroundColor(clrs[est_btn1]);
                btn4.setBackgroundColor(clrs[est_btn4]);
                break;
            }
            case '4':
            {
                if (est_btn4 == 0){est_btn4++;}
                else {est_btn4--;}
                if (est_btn2 == 0){est_btn2++;}
                else {est_btn2--;}
                if (est_btn3 == 0){est_btn3++;}
                else {est_btn3--;}
                view.setBackgroundColor(clrs[est_btn4]);
                btn2.setBackgroundColor(clrs[est_btn2]);
                btn3.setBackgroundColor(clrs[est_btn3]);
                break;
            }
            default:
            {
                Toast.makeText(MainActivity.this, "Something went wrong...", Toast.LENGTH_LONG).show();
                break;
            }
        }
        comprobarGanar();
    }

}