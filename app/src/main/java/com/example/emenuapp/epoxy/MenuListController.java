package com.example.emenuapp.epoxy;

import com.airbnb.epoxy.TypedEpoxyController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Random;

public class MenuListController extends TypedEpoxyController<JSONObject> {

    @Override
    protected void buildModels(JSONObject category) {

        try {
            JSONArray subcategories = category.getJSONArray("subcategories");

            for (int j = 0; j < subcategories.length(); j++) {

                JSONObject subcategory = subcategories.getJSONObject(j);
                JSONArray items = subcategory.getJSONArray("menu_items");

                CharSequence subcategoryHeader = subcategory.getString("subcategory_name");
                new SubcategoryHeaderModel_()
                        .id(subcategoryHeader)
                        .subcategoryHeader(subcategoryHeader)
                        .addTo(this);

                for (int k = 0; k < items.length(); k++) {

                    JSONObject item = items.getJSONObject(k);

                    CharSequence name = item.getString("item_name");
                    CharSequence desc = item.getString("item_desc");
                    CharSequence price = item.getString("item_price");

                    Random r = new Random();

                    boolean showPicture = r.nextInt() % 2 == 0;

                    if (showPicture) {
                        new MenuItemWithImageModel_()
                                .id(name)
                                .menuItemName(name)
                                .menuItemDescription(desc)
                                .menuItemPrice("$" + price)
                                .addTo(this);
                    } else {
                        new MenuItemNoImageModel_()
                                .id(name)
                                .menuItemName(name)
                                .menuItemDescription(desc)
                                .menuItemPrice(price)
                                .addTo(this);
                    }

                }
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
}
