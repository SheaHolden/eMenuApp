package com.example.emenuapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

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

        DividerItemDecoration divider = new DividerItemDecoration(recycler.getContext(), LinearLayout.VERTICAL);
        divider.setDrawable(getDrawable(R.drawable.divider));
        recycler.addItemDecoration(divider);

        controller.setData(testData);
    }

    public void onSavedMenuItemClick(View view) {
        String s = (String) view.findViewById(R.id.savedMenuName).getTag();
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
