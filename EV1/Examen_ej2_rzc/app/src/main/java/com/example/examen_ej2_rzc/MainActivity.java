package com.example.examen_ej2_rzc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView cpu, yo, coincidencia;
    RadioGroup rbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cpu = findViewById(R.id.CPUans);
        yo = findViewById(R.id.TUans);
        coincidencia = findViewById(R.id.coincidencia);
        rbtn = findViewById(R.id.radioGroup);
    }

    public void elegir(View view){
        int CPUdecision = random(3);
        String myDecision = getMyChoice(getSelectedRadiobutton());
        if (getSelectedRadiobutton()==-1){
            showNotification("SELECCIONE UNA OPCION");
        }
        else {
            if (CPUdecision==2){cpu.setText("X");coincidencia.setText(coincide("X", myDecision));}
            else if (CPUdecision==3){cpu.setText("2");coincidencia.setText(coincide("2", myDecision));}
            else{cpu.setText("1");coincidencia.setText(coincide("1", myDecision));}
            yo.setText(""+myDecision);
        }

    }

    private String coincide(String cpu, String yo){

        if (yo.equals(cpu)){
            return "SI COINCIDIMOS";
        }
        return "NO COINCIDIMOS";
    }

    private String getMyChoice(int num){
        if (num==1){
            return "1";
        }
        else if (num==3){
            return "X";
        }
        else if (num==5){
            return "2";
        }
        return "-1";
    }

    private int getSelectedRadiobutton(){
        int radioButtonID = rbtn.getCheckedRadioButtonId();
        View radioButton = rbtn.findViewById(radioButtonID);
        int idx = rbtn.indexOfChild(radioButton);
        return idx;
    }

    private int random(int n) {
        return (int) Math.floor(Math.random() * n + 1);
    }

    public void showNotification(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }


}