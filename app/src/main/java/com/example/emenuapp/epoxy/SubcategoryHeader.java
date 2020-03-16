package com.example.emenuapp.epoxy;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.airbnb.epoxy.ModelView;
import com.airbnb.epoxy.TextProp;
import com.example.emenuapp.R;

/**
 * ViewModel class for subcategory headers.
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
public class SubcategoryHeader extends LinearLayout {

    private TextView subcategoryHeader;

    public SubcategoryHeader(Context context) {
        super(context);
        init();
    }

    public SubcategoryHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SubcategoryHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        inflate(getContext(), R.layout.subcategory_header, this);
        subcategoryHeader = findViewById(R.id.subcategoryHeader);
    }

    @TextProp()
    public void setSubcategoryHeader(CharSequence subcategoryHeader) {
        this.subcategoryHeader.setText(subcategoryHeader);
    }

}
