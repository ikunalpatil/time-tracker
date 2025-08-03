#!/bin/bash

echo "=========================================="
echo "Proximity Lockscreen APK Builder"
echo "=========================================="

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print colored output
print_status() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Check if we're in the right directory
if [ ! -f "app/build.gradle" ]; then
    print_error "Please run this script from the project root directory"
    exit 1
fi

print_status "Checking system requirements..."

# Check for Java
if command -v java &> /dev/null; then
    JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2)
    print_success "Java found: $JAVA_VERSION"
else
    print_error "Java not found. Please install Java 8 or higher"
    exit 1
fi

# Check for Gradle
if command -v ./gradlew &> /dev/null; then
    print_success "Gradle wrapper found"
else
    print_error "Gradle wrapper not found. Please ensure gradlew is present"
    exit 1
fi

# Set up Android SDK
print_status "Setting up Android SDK..."

ANDROID_HOME="/usr/local/android-sdk"
if [ ! -d "$ANDROID_HOME" ]; then
    print_warning "Android SDK not found at $ANDROID_HOME"
    print_status "Creating Android SDK directory..."
    sudo mkdir -p "$ANDROID_HOME"
    sudo chown $USER:$USER "$ANDROID_HOME"
fi

# Download Android SDK if not present
if [ ! -d "$ANDROID_HOME/cmdline-tools" ]; then
    print_status "Downloading Android SDK Command Line Tools..."
    cd "$ANDROID_HOME"
    
    # Download command line tools
    wget -q https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip
    
    if [ $? -eq 0 ]; then
        print_success "Downloaded Android SDK Command Line Tools"
        
        # Extract and set up
        unzip -q commandlinetools-linux-11076708_latest.zip
        mkdir -p cmdline-tools
        mv cmdline-tools latest
        mv latest cmdline-tools/
        rm commandlinetools-linux-11076708_latest.zip
        
        print_success "Android SDK Command Line Tools extracted"
    else
        print_error "Failed to download Android SDK Command Line Tools"
        exit 1
    fi
    
    cd - > /dev/null
fi

# Set environment variables
export ANDROID_HOME="$ANDROID_HOME"
export PATH="$PATH:$ANDROID_HOME/cmdline-tools/latest/bin"

print_status "Accepting Android SDK licenses..."
echo "y" | sdkmanager --licenses > /dev/null 2>&1

print_status "Installing required Android SDK components..."
sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0" > /dev/null 2>&1

if [ $? -eq 0 ]; then
    print_success "Android SDK components installed"
else
    print_warning "Some SDK components may not have installed properly"
fi

# Update local.properties
print_status "Updating local.properties..."
echo "sdk.dir=$ANDROID_HOME" > local.properties
print_success "local.properties updated"

# Clean and build
print_status "Cleaning previous builds..."
./gradlew clean > /dev/null 2>&1

print_status "Building release APK..."
./gradlew assembleRelease

if [ $? -eq 0 ]; then
    print_success "APK built successfully!"
    
    # Find the APK
    APK_PATH=$(find app/build/outputs/apk/release -name "*.apk" 2>/dev/null | head -n 1)
    
    if [ -n "$APK_PATH" ]; then
        print_success "APK location: $APK_PATH"
        
        # Show APK info
        APK_SIZE=$(du -h "$APK_PATH" | cut -f1)
        print_status "APK size: $APK_SIZE"
        
        # Copy to releases directory
        mkdir -p releases
        cp "$APK_PATH" "releases/proximity-lockscreen-signed.apk"
        print_success "APK copied to: releases/proximity-lockscreen-signed.apk"
        
        echo ""
        echo "=========================================="
        print_success "Build completed successfully!"
        echo "=========================================="
        echo ""
        echo "Next steps:"
        echo "1. Transfer the APK to your Android device"
        echo "2. Enable 'Unknown Sources' in device settings"
        echo "3. Install the APK"
        echo "4. Grant necessary permissions"
        echo "5. Start the service and test proximity sensor"
        echo ""
        echo "For detailed instructions, see README_PROXIMITY_LOCKSCREEN.md"
        
    else
        print_error "APK not found in expected location"
    fi
else
    print_error "Build failed. Check the error messages above."
    echo ""
    echo "Common solutions:"
    echo "1. Ensure you have sufficient disk space"
    echo "2. Check that all SDK components are installed"
    echo "3. Verify Java version compatibility"
    echo "4. Try running: ./gradlew assembleRelease --stacktrace"
    exit 1
fi