# Bubble Floating Android App

A lightweight Android application that creates a floating bubble overlay. When you tap the bubble, it displays "Hello Sagar".

## Features

- Floating bubble overlay that stays on top of other apps
- Draggable bubble (touch and drag to move)
- Tap to show "Hello Sagar" message
- Minimal APK size with optimizations
- Works on Android 6.0 (API 23) and above

## How to Build

1. Open the project in Android Studio
2. Build the project: `./gradlew assembleRelease`
3. The APK will be generated in `app/build/outputs/apk/release/`

## How to Use

1. Install the APK on your Android device
2. Launch the app
3. Grant overlay permission when prompted
4. The bubble will appear on your screen
5. Drag the bubble to move it around
6. Tap the bubble to see "Hello Sagar"

## Permissions Required

- `SYSTEM_ALERT_WINDOW`: To display the floating bubble
- `FOREGROUND_SERVICE`: To keep the bubble service running

## Technical Details

- Minimum SDK: 23 (Android 6.0)
- Target SDK: 34 (Android 14)
- Uses WindowManager for overlay functionality
- Optimized with ProGuard for minimal APK size
- Material Design components for modern UI

## File Structure

```
app/
├── src/main/
│   ├── java/com/example/bubbleapp/
│   │   ├── MainActivity.java
│   │   └── BubbleService.java
│   └── res/
│       ├── layout/
│       │   ├── activity_main.xml
│       │   └── bubble_layout.xml
│       ├── drawable/
│       │   └── bubble_background.xml
│       └── values/
│           ├── strings.xml
│           └── themes.xml
└── build.gradle
```

The app is designed to be as lightweight as possible while maintaining functionality.
