package com.example.applistintelefonico;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class Contacto {
    private String Nombre;
    private String Foto;
    private String Numero;

    public Contacto(String name, String picture, String number){
        Nombre = name;
        Foto = picture;
        Numero = number;
    }

    public String getNombre() {
        return Nombre;
    }
    public String getFoto() {
        return Foto;
    }
    public String getNumero() {
        return Numero;
    }

    public void llamada(){

    }

}
