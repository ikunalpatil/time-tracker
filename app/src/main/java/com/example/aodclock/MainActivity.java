package com.example.aodclock;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {
    private TextView timeTextView;
    private TextView dateTextView;
    private Handler handler;
    private Runnable updateTimeRunnable;

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
        
        handler = new Handler(Looper.getMainLooper());
        updateTimeRunnable = new Runnable() {
            @Override
            public void run() {
                updateTime();
                handler.postDelayed(this, 1000);
            }
        };
        
        // Start time updates
        updateTime();
        handler.post(updateTimeRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTime();
        handler.post(updateTimeRunnable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(updateTimeRunnable);
    }

    private void updateTime() {
        try {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.getDefault());
            String currentTime = timeFormat.format(new Date());
            String currentDate = dateFormat.format(new Date());
            
            if (timeTextView != null) {
                timeTextView.setText(currentTime);
            }
            if (dateTextView != null) {
                dateTextView.setText(currentDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}