package com.example.otherapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String AUTHORITY = "com.example.truthordaregame.provider";
    private static final String PATH = "players_table";
    private static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);

    private TextView playersTextView;
    private EditText playerNameEditText;
    private Button addPlayerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playersTextView = findViewById(R.id.playersTextView);
        playerNameEditText = findViewById(R.id.playerNameEditText);
        addPlayerButton = findViewById(R.id.addPlayerButton);

        loadPlayers();

        addPlayerButton.setOnClickListener(view -> {
            String playerName = playerNameEditText.getText().toString().trim();
            if (!playerName.isEmpty()) {
                addPlayer(playerName);
                playerNameEditText.setText("");
                loadPlayers();
            }
        });
    }

    private void loadPlayers() {
        StringBuilder playerNames = new StringBuilder();
        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            Log.d("MainActivity", "Cursor is not null");
            while (cursor.moveToNext()) {
                int index = cursor.getColumnIndex("name");
                if (index != -1) {
                    playerNames.append(cursor.getString(index)).append("\n");
                }
            }
            cursor.close();
        } else {
            Log.e("MainActivity", "Cursor is null");
        }
        playersTextView.setText(playerNames.toString());
    }

    private void addPlayer(String name) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        Uri uri = getContentResolver().insert(CONTENT_URI, values);
        if (uri != null) {
            Log.d("MainActivity", "Player added successfully");
        } else {
            Log.e("MainActivity", "Failed to add player");
        }
    }
}
