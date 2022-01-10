package com.example.repaso_practicar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout contenido;
    String bgOptions [] = {"white","black", "blue", "green", "wood", "sanic"};
    Spinner bgcolorspiner;
    ImageView imagenes;
    Drawable [] imgs = new Drawable[2];
    int currentFoto = imgs.length-1;
//    Button izquierda, derecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contenido = findViewById(R.id.cont);
        bgcolorspiner = findViewById(R.id.bgcPicker);
        imagenes = findViewById(R.id.imagenes);
        imgs[0]=getDrawable(R.drawable.woodbg);
        imgs[1]=getDrawable(R.drawable.sanic);
//        izquierda = findViewById(R.id.btnizq);
//        derecha = findViewById(R.id.btnder);

        spinInit(bgOptions);
        bgcolorspiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object selected = bgcolorspiner.getSelectedItem();
//                Toast.makeText(getApplicationContext(),selected.toString(), Toast.LENGTH_LONG).show();
                changebgSpinner(selected.toString());
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
//        bgcolorspiner.getSelectedItemPosition();
//        Object selected = bgcolorspiner.getSelectedItem();
//        Toast.makeText(getApplicationContext(),selected.toString(), Toast.LENGTH_LONG).show();
    }

    public void spinInit(String [] options){
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options);
        adaptador.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        bgcolorspiner.setAdapter(adaptador);
    }

    public void changebgSpinner(String bgoption){

        if (bgoption.equals("black")) {
            bgcolorspiner.setBackgroundColor(Color.WHITE);
        }
        else{
            bgcolorspiner.setBackgroundColor(Color.TRANSPARENT);
        }
        switch (bgoption){
            case "white":{
                contenido.setBackgroundColor(Color.WHITE);
                break;
            }
            case "black":{
                contenido.setBackgroundColor(Color.BLACK);
                break;
            }
            case "blue":{
                contenido.setBackgroundColor(Color.BLUE);
                break;
            }
            case "green":{
                contenido.setBackgroundColor(Color.GREEN);
                break;
            }
            case "wood":{
                contenido.setBackground(imgs[0]);
                break;
            }
            case "sanic":{
                contenido.setBackground(imgs[1]);
                break;
            }
            default:{
                Toast.makeText(getApplicationContext(), "El fondo " + bgoption + " no existe", Toast.LENGTH_LONG).show();
                break;
            }
        }
    }

    public void flDerecha(View view){
        if (currentFoto<imgs.length-1){
            currentFoto++;
            imagenes.setImageResource(R.drawable.sanic);
        }
        else {
            currentFoto = 0;
            imagenes.setImageResource(R.drawable.woodbg);
        }
    }

    public void flIzquierda(View view){
        if (currentFoto>0){
            currentFoto--;
            imagenes.setImageResource(R.drawable.woodbg);
        }
        else {
            currentFoto = imgs.length-1;
            imagenes.setImageResource(R.drawable.sanic);
        }
    }

    public void openSQL(View view){
        Intent i = new Intent(this, SQLite.class);
        startActivity(i);
    }
    public void showNotification(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

}