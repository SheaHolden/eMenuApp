package com.example.emenuapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSavedMenusButtonClick(View view) {
        startActivity(
                new Intent(this, BrowseMenuActivity.class)
        );
    }
    public void onQRScannerClick(View view) {
        startActivity(
                new Intent(this, QRScanActivity.class)
        );
    }
}
