package com.example.emenuapp.epoxy;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.epoxy.ModelProp;
import com.airbnb.epoxy.ModelView;
import com.example.emenuapp.R;

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
public class SavedMenuItem extends LinearLayout {

    private TextView venueName;
    private TextView venueAddress;

    public SavedMenuItem(Context context) {
        super(context);
        init(context);
    }

    public SavedMenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(Context context) {

        inflate(context, R.layout.saved_menu_item, this);
        venueName = findViewById(R.id.savedMenuName);
        venueAddress = findViewById(R.id.savedMenuAddress);
    }

    @ModelProp
    public void setVenueName(String venueName) {
        this.venueName.setText(venueName);
    }

    @ModelProp
    public void setVenueAddress(String venueAddress) {
        this.venueAddress.setText(venueAddress);
    }
}
