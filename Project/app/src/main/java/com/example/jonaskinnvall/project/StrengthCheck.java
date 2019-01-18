package com.example.jonaskinnvall.project;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StrengthCheck extends LinearLayout {

    Context context;
    EditText passWord;
    TextView strText;

    public StrengthCheck(Context cont) {
        super(cont);
        this.context = cont;

        init();
    }

    private void init() {

        this.setOrientation(VERTICAL);
        LinearLayout.LayoutParams strParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(strParams);

        passWord = new EditText(context);
        passWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        strText = new TextView(context);

        passWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String thePassword = s.toString();

                if (TextView.VISIBLE != strText.getVisibility())
                    return;

                if (thePassword.isEmpty()) {
                    strText.setText("");
                    return;
                }

                PassWordStrength str = PassWordStrength.calculateStrength(thePassword);
                strText.setText(str.getText(context));
                strText.setTextColor(str.getColor());
            }
        });

        addView(strText);
        addView(passWord);
    }

    public String getText(){
        return passWord.getText().toString();
    }
}
