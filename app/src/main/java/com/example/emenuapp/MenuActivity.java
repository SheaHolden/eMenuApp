package com.example.emenuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private String menuJson;

    public static final String EXTRA_MENU_DATA = "extra_menu_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        handleIntents();
    }

    private void handleIntents() {

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_MENU_DATA)) {

            // Testing the example menu
            menuJson = intent.getStringExtra(EXTRA_MENU_DATA);
            TextView testJsonField = findViewById(R.id.menuJsonTest);
            testJsonField.setText(menuJson);
        }
    }
}
