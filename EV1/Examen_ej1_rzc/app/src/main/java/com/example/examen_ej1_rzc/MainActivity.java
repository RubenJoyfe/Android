package com.example.examen_ej1_rzc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText dia, mes, anno, fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dia = findViewById(R.id.ptdia);
        mes = findViewById(R.id.ptmes);
        anno = findViewById(R.id.ptanno);
        fecha = findViewById(R.id.ptFecha);

    }


    public void calcularFecha(View view){
        String day = dia.getText().toString();
        String month = mes.getText().toString();
        String year = anno.getText().toString();
        if (day.isEmpty() || month.isEmpty() || year.isEmpty() || Integer.parseInt(day)>31 || Integer.parseInt(month)>12){
            return;
        }
        if (Integer.parseInt(day)/10 < 1){
            day = "0"+day;
        }
        if(Integer.parseInt(month)/10 < 1){
            month = "0"+month;
        }

        fecha.setText(day+"/"+month+"/"+year);

        if (Integer.parseInt(year)%4==0){
            showNotification("Año Bisiesto");
        }
        else {showNotification("Año NO Bisiesto");}

    }

    public void showNotification(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}