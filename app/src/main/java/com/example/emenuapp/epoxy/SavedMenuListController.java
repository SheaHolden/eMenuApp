package com.example.emenuapp.epoxy;

import com.airbnb.epoxy.TypedEpoxyController;

import java.util.List;

public class SavedMenuListController extends TypedEpoxyController<List<String>> {

    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";


    @Override
    protected void buildModels(List<String> data) {

        int i = 0;
        while (i < data.size()) {

            Character current = alphabet.charAt(i);
            String str = data.get(i);

            // Add alphabetic header
            new AlphabeticHeadingModel_()
                    .id(i)
                    .alphabetHeading(current.toString())
                    .addTo(this);

            while (str.charAt(0) == current) {
            }
        }
    }
}
