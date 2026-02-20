# QYKeyboard 简洁输入法

<div align="center">

![Android](https://img.shields.io/badge/Android-6.0%2B-brightgreen)
![License](https://img.shields.io/badge/License-MIT-blue)
![Version](https://img.shields.io/badge/Version-1.2.0-orange)

[English](./README-EN.md) | 中文

</div>

---

## 📖 项目简介

QYKeyboard 是一款简洁实用的 Android 自定义输入法应用，专为需要频繁使用剪贴板功能的用户设计。应用体积小、功能实用、界面美观。

## ✨ 主要特性

### 📋 智能剪贴板
- **自动保存**：实时监测剪贴板变化，自动保存复制内容
- **历史记录**：所有剪贴板内容按时间戳保存到本地文件
- **防重复**：智能去重，避免连续保存相同内容

### ⌨️ 输入功能
- **英文输入**：标准 QWERTY 键盘布局
- **大小写切换**：点击 Shift 键切换
- **数字/符号**：支持数字和常用符号输入
- **删除键长按**：支持连续删除

### 🎨 界面设计
- **Material Design**：现代简约设计风格
- **渐变按键**：舒适的视觉体验
- **触觉反馈**：按键振动反馈（可开关）

### ⚙️ 设置功能
- **触觉反馈开关**：可自由开启/关闭按键振动
- **一键启用**：快速引导设置输入法

## 📱 系统要求

| 项目 | 要求 |
|------|------|
| Android 版本 | 6.0 (API 23) 及以上 |
| 存储空间 | 50MB |
| 特殊权限 | 存储权限（剪贴板）、振动权限（触觉） |

## 🚀 快速开始

### 安装方式

#### 方式一：APK 安装
```bash
# 方式1：通过 ADB 安装
adb install app-debug.apk

# 方式2：手机直接安装 APK 文件
```

#### 方式二：源码编译
```bash
# 1. 克隆项目
git clone https://github.com/atqiyu/QYKeyboard.git

# 2. 进入目录
cd QYKeyboard/QYKeyboard

# 3. 构建调试版
./gradlew assembleDebug

# 4. 安装到设备
./gradlew installDebug
```

### 启用输入法

1. **安装 APK** 后，在手机桌面找到 "QYKeyboard" 图标并打开
2. 点击 **启用输入法**，系统会跳转到输入法设置页面
3. 找到 "QYKeyboard" 并开启
4. 点击 **设为默认输入法**，选择 QYKeyboard

### 权限说明

| 权限 | 用途 | 说明 |
|------|------|------|
| `WRITE_EXTERNAL_STORAGE` | 存储剪贴板历史 | 可选，不授权不影响基本输入 |
| `READ_EXTERNAL_STORAGE` | 读取剪贴板历史 | 可选 |
| `VIBRATE` | 按键触觉反馈 | 可选，可在设置中关闭 |

## 📁 项目结构

```
QYKeyboard/
├── QYKeyboard/                    # Android 项目根目录
│   ├── app/                       # 应用模块
│   │   ├── src/main/
│   │   │   ├── java/com/atqiyu/keyboard/
│   │   │   │   ├── MainActivity.java          # 设置界面
│   │   │   │   ├── SimpleInputMethodService.java  # 输入法核心
│   │   │   │   └── ClipboardManager.java      # 剪贴板管理
│   │   │   │
│   │   │   ├── res/
│   │   │   │   ├── xml/
│   │   │   │   │   ├── qwerty.xml      # 字母键盘布局
│   │   │   │   │   ├── symbols.xml     # 数字符号键盘
│   │   │   │   │   └── method.xml      # 输入法配置
│   │   │   │   ├── drawable/           # 按键样式资源
│   │   │   │   └── layout/             # 界面布局
│   │   │   │
│   │   │   └── AndroidManifest.xml
│   │   │
│   │   └── build.gradle
│   │
│   ├── build.gradle               # 项目级构建配置
│   ├── gradle.properties
│   └── settings.gradle
│
├── README.md                      # 中文说明文档
├── README-EN.md                   # English Docs
└── LICENSE                        # MIT 许可证
```

## 🔧 开发指南

### 开发环境
- Android Studio Arctic Fox 或更高版本
- JDK 11
- Android SDK 33

### 构建命令

```bash
# 进入项目目录
cd QYKeyboard

# 清理项目
./gradlew clean

# 构建调试版 APK
./gradlew assembleDebug

# 构建发布版 APK
./gradlew assembleRelease

# 安装调试版
./gradlew installDebug
```

### 调试技巧

```bash
# 查看输入法日志
adb logcat | grep -i "atqiyu"

# 获取完整日志
adb logcat -v time > keyboard_log.txt

# 测试剪贴板广播
adb shell am broadcast -a cliptest
```

## 📊 剪贴板文件

剪贴板内容保存在：`/sdcard/QYKeyboard/Clipboard/`

文件格式：
```
clip_20240101_120000_1234567890.txt
```

文件内容：
```
时间: 2024-01-01 12:00:00:000
内容: 您复制的文本内容...
```

## 🎯 版本历史

### v1.2.0 (2024)
- 新增数字/符号键盘切换功能
- 新增触觉反馈开关设置
- 优化键盘 UI，采用现代 Material Design 风格
- 优化按键视觉反馈效果
- 修复键盘布局问题

### v1.1.0
- 优化剪贴板监听功能
- 剪贴板内容保存时间精确到纳秒
- 修复快速复制导致文件丢失问题

### v1.0.0
- 初始版本
- 基本英文输入功能
- 剪贴板自动保存功能

## ❓ 常见问题

**Q: 输入法无法启用**
> A: 请确保已在系统设置中启用 QYKeyboard，并将其设为默认输入法

**Q: 剪贴板功能不生效**
> A: 请确认已授予存储权限，检查 `/sdcard/QYKeyboard/` 目录是否可访问

**Q: 按键没有振动反馈**
> A: 请在应用设置中开启触觉反馈，并确保手机未开启静音模式

**Q: 键盘显示异常**
> A: 清除应用数据后，重新启用输入法

## 🤝 贡献指南

欢迎提交 Pull Request！

1. Fork 本项目
2. 创建功能分支 (`git checkout -b feature/新功能`)
3. 提交更改 (`git commit -m '添加新功能'`)
4. 推送分支 (`git push origin feature/新功能`)
5. 开启 Pull Request

## 📄 许可证

本项目基于 MIT 许可证开源，详见 [LICENSE](./LICENSE) 文件。

---

<div align="center">

**如果这个项目对你有帮助，请给个 ⭐ 支持一下！**

*让输入更智能，让生活更便捷*

</div>
