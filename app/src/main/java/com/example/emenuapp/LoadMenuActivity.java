package com.example.emenuapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.emenuapp.database.Database;
import com.example.emenuapp.database.SavedMenuEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class LoadMenuActivity extends Activity {

    public static final String EXTRA_MENU_KEY = "EXTRA_MENU_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_menu);

        requestMenu(getKey());
    }

    /**
     * Gets the key from an intent, returns ull if not found
     * @return
     */
    private String getKey() {
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_MENU_KEY)) {
            return intent.getStringExtra(EXTRA_MENU_KEY);
        } else {
            return null;
        }
    }

    /**
     * Sends a request to the menu server and passes the received string to the menu activity
     * If the request fails, it returns an error message to the previous activity
     *
     * @param key
     */
    private void requestMenu(String key) {

        String requestUrl = getString(R.string.menu_server_test_url) + "?id=" + key;
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest
                (Request.Method.GET, requestUrl, response -> {
                    saveLocalEntry(response, key);
                    startMenuActivity(response);
                }, error -> {
                    // TODO: Move the user back to some other activity
                });

        queue.add(request);
    }

    /**
     * Starts the menu activity with a json menu string
     *
     * @param menuJson
     */
    public void startMenuActivity(String menuJson) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(MenuActivity.EXTRA_MENU_DATA, menuJson);
        startActivity(intent);
    }

    /**
     * Saves select menu information to a room database
     * This information will be used to populate the saved menu list
     * @param menuJson
     */
    private void saveLocalEntry(String menuJson, String key) {
        new SaveEntryTask(this, menuJson, key).execute();
    }


    /**
     * For accessing room database asynchronously
     */
    protected static class SaveEntryTask extends AsyncTask<Void, Void, Void> {

        private WeakReference<Activity> weakActivity;
        private String menuJson;
        private String key;

        protected SaveEntryTask(Activity activity, String menuJson, String key) {
            this.weakActivity = new WeakReference<>(activity);
            this.menuJson = menuJson;
            this.key = key;
        }

        @Override
        protected Void doInBackground(Void... aVoid) {

            Database db = Database.getInstance(weakActivity.get());
            SavedMenuEntry entry = new SavedMenuEntry();

            entry.venueId = this.key;
            try {
                JSONObject menu = new JSONObject(menuJson);
                entry.venueName = menu.getString("venue_name");
                entry.venueAddr = menu.getString("venue_addr");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            db.savedEntryMenuDao().insertAll(entry);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ((LoadMenuActivity)weakActivity.get()).startMenuActivity(menuJson);
        }
    }
}
