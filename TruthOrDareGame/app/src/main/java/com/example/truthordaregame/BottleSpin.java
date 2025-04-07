package com.example.truthordaregame;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BottleSpin extends AppCompatActivity {
    private ImageView bottleImageView;
    private TextView resultTextView;
    private Button truth, dare,logout;
    private List<String> playersList;
    private PlayerDatabase db;
    private int rotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bottle_spin);
        playersList = new ArrayList<>();
        bottleImageView = findViewById(R.id.bottleImageView);
        resultTextView = findViewById(R.id.resultTextView);
        logout = findViewById(R.id.logoutid);
        truth = findViewById(R.id.truthid);
        dare = findViewById(R.id.dareid);


        new Thread(() -> {
            List<Player> playersFromDb = db.playerDao().getAllPlayers();
            for (Player player : playersFromDb) {
                playersList.add(player.name);
            }
            runOnUiThread(() -> {
                if (playersList.isEmpty()) {
                    Toast.makeText(this, "No players available. Add players first!", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();


        db = PlayerDatabase.getInstance(this);
        dare.setOnClickListener(view -> startActivity(new Intent(BottleSpin.this, Dare.class)));
        truth.setOnClickListener(view -> startActivity(new Intent(BottleSpin.this, Truth.class)));
        bottleImageView.setOnClickListener(v -> spinTheBottle());
        logout.setOnClickListener(view -> startActivity(new Intent(BottleSpin.this, Logout.class)));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void spinTheBottle() {
        if (playersList.isEmpty()) {
            Toast.makeText(this, "No players available. Add players first!", Toast.LENGTH_SHORT).show();
            return;
        }
        Random random = new Random();
        rotation = random.nextInt(3600) + 720;
        ObjectAnimator animator = ObjectAnimator.ofFloat(bottleImageView, "rotation", 0f, rotation);
        animator.setDuration(2000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
        animator.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                float finalAngle = rotation % 360;
                int winnerIndex = determineWinner(finalAngle, playersList.size());
                String winnerName = playersList.get(winnerIndex);
                resultTextView.setText(winnerName + "'s turn!");
            }
        });
    }
    private int determineWinner(float finalAngle, int numPlayers) {
        return (int) (finalAngle / (360f / numPlayers)) % numPlayers;
    }
}