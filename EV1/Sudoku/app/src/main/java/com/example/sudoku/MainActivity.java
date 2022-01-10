package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Layout;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int x=9, y=9;
    int sudoku[][] = new int[x][y];
    TableLayout sudokuTable;
    TableRow tr[] = new TableRow[x];
    EditText pr[][] = new EditText[x][y];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sudokuTable = findViewById(R.id.sudoku_table);
        buildSudoku(x,y);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
    }

    public void buildSudoku(int x, int y){
        LayoutParams btnslp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT);
        LayoutParams trowlp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        btnslp.weight = 1;
        trowlp.weight = 1;

        for (int i=0; i<x; i++){
            tr[i] = new TableRow(this);
            tr[i].setLayoutParams(trowlp);
//            tr[i].setBackgroundColor(Color.BLUE);
            for(int j=0; j<y; j++){
                pr[i][j] = new EditText(this);
                pr[i][j].setBackgroundColor(Color.rgb(50, 168, 88));
                pr[i][j].setLayoutParams(btnslp);
                pr[i][j].setText(String.valueOf(i));
                pr[i][j].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                pr[i][j].setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                pr[i][j].setTransformationMethod(new NumericKeyBoardTransformationMethod()); //transforma de password a numerico
//                pr[i][j].setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)}); //filtro de longitud
                if (j==0){ tr[i].addView(createSpace("v", 5));}
                tr[i].addView(pr[i][j]);
//                if (i+1!=9 && (i+1)%3==0){
//                    tr[i].addView(createSpace("v", 10));
//                    pr[i][j].setBackgroundColor(Color.BLUE);
//                }
                if (j+1!=9 && (j+1)%3==0){
                    tr[i].addView(createSpace("v", 10));
                }
                else {tr[i].addView(createSpace("v", 5));}

                pr[i][j].addTextChangedListener(new numberIntro(pr[i][j]));

            }
            if (i==0){sudokuTable.addView(createSpace("h", 5));}
            else if (i%3==0){sudokuTable.addView(createSpace("h", 6));}
            sudokuTable.addView(tr[i], new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
            sudokuTable.addView(createSpace("h", 5));
        }
    }

    public Space createSpace(String orientacion, int wh){
        Space sp = new Space(this);
        LayoutParams splayout;

        if (orientacion.equals("h")){
            splayout = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,wh);
        }
        else if (orientacion.equals("v")) {
            splayout = new LayoutParams(wh, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        else if (orientacion.equals("both")) {
            splayout = new LayoutParams(wh, wh);
        }
        else{
            splayout = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        }
        sp.setLayoutParams(splayout);
        return sp;

    }

    public void checkSudoku(View view){
        int r=0, c=0;
        for (r=0;r<y;r++){
            String currentCheck = pr[r][c].getText().toString();
            for (c=1;c<x;c++){
                if (currentCheck.equals(pr[r][c].getText().toString())){
                    pr[r][c].setBackgroundColor(Color.RED);
                }
            }
            c=r;
        }
    }

    private class numberIntro implements TextWatcher{
        private EditText edte2;

        public numberIntro(EditText e) {
            edte2 = e;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void onTextChanged(CharSequence charSequence, int a, int a1, int a2) {

        }
        @Override
        public void afterTextChanged(Editable editable) {
            try {
                Toast t1 = Toast.makeText(getApplicationContext(),edte2.getText().toString(), Toast.LENGTH_LONG);
                t1.show();
            }catch (Exception e){}

        }
    }

    private class NumericKeyBoardTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return source;
        }
    }
}