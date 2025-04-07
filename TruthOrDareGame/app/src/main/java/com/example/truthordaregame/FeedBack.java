package com.example.truthordaregame;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FeedBack extends AppCompatActivity {
    Button sendFeedback;
    EditText feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        sendFeedback = findViewById(R.id.sendfeedback);
        feedback = findViewById(R.id.feedback);

        sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("feedback", feedback.getText().toString());
                getContentResolver().insert(FeedBackContentProvider.CONTENT_URI, values);
                Toast.makeText(getBaseContext(), "Thank you for the feedback!", Toast.LENGTH_SHORT).show();
                feedback.setText("");
            }
        });
    }
}
