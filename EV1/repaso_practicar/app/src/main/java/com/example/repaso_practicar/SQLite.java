package com.example.repaso_practicar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.*;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SQLite extends AppCompatActivity {

    EditText edid, usr;

    SQLiteInit mySql = new SQLiteInit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        edid = findViewById(R.id.etId);
        usr = findViewById(R.id.etUsr);
        mySql.crear(this,"");
//        mySql.insertar("Macaco");
//        mySql.select(1);
    }

    public void BuscarUsuario(View view){
        String [] resultado;
        try {
            resultado = mySql.select(Integer.parseInt(edid.getText().toString()));
            edid.setText(resultado[0]);
            usr.setText(resultado[1]);
        }
        catch (Exception e){
            showNotification(e.getMessage().toString());
            showNotification("Introduce valores numéricos en la id");
        }

    }

    public void InsertarUsuario(View view){
        try {
            mySql.insertar(usr.getText().toString());
            usr.setText("");
        }
        catch (Exception e){
            showNotification(e.getMessage().toString());
            showNotification("ERROR");
        }
    }

    public void ActualizarUsuario(View view){
        try {
            int idChg = Integer.parseInt(edid.getText().toString());
            String newName = usr.getText().toString();
            if (!newName.isEmpty()){
                mySql.update(idChg,newName);
                return;
            }
            showNotification("El nombre no puede estar en blanco");
        }
        catch (Exception e){
            showNotification(e.getMessage().toString());
            showNotification("Introduce un valor numérico");
        }
    }

    public void closeW(View view){
        finish();
    }

    public void showNotification(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    public class SQLiteInit{
        SQLiteOpenHelper sqlLite;
        String finalNameTable;
        SQLiteDatabase bd;


        public SQLiteInit(){

        }

        public void crear(Context ctx, String nameTable) {
            nameTable = (nameTable == "") ? "usuarios" : nameTable;
            finalNameTable= nameTable;
            sqlLite = new SQLiteOpenHelper(ctx, finalNameTable, null, 1) {
                @Override
                public void onCreate(SQLiteDatabase sqLiteDatabase) {
                    sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + finalNameTable + " (id_usuario int primary key, nombre VARCHAR(30));");
                }

                @Override
                public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

                }
            };
            bd = sqlLite.getWritableDatabase();
        }

        public void insertar(String nom){
            // INSERT
            if (!nom.isEmpty()) {
                ContentValues registro = new ContentValues();
                registro.put("id_usuario", getNewId());
                registro.put("nombre", nom);
                int sw = (int) bd.insert(finalNameTable, null, registro);
                if (sw == 1) {
                    // todo OK
                }
            }
            else {
                showNotification("No puedes insertar un nombre vacio");
            }

        }

        public void update(int id, String newName){
            // UPDATE
            if (!newName.isEmpty()){
                ContentValues registro = new ContentValues();
                registro.put("nombre", newName);
                int sw = bd.update(finalNameTable, registro, "id_usuario="+id,null);
                if (sw == 1) {
                    // todo OK
                }
            }
            else {
                showNotification("No puedes dejar un nombre vacio");
            }
        }

        public void delete(int id){
            // DELETE
            int sw = bd.delete(finalNameTable, "id_usuario="+id, null);
            if (sw == 1) {
                // todo OK
            }
        }

        public int getNewId(){
            try {
                Cursor cursor = bd.rawQuery("SELECT MAX(id_usuario) FROM " + finalNameTable +";", null);
                if (cursor.moveToNext()) {
                    while (!cursor.isAfterLast()) {
                        return Integer.parseInt(cursor.getString(0))+1;
                    }
                }
                return 1;
            }
            catch (Exception e){
                showNotification(e.getMessage().toString());
            }
            return 1;
        }

        public String[] select(int id){
            String [] resultado = new String[2];
            resultado[0]="Not found";
            resultado[1]="Not found";
            // SELECT
            Cursor cursor = bd.rawQuery("SELECT * FROM " + finalNameTable + " WHERE id_usuario="+id, null);
            if (cursor.moveToNext()) {
                while (!cursor.isAfterLast()) {
//                    Toast.makeText(getApplicationContext(), cursor.getString(0), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), cursor.getString(1), Toast.LENGTH_SHORT).show();
                    resultado[0]=cursor.getString(0);
                    resultado[1]=cursor.getString(1);
                    cursor.moveToNext();
                }
            }
            //bd.close();
            return resultado;
        }
        public void showNotification(String text) {
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        }

    }

}

