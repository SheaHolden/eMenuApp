package com.example.emenuapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.example.emenuapp.database.Database;
import com.example.emenuapp.database.SavedMenuEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSavedMenusButtonClick(View view) {
        Intent intent = new Intent(this, BrowseMenuActivity.class);
        startActivity(intent);
    }
}
