package com.example.emenuapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LoadMenuActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_menu);

        // TODO: Receive the menu id from either NFC or QR

        // This is a temporary, hardcoded menu id
        requestMenu("testmenu1");
    }


    /**
     * Sends a request to the menu server and passes the received string to the menu activity
     * If the request fails, it returns an error message to the previous activity
     *
     * @param restaurantId
     */
    private void requestMenu(String restaurantId) {

        String requestUrl = getString(R.string.menu_server_test_url) + "?id=" + restaurantId;
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest
                (Request.Method.GET, requestUrl, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        startMenuActivity(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        printErrorMessage();
                        // TODO: Move the user back to some other activity
                    }
                });

        queue.add(request);
    }


    /**
     * Starts the menu activity with a json menu string
     *
     * @param menuJson
     */
    private void startMenuActivity(String menuJson) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(MenuActivity.EXTRA_MENU_DATA, menuJson);
        startActivity(intent);
    }

    /**
     * Temporary error message
     */
    private void printErrorMessage() {
        Toast.makeText(this, "Failed to load menu.", Toast.LENGTH_SHORT).show();
    }


}
