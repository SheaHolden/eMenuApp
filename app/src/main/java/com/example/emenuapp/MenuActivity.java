package com.example.emenuapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.viewpager2.widget.ViewPager2;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.example.emenuapp.epoxy.MenuListController;

import org.json.JSONException;
import org.json.JSONObject;

public class MenuActivity extends AppCompatActivity {

    private String menuJson;
    public static final String EXTRA_MENU_DATA = "extra_menu_data";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        handleIntents();

        ViewPager2 pager = findViewById(R.id.categoryPager);
        CategoryFragmentAdapter adapter = new CategoryFragmentAdapter(this, menuJson, pager);
        pager.setAdapter(adapter);
        adapter.initMediator();

        try {
            JSONObject menu = new JSONObject(menuJson);
            getSupportActionBar().setTitle(menu.getString("venue_name"));
            getSupportActionBar().setElevation(4);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void handleIntents() {

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_MENU_DATA)) {
            menuJson = intent.getStringExtra(EXTRA_MENU_DATA);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // Go back to the home screen
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }
}
