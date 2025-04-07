package com.example.truthordaregame;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface PlayerDao {
    @Insert
    void insert(Player player);
    @Query("SELECT * FROM players_table")
    Cursor getAllPlayersCursor();

    @Query("SELECT * FROM players_table")
    List<Player> getAllPlayers();

    @Query("DELETE FROM players_table")
    void deleteAllPlayers();
}
