#!/bin/bash

echo "Creating a basic APK file..."

# Create APK directory structure
mkdir -p simple-apk/META-INF/com/android
mkdir -p simple-apk/res/layout
mkdir -p simple-apk/res/drawable
mkdir -p simple-apk/res/values
mkdir -p simple-apk/assets

# Create AndroidManifest.xml
cat > simple-apk/AndroidManifest.xml << 'EOF'
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.bubbleapp"
    android:versionCode="1"
    android:versionName="1.0">

    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/Theme.BubbleApp">

        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:theme="@style/Theme.BubbleApp">
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

# Create a simple APK info file
cat > simple-apk/APK_INFO.txt << 'EOF'
BUBBLE FLOATING APP - APK INFO

This is a placeholder APK structure for the bubble floating app.

ACTUAL APK BUILDING:
To build the real APK, you need:
1. Android Studio installed
2. Android SDK installed
3. Run: ./gradlew assembleRelease

The actual APK will be ~1.5MB and include:
- Floating bubble overlay
- Draggable functionality
- "Hello Sagar" tap response
- Minimal size optimization

This placeholder shows the APK structure but is not installable.
EOF

# Create a zip file that mimics APK structure
cd simple-apk
zip -r ../bubble-app-placeholder.apk . > /dev/null 2>&1
cd ..

echo "Created bubble-app-placeholder.apk"
echo "Note: This is a placeholder APK structure, not a real installable APK"
echo ""
echo "To build the real APK:"
echo "1. Install Android Studio"
echo "2. Open this project"
echo "3. Run: ./gradlew assembleRelease"
echo "4. Real APK will be in: app/build/outputs/apk/release/"