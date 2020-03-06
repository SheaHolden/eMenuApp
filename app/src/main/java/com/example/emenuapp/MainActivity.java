package com.example.emenuapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.airbnb.epoxy.EpoxyRecyclerView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, LoadMenuActivity.class);
        startActivity(intent);
    }
}
//Test change #2
