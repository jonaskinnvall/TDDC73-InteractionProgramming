package com.example.jonaskinnvall.project;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Row extends LinearLayout {

    protected EditText inText;
    protected StrengthCheck passWord;
    protected int inType;

    protected boolean isFilled = false;

    LinearLayout rowLayout;


    public Row(Context context, String title, StrengthCheck password){
        super(context);

        passWord = password;

        rowLayout = new LinearLayout(context);
        rowLayout.setOrientation(VERTICAL);

        LayoutParams rowParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        TextView text = new TextView(context);
        text.setText(title);

        rowLayout.addView(text);
        rowLayout.addView(password);
        rowLayout.setLayoutParams(rowParams);
        this.addView(rowLayout);
    }

    public Row(Context context, String title, int inType){
        super(context);

        this.inType = inType;


        rowLayout = new LinearLayout(context);
        rowLayout.setOrientation(VERTICAL);

        LayoutParams rowParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        TextView text = new TextView(context);
        text.setText(title);

        inText = new EditText(context);
        if(inType == InputType.TYPE_TEXT_VARIATION_PASSWORD){
            inText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }

        inText.addTextChangedListener(watcher);

        rowLayout.addView(text);
        rowLayout.addView(inText);
        rowLayout.setLayoutParams(rowParams);
        this.addView(rowLayout);
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            String text = s.toString();

            if((inType == InputType.TYPE_CLASS_TEXT && (text.matches("[a-zA-Zåäö_0-9]+") || text.matches("[a-zA-Zåäö ]+")))
                    || (inType == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS && text.matches(".+[@].*[.].+"))){

                inText.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_green_light), android.graphics.PorterDuff.Mode.SRC_ATOP);
                isFilled = true;
            } else{
                inText.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), android.graphics.PorterDuff.Mode.SRC_ATOP);
                isFilled = false;
            }

        }
    };

    public LinearLayout getRow(){
        return this;
    }

    public boolean filled(){
        System.out.println("isFilled: " + isFilled);
        return isFilled;
    }
}
