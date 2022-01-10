package com.example.examen_ej4_rzc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText de, a, mieleccion;
    int adivinar=0, bot, top;
    boolean adivinado=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Mis inputs
        de = findViewById(R.id.menor);
        a = findViewById(R.id.mayor);
        mieleccion = findViewById(R.id.eleccion);
    }

    public void comprobar(View view){
        int menor, mayor, check;
        try {
            menor = Integer.parseInt(de.getText().toString());
            mayor = Integer.parseInt(a.getText().toString());
            if (menor>=mayor){
                showNotification("El primer numero debe ser menor que el segundo numero");
                return;
            }
        }
        catch (Exception e){
            showNotification("Introduzca un numero menor y un numero mayor");
            return;
        }

        try {
            check = Integer.parseInt(mieleccion.getText().toString());
        }
        catch (Exception e){
            showNotification("Introduzca un numero para comprobar");
            return;
        }

        if(adivinado){
            adivinar=random(mayor);
            bot=menor;
            top=mayor;
            adivinado=false;
        }

        if(check==adivinar){
            showNotification("¡NUMERO ENCONTRADO!");
            adivinado=true;
            return;
        }

        if (adivinar<check){
            top = check;
        }else{bot = check;}

        showNotification("El número está entre el "+bot+" y el "+top);


    }

    public void showNotification(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    private int random(int n) {
        return (int) Math.floor(Math.random() * n + 1);
    }

}