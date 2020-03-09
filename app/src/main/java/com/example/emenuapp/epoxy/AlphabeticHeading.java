package com.example.emenuapp.epoxy;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.epoxy.ModelProp;
import com.airbnb.epoxy.ModelView;
import com.example.emenuapp.R;

@ModelView
public class AlphabeticHeading extends LinearLayout {

    private TextView alphabetHeading;

    public AlphabeticHeading(Context context) {
        super(context);

        init();
    }

    private void init() {
        this.alphabetHeading = findViewById(R.id.alphabeticHeader);
    }

    @ModelProp
    public void setAlphabetHeading(String letter) {
        this.alphabetHeading.setText(letter);
    }
}
