#!/bin/bash

# AOD Clock Build Script
echo "Building AOD Clock APK..."

# Set Android SDK environment
export ANDROID_HOME=/workspace/android-sdk
export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools

# Clean and build
./gradlew clean assembleRelease

# Check if build was successful
if [ $? -eq 0 ]; then
    echo "Build successful!"
    
    # Copy APK to workspace root
    cp app/build/outputs/apk/release/app-release-unsigned.apk aod-clock.apk
    
    # Show APK size
    echo "APK size:"
    ls -lh aod-clock.apk
    
    echo "AOD Clock APK is ready: aod-clock.apk"
else
    echo "Build failed!"
    exit 1
fi