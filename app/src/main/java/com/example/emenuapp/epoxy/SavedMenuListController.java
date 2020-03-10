package com.example.emenuapp.epoxy;

import com.airbnb.epoxy.TypedEpoxyController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.airbnb.epoxy.EpoxyAsyncUtil.getAsyncBackgroundHandler;

public class SavedMenuListController extends TypedEpoxyController<List<String>> {

    @Override
    protected void buildModels(List<String> data) {

        // Sort the list
        Collections.sort(data);
        Set<Character> used = new HashSet<>();

        for (int i = 0; i < data.size(); i++) {

            String item = data.get(i);
            Character firstChar = item.charAt(0);

            if (!used.contains(firstChar)) {

                new AlphabeticHeadingModel_()
                        .id(i)
                        .alphabetHeading(firstChar.toString().toUpperCase())
                        .addTo(this);

                used.add(firstChar);
            }

            new SavedMenuItemModel_()
                    .id(i)
                    .venueId("testMenu1")
                    .venueName(item)
                    .venueAddress("1234 Test road")
                    .addTo(this);
        }
    }
}
