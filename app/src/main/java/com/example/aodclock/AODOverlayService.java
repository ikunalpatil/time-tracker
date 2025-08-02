package com.example.aodclock;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AODOverlayService extends Service {
    private static final String CHANNEL_ID = "AODOverlayChannel";
    private static final int NOTIFICATION_ID = 2;
    
    private WindowManager windowManager;
    private View overlayView;
    private TextView overlayTimeTextView;
    private TextView overlayDateTextView;
    private Handler handler;
    private PowerManager.WakeLock wakeLock;
    private boolean isScreenOn = true;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        handler = new Handler(Looper.getMainLooper());
        
        // Acquire wake lock
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(
            PowerManager.PARTIAL_WAKE_LOCK,
            "AODOverlay::WakeLock"
        );
        wakeLock.acquire();
        
        createOverlayView();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("AOD Clock Overlay")
            .setContentText("Always On Display Active")
            .setSmallIcon(android.R.drawable.ic_menu_recent_history)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(true)
            .build();
            
        startForeground(NOTIFICATION_ID, notification);
        
        // Start time updates
        startTimeUpdates();
        
        return START_STICKY;
    }

    private void createOverlayView() {
        if (Settings.canDrawOverlays(this)) {
            WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                PixelFormat.TRANSLUCENT
            );
            
            params.gravity = Gravity.CENTER;
            
            LayoutInflater inflater = LayoutInflater.from(this);
            overlayView = inflater.inflate(R.layout.aod_overlay, null);
            
            overlayTimeTextView = overlayView.findViewById(R.id.overlayTimeTextView);
            overlayDateTextView = overlayView.findViewById(R.id.overlayDateTextView);
            
            // Set initial time
            updateOverlayTime();
            
            try {
                windowManager.addView(overlayView, params);
            } catch (Exception e) {
                // Handle permission denied or other errors
                e.printStackTrace();
            }
        }
    }

    private void startTimeUpdates() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateOverlayTime();
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    private void updateOverlayTime() {
        if (overlayTimeTextView != null && overlayDateTextView != null) {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.getDefault());
            String currentTime = timeFormat.format(new Date());
            String currentDate = dateFormat.format(new Date());
            
            overlayTimeTextView.setText(currentTime);
            overlayDateTextView.setText(currentDate);
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                "AOD Overlay Service",
                NotificationManager.IMPORTANCE_LOW
            );
            channel.setDescription("Always On Display Overlay Service");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        
        if (overlayView != null && windowManager != null) {
            try {
                windowManager.removeView(overlayView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if (wakeLock != null && wakeLock.isHeld()) {
            wakeLock.release();
        }
        
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}