package com.example.emenuapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.emenuapp.database.Database;
import com.example.emenuapp.database.SavedMenuEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;


/**
 * Loads a menu from the menu server and feeds it to the menu activity.
 * Also displays a loading screen to the user.
 */
public class LoadMenuActivity extends Activity {

    public static final String EXTRA_MENU_KEY = "EXTRA_MENU_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_menu);

        requestMenu(getKey());
    }



    /**
     * NFC intent gets funneled through this function when the app is already open.
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        requestMenu(getKey());
    }



    /**
     * Gets the key from an intent, returns null if not found
     * @return
     */
    private String getKey() {
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_MENU_KEY)) {
            return intent.getStringExtra(EXTRA_MENU_KEY);
        }
        else if (intent.getAction().equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
            return parseNfcData(intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES));
        } else {
            return null;
        }
    }



    /**
     * Takes the parcelable array extra from the nfc intent and parses its string payload.
     * @param raw
     * @return
     */
    private String parseNfcData(Parcelable[] raw) {

        if (raw == null) return null;
        NdefMessage msg = (NdefMessage) raw[0];
        NdefRecord[] records = msg.getRecords();
        if (records == null) return null;

        NdefRecord record = records[0];
        if (record.toMimeType().equals("text/plain")) {
            byte[] payload = record.getPayload();
            return new String(Arrays.copyOfRange(payload, 3, payload.length));
        } else {
            return null;
        }
     }



    /**
     * Sends a request to the menu server and passes the received string to the menu activity
     * If the request fails, it returns an error message to the previous activity
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
                    Toast.makeText(this, "Failed to load menu", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(this, MainActivity.class);
                    startActivity(intent);
                });

        queue.add(request);
    }



    /**
     * Starts the menu activity with a json menu string
     * @param menuJson
     */
    public void startMenuActivity(String menuJson) {

        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(MenuActivity.EXTRA_MENU_DATA, menuJson);
        startActivity(intent);
        finish();
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
     * Saves an entry to the room database if it hasn't been already.
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
            List<SavedMenuEntry> entries = db.savedEntryMenuDao().getByVenueId(key);

            if (entries.isEmpty()) {

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
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ((LoadMenuActivity)weakActivity.get()).startMenuActivity(menuJson);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Database.destroyInstance();
    }
}
