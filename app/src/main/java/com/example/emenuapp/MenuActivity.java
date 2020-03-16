package com.example.emenuapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.viewpager2.widget.ViewPager2;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.example.emenuapp.epoxy.MenuListController;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Displays a menu to the user.
 */
public class MenuActivity extends AppCompatActivity {

    private String menuJson;
    public static final String EXTRA_MENU_DATA = "extra_menu_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Receive intents
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_MENU_DATA)) {
            menuJson = intent.getStringExtra(EXTRA_MENU_DATA);
        }

        configureActionBar();
        configureViewPager();
    }



    /**
     * Configures the action bar with a back button.
     */
    private void configureActionBar() {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        try {
            JSONObject menu = new JSONObject(menuJson);
            getSupportActionBar().setTitle(menu.getString("venue_name"));
            getSupportActionBar().setElevation(4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    /**
     * Sets up the view pager with the fragment state adapter.
     */
    private void configureViewPager() {

        ViewPager2 pager = findViewById(R.id.categoryPager);
        CategoryFragmentAdapter adapter = new CategoryFragmentAdapter(this, menuJson, pager);
        pager.setAdapter(adapter);
        adapter.initMediator();
    }



    /**
     * Enables and inflates the context menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overflow_menu, menu);

        return true;
    }



    /**
     * Handles overflow menu option clicks.
     * Translation feature not implemented as of yet.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {

            case R.id.overflow_translations:
                Toast.makeText(this, "No translations available", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * Opens the badge legend when the badge container is clicked.
     * @param view
     */
    public void onBadgeContainerClicked(View view) {
        if (((LinearLayout)view).getChildCount() > 0) {
            BadgeLegendDialog dialog = new BadgeLegendDialog();
            dialog.show(getSupportFragmentManager(), "Badge Dialog");
        }
    }
}
