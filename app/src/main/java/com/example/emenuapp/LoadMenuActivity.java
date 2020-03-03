package com.example.emenuapp;

import android.app.Activity;
import android.os.Bundle;

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
    }

    /**
     * Sends a request to the menu server and passes the received string to the menu activity
     * If the request fails, it returns an error message to the previous activity
     *
     * @param restaurantId
     */
    private void requestMenu(int restaurantId) {

        String requestUrl = "cs.tru.ca/sholden/.../test.php?id=" + restaurantId;

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest
                (Request.Method.GET, requestUrl, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO: Pass the MenuData object to the menu activity through an intent
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        // TODO: Pass an error code back to the last activity via intent
                    }
                });

        queue.add(request);
    }
}
