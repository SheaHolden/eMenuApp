package com.example.emenuapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.example.emenuapp.database.Database;
import com.example.emenuapp.database.SavedMenuEntry;
import com.example.emenuapp.epoxy.SavedMenuListController;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * This activity show a list of menus that have been saved previously. Tapping on a menu
 * loads it and displays it to the user.
 */
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

        // Configure the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Saved Menus");

        // Fetch menu entries from the database
        new FetchMenuEntriesTask(this).execute();
    }



    /**
     * Creates the recycler view that contains the saved menu entries
     * @param entries
     */
    private void buildRecycler(List<SavedMenuEntry> entries) {

        recycler = findViewById(R.id.savedMenuRecycler);
        controller = new SavedMenuListController();
        recycler.setController(controller);

        DividerItemDecoration divider = new DividerItemDecoration(recycler.getContext(), LinearLayout.VERTICAL);
        divider.setDrawable(getDrawable(R.drawable.divider));
        recycler.addItemDecoration(divider);

        controller.setData(entries);
    }



    /**
     * When an entry is clicked, feeds the menu key to the loading activity
     * @param view
     */
    public void onSavedMenuItemClick(View view) {
        String s = (String) view.findViewById(R.id.savedMenuName).getTag();

        Intent intent = new Intent();
        intent.setClass(this, LoadMenuActivity.class);
        intent.putExtra(LoadMenuActivity.EXTRA_MENU_KEY, s);
        startActivity(intent);
    }



    /**
     * Represents an asynchronous task to fetch menu entries from the room database.
     * Necessary so that loading doesn't freeze the main UI thread.
     */
    protected static class FetchMenuEntriesTask extends AsyncTask<Void, Void, List<SavedMenuEntry>> {

        WeakReference<Activity> weakActivity;

        protected FetchMenuEntriesTask(Activity activity) {
            this.weakActivity = new WeakReference<>(activity);
        }

        @Override
        protected List<SavedMenuEntry> doInBackground(Void... voids) {
            return (Database.getInstance(weakActivity.get())).savedEntryMenuDao().getAll();
         }

        /**
         * Populate the recycler view once data has been fetched from the database
         * @param entries
         */
        @Override
        protected void onPostExecute(List<SavedMenuEntry> entries) {
            super.onPostExecute(entries);
            if (!entries.isEmpty()) {
                ((BrowseMenuActivity)weakActivity.get()).buildRecycler(entries);
            } else {
                Toast.makeText(weakActivity.get(), "You have no menus saved", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(weakActivity.get(), MainActivity.class);
                weakActivity.get().startActivity(intent);
                weakActivity.get().finish();
            }
        }
    }
}
