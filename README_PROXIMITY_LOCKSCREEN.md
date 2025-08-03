# Proximity Screen Wake Android App

A lightweight Android application that uses the proximity sensor to turn on the screen for 5 seconds when you wave your hand over it, specifically designed for lockscreen functionality.

## Features

- **Proximity Sensor Integration**: Monitors the device's proximity sensor for hand gestures
- **Screen Wake Control**: Automatically turns on the screen when proximity is detected
- **5-Second Timer**: Keeps the screen on for exactly 5 seconds after detection
- **Ultra Lightweight**: Minimal resource usage and small APK size
- **Auto-start on Boot**: Automatically starts the service when the device boots
- **Foreground Service**: Runs as a foreground service with persistent notification
- **Debounced Detection**: Prevents rapid triggering with 500ms debounce

## How It Works

1. **Sensor Monitoring**: The app continuously monitors the proximity sensor
2. **Gesture Detection**: When an object (hand) is detected near the sensor, it triggers the screen wake
3. **Screen Wake**: Turns on the screen and sets brightness to maximum for visibility
4. **5-Second Timer**: Keeps the screen on for exactly 5 seconds
5. **Auto Turn Off**: Automatically turns off the screen after 5 seconds
6. **Lockscreen Compatible**: Works specifically when the device is locked or screen is off

## Permissions Required

- `android.permission.SYSTEM_ALERT_WINDOW` - For overlay functionality
- `android.permission.FOREGROUND_SERVICE` - To run as foreground service
- `android.permission.WAKE_LOCK` - To keep the service running and wake screen
- `android.permission.DISABLE_KEYGUARD` - For lockscreen interaction
- `android.permission.RECEIVE_BOOT_COMPLETED` - For auto-start on boot
- `android.permission.WRITE_SETTINGS` - For brightness control
- `android.hardware.sensor.proximity` - Hardware feature requirement

## Installation

### Prerequisites

1. **Android Studio** or **Android SDK** installed
2. **Java Development Kit (JDK)** 8 or higher
3. **Android device** with proximity sensor

### Building the APK

#### Method 1: Using Android Studio

1. Clone or download this project
2. Open the project in Android Studio
3. Wait for Gradle sync to complete
4. Go to `Build` → `Build Bundle(s) / APK(s)` → `Build APK(s)`
5. The signed APK will be generated in `app/build/outputs/apk/release/`

#### Method 2: Using Command Line

1. Ensure you have Android SDK installed and configured
2. Set environment variables:
   ```bash
   export ANDROID_HOME=/path/to/android/sdk
   export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin
   ```

3. Accept SDK licenses:
   ```bash
   yes | sdkmanager --licenses
   ```

4. Install required SDK components:
   ```bash
   sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0"
   ```

5. Build the APK:
   ```bash
   ./gradlew assembleRelease
   ```

#### Method 3: Using the Build Script

1. Run the provided build script:
   ```bash
   chmod +x setup_and_build.sh
   ./setup_and_build.sh
   ```

2. This will automatically set up the SDK and build the APK

## Installation on Device

1. **Enable Unknown Sources**: Go to Settings → Security → Unknown Sources
2. **Install APK**: Transfer and install the generated APK file
3. **Grant Permissions**: 
   - Allow overlay permissions when prompted
   - Grant system alert window permission
   - Grant write settings permission for brightness control
4. **Start Service**: Open the app and tap "Start Service"

## Usage

1. **Launch the App**: Open "Proximity Screen Wake" from your app drawer
2. **Start Service**: Tap the "Start Service" button
3. **Grant Permissions**: Follow the prompts to grant necessary permissions
4. **Test Functionality**: 
   - Lock your device or let the screen turn off
   - Wave your hand over the proximity sensor (usually near the earpiece)
   - The screen should turn on and stay on for 5 seconds
5. **Observe**: The screen will automatically turn off after 5 seconds

## Configuration

### Auto-start on Boot

The app automatically starts on device boot. To disable this:
1. Go to your device's app settings
2. Find "Proximity Screen Wake"
3. Disable "Auto-start" or "Start on boot"

### Service Management

- **Start Service**: Tap "Start Service" in the app
- **Stop Service**: Tap "Stop Service" in the app
- **Service Status**: The app shows current service status

## Troubleshooting

### Common Issues

1. **Screen doesn't turn on when proximity detected**:
   - Ensure the service is running (check notification)
   - Verify proximity sensor permissions
   - Check if device has a working proximity sensor
   - Test with another proximity sensor app

2. **Screen turns off immediately**:
   - Check battery optimization settings
   - Disable battery optimization for this app
   - Ensure the app is not being killed by the system
   - Verify wake lock permissions

3. **Service stops unexpectedly**:
   - Check battery optimization settings
   - Disable battery optimization for this app
   - Ensure the app is not being killed by the system
   - Check device's aggressive battery saving features

4. **App crashes on startup**:
   - Clear app data and cache
   - Reinstall the app
   - Check device compatibility (requires Android 6.0+)

### Device Compatibility

- **Minimum Android Version**: 6.0 (API 23)
- **Target Android Version**: 14 (API 34)
- **Required Hardware**: Proximity sensor
- **Recommended**: Devices with reliable proximity sensors

## Technical Details

### Architecture

- **MainActivity**: User interface for service control
- **ProximityService**: Background service handling sensor events and screen control
- **BootReceiver**: Broadcast receiver for auto-start functionality

### Key Components

1. **SensorManager**: Manages proximity sensor events
2. **PowerManager**: Handles wake locks and screen control
3. **Settings.System**: Controls screen brightness
4. **NotificationManager**: Manages foreground service notification
5. **Handler**: Manages 5-second timer for screen control

### Performance Optimizations

- **Debounced Events**: 500ms debounce prevents rapid triggering
- **Efficient Sensor Usage**: Uses SENSOR_DELAY_NORMAL for optimal performance
- **Minimal Resource Usage**: Lightweight service with minimal memory footprint
- **ProGuard Optimization**: Code obfuscation and size reduction
- **Timer Management**: Efficient handling of screen on/off timing

## Security Considerations

- **Minimal Permissions**: Only requests necessary permissions
- **No Data Collection**: Does not collect or transmit user data
- **Local Processing**: All sensor processing happens locally
- **Secure Implementation**: Follows Android security best practices

## License

This project is provided as-is for educational and personal use.

## Support

For issues or questions:
1. Check the troubleshooting section above
2. Verify device compatibility
3. Ensure all permissions are granted
4. Test with a different device if possible

## Future Enhancements

Potential improvements for future versions:
- Customizable screen on duration
- Gesture pattern recognition
- Integration with other sensors
- Advanced power management
- User interface improvements
- Multiple proximity detection patterns