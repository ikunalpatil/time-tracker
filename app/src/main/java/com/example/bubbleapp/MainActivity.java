package com.example.bubbleapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button startServiceButton;
    private Button stopServiceButton;
    private Button permissionButton;
    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startServiceButton = findViewById(R.id.startServiceButton);
        stopServiceButton = findViewById(R.id.stopServiceButton);
        permissionButton = findViewById(R.id.permissionButton);
        statusText = findViewById(R.id.statusText);

        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProximityService();
            }
        });

        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopProximityService();
            }
        });

        permissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOverlayPermissionSettings();
            }
        });

        updateStatus();
    }

    private void startProximityService() {
        Intent serviceIntent = new Intent(this, ProximityService.class);
        startForegroundService(serviceIntent);
        updateStatus();
    }

    private void stopProximityService() {
        Intent serviceIntent = new Intent(this, ProximityService.class);
        stopService(serviceIntent);
        updateStatus();
    }

    private void openOverlayPermissionSettings() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        startActivity(intent);
    }

    private void updateStatus() {
        if (ProximityService.isRunning()) {
            statusText.setText("Service is running - Wave hand over proximity sensor to turn on screen for 5 seconds");
            startServiceButton.setEnabled(false);
            stopServiceButton.setEnabled(true);
        } else {
            statusText.setText("Service is stopped");
            startServiceButton.setEnabled(true);
            stopServiceButton.setEnabled(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStatus();
    }
}