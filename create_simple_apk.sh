#!/bin/bash

echo "Creating Proximity Screen Wake APK..."

# Create APK directory structure
mkdir -p proximity-apk/META-INF/com/android
mkdir -p proximity-apk/assets
mkdir -p proximity-apk/res/drawable
mkdir -p proximity-apk/res/layout
mkdir -p proximity-apk/res/values
mkdir -p proximity-apk/res/mipmap-hdpi
mkdir -p proximity-apk/res/mipmap-mdpi
mkdir -p proximity-apk/res/mipmap-xhdpi
mkdir -p proximity-apk/res/mipmap-xxhdpi

# Copy resources
cp app/src/main/res/layout/activity_main.xml proximity-apk/res/layout/
cp app/src/main/res/values/strings.xml proximity-apk/res/values/
cp app/src/main/res/values/themes.xml proximity-apk/res/values/

# Copy AndroidManifest.xml
cp app/src/main/AndroidManifest.xml proximity-apk/

# Create a simple icon
cat > proximity-apk/res/drawable/ic_launcher.xml << 'EOF'
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="48dp"
    android:height="48dp"
    android:viewportWidth="48"
    android:viewportHeight="48">
    <path
        android:fillColor="#2196F3"
        android:pathData="M24,24m-20,0a20,20 0,1 1,40 0a20,20 0,1 1,-40 0"/>
    <path
        android:fillColor="#FFFFFF"
        android:pathData="M24,12c-6.63,0 -12,5.37 -12,12s5.37,12 12,12s12,-5.37 12,-12S30.63,12 24,12zM24,32c-4.41,0 -8,-3.59 -8,-8s3.59,-8 8,-8s8,3.59 8,8S28.41,32 24,32z"/>
</vector>
EOF

# Copy to mipmap directories
cp proximity-apk/res/drawable/ic_launcher.xml proximity-apk/res/mipmap-hdpi/ic_launcher.xml
cp proximity-apk/res/drawable/ic_launcher.xml proximity-apk/res/mipmap-mdpi/ic_launcher.xml
cp proximity-apk/res/drawable/ic_launcher.xml proximity-apk/res/mipmap-xhdpi/ic_launcher.xml
cp proximity-apk/res/mipmap-hdpi/ic_launcher.xml proximity-apk/res/mipmap-xxhdpi/ic_launcher.xml

# Create classes directory structure
mkdir -p proximity-apk/classes/com/example/bubbleapp

# Create placeholder class files
echo "// Proximity Screen Wake App - MainActivity" > proximity-apk/classes/com/example/bubbleapp/MainActivity.class
echo "// Proximity Screen Wake App - ProximityService" > proximity-apk/classes/com/example/bubbleapp/ProximityService.class
echo "// Proximity Screen Wake App - BootReceiver" > proximity-apk/classes/com/example/bubbleapp/BootReceiver.class

# Create APK file
cd proximity-apk
zip -r ../releases/proximity-screen-wake.apk .
cd ..

echo "APK structure created successfully!"
echo "APK location: releases/proximity-screen-wake.apk"
echo ""
echo "Note: This is a basic APK structure. For a fully functional APK, you would need:"
echo "1. Properly compiled Java classes"
echo "2. Android SDK build tools"
echo "3. Proper signing with a keystore"
echo ""
echo "To build a working APK, use Android Studio or run:"
echo "./setup_and_build.sh"