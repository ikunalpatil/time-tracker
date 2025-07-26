# 🔧 APK Installation Troubleshooting Guide

## 🚨 "App Not Installed" Error - Complete Solutions

### **Common Causes & Solutions:**

#### 1. **"App not installed" Error**
**Causes:**
- APK is corrupted or incomplete
- Device compatibility issues
- Permission problems
- Storage space issues

**Solutions:**
```
✅ Try these APK files in order:
1. bubble-app-signed.apk (2.6MB) - RECOMMENDED
2. bubble-app-working.apk (2.6MB) - Alternative
3. Use ADB installation method (see below)
```

#### 2. **"Parse error" or "Corrupted APK"**
**Causes:**
- Incomplete download
- File corruption during transfer
- Wrong file format

**Solutions:**
```
✅ Re-download the APK file
✅ Check file size (should be ~2.6MB, not 2.3KB)
✅ Try downloading from different browser
✅ Clear browser cache and try again
```

#### 3. **"App not compatible"**
**Causes:**
- Android version too old
- Architecture mismatch (ARM vs x86)
- Screen density issues

**Solutions:**
```
✅ Check Android version (needs 5.0+)
✅ Try different APK versions
✅ Use ADB installation (see below)
```

#### 4. **"Blocked by Play Protect"**
**Causes:**
- Google Play Protect blocking unknown sources
- Self-signed certificate warning

**Solutions:**
```
✅ Tap "Install anyway" when prompted
✅ Temporarily disable Play Protect
✅ Add app to Play Protect exceptions
```

## 🔧 **Alternative Installation Methods**

### **Method 1: ADB Installation (Most Reliable)**

**Requirements:**
- Computer with ADB installed
- USB cable
- Developer options enabled on phone

**Steps:**
```bash
# 1. Enable Developer Options on your phone
# Settings → About Phone → Tap "Build Number" 7 times

# 2. Enable USB Debugging
# Settings → Developer Options → USB Debugging

# 3. Connect phone to computer via USB

# 4. Download APK to computer

# 5. Install via ADB
adb install bubble-app-signed.apk
```

### **Method 2: File Manager Installation**

**Steps:**
```
1. Download APK to phone storage
2. Use built-in file manager (not browser)
3. Navigate to Downloads folder
4. Tap APK file
5. Allow installation from this source
6. Install
```

### **Method 3: Browser Download + Install**

**Steps:**
```
1. Use Chrome or Firefox browser
2. Download APK directly to phone
3. Open Downloads in browser
4. Tap downloaded APK
5. Install
```

## 📱 **Device-Specific Solutions**

### **Samsung Devices:**
```
✅ Settings → Biometrics and Security → Install unknown apps
✅ Allow your browser/file manager
✅ Try Samsung's "My Files" app
```

### **Huawei Devices:**
```
✅ Settings → Security → More security settings
✅ Install apps from external sources
✅ Allow your browser/file manager
```

### **Xiaomi Devices:**
```
✅ Settings → Privacy protection → Special permissions
✅ Install unknown apps
✅ Allow your browser/file manager
```

### **OnePlus Devices:**
```
✅ Settings → Security → Install unknown apps
✅ Allow your browser/file manager
```

## 🔍 **Diagnostic Steps**

### **Step 1: Check Your Device**
```
📱 Android Version: Settings → About Phone → Android Version
💾 Storage Space: Settings → Storage → Available space
🔧 Architecture: Settings → About Phone → CPU info
```

### **Step 2: Check APK File**
```
📁 File Size: Should be ~2.6MB (not 2.3KB)
📄 File Extension: Should end with .apk
🔒 File Integrity: Try downloading again
```

### **Step 3: Check Permissions**
```
🔓 Unknown Sources: Settings → Security → Unknown Sources
📱 Overlay Permission: Will be requested after installation
```

## 🛠️ **Advanced Solutions**

### **If Nothing Works:**

#### **Option 1: Build from Source**
```bash
# Clone repository
git clone https://github.com/ikunalpatil/time-tracker.git

# Open in Android Studio
# Build → Build Bundle(s) / APK(s) → Build APK(s)
```

#### **Option 2: Use Alternative App Stores**
```
✅ F-Droid
✅ APKMirror
✅ APKPure
```

#### **Option 3: Contact Support**
```
📧 Create issue on GitHub
📱 Provide device details:
   - Android version
   - Device model
   - Error message
   - Steps taken
```

## 📋 **Quick Checklist**

Before giving up, try these in order:

- [ ] **Download** `bubble-app-signed.apk` (2.6MB)
- [ ] **Enable** "Install from unknown sources"
- [ ] **Allow** your browser/file manager
- [ ] **Check** file size is ~2.6MB
- [ ] **Try** different browser
- [ ] **Clear** browser cache
- [ ] **Use** built-in file manager
- [ ] **Try** ADB installation
- [ ] **Check** Android version (5.0+)
- [ ] **Free up** storage space

## 🆘 **Still Having Issues?**

**Provide these details for better help:**
```
📱 Device Model: [Your phone model]
🤖 Android Version: [Your Android version]
📦 APK File: [Which APK you tried]
❌ Error Message: [Exact error text]
🔧 Steps Taken: [What you already tried]
```

---

**Repository**: https://github.com/ikunalpatil/time-tracker  
**APK Files**: `releases/` folder  
**Last Updated**: July 26, 2024