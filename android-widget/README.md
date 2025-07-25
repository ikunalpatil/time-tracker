# Digital Clock Widget - Ultra-Lightweight Edition

An **ULTRA-LIGHTWEIGHT** Android home screen widget that displays the current time and date in digital format with perfect phone time synchronization - now only **16 KB**!

## 🚀 AMAZING SIZE REDUCTION!

- **Previous version**: 5.5 MB
- **New ultra-lightweight**: **16 KB** 
- **Size reduction**: **99.7%** smaller!
- **Release version**: **8 KB** (unsigned)

## Features

- **Ultra-Lightweight**: Only 16 KB - smaller than most image files!
- **Zero Dependencies**: Uses only Android framework, no external libraries
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
- **Minimal Code**: Optimized and simplified implementation

## Installation

1. Open the project in Android Studio
2. Build and install the app on your Android device
3. Long-press on your home screen
4. Select "Widgets" from the menu
5. Find "Digital Clock Widget" and drag it to your home screen

## Technical Details

- **APK Size**: **16 KB** (debug) / **8 KB** (release)
- **Minimum SDK**: Android 5.0 (API 21)
- **Target SDK**: Android 14 (API 34)
- **Update Frequency**: Every 30 seconds + system time broadcasts
- **Permissions**: None required
- **Dependencies**: None (pure Android framework)

## Widget Display

The widget shows:
- **Time**: In HH:MM format (24-hour) - synced with phone time
- **Date**: Day of week and date (e.g., "Mon, Jan 1") - uses phone's locale

## Ultra-Lightweight Optimizations

This version achieves its tiny size through:
1. **Zero Dependencies**: Removed all external libraries (AppCompat, Material Design)
2. **Minimal Theme**: Uses standard Android framework themes
3. **Aggressive ProGuard**: Maximum code shrinking and obfuscation
4. **Resource Optimization**: Removed unnecessary resources and icons
5. **Simplified Code**: Streamlined widget implementation
6. **No Backup Rules**: Removed unnecessary XML configuration files

## Size Comparison

| Version | Size | Reduction |
|---------|------|-----------|
| Original with dependencies | 5.5 MB | - |
| **Ultra-lightweight** | **16 KB** | **99.7%** |
| Release (unsigned) | **8 KB** | **99.85%** |

## Time Synchronization

The widget uses multiple methods to ensure accurate time display:
1. **System Calendar**: Uses Calendar.getInstance() with proper timezone
2. **Broadcast Receivers**: Listens for TIME_TICK, TIME_SET, TIMEZONE_CHANGED
3. **AlarmManager**: Regular updates every 30 seconds for accuracy
4. **Minimal Overhead**: Optimized for maximum efficiency

## Building Ultra-Lightweight APK

To build the ultra-lightweight version:

```bash
cd android-widget
./gradlew assembleDebug    # 16 KB signed APK
./gradlew assembleRelease  # 8 KB unsigned APK
```

## License

Open source - feel free to use and modify as needed.