package com.example.bubbleapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.WindowManager;
import androidx.core.app.NotificationCompat;

public class ProximityService extends Service implements SensorEventListener {
    private static final String CHANNEL_ID = "ProximityServiceChannel";
    private static final int NOTIFICATION_ID = 1;
    private static boolean isServiceRunning = false;

    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private PowerManager.WakeLock wakeLock;
    private PowerManager.WakeLock screenWakeLock;
    private WindowManager windowManager;
    private boolean isProximityNear = false;
    private long lastProximityChange = 0;
    private static final long PROXIMITY_DEBOUNCE_TIME = 500; // 500ms debounce
    private static final long SCREEN_ON_DURATION = 5000; // 5 seconds
    private Handler screenHandler;
    private boolean isScreenOnTimerActive = false;

    @Override
    public void onCreate() {
        super.onCreate();
        isServiceRunning = true;
        
        // Initialize sensor manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        
        // Initialize power manager for wake locks
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "ProximityService::WakeLock");
        screenWakeLock = powerManager.newWakeLock(
            PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP,
            "ProximityService::ScreenWakeLock");
        
        // Initialize window manager
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        
        // Initialize handler for screen timer
        screenHandler = new Handler();
        
        createNotificationChannel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(NOTIFICATION_ID, createNotification());
        
        if (proximitySensor != null) {
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        
        if (wakeLock != null && !wakeLock.isHeld()) {
            wakeLock.acquire();
        }
        
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isServiceRunning = false;
        
        if (sensorManager != null && proximitySensor != null) {
            sensorManager.unregisterListener(this, proximitySensor);
        }
        
        if (wakeLock != null && wakeLock.isHeld()) {
            wakeLock.release();
        }
        
        if (screenWakeLock != null && screenWakeLock.isHeld()) {
            screenWakeLock.release();
        }
        
        // Cancel any pending screen timer
        if (screenHandler != null) {
            screenHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            long currentTime = System.currentTimeMillis();
            
            // Debounce to prevent rapid changes
            if (currentTime - lastProximityChange < PROXIMITY_DEBOUNCE_TIME) {
                return;
            }
            
            float distance = event.values[0];
            boolean isNear = distance < proximitySensor.getMaximumRange();
            
            if (isNear != isProximityNear) {
                isProximityNear = isNear;
                lastProximityChange = currentTime;
                
                if (isNear) {
                    // Object detected - turn on screen and keep it on for 5 seconds
                    turnOnScreenForDuration();
                } else {
                    // Object removed - let the timer handle screen off
                    // Don't immediately turn off screen, let the 5-second timer expire
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used
    }

    private void turnOnScreenForDuration() {
        // Cancel any existing timer
        if (screenHandler != null) {
            screenHandler.removeCallbacksAndMessages(null);
        }
        
        // Turn on the screen
        turnOnScreen();
        
        // Set timer to turn off screen after 5 seconds
        screenHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                turnOffScreen();
            }
        }, SCREEN_ON_DURATION);
        
        isScreenOnTimerActive = true;
    }

    private void turnOnScreen() {
        try {
            // Wake up the screen if it's locked or off
            if (screenWakeLock != null && !screenWakeLock.isHeld()) {
                screenWakeLock.acquire(SCREEN_ON_DURATION + 1000); // Acquire for 6 seconds to be safe
            }
            
            // Set screen brightness to maximum for better visibility
            Settings.System.putInt(getContentResolver(), 
                Settings.System.SCREEN_BRIGHTNESS, 255);
            
        } catch (Exception e) {
            // Handle permission issues gracefully
        }
    }

    private void turnOffScreen() {
        try {
            // Release screen wake lock to allow screen to turn off
            if (screenWakeLock != null && screenWakeLock.isHeld()) {
                screenWakeLock.release();
            }
            
            // Restore moderate brightness level
            Settings.System.putInt(getContentResolver(), 
                Settings.System.SCREEN_BRIGHTNESS, 128);
            
            isScreenOnTimerActive = false;
            
        } catch (Exception e) {
            // Handle permission issues gracefully
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                "Proximity Service Channel",
                NotificationManager.IMPORTANCE_LOW
            );
            channel.setDescription("Channel for proximity sensor service");
            
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }

    private Notification createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Proximity Sensor Active")
            .setContentText("Wave hand over sensor to turn on screen for 5 seconds")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(true);

        return builder.build();
    }

    public static boolean isRunning() {
        return isServiceRunning;
    }
}