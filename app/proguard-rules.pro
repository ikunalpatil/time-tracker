# Add project specific ProGuard rules here.
-keep class com.example.bubbleapp.** { *; }
-dontwarn com.example.bubbleapp.**

# Remove unused code
-dontwarn android.support.**
-dontwarn androidx.**
-keep class androidx.appcompat.** { *; }
-keep class com.google.android.material.** { *; }

# Optimize
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification