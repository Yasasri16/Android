package com.example.truthordare;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.CompoundButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity2 extends AppCompatActivity {
    private WifiChangeReceiver wifiChangeReceiver = new WifiChangeReceiver();
    private Switch darkModeSwitch;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        sharedPreferences = getSharedPreferences("DarkModePrefs", MODE_PRIVATE);
        boolean isDarkMode = sharedPreferences.getBoolean("darkMode", false);

        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(wifiChangeReceiver, filter);

        Button kids = findViewById(R.id.kidsid);
        Button teens = findViewById(R.id.teensid);
        Button adults = findViewById(R.id.adultsid);
        Button couples = findViewById(R.id.couplesid);
        darkModeSwitch = findViewById(R.id.darkModeSwitch);

        darkModeSwitch.setChecked(isDarkMode);

        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }

                editor = sharedPreferences.edit();
                editor.putBoolean("darkMode", isChecked);
                editor.apply();
            }
        });



        View.OnClickListener categoryClickListener = view -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            startActivity(intent);
        };

        kids.setOnClickListener(categoryClickListener);
        teens.setOnClickListener(categoryClickListener);
        adults.setOnClickListener(categoryClickListener);
        couples.setOnClickListener(categoryClickListener);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(wifiChangeReceiver);
    }
}

