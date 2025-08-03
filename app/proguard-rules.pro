# Add project specific ProGuard rules here.
-keep class com.example.bubbleapp.** { *; }
-dontwarn com.example.bubbleapp.**

# Keep service and receiver classes
-keep class com.example.bubbleapp.ProximityService { *; }
-keep class com.example.bubbleapp.BootReceiver { *; }
-keep class com.example.bubbleapp.MainActivity { *; }

# Remove unused code
-dontwarn android.support.**
-dontwarn androidx.**
-keep class androidx.appcompat.** { *; }
-keep class androidx.core.** { *; }

# Keep sensor and system classes
-keep class android.hardware.** { *; }
-keep class android.os.** { *; }
-keep class android.app.** { *; }
-keep class android.content.** { *; }

# Optimize
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification