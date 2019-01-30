package com.example.jonaskinnvall.project;

import android.content.Context;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class AccountRegistration extends LinearLayout {

    Context context;

    ArrayList<Row> rowList;

    public AccountRegistration(final Context context) {
        super(context);
        this.context = context;

        setOrientation(VERTICAL);
        LinearLayout.LayoutParams regParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(regParams);

        rowList = new ArrayList<>();
    }

    //Creating new row
    public void createRow(Row row){

        rowList.add(row);

        addView(row.getRow());

    }
}
