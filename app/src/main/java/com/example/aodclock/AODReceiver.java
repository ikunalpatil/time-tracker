package com.example.aodclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

public class AODReceiver extends BroadcastReceiver {
    
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        
        if (action != null) {
            switch (action) {
                case Intent.ACTION_SCREEN_OFF:
                    // Screen turned off - start AOD overlay
                    startAODOverlay(context);
                    break;
                    
                case Intent.ACTION_SCREEN_ON:
                    // Screen turned on - stop AOD overlay
                    stopAODOverlay(context);
                    break;
                    
                case Intent.ACTION_USER_PRESENT:
                    // User unlocked the device - stop AOD overlay
                    stopAODOverlay(context);
                    break;
                    
                case Intent.ACTION_BOOT_COMPLETED:
                    // Device booted - start AOD service
                    startAODService(context);
                    break;
            }
        }
    }
    
    private void startAODOverlay(Context context) {
        Intent overlayIntent = new Intent(context, AODOverlayService.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            context.startForegroundService(overlayIntent);
        } else {
            context.startService(overlayIntent);
        }
    }
    
    private void stopAODOverlay(Context context) {
        Intent overlayIntent = new Intent(context, AODOverlayService.class);
        context.stopService(overlayIntent);
    }
    
    private void startAODService(Context context) {
        Intent serviceIntent = new Intent(context, AODService.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent);
        } else {
            context.startService(serviceIntent);
        }
    }
}