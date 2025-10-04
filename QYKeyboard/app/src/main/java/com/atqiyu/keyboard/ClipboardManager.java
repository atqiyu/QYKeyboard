package com.atqiyu.keyboard;

import android.content.ClipboardManager;
import android.content.ClipData;
import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class ClipboardManager {  // 保持类名为 ClipboardManager
    private static final String CLIPBOARD_DIR = "QYKeyboard/Clipboard";
    private static android.content.ClipboardManager systemClipboardManager;
    private static android.content.ClipboardManager.OnPrimaryClipChangedListener clipChangedListener;
    
    // 初始化剪贴板监听
    public static void initClipboardListener(final Context context) {
        if (systemClipboardManager == null) {
            systemClipboardManager = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        }
        
        if (clipChangedListener == null) {
            clipChangedListener = new android.content.ClipboardManager.OnPrimaryClipChangedListener() {
                @Override
                public void onPrimaryClipChanged() {
                    // 当剪贴板内容变化时自动保存
                    saveCurrentClipboardToFile(context);
                }
            };
            systemClipboardManager.addPrimaryClipChangedListener(clipChangedListener);
        }
    }
    
    // 移除监听（避免内存泄漏）
    public static void removeClipboardListener(Context context) {
        if (systemClipboardManager != null && clipChangedListener != null) {
            systemClipboardManager.removePrimaryClipChangedListener(clipChangedListener);
            clipChangedListener = null;
        }
    }
    
    // 保存当前剪贴板内容到文件
    private static void saveCurrentClipboardToFile(Context context) {
        try {
            String clipboardText = getCurrentClipboardText(context);
            if (clipboardText != null && !clipboardText.trim().isEmpty()) {
                saveToFile(context, clipboardText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // 获取当前剪贴板文本（不触发保存）
    private static String getCurrentClipboardText(Context context) {
        try {
            if (systemClipboardManager != null && systemClipboardManager.hasPrimaryClip()) {
                ClipData clipData = systemClipboardManager.getPrimaryClip();
                if (clipData != null && clipData.getItemCount() > 0) {
                    CharSequence text = clipData.getItemAt(0).getText();
                    if (text != null) {
                        return text.toString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveToFile(Context context, String text) {
        if (text == null || text.trim().isEmpty()) return;try {
            // 获取外部存储目录
            File externalDir = Environment.getExternalStorageDirectory();
            File appDir = new File(externalDir, CLIPBOARD_DIR);

            // 创建目录（如果不存在）
            if (!appDir.exists()) {
                appDir.mkdirs();
            }

            // 创建带时间戳的文件名
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                    .format(new Date());
            File file = new File(appDir, "clip_" + timeStamp + ".txt");

            // 写入文件
            FileWriter writer = new FileWriter(file);
            writer.write("时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    .format(new Date()) + "\n");
            writer.write("内容: " + text);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getClipboardText(Context context) {
        try {
            // 使用系统剪贴板管理器
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard != null && clipboard.hasPrimaryClip()) {
                ClipData clipData = clipboard.getPrimaryClip();
                if (clipData != null && clipData.getItemCount() > 0) {
                    CharSequence text = clipData.getItemAt(0).getText();
                    if (text != null) {
                        String textStr = text.toString();
                        // 保存到文件
                        saveToFile(context, textStr);
                        return textStr;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void copyToClipboard(Context context, String text) {
        try {
            // 使用系统剪贴板管理器
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard != null) {
                clipboard.setPrimaryClip(ClipData.newPlainText("text", text));
                // 这里不需要手动保存，因为监听器会自动处理
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
