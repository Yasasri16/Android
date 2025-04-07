package com.example.truthordare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    private EditText etPlayerName;
    private Uri uri;
    private Button btnAddPlayer, btnShowPrevPlayers, btnDeepLink;
    private RecyclerView rvPlayers;
    private PlayerAdapter playerAdapter;
    private List<String> playerNames = new ArrayList<>();
    private Button start;
    private PlayerDatabase db;
    private PlayerDao playerDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        etPlayerName = findViewById(R.id.editplayerid);
        btnAddPlayer = findViewById(R.id.addid);
        btnShowPrevPlayers = findViewById(R.id.showid);
        start = findViewById(R.id.startid);
        rvPlayers = findViewById(R.id.playerslistid);
        rvPlayers.setLayoutManager(new LinearLayoutManager(this));

        db = PlayerDatabase.getInstance(this);
        playerDao = db.playerDao();

        playerAdapter = new PlayerAdapter(playerNames);
        rvPlayers.setAdapter(playerAdapter);

        // getting the data from intent into the Uri.
        uri = getIntent().getData();
        if (uri != null) {
            // getting the path segments and storing it into the list.
            List<String> parameters = uri.getPathSegments();
            if (parameters != null && !parameters.isEmpty()) {
                // Extract the last segment safely
                String param = parameters.get(parameters.size() - 1);
                Toast.makeText(this, "Parameter: " + param, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No parameters found in the URI.", Toast.LENGTH_SHORT).show();
            }
        }


        btnAddPlayer.setOnClickListener(v -> {
            String playerName = etPlayerName.getText().toString().trim();
            if (!playerName.isEmpty()) {
                addPlayer(playerName);
            } else {
                Toast.makeText(MainActivity3.this, "Please enter a player name.", Toast.LENGTH_SHORT).show();
            }
        });

        btnShowPrevPlayers.setOnClickListener(v -> loadPlayers());

        start.setOnClickListener(view -> {
            Intent in = new Intent(MainActivity3.this, MainActivity4.class);
            startActivity(in);
        });
    }

    private void addPlayer(String playerName) {
        new Thread(() -> {
            Player player = new Player(playerName);
            playerDao.insert(player);
            runOnUiThread(() -> {
                playerNames.add(playerName);
                playerAdapter.notifyItemInserted(playerNames.size() - 1);
                etPlayerName.setText("");
            });
        }).start();
    }

    private void loadPlayers() {
        new Thread(() -> {
            List<Player> players = playerDao.getAllPlayers();
            runOnUiThread(() -> {
                playerNames.clear();
                for (Player player : players) {
                    playerNames.add(player.name);
                }
                playerAdapter.notifyDataSetChanged();
            });
        }).start();
    }

}
