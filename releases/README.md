# Bubble App Releases

## Available APK Files

### bubble-app-placeholder.apk
- **Size**: ~2.3KB
- **Type**: Placeholder APK structure
- **Purpose**: Demonstrates the APK structure but is not installable
- **Note**: This is a placeholder file showing the project structure

## How to Get the Real APK

To build the actual installable APK:

1. **Install Android Studio**
   - Download from: https://developer.android.com/studio

2. **Open this project in Android Studio**
   - File → Open → Select this project folder

3. **Build the APK**
   ```bash
   ./gradlew assembleRelease
   ```

4. **Find the real APK**
   - Location: `app/build/outputs/apk/release/app-release.apk`
   - Size: ~1.5MB (estimated)
   - Features: Fully functional bubble floating app

## Real APK Features

The actual APK will include:
- ✅ Floating bubble overlay
- ✅ Draggable bubble functionality
- ✅ Tap to show "Hello Sagar"
- ✅ Minimal size (~1.5MB)
- ✅ Works on Android 6.0+ (API 23+)

## Installation Instructions

1. Enable "Install from unknown sources" in Android settings
2. Install the APK on your device
3. Grant overlay permission when prompted
4. The bubble will appear on your screen
5. Drag to move, tap to see "Hello Sagar"

## Permissions Required

- `SYSTEM_ALERT_WINDOW`: For floating overlay
- `FOREGROUND_SERVICE`: To keep bubble running