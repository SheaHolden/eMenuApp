package com.example.emenuapp.epoxy;

import com.airbnb.epoxy.TypedEpoxyController;
import com.example.emenuapp.database.SavedMenuEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.airbnb.epoxy.EpoxyAsyncUtil.getAsyncBackgroundHandler;

public class SavedMenuListController extends TypedEpoxyController<List<SavedMenuEntry>> {

    @Override
    protected void buildModels(List<SavedMenuEntry> data) {

        // Sort the list
        Collections.sort(data, (o1, o2) -> o1.venueName.compareTo(o2.venueName));
        Set<Character> used = new HashSet<>();

        for (int i = 0; i < data.size(); i++) {

            SavedMenuEntry entry = data.get(i);
            Character firstChar = entry.venueName.charAt(0);

            if (!used.contains(firstChar)) {

                new AlphabeticHeadingModel_()
                        .id(i)
                        .alphabetHeading(firstChar.toString().toUpperCase())
                        .addTo(this);

                used.add(firstChar);
            }

            new SavedMenuItemModel_()
                    .id(entry.id)
                    .venueId(entry.venueId)
                    .venueName(entry.venueName)
                    .venueAddress(entry.venueAddr)
                    .addTo(this);
        }
    }
}
