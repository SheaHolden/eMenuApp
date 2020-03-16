package com.example.emenuapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * The home screen.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Navigate to the browse menu activity.
     * @param view The view that was clicked
     */
    public void onSavedMenusButtonClick(View view) {
        startActivity(
                new Intent(this, BrowseMenuActivity.class)
        );
    }

    /**
     * Navigate to the QR scanner activity
     * @param view The view that was clicked
     */
    public void onQRScannerClick(View view) {
        startActivity(
                new Intent(this, QRScanActivity.class)
        );
    }
}
