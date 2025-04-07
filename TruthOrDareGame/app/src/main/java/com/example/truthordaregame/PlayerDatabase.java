package com.example.truthordaregame;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Player.class}, version = 1, exportSchema = false)
public abstract class PlayerDatabase extends RoomDatabase {
    private static PlayerDatabase INSTANCE;

    public abstract com.example.truthordaregame.PlayerDao playerDao();

    public static synchronized PlayerDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            PlayerDatabase.class, "player_database"
                    )
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
