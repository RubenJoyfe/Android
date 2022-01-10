package com.example.multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkClaveAcceso(View view) {
        EditText campoClave = findViewById(R.id.edtxtClave);
        String inputClave = campoClave.getText().toString();

        if (inputClave.equals("abc")){
            launchInfoScr();
        }
        else {
            Toast t = Toast.makeText(getApplicationContext(),
                    "Clave no valida", Toast.LENGTH_LONG);
            t.show();
        }
        campoClave.setText("");

    }

    private void launchInfoScr(){
        Intent info = new Intent(this, information.class);
        startActivity(info);
    }

}