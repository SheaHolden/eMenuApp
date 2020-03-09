package com.example.emenuapp.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {SavedMenuEntry.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract SavedMenuEntryDao savedEntryMenuDao();

    private static Database instance;

    public static Database getInstance(Context context) {
        if (instance != null) {
            return instance;
        }
        instance = Room.databaseBuilder(context,
                Database.class, "database-name").build();
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }
}
