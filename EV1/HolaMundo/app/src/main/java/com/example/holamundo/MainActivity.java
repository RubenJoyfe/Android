package com.example.holamundo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
//import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText op1, op2;
    private TextView res;
    private RadioGroup operaciones;
    private float resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res = findViewById(R.id.textView);
        op1 = findViewById(R.id.Et1);
        op2 = findViewById(R.id.Et2);

        operaciones = findViewById(R.id.operaciones);
        //res.setText(String.valueOf(idx));

    }

    public void borrar(View view){
        op1.setText("");
        op2.setText("");
    }

    public void answ(View view){
        op1.setText(String.valueOf(resultado));
    }

    public void calcular(View view){
       try {
           View radioButton = operaciones.findViewById(operaciones.getCheckedRadioButtonId());
           int idx = operaciones.indexOfChild(radioButton);

           resultado=0;
           switch (idx){
               case 0:
               {
                   resultado = suma(Float.parseFloat(String.valueOf(op1.getText())), Float.parseFloat(String.valueOf(op2.getText())));
                   break;
               }
               case 1:
               {
                   resultado = resta(Float.parseFloat(String.valueOf(op1.getText())), Float.parseFloat(String.valueOf(op2.getText())));
                   break;
               }
               case 2:
               {
                   resultado = multiplicacion(Float.parseFloat(String.valueOf(op1.getText())), Float.parseFloat(String.valueOf(op2.getText())));
                   break;
               }
               case 3:
               {
                   resultado = division(Float.parseFloat(String.valueOf(op1.getText())), Float.parseFloat(String.valueOf(op2.getText())));
                   break;
               }
           }

           res.setText(String.valueOf(resultado));
       }
       catch(Exception error1){
            res.setText("Incorrect Input");
        }
    }

    public float suma(float a, float b){
        return (a+b);
    }
    public float resta(float a, float b){
        return (a-b);
    }
    public float multiplicacion(float a, float b){
        return (a*b);
    }
    public float division(float a, float b){ return (a/b);}
}

