package com.example.emenuapp.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SavedMenuEntry {

    @NonNull
    @PrimaryKey()
    public String id;

    @ColumnInfo(name = "venue_name")
    public String venueName;

    @ColumnInfo(name = "venue_addr")
    public String venueAddr;
}
