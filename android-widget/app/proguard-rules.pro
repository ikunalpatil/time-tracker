# Ultra-lightweight widget ProGuard rules for maximum size reduction

# Keep widget provider
-keep class com.timewidget.digitalclock.DigitalClockWidget { *; }

# Remove debugging
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
}

# Aggressive optimization
-dontpreverify
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification
-repackageclasses ''

# Remove unused code
-dontwarn **
-ignorewarnings

# Keep only essential classes
-keep class * extends android.appwidget.AppWidgetProvider
-keep class * extends android.content.BroadcastReceiver