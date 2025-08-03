#!/bin/bash

echo "Creating a simple test APK for verification..."

# Create a minimal test APK structure
mkdir -p test-apk/META-INF/com/android
mkdir -p test-apk/assets
mkdir -p test-apk/res/drawable
mkdir -p test-apk/res/layout
mkdir -p test-apk/res/values
mkdir -p test-apk/res/mipmap-hdpi
mkdir -p test-apk/res/mipmap-mdpi
mkdir -p test-apk/res/mipmap-xhdpi
mkdir -p test-apk/res/mipmap-xxhdpi

# Create a very simple AndroidManifest.xml
cat > test-apk/AndroidManifest.xml << 'EOF'
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.testapp">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/Theme.AppCompat.Light">

        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

    </application>

</manifest>
EOF

# Create a simple layout
cat > test-apk/res/layout/activity_main.xml << 'EOF'
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical"
    android:padding="32dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Test App"
        android:textSize="24sp"
        android:textStyle="bold"
        android:layout_marginBottom="16dp" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="This is a test APK to verify installation"
        android:textSize="16sp"
        android:gravity="center" />

</LinearLayout>
EOF

# Create strings.xml
cat > test-apk/res/values/strings.xml << 'EOF'
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="app_name">Test App</string>
</resources>
EOF

# Create a simple icon
cat > test-apk/res/drawable/ic_launcher.xml << 'EOF'
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="48dp"
    android:height="48dp"
    android:viewportWidth="48"
    android:viewportHeight="48">
    <path
        android:fillColor="#4CAF50"
        android:pathData="M24,24m-20,0a20,20 0,1 1,40 0a20,20 0,1 1,-40 0"/>
    <path
        android:fillColor="#FFFFFF"
        android:pathData="M24,12c-6.63,0 -12,5.37 -12,12s5.37,12 12,12s12,-5.37 12,-12S30.63,12 24,12zM24,32c-4.41,0 -8,-3.59 -8,-8s3.59,-8 8,-8s8,3.59 8,8S28.41,32 24,32z"/>
</vector>
EOF

# Copy icon to mipmap directories
cp test-apk/res/drawable/ic_launcher.xml test-apk/res/mipmap-hdpi/ic_launcher.xml
cp test-apk/res/drawable/ic_launcher.xml test-apk/res/mipmap-mdpi/ic_launcher.xml
cp test-apk/res/drawable/ic_launcher.xml test-apk/res/mipmap-xhdpi/ic_launcher.xml
cp test-apk/res/mipmap-hdpi/ic_launcher.xml test-apk/res/mipmap-xxhdpi/ic_launcher.xml

# Create classes directory structure
mkdir -p test-apk/classes/com/example/testapp

# Create a simple DEX file structure (this is a placeholder)
echo "// Test App Classes" > test-apk/classes/com/example/testapp/MainActivity.class

# Create APK file
cd test-apk
zip -r ../releases/test-app.apk .
cd ..

echo "Test APK created: releases/test-app.apk"
echo ""
echo "This is a minimal test APK to verify the installation process."
echo "If this APK installs successfully, we know the issue is with our main APK."
echo "If this APK also fails, we know there's a broader issue with the APK structure."