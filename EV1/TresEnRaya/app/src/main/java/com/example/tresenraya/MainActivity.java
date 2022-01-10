package com.example.tresenraya;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer sounds[];
    TableRow r1, r2, r3;
    ImageView turnoIndicator;
    ImageButton[][] listaiBotones;
    Drawable[] dr = new Drawable[4];
    TextView titulo, turnotxt;
    int turno = 0, actualColor = 0;
    boolean jugando=true, modoDiablo=false;
    ConstraintLayout body;

    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        body = findViewById(R.id.body);
            anim=AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
            body.setAnimation(anim);
        titulo = findViewById(R.id.title);
        turnotxt = findViewById(R.id.turnotxt);
        turnoIndicator = findViewById(R.id.turno);
        r1 = findViewById(R.id.row1);
        r2 = findViewById(R.id.row2);
        r3 = findViewById(R.id.row3);
        iniciarBotones();
        sounds  = new MediaPlayer[2];
        sounds[0] = MediaPlayer.create(this, R.raw.basketbc1);
        sounds[1] = MediaPlayer.create(this, R.raw.errorxp);
        click();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void iniciarBotones(){
        listaiBotones = new ImageButton[3][3];
        dr[0] = getDrawable(R.drawable.carastyle);
        dr[1] = getDrawable(R.drawable.cruzstyle);
        dr[2] = getDrawable(R.drawable.emptystyle);
        dr[3] = getDrawable(R.drawable.darkemptystyle);
//        for (int b = 0; b< listaiBotones.length; b++){
//            for (int t = 0; t< listaiBotones.length; t++){
////                listaiBotones[b][t];
//            }
//        }
        listaiBotones[0][0] = findViewById(R.id.bt1);
        listaiBotones[0][1] = findViewById(R.id.bt2);
        listaiBotones[0][2] = findViewById(R.id.bt3);
        listaiBotones[1][0] = findViewById(R.id.bt4);
        listaiBotones[1][1] = findViewById(R.id.bt5);
        listaiBotones[1][2] = findViewById(R.id.bt6);
        listaiBotones[2][0] = findViewById(R.id.bt7);
        listaiBotones[2][1] = findViewById(R.id.bt8);
        listaiBotones[2][2] = findViewById(R.id.bt9);
        for (int b = 0; b < listaiBotones.length; b++) {
            for (int t = 0; t < listaiBotones.length; t++) {
                listaiBotones[b][t].clearAnimation();
                listaiBotones[b][t].setBackground(dr[actualColor+2]);
            }
        }
        if (actualColor==1){ darkMode();}else{lightMode();};
    }


    private void click(){
        for (int b = 0; b< listaiBotones.length; b++) {
            for (int t = 0; t < listaiBotones.length; t++) {
                listaiBotones[b][t].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (jugando) {
                            if (!modoDiablo && v.getBackground().toString().equals(dr[actualColor+2].toString())){ //Si la casilla no esta ocupada por una ficha
                                anim=AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoomin);
                                reproducir(sounds[0]);
                                v.setAnimation(anim);
                                v.setBackground(dr[turno]);
                                if (!comprobarGanador()) {
                                    turno = (turno == 0) ? 1 : 0;
                                    turnoIndicator.setBackground(dr[turno]);
                                } else {
                                    Toast ganador = Toast.makeText(getApplicationContext(), "Ganador: Jugador " + String.valueOf(turno + 1), Toast.LENGTH_LONG);
                                    ganador.show();
                                }
                            }
                        }
                        else {
                            reproducir(sounds[1]);
//                            Toast resetear = Toast.makeText(getApplicationContext(), "Borra el tablero para volver a jugar.", Toast.LENGTH_LONG);
//                            resetear.show();
                        }
                    }
                });
            }
        }

    }

    public void reproducir(MediaPlayer sound) {
        sound.start();
    }

    public void borrarTablero(View view) {
        jugando=true;
        turno=0;
        turnoIndicator.setBackground(dr[turno]);
        for (int b = 0; b < listaiBotones.length; b++) {
            for (int t = 0; t < listaiBotones.length; t++) {
                listaiBotones[b][t].clearAnimation();
                listaiBotones[b][t].setBackground(dr[actualColor+2]);
            }
        }
    }

    public boolean comprobarGanador(){
        int fila=0, col=0, diag=0, diagInv=0;
        for (int b = 0; b < listaiBotones.length; b++) {
            if (listaiBotones[b][b].getBackground().toString().equals(dr[turno].toString())){diag++;}else{diag=0;}
            if (listaiBotones[b][listaiBotones.length-(1+b)].getBackground().toString().equals(dr[turno].toString())){diagInv++;}else{diagInv=0;}
            for (int t = 0; t < listaiBotones.length; t++) {
                if (listaiBotones[t][b].getBackground().toString().equals(dr[turno].toString())){col++;}else{col=0;}
                if (listaiBotones[b][t].getBackground().toString().equals(dr[turno].toString())){fila++;}else{fila=0;}
            }
            if (fila==3 || col==3 || diag==3 || diagInv==3) {jugando=false; return true;}
        }
        return false;
    }

    public void cambiarTema(View v) {
        if (actualColor==0) {
            darkMode();
            v.setBackgroundColor(Color.parseColor("#ffffff"));
            actualColor++;
        }
        else {
            lightMode();
            v.setBackgroundColor(Color.parseColor("#000000"));
            actualColor--;
        }

    }

    public void darkMode(){
        body.setBackgroundColor(Color.parseColor("#1f2b38"));
        estiloCasillas(dr[3]);
        titulo.setTextColor(Color.parseColor("#445c75"));
        turnotxt.setTextColor(Color.parseColor("#445c75"));
    }
    public void lightMode(){
        body.setBackgroundColor(Color.parseColor("#ededed"));
        estiloCasillas(dr[2]);
        titulo.setTextColor(Color.parseColor("#3d3d3d"));
        turnotxt.setTextColor(Color.parseColor("#3d3d3d"));
    }

    public void estiloCasillas(Drawable estilo){
        for (int b = 0; b< listaiBotones.length; b++){
            for (int t = 0; t< listaiBotones.length; t++){
                if (listaiBotones[b][t].getBackground().toString().equals(dr[actualColor+2].toString())) {
                    listaiBotones[b][t].setBackground(estilo);
                }
            }
        }
    }

}