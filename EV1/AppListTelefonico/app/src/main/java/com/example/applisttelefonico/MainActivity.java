package com.example.applisttelefonico;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    LinearLayout contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactList = findViewById(R.id.listConstacto);

        contactList.addView(cosas("Ruben", "+34 640394912"));
    }

    public LinearLayout cosas(String name, String number){
        String myname = "Ruben";
        String mynum = "+34 602254869";

        LinearLayout liLaContact = new LinearLayout(this);
        liLaContact.setBackgroundResource(R.drawable.customborder);
        liLaContact.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams layout_608 = new LayoutParams();
        layout_608.width = LayoutParams.MATCH_PARENT;
        layout_608.height = LayoutParams.WRAP_CONTENT;
        liLaContact.setLayoutParams(layout_608);

        Space spaceCt = new Space(this);
        LayoutParams spaceLayP = new LayoutParams();
        spaceLayP.width = 5;
        spaceLayP.height = LayoutParams.WRAP_CONTENT;
        spaceCt.setLayoutParams(spaceLayP);
        liLaContact.addView(spaceCt);

        ImageView contactImage = new ImageView(this);
        contactImage.setPadding(0,5,0,5);
        contactImage.setImageResource(R.drawable.user);
        LayoutParams imgLayP = new LayoutParams();
        imgLayP.width = 75;
        imgLayP.height = 75;
        contactImage.setLayoutParams(imgLayP);
        liLaContact.addView(contactImage);

        LinearLayout contactInfo = new LinearLayout(this);
        contactInfo.setOrientation(LinearLayout.VERTICAL);
        LayoutParams crcLayP = new LayoutParams();
        crcLayP.width = LayoutParams.WRAP_CONTENT;
        crcLayP.height = LayoutParams.MATCH_PARENT;
        crcLayP.leftMargin = 5;
        contactInfo.setLayoutParams(crcLayP);

        TextView contName = new TextView(this);
        contName.setPadding(0,5,0,5);
        contName.setText(myname);
        contName.setTextSize((20/getApplicationContext().getResources().getDisplayMetrics().density));
        LayoutParams nameLayP = new LayoutParams();
        nameLayP.width = LayoutParams.WRAP_CONTENT;
        nameLayP.height = LayoutParams.WRAP_CONTENT;
        contName.setLayoutParams(nameLayP);
        contactInfo.addView(contName);

        TextView contNumb = new TextView(this);
        contNumb.setText(mynum);
        LayoutParams layout_667 = new LayoutParams();
        layout_667.width = LayoutParams.WRAP_CONTENT;
        layout_667.height = LayoutParams.WRAP_CONTENT;
        layout_667.weight = 1;
        contNumb.setLayoutParams(layout_667);
        contactInfo.addView(contNumb);
        liLaContact.addView(contactInfo);

        return liLaContact;
    }

}

