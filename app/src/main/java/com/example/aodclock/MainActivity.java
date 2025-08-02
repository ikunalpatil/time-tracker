package com.example.aodclock;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {
    private TextView timeTextView;
    private TextView dateTextView;
    private TextView permissionTextView;
    private Handler handler;
    private Runnable updateTimeRunnable;
    private static final int OVERLAY_PERMISSION_REQUEST_CODE = 1234;
    private static final int BATTERY_OPTIMIZATION_REQUEST_CODE = 5678;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Set up fullscreen AOD display
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN |
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
            WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
            WindowManager.LayoutParams.FLAG_FULLSCREEN |
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
            WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        );
        
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN |
                       View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                       View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                       View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                       View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                       View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        
        setContentView(R.layout.activity_main);
        
        timeTextView = findViewById(R.id.timeTextView);
        dateTextView = findViewById(R.id.dateTextView);
        permissionTextView = findViewById(R.id.permissionTextView);
        
        handler = new Handler(Looper.getMainLooper());
        updateTimeRunnable = new Runnable() {
            @Override
            public void run() {
                updateTime();
                handler.postDelayed(this, 1000);
            }
        };
        
        // Check and request permissions
        checkAndRequestPermissions();
        
        // Start AOD service
        Intent serviceIntent = new Intent(this, AODService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent);
        } else {
            startService(serviceIntent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTime();
        handler.post(updateTimeRunnable);
        checkAndRequestPermissions();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(updateTimeRunnable);
    }

    private void checkAndRequestPermissions() {
        boolean allPermissionsGranted = true;
        
        // Check overlay permission
        if (!Settings.canDrawOverlays(this)) {
            allPermissionsGranted = false;
            permissionTextView.setVisibility(View.VISIBLE);
            permissionTextView.setText("Tap to enable Always On Display permissions");
            permissionTextView.setOnClickListener(v -> requestOverlayPermission());
        } else {
            permissionTextView.setVisibility(View.GONE);
        }
        
        // Check battery optimization
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!isIgnoringBatteryOptimizations()) {
                allPermissionsGranted = false;
                requestBatteryOptimizationPermission();
            }
        }
        
        if (allPermissionsGranted) {
            permissionTextView.setVisibility(View.GONE);
            // Start AOD overlay service
            startAODOverlay();
        }
    }

    private void requestOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, OVERLAY_PERMISSION_REQUEST_CODE);
        }
    }

    private void requestBatteryOptimizationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            startActivityForResult(intent, BATTERY_OPTIMIZATION_REQUEST_CODE);
        }
    }

    private boolean isIgnoringBatteryOptimizations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String packageName = getPackageName();
            String powerManager = getSystemService(POWER_SERVICE).getClass().getName();
            try {
                return Settings.Global.getInt(getContentResolver(), 
                    "app_standby_enabled") == 0;
            } catch (Settings.SettingNotFoundException e) {
                return false;
            }
        }
        return true;
    }

    private void startAODOverlay() {
        // Start the AOD overlay service
        Intent overlayIntent = new Intent(this, AODOverlayService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(overlayIntent);
        } else {
            startService(overlayIntent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OVERLAY_PERMISSION_REQUEST_CODE) {
            if (Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "Always On Display permission granted!", Toast.LENGTH_SHORT).show();
                checkAndRequestPermissions();
            } else {
                Toast.makeText(this, "Permission required for Always On Display", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void updateTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.getDefault());
        String currentTime = timeFormat.format(new Date());
        String currentDate = dateFormat.format(new Date());
        timeTextView.setText(currentTime);
        dateTextView.setText(currentDate);
    }
}