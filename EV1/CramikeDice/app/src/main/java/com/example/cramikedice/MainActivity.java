package com.example.cramikedice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    boolean jugando = false;
    int rondas=10, rondaActual=1;
    int secuencia [], pos;
    ImageButton btncolores [];
    Drawable color [];
    ImageButton mostrarSimon;
    Button btnjugar;

    Animation anim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mostrarSimon = findViewById(R.id.bt0indicator);
        btnjugar = findViewById(R.id.btnGame);
        iniciarBotones();
        iniciarColores();


//        mostrarSimon.setBackground(color[secuencia[0]]);
    }

    public void crearSecuencia(int tam){
        secuencia = new int[tam];
        for (int i=0; i<secuencia.length;i++){
            secuencia[i]=(int) (Math.random()*4);
        }
    }
    public void iniciarColores(){
        color = new Drawable[5];
        color[0] = getDrawable(R.drawable.redstylesimon);
        color[1] = getDrawable(R.drawable.bluestylesimon);
        color[2] = getDrawable(R.drawable.greenstylesimon);
        color[3] = getDrawable(R.drawable.yellowstylesimon);
        color[4] = getDrawable(R.drawable.darkemptystylesimon);
    }
    public void iniciarBotones(){
        btncolores = new ImageButton[4];
        for (int i=0;i<btncolores.length;i++){
            int id = getResources().getIdentifier("bt"+i, "id", getPackageName());
            btncolores[i]=findViewById(id);
        }
//        btncolores[0] = findViewById(R.id.bt1);
//        btncolores[1] = findViewById(R.id.bt2);
//        btncolores[2] = findViewById(R.id.bt3);
//        btncolores[3] = findViewById(R.id.bt4);
    }

    public void simon(View view){
        Handler handler = new Handler();
        for (int i=0; i<rondaActual;i++){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mostrarSimon.setBackground(color[secuencia[pos]]);
                    pos++;
                    anim=AnimationUtils.loadAnimation(MainActivity.this, R.anim.sneaksimon);
                    mostrarSimon.setAnimation(anim);
                }
            }, 1000*i);
        }
        pos=0;
        jugando=true;
        rondaActual++;

    }

    public void jugar(View view) {
        if (!jugando){
            jugarbtn(2);
            crearSecuencia(rondas);
            Handler handler = new Handler();
            for (int i=0; i<rondaActual;i++){
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mostrarSimon.setBackground(color[secuencia[pos]]);
                        anim=AnimationUtils.loadAnimation(MainActivity.this, R.anim.sneaksimon);
                        mostrarSimon.setAnimation(anim);
                        pos++;
                    }
                }, 1000*i);
            }
            pos=0;
            jugando=true;
            rondaActual++;
        }
        else{
            jugarbtn(1);
            jugando=false;
//            rondaActual=1;
//            pos=0;
        }

    }

    private void jugarbtn(int modo){
        switch (modo){
            case 1:
            {
                btnjugar.setText("Jugar");
                break;
            }
            case 2:
            {
                btnjugar.setText("Jugando");
                break;
            }
            default:
            {
                Log.e("jugarBTNdefault:", "Solo valores 1 y 2 para activar y desactiar boton jugar");
                break;
            }
        }
    }

}