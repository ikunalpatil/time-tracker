package com.timewidget.digitalclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.widget.RemoteViews;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Implementation of App Widget functionality.
 * Enhanced digital clock widget that syncs with phone time and updates accurately.
 */
public class DigitalClockWidget extends AppWidgetProvider {

    private static final String ACTION_UPDATE_TIME = "com.timewidget.digitalclock.UPDATE_TIME";
    private static AlarmManager alarmManager;
    private static PendingIntent pendingIntent;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        try {
            // Get current time using Calendar for better accuracy
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getDefault());
            
            // Format time and date using phone's locale and timezone
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d", Locale.getDefault());
            
            timeFormat.setTimeZone(TimeZone.getDefault());
            dateFormat.setTimeZone(TimeZone.getDefault());
            
            String timeText = timeFormat.format(calendar.getTime());
            String dateText = dateFormat.format(calendar.getTime());

            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.digital_clock_widget);
            views.setTextViewText(R.id.widget_time, timeText);
            views.setTextViewText(R.id.widget_date, dateText);

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        } catch (Exception e) {
            // Fallback to basic time display if there's an error
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.digital_clock_widget);
            views.setTextViewText(R.id.widget_time, new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));
            views.setTextViewText(R.id.widget_date, new SimpleDateFormat("EEE, MMM d", Locale.getDefault()).format(new Date()));
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    private static void updateAllWidgets(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(
            new android.content.ComponentName(context, DigitalClockWidget.class));
        
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private void setupTimeUpdateAlarm(Context context) {
        Intent intent = new Intent(context, DigitalClockWidget.class);
        intent.setAction(ACTION_UPDATE_TIME);
        
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        
        if (alarmManager != null) {
            // Update every 30 seconds for better accuracy
            long intervalMillis = 30 * 1000;
            long triggerAtMillis = SystemClock.elapsedRealtime() + intervalMillis;
            
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, 
                triggerAtMillis, intervalMillis, pendingIntent);
        }
    }

    private void cancelTimeUpdateAlarm(Context context) {
        if (alarmManager != null && pendingIntent != null) {
            alarmManager.cancel(pendingIntent);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // Update all widgets immediately
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        
        // Set up regular updates
        setupTimeUpdateAlarm(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        
        String action = intent.getAction();
        if (action != null) {
            switch (action) {
                case ACTION_UPDATE_TIME:
                case Intent.ACTION_TIME_TICK:
                case Intent.ACTION_TIME_CHANGED:
                case Intent.ACTION_TIMEZONE_CHANGED:
                case Intent.ACTION_DATE_CHANGED:
                    updateAllWidgets(context);
                    break;
            }
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Register for time-related broadcasts
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_TIME_TICK);
        filter.addAction(Intent.ACTION_TIME_CHANGED);
        filter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        filter.addAction(Intent.ACTION_DATE_CHANGED);
        
        // Start the alarm for regular updates
        setupTimeUpdateAlarm(context);
        
        // Immediate update
        updateAllWidgets(context);
    }

    @Override
    public void onDisabled(Context context) {
        // Cancel the alarm when the last widget is removed
        cancelTimeUpdateAlarm(context);
    }
}