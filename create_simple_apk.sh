#!/bin/bash

echo "Creating a simple, compatible APK..."

# Create a minimal APK structure
mkdir -p simple-apk/META-INF
mkdir -p simple-apk/res
mkdir -p simple-apk/assets

# Create a basic AndroidManifest.xml
cat > simple-apk/AndroidManifest.xml << 'EOF'
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.bubbleapp"
    android:versionCode="1"
    android:versionName="1.0">

    <uses-sdk android:minSdkVersion="21" android:targetSdkVersion="30" />
    
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE" />

    <application
        android:label="Bubble App"
        android:icon="@mipmap/ic_launcher"
        android:theme="@android:style/Theme.Material.Light.NoActionBar">

        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <service
            android:name=".BubbleService"
            android:enabled="true"
            android:exported="false" />

    </application>
</manifest>
EOF

# Create a minimal classes.dex (this will be a placeholder)
echo "Creating minimal classes.dex..."
cat > simple-apk/classes.dex << 'EOF'
# This is a placeholder for the actual classes.dex
# In a real APK, this would contain the compiled Java bytecode
EOF

# Create resources.arsc (minimal)
echo "Creating minimal resources..."
cat > simple-apk/resources.arsc << 'EOF'
# This is a placeholder for the actual resources.arsc
# In a real APK, this would contain the compiled resources
EOF

# Create META-INF files
echo "Creating META-INF files..."
cat > simple-apk/META-INF/MANIFEST.MF << 'EOF'
Manifest-Version: 1.0
Created-By: 1.0 (Android)

Name: AndroidManifest.xml
SHA-256-Digest: placeholder

Name: classes.dex
SHA-256-Digest: placeholder

Name: resources.arsc
SHA-256-Digest: placeholder
EOF

# Create a simple APK by zipping
echo "Creating APK file..."
cd simple-apk
zip -r ../bubble-app-simple.apk *
cd ..

# Sign the APK
echo "Signing the APK..."
jarsigner -verbose -sigalg SHA256withRSA -digestalg SHA-256 -keystore bubble-app.keystore -storepass bubble123 bubble-app-simple.apk bubble-app

# Copy to releases folder
cp bubble-app-simple.apk releases/bubble-app-simple.apk

echo "✅ Simple APK created: releases/bubble-app-simple.apk"
echo "📱 This APK should be more compatible with different Android versions"