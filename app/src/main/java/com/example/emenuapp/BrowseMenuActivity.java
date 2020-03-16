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

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.example.emenuapp.database.Database;
import com.example.emenuapp.database.SavedMenuEntry;
import com.example.emenuapp.epoxy.SavedMenuListController;

import java.lang.ref.WeakReference;
import java.util.List;

public class BrowseMenuActivity extends AppCompatActivity {

    private EpoxyRecyclerView recycler;
    private SavedMenuListController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Saved Menus");

        new FetchMenuEntriesTask(this).execute();
    }

    private void buildRecycler(List<SavedMenuEntry> entries) {

        recycler = findViewById(R.id.savedMenuRecycler);
        controller = new SavedMenuListController();
        recycler.setController(controller);

        DividerItemDecoration divider = new DividerItemDecoration(recycler.getContext(), LinearLayout.VERTICAL);
        divider.setDrawable(getDrawable(R.drawable.divider));
        recycler.addItemDecoration(divider);

        controller.setData(entries);
    }

    public void onSavedMenuItemClick(View view) {
        String s = (String) view.findViewById(R.id.savedMenuName).getTag();

        Intent intent = new Intent();
        intent.setClass(this, LoadMenuActivity.class);
        intent.putExtra(LoadMenuActivity.EXTRA_MENU_KEY, s);
        startActivity(intent);
    }

    protected static class FetchMenuEntriesTask extends AsyncTask<Void, Void, List<SavedMenuEntry>> {

        WeakReference<Activity> weakActivity;

        protected FetchMenuEntriesTask(Activity activity) {
            this.weakActivity = new WeakReference<>(activity);
        }

        @Override
        protected List<SavedMenuEntry> doInBackground(Void... voids) {
            return (Database.getInstance(weakActivity.get())).savedEntryMenuDao().getAll();
         }

        @Override
        protected void onPostExecute(List<SavedMenuEntry> entries) {
            super.onPostExecute(entries);
            ((BrowseMenuActivity)weakActivity.get()).buildRecycler(entries);
        }
    }
}
