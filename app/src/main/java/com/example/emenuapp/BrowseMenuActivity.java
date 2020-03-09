package com.example.emenuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.example.emenuapp.epoxy.SavedMenuListController;

import java.util.ArrayList;
import java.util.List;

public class BrowseMenuActivity extends AppCompatActivity {

    private EpoxyRecyclerView recycler;
    private SavedMenuListController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_menu);

        init();
    }

    private void init() {

        List<String> testData = new ArrayList<>();
        testData.add("Aeroplane");
        testData.add("Aircraft Carrier");
        testData.add("Backpack");
        testData.add("Borderline");
        testData.add("Billiards");
        testData.add("Doppler");
        testData.add("Library");
        testData.add("Mosquito");

        recycler = findViewById(R.id.savedMenuRecycler);
        controller = new SavedMenuListController();
        recycler.setController(controller);
        controller.setData(testData);
    }
}
