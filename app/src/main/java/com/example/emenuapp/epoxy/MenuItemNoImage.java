package com.example.emenuapp.epoxy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.airbnb.epoxy.ModelProp;
import com.airbnb.epoxy.ModelView;
import com.airbnb.epoxy.TextProp;
import com.example.emenuapp.R;

/**
 * Represents a menu item view with no image.
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
public class MenuItemNoImage extends LinearLayout {

    private TextView itemName;
    private TextView itemDescription;
    private TextView itemPrice;
    private LinearLayout badgeContainer;

    public MenuItemNoImage(@NonNull Context context) {
        super(context);
        init();
    }
    public MenuItemNoImage(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {
        inflate(getContext(), R.layout.menu_item_2, this);

        itemName = findViewById(R.id.menuItemName);
        itemDescription = findViewById(R.id.menuItemDescription);
        itemPrice = findViewById(R.id.menuItemPrice);
        badgeContainer = findViewById(R.id.badgeLayout);
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


    /**
     * Hides the description hide is true.
     * @param hide
     */
    @ModelProp
    public void setHideDescription(Boolean hide) {
        if (hide.booleanValue())
            this.itemDescription.setVisibility(View.GONE);
        else
            this.itemDescription.setVisibility(View.VISIBLE);
    }


    /**
     * Constructs a set of badges for an item and adds them to the badge container.
     * @param badges
     */
    @ModelProp
    public void setBadges(String[] badges) {

        badgeContainer.removeAllViews();
        for (String type: badges) {

            ImageView badge = buildBadge(type);
            badgeContainer.addView(badge);
        }
    }


    /**
     * Constructs a badge.
     * @param type The type of badge to construct.
     * @return
     */
    private ImageView buildBadge(String type) {

        ImageView badge = new ImageView(getContext());

        // ImageView should be 24dp x 24dp
        double density = getContext().getResources().getDisplayMetrics().density;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (24 * density), (int) (24 * density));
        badge.setLayoutParams(params);

        switch(type) {
            case "VEGETARIAN":
                badge.setImageDrawable(getContext().getDrawable(R.drawable.badge_vegetarian));
                break;
            case "GLUTEN_FREE":
                badge.setImageDrawable(getContext().getDrawable(R.drawable.badge_gluten_free));
                break;
            case "VEGAN":
                badge.setImageDrawable(getContext().getDrawable(R.drawable.badge_vegan));
                break;
            case "CHIEFS_CHOICE":
                badge.setImageDrawable(getContext().getDrawable(R.drawable.badge_chefs_choice));
                break;
        }
        return badge;
    }
}
