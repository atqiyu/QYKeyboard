# QYKeyboard

QYKeyboard is a custom Android input method with a built-in clipboard auto-save feature, allowing copied content to be saved to a local file in real time for easy historical reference.

﻿

﻿

# ⌨️ QYKeyboard - Simple Input Method

﻿

<div align="center">
﻿
![Android](https://img.shields.io/badge/Android-6.0%2B-brightgreen)
![License](https://img.shields.io/badge/License-MIT-blue)
![Version](https://img.shields.io/badge/Version-1.0.0-orange)
﻿
QYKeyboard is a basic Android custom input method that currently supports fundamental English input functionality. Its standout feature is an integrated clipboard monitoring function, which automatically saves all text copied by users to the device's storage for easy future reference and use. The keyboard layout is clean and straightforward, featuring uppercase/lowercase switching and basic symbol input, making it ideal for users who need to track clipboard history.
﻿
</div>
﻿
## ✨ Feature functions
﻿
### 🎨 Simple interface
- ** Comfortable color scheme**：Black and white minimalist
﻿
### 📋 Intelligent clipboard
- ** Auto-save**：Real time monitoring of clipboard changes, automatic saving of copied content
- ** History**：All clipboard contents are saved to a local file by timestamp
- ** One-click operation**：Quickly copy and paste to improve input efficiency
﻿
### ⌨️ Practical function
- ** Input **：Basic implementation of English input function
﻿
## 🚀 Quick Start
﻿
### System Requirements 
- Android 6.0 (API 23) and above
- At least 50MB of available storage space
﻿
### Installation Steps
﻿
1. ** Download and Install **
   ```bash
   # Install through ADB
   adb install app-debug.apk
   ```
   - Or install APK on your Android phone by yourself
﻿
2. ** Enable input method **
   - Open "Settings" → "System" → "Language and Input Method"
   - Find 'QYKeyboard' and enable it
﻿
3. ** Set as default **
   - In the input method interface of the settings
   - Select "Default Input Method" → "QYKeyboard"
﻿
### Permission Description
| Permission | Purpose | Necessity |
|------|------|--------|
|Storage Permissions | Save clipboard history | Optional|
|Vibration permission | Button tactile feedback | Optional|
﻿
## 📁 Project Structure
﻿
```
QYKeyboard/
│
├── 项目配置文件/
│   ├── build.gradle          # Project level Gradle build configuration
│   ├── settings.gradle       # Project module settings
│   ├── gradle.properties     # Gradle attribute configuration
│   ├── gradlew               # Gradle wrapper (Linux)
│   ├── gradlew.bat           # Gradle wrapper (Windows)
│   └── local.properties      # Local SDK path configuration
│
└── app/                      # Main application module
    │
    ├── build.gradle          # Application module Gradle configuration
    ├── proguard-rules.pro    # Code obfuscation rules
    │
    ├── src/main/
    │   │
    │   ├── AndroidManifest.xml          # Application manifest file
    │   │
    │   ├── java/com/atqiyu/keyboard/
    │   │   ├── MainActivity.java               # Main Activity, guiding users to enable input methods
    │   │   ├── SimpleInputMethodService.java   # Implementation of Core Input Method Services
    │   │   └── ClipboardManager.java           # Clipboard management and automatic saving function
    │   │
    │   └── res/              # Resource file directory
    │       │
    │       ├── drawable/     # Graphic resources
    │       │   ├── ic_launcher.png             # app icon
    │       │   ├── key_background.xml          # Normal button background
    │       │   ├── key_normal.xml              # Normal button status
    │       │   ├── key_pressed.xml             # Press button status
    │       │   ├── key_preview_background.xml  # Key Preview Background
    │       │   ├── key_special.xml             # Special button style
    │       │   ├── key_special_background.xml  # Special button background
    │       │   └── key_special_pressed.xml     # Special button pressed state
    │       │
    │       ├── layout/       # Interface layout file
    │       │   ├── activity_main.xml    # Main Activity Layout
    │       │   ├── keyboard.xml         # Keyboard main layout
    │       │   └── key_preview.xml      # Button Preview Layout
    │       │
    │       ├── values/       # Definition of Resource Value
    │       │   ├── colors.xml
    │       │   ├── strings.xml
    │       │   └── styles.xml
    │       │
    │       ├── values-v21/   # Android 5.0+ Dedicated resources
    │       │   └── styles.xml
    │       │
    │       └── xml/          # XML Configuration File 
    │           ├── method.xml    # Input Method Service Configuration
    │           └── qwerty.xml    # QWERTY Keyboard layout definition
    │
    └── build/                # Build output directory (automatically generated)
        ├── bin/
        │   ├── app.apk           # Generated APK file
        │   └── resources.ap_     # Compressed resource files
        └── ...                   # Other intermediate files for construction
```
﻿
> The `build` folder in the project can be created by oneself and will be automatically generated during packaging and compilation
﻿
## 🔧 Core Component
﻿
### 🎹 Input Method Service
```java
public class SimpleInputMethodService extends InputMethodService {
	//Keyboard View Creation
	//Key event handling
	//Gesture operation support
}
```
﻿
### 📋 Clipboard Master 
```java
class ClipboardManager {
	//Real time clipboard monitoring
	//Automatic file saving
	//History management
}
```
﻿
### 🎨 Interface Components
- **KeyboardView**: Customize keyboard layout
- **KeyBackground**: Button visual style
- **ColorScheme**: Color Theme System
﻿
## ⚙️ Configuration Instructions
﻿
### Keyboard Layout
Edit 'res/xml/qwerty. xml' custom key layout:
﻿
```xml
<Keyboard>
    <Row>
        <Key android:codes="81" android:keyLabel="q"/>
        <! -- More button configurations -->
    </Row>
</Keyboard>
```
﻿
### Theme Color Scheme
Modify 'res/values/colors. xml' to adjust its appearance:
﻿
```xml
<color name="keyboard_bg">#F5F5F5</color>
<color name="key_normal">#FFFFFF</color>
<color name="key_special">#4CAF50</color>
```
﻿
## 🛠️ Development Guide
﻿
### Environmental Requirements
- Android Studio Arctic Fox and above
- JDK 11
- Android SDK 33
﻿
### Build Command
```bash
# Clean up the project
./gradlew clean
﻿
# Debugging version
./gradlew assembleDebug
﻿
# Release version
./gradlew assembleRelease
﻿
# Install to device
./gradlew installDebug
```
﻿
### Debugging skills
```bash
# View input method logs
adb logcat | grep -i "atqiyu"
﻿
# Test clipboard functionality
adb shell am broadcast -a cliptest
```
﻿
## 📊 Clipboard file structure
﻿
```
/sdcard/QYKeyboard/Clipboard/
├── clip_20231003_143022.txt
├── clip_20231003_143125.txt
└── clip_20231003_143258.txt
```
﻿
Each file contains:
```
Time: October 3, 2023 14:30:22
Content: The copied text content ..
```
﻿
## 🐛 Troubleshooting
﻿
### Frequently Asked Questions
﻿
**Q: Input method cannot be enabled**
A: Check if it is correctly enabled and set as the default input method in the system settings
﻿
**Q: The clipboard function is invalid**
A: Confirm that storage permissions have been granted, check the directory permissions for `/sdcard/QYKeyboard/`
﻿
**Q: Keyboard display abnormality**
A: Clear application data and reactivate input method
﻿
### Log Collection
```bash
# Get detailed error information
adb logcat -v time | grep -i "atqiyu" > keyboard_log.txt
```
﻿
## 🤝 Contribution Guide
﻿
We welcome contributions in all forms!
﻿
1. Fork this project
2. Create a functional branch (`git checkout -b feature/AmazingFeature`)
3. Submit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Turn on Pull Request
﻿
## 📄 LICENSE
﻿
This project adopts the MIT license - view the [LICENSE] file for details
﻿
---
﻿
<div align="center">
﻿
**If this project is helpful to you, please provide it ⭐ Support it!**
﻿
*Make input smarter, make life more convenient*
﻿
</div>
