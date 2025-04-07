package com.example.truthordaregame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Truth extends AppCompatActivity {
    private TextView textView;
    private Button forfeit;
    private Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_truth);
        textView = findViewById(R.id.textView);
        forfeit = findViewById(R.id.forfeitid);
        done = findViewById(R.id.doneid);

        fetchTruthQuestion("pg13");

        forfeit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Truth.this, BottleSpin.class);
                startActivity(intent);
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Truth.this, BottleSpin.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void fetchTruthQuestion(String rating) {
        RetrofitInstance.getInstance().getApiInterface().getTruth(rating).enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Log.d("API", "Response received. Code: " + response.code());

                if (response.isSuccessful()) {
                    Model model = response.body();
                    if (model != null && model.getQuestion() != null) {
                        Log.d("API", "Question: " + model.getQuestion()); // Log the question!
                        textView.setText(model.getQuestion());
                    } else {
                        Log.e("API", "Model or question is null. Model: " + model + ", Question: " + (model != null ? model.getQuestion() : "null"));
                        textView.setText("Error: Invalid API response");
                    }
                } else {
                    Log.e("API", "Response not successful. Code: " + response.code() + ", Message: " + response.message());
                    try {
                        if (response.errorBody() != null) {
                            String errorBody = response.errorBody().string();
                            Log.e("API", "Error Body: " + errorBody);
                            // Attempt to parse error body as JSON for more detail if needed.
                        } else {
                            Log.e("API", "Error Body is null");
                        }
                    } catch (IOException e) {
                        Log.e("API", "Error reading error body: " + e.getMessage());
                    }
                    textView.setText("Error: API request failed");
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.e("API", "API call failed: " + t.getMessage());
                textView.setText("Error: API call failed");
            }
        });
    }
}