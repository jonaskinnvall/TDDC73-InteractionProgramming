package com.example.jonaskinnvall.project;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class AccountRegistration extends LinearLayout {

    Context context;
    LinearLayout regLayout;
    ScrollView scrollView;

    TextView userNameText;
    TextView firstNameText;
    TextView lastNameText;
    TextView mailText;
    TextView confMailText;
    TextView passWordText;
    TextView confPasswordText;

    EditText userName;
    EditText firstName;
    EditText lastName;
    EditText mail;
    EditText confMail;
    EditText confPassword;

    StrengthCheck passWord;

    Button regButton;
    boolean[] filled;


    public AccountRegistration(Context cont) {
        super(cont);
        this.context = cont;
        init();
    }

    private void init(){

        regLayout = new LinearLayout(context);
        regLayout.setOrientation(VERTICAL);
        LinearLayout.LayoutParams regParams = new LinearLayout.LayoutParams(
          LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        regLayout.setLayoutParams(regParams);

        scrollView = new ScrollView(context);
        ScrollView.LayoutParams scrollParams = new ScrollView.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        scrollView.setLayoutParams(scrollParams);

        filled = new boolean[5];
        Arrays.fill(filled, false);

        userNameText = new TextView(context);
        userNameText.setText("Username: ");
        userName = new EditText(context);
        userName.addTextChangedListener(watcher);

        firstNameText = new TextView(context);
        firstNameText.setText("First name: ");
        firstName = new EditText(context);
        firstName.addTextChangedListener(watcher);

        lastNameText = new TextView(context);
        lastNameText.setText("Last name: ");
        lastName = new EditText(context);
        lastName.addTextChangedListener(watcher);

        mailText = new TextView(context);
        mailText.setText("E-mail: ");
        mail = new EditText(context);
        mail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        mail.addTextChangedListener(watcher);

        confMailText = new TextView(context);
        confMailText.setText("Confirm E-mail: ");
        confMail = new EditText(context);
        confMail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        confMail.addTextChangedListener(watcher);

        passWordText = new TextView(context);
        passWordText.setText("Password: ");
        passWord = new StrengthCheck(context);

        confPasswordText = new TextView(context);
        confPasswordText.setText("Confirm password: ");
        confPassword = new EditText(context);
        confPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        confPassword.addTextChangedListener(watcher);

        regButton = new Button(context);
        regButton.setText("Register User");
        regButton.setClickable(false);
        regButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formFilled(filled)) {
                    Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(context, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        });

        regLayout.addView(userNameText);
        regLayout.addView(userName);
        regLayout.addView(firstNameText);
        regLayout.addView(firstName);
        regLayout.addView(lastNameText);
        regLayout.addView(lastName);
        regLayout.addView(mailText);
        regLayout.addView(mail);
        regLayout.addView(confMailText);
        regLayout.addView(confMail);
        regLayout.addView(passWordText);
        regLayout.addView(passWord);
        regLayout.addView(confPasswordText);
        regLayout.addView(confPassword);
        regLayout.addView(regButton);

        scrollView.addView(regLayout);
        addView(scrollView);

    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s != null && !s.toString().equalsIgnoreCase("")) {
                // Checking s.hashCode() to understand which edittext is using right now
                if (userName.getText().hashCode() == s.hashCode()) {
                    //Username
                    if(userName.getText().toString().matches("[a-zA-Zåäö_0-9]+")) {
                        filled[0] = true;
                        userName.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_green_light), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    }else {
                        filled[0] = false;
                        userName.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    }
                    userName.removeTextChangedListener(watcher);
                    userName.addTextChangedListener(watcher);
                } else if (firstName.getText().hashCode() == s.hashCode()) {
                    //First name
                    if(firstName.getText().toString().matches("[a-zA-Zåäö]+")) {
                        filled[1] = true;
                        firstName.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_green_light), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    }else {
                        filled[1] = false;
                        firstName.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    }
                    firstName.removeTextChangedListener(watcher);
                    firstName.addTextChangedListener(watcher);
                } else if (lastName.getText().hashCode() == s.hashCode()) {
                    //Last name
                    if(lastName.getText().toString().matches("[a-zA-Zåäö]+")) {
                        filled[2] = true;
                        lastName.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_green_light), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    }else {
                        filled[2] = false;
                        lastName.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    }
                    lastName.removeTextChangedListener(watcher);
                    lastName.addTextChangedListener(watcher);
                } else if (mail.getText().hashCode() == s.hashCode()) {
                    //Mail address
                    if(mail.getText().toString().matches(".+[@].*[.].+")
                            && mail.getText().toString().length() > 0) {
                        mail.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_green_light), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    }else {
                        mail.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    }
                    mail.removeTextChangedListener(watcher);
                    mail.addTextChangedListener(watcher);
                } else if (confMail.getText().hashCode() == s.hashCode()) {
                    //Confirm mail
                    if(confMail.getText().toString().equals(mail.getText().toString())) {
                        filled[3] = true;
                        confMail.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_green_light), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    } else {
                        filled[3] = false;
                        confMail.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    }
                    confMail.removeTextChangedListener(watcher);
                    confMail.addTextChangedListener(watcher);
                } else if (confPassword.getText().hashCode() == s.hashCode()) {
                    //Confirm password
                    if(confPassword.getText().toString().equals(passWord.getText())) {
                        filled[4] = true;
                        confPassword.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_green_light), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    }else {
                        filled[4] = false;
                        confPassword.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    }
                    confPassword.removeTextChangedListener(watcher);
                    confPassword.addTextChangedListener(watcher);
                }
                // Check if form is filled
                if (formFilled(filled)) {
                    regButton.setTextColor(Color.BLACK);
                    regButton.setClickable(true);
                } else {
                    regButton.setTextColor(Color.LTGRAY);
                    regButton.setClickable(false);
                }
            }
        }
    };

    public boolean formFilled(boolean[] filledList){
        for(int i = 0; i < filledList.length; i++){
            if(filledList[i] == false){
                return false;
            }
        }
        return true;
    }

}
