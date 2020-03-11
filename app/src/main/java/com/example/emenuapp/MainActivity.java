package com.example.emenuapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.airbnb.epoxy.EpoxyRecyclerView;


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
    public void onQRScannerClick(View view) {

        Intent intent = new Intent(this, QRScanActivity.class);
        startActivity(intent);
    }
}
