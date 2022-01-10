package com.example.accesomysql2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;

public class MainActivity extends AppCompatActivity {
    Connection conexionMySQL = null;
    private EditText edServer, edPuerto, edUsuario, edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edServer = findViewById(R.id.edBasedatos);
        edPuerto = findViewById(R.id.edPuerto);
        edUsuario = findViewById(R.id.edName);
        edPassword = findViewById(R.id.edPass);
    }

    public void Btn_click(View view){

    }

}