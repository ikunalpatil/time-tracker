package com.example.bubbleapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            // Start the proximity service on boot
            Intent serviceIntent = new Intent(context, ProximityService.class);
            context.startForegroundService(serviceIntent);
        }
    }
}