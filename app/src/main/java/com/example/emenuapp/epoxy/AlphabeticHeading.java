package com.example.emenuapp.epoxy;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.epoxy.ModelProp;
import com.airbnb.epoxy.ModelView;
import com.example.emenuapp.R;

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
public class AlphabeticHeading extends LinearLayout {

    private TextView alphabetHeading;

    public AlphabeticHeading(Context context) {
        super(context);
        init(context);
    }

    public AlphabeticHeading(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(Context context) {
        inflate(context, R.layout.alphabetic_heading, this);
        this.alphabetHeading = findViewById(R.id.alphabeticHeader);
    }

    @ModelProp
    public void setAlphabetHeading(String letter) {
        this.alphabetHeading.setText(letter);
    }
}
