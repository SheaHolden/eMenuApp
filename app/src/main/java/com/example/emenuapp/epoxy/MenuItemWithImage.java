package com.example.emenuapp.epoxy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.epoxy.ModelProp;
import com.airbnb.epoxy.ModelView;
import com.airbnb.epoxy.TextProp;
import com.example.emenuapp.R;
import com.squareup.picasso.Picasso;

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
public class MenuItemWithImage extends LinearLayout {

    private TextView itemName;
    private TextView itemDescription;
    private TextView itemPrice;
    private ImageView itemImage;

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
        itemImage = findViewById(R.id.menuItemImage);
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

    @TextProp()
    public void setMenuImage(CharSequence menuImageName) {
        String url = getResources().getString(R.string.menu_server_image_dir) + menuImageName;
        Picasso.get().load(url).into(this.itemImage);
    }

    @ModelProp
    public void setHideDescription(Boolean hide) {
        if (hide.booleanValue())
            this.itemDescription.setVisibility(View.GONE);
        else
            this.itemDescription.setVisibility(View.VISIBLE);
    }
}
