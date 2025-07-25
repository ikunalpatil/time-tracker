# Digital Clock Widget

A simple and lightweight Android home screen widget that displays the current time and date in digital format.

## Features

- **Lightweight**: Minimal resource usage and battery impact
- **Clean Design**: Modern, readable digital clock display with date
- **Auto-updates**: Updates every minute to show current time
- **Resizable**: Can be resized horizontally and vertically on home screen
- **Dark Theme**: Semi-transparent dark background with white text for readability

## Installation

1. Open the project in Android Studio
2. Build and install the app on your Android device
3. Long-press on your home screen
4. Select "Widgets" from the menu
5. Find "Digital Clock Widget" and drag it to your home screen

## Technical Details

- **Minimum SDK**: Android 5.0 (API 21)
- **Target SDK**: Android 14 (API 34)
- **Update Frequency**: Every 60 seconds
- **Permissions**: None required

## Widget Display

The widget shows:
- **Time**: In HH:MM format (24-hour)
- **Date**: Day of week and date (e.g., "Mon, Jan 1")

## File Structure

```
android-widget/
├── app/
│   ├── build.gradle
│   ├── proguard-rules.pro
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/timewidget/digitalclock/
│       │   └── DigitalClockWidget.java
│       └── res/
│           ├── drawable/
│           │   ├── ic_launcher_background.xml
│           │   ├── ic_launcher_foreground.xml
│           │   └── widget_background.xml
│           ├── layout/
│           │   └── digital_clock_widget.xml
│           ├── mipmap-anydpi-v26/
│           │   ├── ic_launcher.xml
│           │   └── ic_launcher_round.xml
│           ├── values/
│           │   ├── colors.xml
│           │   ├── strings.xml
│           │   └── themes.xml
│           └── xml/
│               ├── backup_rules.xml
│               ├── data_extraction_rules.xml
│               └── digital_clock_widget_info.xml
├── build.gradle
├── gradle.properties
└── settings.gradle
```

## Building

To build the project:

```bash
cd android-widget
./gradlew assembleDebug
```

## License

Open source - feel free to use and modify as needed.