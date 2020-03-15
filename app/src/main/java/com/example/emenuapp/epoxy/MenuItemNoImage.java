package com.example.emenuapp.epoxy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.ModelProp;
import com.airbnb.epoxy.ModelView;
import com.airbnb.epoxy.TextProp;
import com.example.emenuapp.R;

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
public class MenuItemNoImage extends LinearLayout {

    private TextView itemName;
    private TextView itemDescription;
    private TextView itemPrice;

    public MenuItemNoImage(@NonNull Context context) {
        super(context);
        init();
    }
    public MenuItemNoImage(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MenuItemNoImage(@NonNull Context context, AttributeSet attrs, int defstyle) {
        super(context, attrs, defstyle);
    }


    private void init() {
        inflate(getContext(), R.layout.menu_item_2, this);
        itemName = findViewById(R.id.menuItemName);
        itemDescription = findViewById(R.id.menuItemDescription);
        itemPrice = findViewById(R.id.menuItemPrice);
    }

    @TextProp()
    public void setMenuItemName(CharSequence menuItemName) {
        this.itemName.setText(menuItemName);
    }

    @TextProp()
    public void setMenuItemDescription(CharSequence menuItemDescription) {
        this.itemDescription.setText(menuItemDescription);
    }

    @TextProp()
    public void setMenuItemPrice(CharSequence menuItemPrice) {
        this.itemPrice.setText(menuItemPrice);
    }

    @ModelProp
    public void setHideDescription(Boolean hide) {
        if (hide.booleanValue())
            this.itemDescription.setVisibility(View.GONE);
        else
            this.itemDescription.setVisibility(View.VISIBLE);
    }
}
