# Proximity Lockscreen Android App

A lightweight Android application that uses the proximity sensor to lighten the lockscreen when you wave your hand over it.

## Features

- **Proximity Sensor Integration**: Monitors the device's proximity sensor for hand gestures
- **Lockscreen Brightness Control**: Automatically increases screen brightness when proximity is detected
- **Ultra Lightweight**: Minimal resource usage and small APK size
- **Auto-start on Boot**: Automatically starts the service when the device boots
- **Foreground Service**: Runs as a foreground service with persistent notification
- **Debounced Detection**: Prevents rapid triggering with 500ms debounce

## How It Works

1. **Sensor Monitoring**: The app continuously monitors the proximity sensor
2. **Gesture Detection**: When an object (hand) is detected near the sensor, it triggers the brightness increase
3. **Brightness Control**: Sets screen brightness to maximum (255) when proximity is detected
4. **Restoration**: Returns brightness to moderate level (128) when proximity is no longer detected
5. **Screen Wake**: Wakes up the screen if it's locked

## Permissions Required

- `android.permission.SYSTEM_ALERT_WINDOW` - For overlay functionality
- `android.permission.FOREGROUND_SERVICE` - To run as foreground service
- `android.permission.WAKE_LOCK` - To keep the service running
- `android.permission.DISABLE_KEYGUARD` - For lockscreen interaction
- `android.permission.RECEIVE_BOOT_COMPLETED` - For auto-start on boot
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
   chmod +x build_proximity_apk.sh
   ./build_proximity_apk.sh
   ```

2. Note: This creates the APK structure but requires proper Android SDK setup for full compilation

## Installation on Device

1. **Enable Unknown Sources**: Go to Settings → Security → Unknown Sources
2. **Install APK**: Transfer and install the generated APK file
3. **Grant Permissions**: 
   - Allow overlay permissions when prompted
   - Grant system alert window permission
4. **Start Service**: Open the app and tap "Start Service"

## Usage

1. **Launch the App**: Open "Proximity Lockscreen" from your app drawer
2. **Start Service**: Tap the "Start Service" button
3. **Grant Permissions**: Follow the prompts to grant necessary permissions
4. **Test Functionality**: Wave your hand over the proximity sensor (usually near the earpiece)
5. **Observe**: The lockscreen should brighten when proximity is detected

## Configuration

### Auto-start on Boot

The app automatically starts on device boot. To disable this:
1. Go to your device's app settings
2. Find "Proximity Lockscreen"
3. Disable "Auto-start" or "Start on boot"

### Service Management

- **Start Service**: Tap "Start Service" in the app
- **Stop Service**: Tap "Stop Service" in the app
- **Service Status**: The app shows current service status

## Troubleshooting

### Common Issues

1. **App doesn't respond to proximity sensor**:
   - Ensure the service is running (check notification)
   - Verify proximity sensor permissions
   - Test with another proximity sensor app

2. **Brightness doesn't change**:
   - Check if auto-brightness is enabled (disable it)
   - Verify system settings permissions
   - Some devices may require additional permissions

3. **Service stops unexpectedly**:
   - Check battery optimization settings
   - Disable battery optimization for this app
   - Ensure the app is not being killed by the system

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
- **ProximityService**: Background service handling sensor events
- **BootReceiver**: Broadcast receiver for auto-start functionality

### Key Components

1. **SensorManager**: Manages proximity sensor events
2. **PowerManager**: Handles wake locks and screen control
3. **Settings.System**: Controls screen brightness
4. **NotificationManager**: Manages foreground service notification

### Performance Optimizations

- **Debounced Events**: 500ms debounce prevents rapid triggering
- **Efficient Sensor Usage**: Uses SENSOR_DELAY_NORMAL for optimal performance
- **Minimal Resource Usage**: Lightweight service with minimal memory footprint
- **ProGuard Optimization**: Code obfuscation and size reduction

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
- Customizable brightness levels
- Gesture pattern recognition
- Integration with other sensors
- Advanced power management
- User interface improvements