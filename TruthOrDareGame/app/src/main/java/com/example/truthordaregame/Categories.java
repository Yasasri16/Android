package com.example.truthordaregame;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Categories extends AppCompatActivity {

    private WifiChangeReceiver wifiChangeReceiver = new WifiChangeReceiver();
    private Switch darkModeSwitch;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categories);

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
        Button btnShareApp = findViewById(R.id.button);
        btnShareApp.setOnClickListener(v -> shareApp());
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
            Intent intent = new Intent(Categories.this, AddPlayers.class);
            startActivity(intent);
        };

        kids.setOnClickListener(categoryClickListener);
        teens.setOnClickListener(categoryClickListener);
        adults.setOnClickListener(categoryClickListener);
        couples.setOnClickListener(categoryClickListener);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    private void shareApp() {
        File apkFile = copyApkToDownloads();

        if (apkFile == null || !apkFile.exists()) {
            Log.e("ShareApp", "APK file not found!");
            return;
        }

        Uri apkUri = FileProvider.getUriForFile(this, "com.app.expensetracker.provider", apkFile);

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("application/vnd.android.package-archive");
        shareIntent.putExtra(Intent.EXTRA_STREAM, apkUri);
        shareIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivity(Intent.createChooser(shareIntent, "Share App via"));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(wifiChangeReceiver);
    }
    private File copyApkToDownloads() {
        try {
            ApplicationInfo appInfo = getApplicationContext().getApplicationInfo();
            File sourceApk = new File(appInfo.sourceDir);
            File destination = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "ExpenseTracker.apk");

            try (InputStream in = new FileInputStream(sourceApk);
                 OutputStream out = new FileOutputStream(destination)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
            }

            return destination;  // Return the new file path
        } catch (Exception e) {
            Log.e("ShareApp", "Error copying APK: " + e.getMessage());
            return null;
        }
    }
}