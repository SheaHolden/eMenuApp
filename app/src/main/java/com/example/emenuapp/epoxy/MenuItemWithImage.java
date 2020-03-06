package com.example.emenuapp.epoxy;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.epoxy.ModelView;
import com.airbnb.epoxy.TextProp;
import com.example.emenuapp.R;

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
public class MenuItemWithImage extends LinearLayout {

    private TextView itemName;
    private TextView itemDescription;
    private TextView itemPrice;

    public MenuItemWithImage(@NonNull Context context) {
        super(context);
        init();
    }
    public MenuItemWithImage(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MenuItemWithImage(@NonNull Context context, AttributeSet attrs, int defstyle) {
        super(context, attrs, defstyle);
    }


    private void init() {
        inflate(getContext(), R.layout.menu_item_1, this);
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
}
