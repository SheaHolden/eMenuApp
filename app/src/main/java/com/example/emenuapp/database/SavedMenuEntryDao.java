package com.example.emenuapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SavedMenuEntryDao {

    @Query("Select * from SavedMenuEntry")
    List<SavedMenuEntry> getAll();

    @Query("select * from SavedMenuEntry where (venue_id = :venueId)")
    List<SavedMenuEntry> getByVenueId(String venueId);

    @Insert
    void insertAll(SavedMenuEntry ... entries);
}
