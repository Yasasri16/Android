package com.example.truthordare;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private Button StopMusic;
    private Button StartMusic;
    private Button button;
    private Button play;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);
        play = findViewById(R.id.playid);
        StopMusic = findViewById(R.id.stopmusicid);
        StartMusic = findViewById(R.id.StartMusicButton);
        button = findViewById(R.id.button);

        Picasso.get().load("https://is1-ssl.mzstatic.com/image/thumb/Purple123/v4/a4/02/6b/a4026b45-5c00-cd30-6b27-4da253e9b524/AppIcon-1x_U007emarketing-0-7-0-85-220.png/230x0w.webp").into(imageView);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });

        StartMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this, MusicService.class));
            }
        });

        StopMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this, MusicService.class));
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}