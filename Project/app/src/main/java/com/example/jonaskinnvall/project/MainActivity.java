package com.example.jonaskinnvall.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout layout;
    ScrollView scrollView;

    ArrayList<Row> rowList;
    Button regButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final AccountRegistration accReg = new AccountRegistration(this);

        LinearLayout.LayoutParams regParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(regParams);

        int typeText = InputType.TYPE_CLASS_TEXT;
        int typeMail = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;


        //Create rows and then add to Arraylist rowList
        // Username
        accReg.createRow(new Row(this, "Username", typeText));

        // Name
        accReg.createRow(new Row(this, "Firstname", typeText));
        accReg.createRow(new Row(this, "Lastname", typeText));

        // Email
        accReg.createRow(new Row(this, "E-mail", typeMail));

        // Password
        final StrengthCheck passWord = new StrengthCheck(this);
        accReg.createRow(new Row(this, "Password", passWord));



        rowList = accReg.rowList;


        regButton = new Button(this);
        regButton.setText("Register User");

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean allFilled = true;
                for (int i = 0; i < rowList.size(); i++) {
                    View view = rowList.get(i);
                    if(view instanceof Row && ((Row) view).passWord == null) {
                        Row temp = (Row)view;
                        if(!temp.filled()){
                            allFilled = false;
                        }
                    }
                    else if(((Row) view).passWord instanceof StrengthCheck){
                        StrengthCheck temp = passWord;
                        if(!temp.filled())
                            allFilled = false;
                    }
                }

                if(allFilled) {
                    Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        });


        scrollView = new ScrollView(this);
        ScrollView.LayoutParams scrollParams = new ScrollView.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        scrollView.setLayoutParams(scrollParams);


        layout.addView(accReg);
        layout.addView(regButton);

        scrollView.addView(layout);

        setContentView(scrollView);
    }
}
