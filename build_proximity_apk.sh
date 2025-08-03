#!/bin/bash

echo "Building Proximity Lockscreen APK..."

# Set environment variables
export ANDROID_HOME=/usr/local/android-sdk
export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin

# Create a simple build approach
echo "Creating APK structure..."

# Create the APK directory structure
mkdir -p proximity-lockscreen-apk/META-INF/com/android
mkdir -p proximity-lockscreen-apk/assets
mkdir -p proximity-lockscreen-apk/res/drawable
mkdir -p proximity-lockscreen-apk/res/layout
mkdir -p proximity-lockscreen-apk/res/values
mkdir -p proximity-lockscreen-apk/res/mipmap-hdpi
mkdir -p proximity-lockscreen-apk/res/mipmap-mdpi
mkdir -p proximity-lockscreen-apk/res/mipmap-xhdpi
mkdir -p proximity-lockscreen-apk/res/mipmap-xxhdpi

# Copy the compiled classes (we'll need to compile them first)
echo "Compiling Java sources..."

# Try to use javac if available
if command -v javac &> /dev/null; then
    echo "Using system javac..."
    javac -cp "android.jar:androidx-appcompat-1.6.1.jar:androidx-core-1.10.1.jar" \
          -d proximity-lockscreen-apk/classes \
          app/src/main/java/com/example/bubbleapp/*.java
else
    echo "javac not found, creating placeholder..."
    mkdir -p proximity-lockscreen-apk/classes/com/example/bubbleapp
    echo "// Placeholder for compiled classes" > proximity-lockscreen-apk/classes/com/example/bubbleapp/MainActivity.class
    echo "// Placeholder for compiled classes" > proximity-lockscreen-apk/classes/com/example/bubbleapp/ProximityService.class
    echo "// Placeholder for compiled classes" > proximity-lockscreen-apk/classes/com/example/bubbleapp/BootReceiver.class
fi

# Copy resources
cp app/src/main/res/layout/activity_main.xml proximity-lockscreen-apk/res/layout/
cp app/src/main/res/values/strings.xml proximity-lockscreen-apk/res/values/
cp app/src/main/res/values/themes.xml proximity-lockscreen-apk/res/values/

# Copy AndroidManifest.xml
cp app/src/main/AndroidManifest.xml proximity-lockscreen-apk/

# Create a simple icon
echo "Creating app icon..."
cat > proximity-lockscreen-apk/res/drawable/ic_launcher.xml << 'EOF'
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
cp proximity-lockscreen-apk/res/drawable/ic_launcher.xml proximity-lockscreen-apk/res/mipmap-hdpi/ic_launcher.xml
cp proximity-lockscreen-apk/res/drawable/ic_launcher.xml proximity-lockscreen-apk/res/mipmap-mdpi/ic_launcher.xml
cp proximity-lockscreen-apk/res/drawable/ic_launcher.xml proximity-lockscreen-apk/res/mipmap-xhdpi/ic_launcher.xml
cp proximity-lockscreen-apk/res/mipmap-hdpi/ic_launcher.xml proximity-lockscreen-apk/res/mipmap-xxhdpi/ic_launcher.xml

echo "APK structure created successfully!"
echo "Note: This is a basic structure. For a fully functional APK, you would need:"
echo "1. Properly compiled Java classes"
echo "2. Android SDK build tools"
echo "3. Proper signing with a keystore"
echo ""
echo "The APK structure is ready in: proximity-lockscreen-apk/"
echo "To create a working APK, you would need to:"
echo "1. Install Android SDK build tools"
echo "2. Compile the Java sources"
echo "3. Package and sign the APK"