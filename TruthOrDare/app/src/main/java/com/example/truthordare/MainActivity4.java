package com.example.truthordare;

import android.animation.ObjectAnimator;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity4 extends AppCompatActivity {
    private ImageView bottleImageView;
    private TextView resultTextView;
    private Button truth, dare;
    private List<String> playersList;
    private PlayerDatabase db;
    private int rotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        playersList = new ArrayList<>();
        bottleImageView = findViewById(R.id.bottleImageView);
        resultTextView = findViewById(R.id.resultTextView);
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
        dare.setOnClickListener(view -> startActivity(new Intent(MainActivity4.this, MainActivity6.class)));
        truth.setOnClickListener(view -> startActivity(new Intent(MainActivity4.this, MainActivity5.class)));
        bottleImageView.setOnClickListener(v -> spinTheBottle());


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
