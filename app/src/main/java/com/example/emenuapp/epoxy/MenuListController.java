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
            if (category.has("subcategories")) {
                JSONArray subcategories = category.getJSONArray("subcategories");

                for (int i = 0; i < subcategories.length(); i++) {

                    JSONObject subcategory = subcategories.getJSONObject(i);
                    String subcategoryName = subcategory.getString("subcategory_name");
                    new SubcategoryHeaderModel_()
                            .id(subcategoryName)
                            .subcategoryHeader(subcategoryName)
                            .addTo(this);

                    JSONArray items = subcategory.getJSONArray("items");
                    buildItemList(items);
                }

            } else {
                JSONArray items = category.getJSONArray("items");
                buildItemList(items);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void buildItemList(JSONArray items) throws JSONException {

        for (int i = 0; i < items.length(); i++) {

            JSONObject item = items.getJSONObject(i);

            boolean hasDescription = item.has("item_desc");
            boolean hasImage = item.has("item_image");

            buildItem(item, hasDescription, hasImage);
        }
    }

    public void buildItem(JSONObject item, boolean hasDescription, boolean hasImage) throws JSONException{

        CharSequence name = item.getString("item_name");
        CharSequence price = item.getString("item_price");

        String badgeTypes[];
        if (item.has("item_badges")) {
            JSONArray badges = item.getJSONArray("item_badges");

            badgeTypes = new String[badges.length()];
            for (int i = 0; i < badges.length(); i++) {
                badgeTypes[i] = badges.getString(i);
            }
        } else {
            badgeTypes = new String[] {};
        }

        if (hasImage && hasDescription) {

            CharSequence imageName = item.getString("item_image");
            CharSequence description = item.getString("item_desc");

            new MenuItemWithImageModel_()
                    .id(name)
                    .menuItemName(name)
                    .menuItemDescription(description)
                    .hideDescription(false)
                    .menuItemPrice(price)
                    .menuImage(imageName)
                    .badges(badgeTypes)
                    .addTo(this);
        }
        else if (!hasImage && hasDescription) {

            CharSequence description = item.getString("item_desc");

            new MenuItemNoImageModel_()
                    .id(name)
                    .menuItemName(name)
                    .menuItemDescription(description)
                    .menuItemPrice(price)
                    .hideDescription(false)
                    .badges(badgeTypes)
                    .addTo(this);
        }
        else if (hasImage && !hasDescription) {

            CharSequence imageName = item.getString("item_image");

            new MenuItemWithImageModel_()
                    .id(name)
                    .menuItemName(name)
                    .menuItemPrice(price)
                    .menuItemDescription("")
                    .hideDescription(true)
                    .menuImage(imageName)
                    .badges(badgeTypes)
                    .addTo(this);
        }
        else if (!hasImage && !hasDescription) {

            new MenuItemNoImageModel_()
                    .id(name)
                    .menuItemName(name)
                    .menuItemDescription("")
                    .hideDescription(true)
                    .menuItemPrice(price)
                    .badges(badgeTypes)
                    .addTo(this);
        }
    }
}
