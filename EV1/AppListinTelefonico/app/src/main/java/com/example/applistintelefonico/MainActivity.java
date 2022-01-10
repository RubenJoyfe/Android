package com.example.applistintelefonico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contacto> listaContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContactos = new ArrayList<Contacto>();
        listaContactos.add(new Contacto("Ruben", "", "666111222"));
        listaContactos.add(new Contacto("Lucas", "", "666351231"));
        listaContactos.add(new Contacto("Carlota", "", "666999666"));

        AdaptadorPersonas adaptador = new AdaptadorPersonas(this);
        ListView conts = findViewById(R.id.contacts);
        conts.setAdapter(adaptador);
        conts.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posititon, long id) {
                String TelNum = listaContactos.get(posititon).getNumero();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+TelNum));

                try {
                    startActivity(callIntent);
                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error ", Toast.LENGTH_SHORT);
                }

            }
        });
    }

    class AdaptadorPersonas extends ArrayAdapter<Contacto> {

        AppCompatActivity appCompatActivity;

        AdaptadorPersonas(AppCompatActivity context) {
            super(context, R.layout.contacto, listaContactos);
            appCompatActivity = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.contacto, null);

            TextView nombre = item.findViewById(R.id.contName);
            nombre.setText(listaContactos.get(position).getNombre());

            TextView numero = item.findViewById(R.id.contNumb);
            String num = listaContactos.get(position).getNumero();
            numero.setText(num);



            ImageView imageView1 = item.findViewById(R.id.imageView);
            if (listaContactos.get(position).getFoto()=="")
                imageView1.setImageResource(R.drawable.user);
            else
                imageView1.setImageResource(R.drawable.user);
            return(item);
        }
    }

}