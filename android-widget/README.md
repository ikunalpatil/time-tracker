# Digital Clock Widget

A simple and lightweight Android home screen widget that displays the current time and date in digital format with accurate phone time synchronization.

## Features

- **Lightweight**: Minimal resource usage and battery impact
- **Accurate Time Sync**: Automatically syncs with phone's time and timezone
- **Real-time Updates**: Updates every 30 seconds and responds to time changes
- **Clean Design**: Modern, readable digital clock display with date
- **Auto-updates**: Responds to time zone changes, manual time changes, and system time updates
- **Resizable**: Can be resized horizontally and vertically on home screen
- **Dark Theme**: Semi-transparent dark background with white text for readability

## Enhanced Time Features

- **Phone Time Sync**: Always displays the exact phone time
- **Timezone Aware**: Automatically adjusts for timezone changes
- **System Integration**: Responds to system time broadcasts (TIME_TICK, TIME_SET, TIMEZONE_CHANGED)
- **Accurate Updates**: Uses Calendar API with proper timezone handling
- **Error Handling**: Fallback mechanisms for reliable time display

## Installation

1. Open the project in Android Studio
2. Build and install the app on your Android device
3. Long-press on your home screen
4. Select "Widgets" from the menu
5. Find "Digital Clock Widget" and drag it to your home screen

## Technical Details

- **Minimum SDK**: Android 5.0 (API 21)
- **Target SDK**: Android 14 (API 34)
- **Update Frequency**: Every 30 seconds + system time broadcasts
- **Permissions**: None required

## Widget Display

The widget shows:
- **Time**: In HH:MM format (24-hour) - synced with phone time
- **Date**: Day of week and date (e.g., "Mon, Jan 1") - uses phone's locale

## Time Synchronization

The widget uses multiple methods to ensure accurate time display:
1. **System Calendar**: Uses Calendar.getInstance() with proper timezone
2. **Broadcast Receivers**: Listens for TIME_TICK, TIME_SET, TIMEZONE_CHANGED
3. **AlarmManager**: Regular updates every 30 seconds for accuracy
4. **Error Handling**: Fallback to Date() if Calendar fails

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