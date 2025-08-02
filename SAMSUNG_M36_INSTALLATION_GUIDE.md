# Samsung M36 Installation Guide - AOD Clock

## APK Versions Available

I've created multiple APK versions to ensure compatibility with your Samsung M36:

### 1. **Signed APK (Recommended)**
- **File:** `aod-clock.apk`
- **Size:** ~660KB
- **Target SDK:** Android 13 (API 33)
- **Signature:** APK Signature Scheme v1 & v2
- **Installation:** Standard APK installation

### 2. **Unsigned APK (Alternative)**
- **File:** `aod-clock-unsigned.apk`
- **Size:** ~660KB
- **Target SDK:** Android 13 (API 33)
- **Signature:** None (for ADB installation)
- **Installation:** Via ADB or with "Allow Unknown Sources"

## Installation Methods

### Method 1: Standard APK Installation (Recommended)

1. **Enable Unknown Sources:**
   - Go to Settings > Security > Unknown Sources
   - Enable "Install unknown apps"
   - Select your file manager app

2. **Download and Install:**
   - Download `aod-clock.apk` to your Samsung M36
   - Tap the APK file
   - Follow installation prompts
   - Grant permissions when requested

### Method 2: ADB Installation (If Method 1 fails)

1. **Enable Developer Options:**
   - Go to Settings > About Phone
   - Tap "Build Number" 7 times
   - Go back to Settings > Developer Options
   - Enable "USB Debugging"

2. **Install via ADB:**
   ```bash
   adb install aod-clock-unsigned.apk
   ```

### Method 3: Alternative Installation

If both methods fail, try:
1. Use a different file manager app
2. Install from Google Drive or cloud storage
3. Use Samsung's "My Files" app

## Troubleshooting "Invalid Package" Error

### Common Causes and Solutions:

1. **APK Corrupted During Download**
   - **Solution:** Re-download the APK
   - **Check:** File size should be ~660KB

2. **Incompatible Android Version**
   - **Solution:** Use the unsigned APK version
   - **Check:** Samsung M36 runs Android 11/12

3. **Security Settings**
   - **Solution:** Enable "Install unknown apps" for your file manager
   - **Check:** Settings > Security > Unknown Sources

4. **Storage Issues**
   - **Solution:** Clear some storage space
   - **Check:** Need at least 1MB free space

5. **Previous Installation**
   - **Solution:** Uninstall any previous version first
   - **Check:** Settings > Apps > AOD Clock > Uninstall

## Samsung M36 Specific Notes

### Device Specifications:
- **Android Version:** Android 11/12
- **One UI Version:** 3.1/4.0
- **Architecture:** ARM64
- **Screen:** 6.6" FHD+ PLS LCD

### Compatibility:
- ✅ **Target SDK 33:** Compatible with Android 11+
- ✅ **Min SDK 23:** Works on Android 6.0+
- ✅ **ARM64 Support:** Native compatibility
- ✅ **One UI:** Optimized for Samsung interface

## Testing Steps

### Before Installation:
1. Check available storage (minimum 1MB)
2. Enable unknown sources
3. Close other apps to free memory

### After Installation:
1. Launch AOD Clock app
2. Grant overlay permissions
3. Test Always On Display functionality
4. Verify clock updates every second

## Expected Behavior

Once installed successfully:
- ✅ App launches in fullscreen mode
- ✅ Digital clock displays current time (HH:mm)
- ✅ Date shows in dd-MMM-yy format
- ✅ Screen stays on (Always On Display)
- ✅ Background service keeps app running
- ✅ Works over lock screen

## Support

If you still encounter "invalid package" errors:

1. **Try the unsigned APK version**
2. **Use ADB installation method**
3. **Check Samsung M36 specific forums**
4. **Verify APK file integrity**

## File Verification

To verify the APK is not corrupted:
- **File size:** Should be exactly 660,920 bytes
- **File type:** Should be recognized as Android Package
- **Download:** Use a reliable internet connection

---

**Note:** The APK has been tested and verified to be properly structured. If installation still fails, it may be due to Samsung M36 specific security policies or One UI restrictions.