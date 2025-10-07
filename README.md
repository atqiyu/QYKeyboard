# QYKeyboard
QYKeyboard is a custom Android input method with a built-in clipboard auto-save feature, allowing copied content to be saved to a local file in real time for easy historical reference.

### -> [To English Version](./README-EN.md)

# ⌨️ QYKeyboard - 简洁输入法

<div align="center">

![Android](https://img.shields.io/badge/Android-6.0%2B-brightgreen)
![License](https://img.shields.io/badge/License-MIT-blue)
![Version](https://img.shields.io/badge/Version-1.1.0-orange)

QYKeyboard 是一个基础的 Android 自定义输入法，目前实现了基本的英文输入功能。特色是内置了剪贴板监测功能，能够自动保存用户复制的所有文本内容到手机存储中，方便后续查看和使用。键盘布局简洁清晰，支持大小写切换和基础符号输入，适合需要记录剪贴板历史的用户使用。

</div>

## ✨ 特性功能

### 🎨 简约界面
- **舒适配色**：黑白简约

### 📋 智能剪贴板
- **自动保存**：实时监测剪贴板变化，自动保存复制内容
- **历史记录**：所有剪贴板内容按时间戳保存到本地文件
- **一键操作**：快速复制粘贴，提升输入效率

### ⌨️ 实用功能
- **输入**：基本实现英文输入功能

## 🚀 快速开始

### 系统要求
- Android 6.0 (API 23) 及以上
- 至少 50MB 可用存储空间

### 安装步骤

1. **下载安装**
   ```bash
   # 通过ADB安装
   adb install app-debug.apk
   ```
   - 或在安卓手机上自行安装apk

2. **启用输入法**
   - 打开"设置" → "系统" → "语言和输入法"
   - 找到"QYKeyboard"并启用

3. **设置为默认**
   - 在设置里的输入法界面
   - 选择"默认输入法" → "QYKeyboard"

### 权限说明
| 权限 | 用途 | 必要性 |
|------|------|--------|
| 存储权限 | 保存剪贴板历史记录 | 可选 |
| 振动权限 | 按键触觉反馈 | 可选 |

## 📁 项目结构

```
QYKeyboard/
│
├── 项目配置文件/
│   ├── build.gradle          # 项目级Gradle构建配置
│   ├── settings.gradle       # 项目模块设置
│   ├── gradle.properties     # Gradle属性配置
│   ├── gradlew               # Gradle包装器(Linux)
│   ├── gradlew.bat           # Gradle包装器(Windows)
│   └── local.properties      # 本地SDK路径配置
│
└── app/                      # 主应用模块
    │
    ├── build.gradle          # 应用模块Gradle配置
    ├── proguard-rules.pro    # 代码混淆规则
    │
    ├── src/main/
    │   │
    │   ├── AndroidManifest.xml          # 应用清单文件
    │   │
    │   ├── java/com/atqiyu/keyboard/
    │   │   ├── MainActivity.java               # 主Activity，引导用户启用输入法
    │   │   ├── SimpleInputMethodService.java   # 核心输入法服务实现
    │   │   └── ClipboardManager.java           # 剪贴板管理和自动保存功能
    │   │
    │   └── res/              # 资源文件目录
    │       │
    │       ├── drawable/     # 图形资源
    │       │   ├── ic_launcher.png             # 应用图标
    │       │   ├── key_background.xml          # 普通按键背景
    │       │   ├── key_normal.xml              # 按键正常状态
    │       │   ├── key_pressed.xml             # 按键按下状态
    │       │   ├── key_preview_background.xml  # 按键预览背景
    │       │   ├── key_special.xml             # 特殊按键样式
    │       │   ├── key_special_background.xml  # 特殊按键背景
    │       │   └── key_special_pressed.xml     # 特殊按键按下状态
    │       │
    │       ├── layout/       # 界面布局文件
    │       │   ├── activity_main.xml    # 主Activity布局
    │       │   ├── keyboard.xml         # 键盘主布局
    │       │   └── key_preview.xml      # 按键预览布局
    │       │
    │       ├── values/       # 资源值定义
    │       │   ├── colors.xml    # 颜色定义
    │       │   ├── strings.xml   # 字符串资源
    │       │   └── styles.xml    # 样式定义
    │       │
    │       ├── values-v21/   # Android 5.0+专用资源
    │       │   └── styles.xml    # Material Design样式
    │       │
    │       └── xml/          # XML配置文件
    │           ├── method.xml    # 输入法服务配置
    │           └── qwerty.xml    # QWERTY键盘布局定义
    │
    └── build/                # 构建输出目录(自动生成)
        ├── bin/
        │   ├── app.apk           # 生成的APK文件
        │   └── resources.ap_     # 压缩的资源文件
        └── ...                   # 其他构建中间文件
```

> 项目中的build文件夹可自行创建，打包编译时会自动生成

## 🔧 核心组件

### 🎹 输入法服务
```java
public class SimpleInputMethodService extends InputMethodService {
    // 键盘视图创建
    // 按键事件处理
    // 手势操作支持
}
```

### 📋 剪贴板管理
```java
class ClipboardManager {
    // 实时剪贴板监听
    // 自动文件保存
    // 历史记录管理
}
```

### 🎨 界面组件
- **KeyboardView**: 自定义键盘布局
- **KeyBackground**: 按键视觉样式
- **ColorScheme**: 色彩主题系统

## ⚙️ 配置说明

### 键盘布局
编辑 `res/xml/qwerty.xml` 自定义按键布局：

```xml
<Keyboard>
    <Row>
        <Key android:codes="81" android:keyLabel="q"/>
        <!-- 更多按键配置 -->
    </Row>
</Keyboard>
```

### 主题配色
修改 `res/values/colors.xml` 调整外观：

```xml
<color name="keyboard_bg">#F5F5F5</color>
<color name="key_normal">#FFFFFF</color>
<color name="key_special">#4CAF50</color>
```

## 🛠️ 开发指南

### 环境要求
- Android Studio Arctic Fox 及以上
- JDK 11
- Android SDK 33

### 构建命令
```bash
# 清理项目
./gradlew clean

# 调试版本
./gradlew assembleDebug

# 发布版本
./gradlew assembleRelease

# 安装到设备
./gradlew installDebug
```

### 调试技巧
```bash
# 查看输入法日志
adb logcat | grep -i "atqiyu"

# 测试剪贴板功能
adb shell am broadcast -a cliptest
```

## 📊 剪贴板文件结构

```
/sdcard/QYKeyboard/Clipboard/
├── clip_20231003_143022.txt
├── clip_20231003_143125.txt
└── clip_20231003_143258.txt
```

每个文件包含：
```
时间: 2023-10-03 14:30:22
内容: 复制的文本内容...
```

## 🐛 故障排除

### 常见问题

**Q: 输入法无法启用**
A: 检查是否在系统设置中正确启用并设置为默认输入法

**Q: 剪贴板功能无效**
A: 确认已授予存储权限，检查 `/sdcard/QYKeyboard/` 目录权限

**Q: 键盘显示异常**
A: 清除应用数据，重新启用输入法

### 日志收集
```bash
# 获取详细错误信息
adb logcat -v time | grep -i "atqiyu" > keyboard_log.txt
```

---
##### Update QYKeyborad v1.1.0 (更新)
- 优化剪贴板监听功能
- 优化剪贴板内容的保存和部分处理逻辑
- 剪贴板内容保存文件的时间精确到纳秒(防止快速复制内容造成文件丢失)

## 🤝 贡献指南

我们欢迎各种形式的贡献！

1. Fork 本项目
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

---

<div align="center">

**如果这个项目对你有帮助，请给个 ⭐️ 支持一下！**

*让输入更智能，让生活更便捷*

</div>
