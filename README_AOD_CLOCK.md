# AOD Clock - Always On Display Digital Clock

An ultra-lightweight Android application that provides an Always On Display (AOD) digital clock for Samsung phones and other Android devices.

## Features

- **Live Digital Clock**: Displays current time in HH:mm format
- **Date Display**: Shows current date in dd-MMM-yy format (e.g., 02-Aug-24)
- **Bold Roboto Font**: Clean, readable typography using system fonts
- **Ultra-Lightweight**: APK size only ~623KB
- **Always On Display**: Keeps screen active and displays over lock screen
- **Fullscreen Mode**: Immersive experience with hidden system UI
- **Background Service**: Continues running even when app is in background

## Technical Specifications

- **Target SDK**: Android 34 (Android 14)
- **Minimum SDK**: Android 23 (Android 6.0)
- **APK Size**: ~623KB
- **Permissions Required**:
  - SYSTEM_ALERT_WINDOW (to display over other apps)
  - FOREGROUND_SERVICE (for background operation)
  - WAKE_LOCK (to keep screen on)
  - DISABLE_KEYGUARD (to display over lock screen)

## Installation

1. Enable "Install from Unknown Sources" in your Android device settings
2. Transfer the `aod-clock.apk` file to your device
3. Install the APK file
4. Grant the required permissions when prompted
5. Launch the app

## Usage

1. **Launch the App**: Tap the AOD Clock icon to start
2. **Grant Permissions**: Allow overlay permissions when prompted
3. **Enjoy**: The digital clock will display in fullscreen mode
4. **Background Operation**: The app will continue running in the background

## Features for Samsung Phones

- Optimized for Samsung's Always On Display feature
- Compatible with Samsung's screen timeout settings
- Works with Samsung's battery optimization features
- Designed for Samsung's AMOLED displays

## Customization

The app uses system fonts and colors for optimal performance:
- Time: 72sp bold sans-serif-light font
- Date: 18sp normal sans-serif-light font
- Background: Black for OLED power efficiency
- Text: White for maximum contrast

## Technical Details

- **MainActivity**: Handles the UI and time updates
- **AODService**: Background service for continuous operation
- **Layout**: Simple RelativeLayout with centered text views
- **Optimization**: Aggressive ProGuard rules for minimal APK size
- **Dependencies**: Only androidx.appcompat for compatibility

## Troubleshooting

- **App not displaying**: Check overlay permissions in Settings > Apps > AOD Clock > Permissions
- **Screen turns off**: Ensure "Keep screen on" is enabled in the app
- **Battery drain**: The app is optimized for minimal battery usage with black background

## License

This application is provided as-is for educational and personal use.