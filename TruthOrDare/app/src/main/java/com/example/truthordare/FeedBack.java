package com.example.truthordare;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FeedBack extends AppCompatActivity {

    Button sendFeedback;
    EditText feedback;
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feed_back);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sendFeedback = findViewById(R.id.sendFeedback);
        feedback = findViewById(R.id.feedback);
        textView = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView3);

        sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(ContentProvider.feedback, feedback.getText().toString());
                getContentResolver().insert(ContentProvider.contentUri, values);
                Toast.makeText(getBaseContext(), "Thankyou for the feedback!!", Toast.LENGTH_SHORT).show();
                feedback.setText("");
            }
        });
    }
}