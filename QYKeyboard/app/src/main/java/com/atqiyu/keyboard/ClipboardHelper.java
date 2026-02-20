package com.atqiyu.keyboard;

import android.content.ClipData;
import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class ClipboardHelper {
    private static final String CLIPBOARD_DIR = "QYKeyboard/Clipboard";
    private static android.content.ClipboardManager systemClipboardManager;
    private static android.content.ClipboardManager.OnPrimaryClipChangedListener clipChangedListener;
    
	private static final java.util.concurrent.ExecutorService clipExecutor = java.util.concurrent.Executors.newSingleThreadExecutor();
    private static final Object fileWriteLock = new Object();
    private static String lastContent = "";
	
    public static void initClipboardListener(final Context context) {
        if (systemClipboardManager == null) {
            systemClipboardManager = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        }
        
        if (clipChangedListener == null) {
            clipChangedListener = new android.content.ClipboardManager.OnPrimaryClipChangedListener() {
                @Override
                public void onPrimaryClipChanged() {
                    saveCurrentClipboardToFile(context);
                }
            };
            systemClipboardManager.addPrimaryClipChangedListener(clipChangedListener);
        }
    }
    
    public static void removeClipboardListener(Context context) {
        if (systemClipboardManager != null && clipChangedListener != null) {
            systemClipboardManager.removePrimaryClipChangedListener(clipChangedListener);
            clipChangedListener = null;
        }
    }
    
	private static void saveCurrentClipboardToFile(final Context context) {
		clipExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						String clipboardText = getCurrentClipboardText(context);
						if (clipboardText == null || clipboardText.trim().isEmpty()) return;

						if (!clipboardText.equals(lastContent)) {
							synchronized (fileWriteLock) {
								lastContent = clipboardText;
								saveToFile(context, clipboardText);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}
    
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

	private static void saveToFile(Context context, String text) {
		if (text == null || text.trim().isEmpty()) return;

		try {
			File externalDir = Environment.getExternalStorageDirectory();
			File appDir = new File(externalDir, CLIPBOARD_DIR);

			if (!appDir.exists()) {
				appDir.mkdirs();
			}

			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS", Locale.getDefault())
                .format(new Date());
			long nanoTime = System.nanoTime();
			File file = new File(appDir, "clip_" + timeStamp + "_" + nanoTime + ".txt");

			FileWriter writer = new FileWriter(file);
			writer.write("时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.getDefault())
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
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard != null && clipboard.hasPrimaryClip()) {
                ClipData clipData = clipboard.getPrimaryClip();
                if (clipData != null && clipData.getItemCount() > 0) {
                    CharSequence text = clipData.getItemAt(0).getText();
                    if (text != null) {
                        String textStr = text.toString();
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
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard != null) {
                clipboard.setPrimaryClip(ClipData.newPlainText("text", text));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static void shutdown() {
		if (clipExecutor != null && !clipExecutor.isShutdown()) {
			clipExecutor.shutdown();
		}
	}
}
