package com.example.truthordare;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "players_table")
public class Player {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;

    public Player(String name) {
        this.name = name;
    }
}
