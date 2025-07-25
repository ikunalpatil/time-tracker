package com.timewidget.digitalclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.widget.RemoteViews;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Ultra-lightweight digital clock widget with REAL-TIME precision
 * Updates every second for maximum accuracy
 */
public class DigitalClockWidget extends AppWidgetProvider {

    private static final String UPDATE_ACTION = "UPDATE_TIME";

    static void updateWidget(Context context, AppWidgetManager manager, int widgetId) {
        Calendar cal = Calendar.getInstance();
        String time = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(cal.getTime());
        String date = new SimpleDateFormat("EEE, MMM d", Locale.getDefault()).format(cal.getTime());

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.digital_clock_widget);
        views.setTextViewText(R.id.widget_time, time);
        views.setTextViewText(R.id.widget_date, date);
        manager.updateAppWidget(widgetId, views);
    }

    private static void updateAll(Context context) {
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        int[] ids = manager.getAppWidgetIds(new ComponentName(context, DigitalClockWidget.class));
        for (int id : ids) updateWidget(context, manager, id);
    }

    private void setAlarm(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DigitalClockWidget.class).setAction(UPDATE_ACTION);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        
        // Real-time precision: Update every 1 second
        long intervalMillis = 1000; // 1 second
        long triggerAtMillis = SystemClock.elapsedRealtime() + intervalMillis;
        
        am.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtMillis, intervalMillis, pi);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager manager, int[] widgetIds) {
        for (int id : widgetIds) updateWidget(context, manager, id);
        setAlarm(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (UPDATE_ACTION.equals(action) || Intent.ACTION_TIME_TICK.equals(action) || 
            Intent.ACTION_TIME_CHANGED.equals(action) || Intent.ACTION_TIMEZONE_CHANGED.equals(action)) {
            updateAll(context);
        } else {
            super.onReceive(context, intent);
        }
    }

    @Override
    public void onEnabled(Context context) {
        setAlarm(context);
        updateAll(context);
    }
}