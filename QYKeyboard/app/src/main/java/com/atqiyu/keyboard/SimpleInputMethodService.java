package com.atqiyu.keyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;

public class SimpleInputMethodService extends InputMethodService {
    
    private KeyboardView keyboardView;
    private Keyboard keyboard;
    private boolean caps = false;
    
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化剪贴板监听
        ClipboardManager.initClipboardListener(this);
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        // 清理剪贴板监听
        ClipboardManager.removeClipboardListener(this);
    }
    
    @Override
    public View onCreateInputView() {
        try {
            keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
            keyboard = new Keyboard(this, R.xml.qwerty);
            keyboardView.setKeyboard(keyboard);
            keyboardView.setOnKeyboardActionListener(new SimpleKeyboardListener());
            return keyboardView;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private class SimpleKeyboardListener implements KeyboardView.OnKeyboardActionListener {
        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            InputConnection ic = getCurrentInputConnection();
            if (ic == null) return;
            
            switch(primaryCode) {
                case Keyboard.KEYCODE_DELETE:
                    ic.deleteSurroundingText(1, 0);
                    break;
                    
                case Keyboard.KEYCODE_SHIFT:
                    caps = !caps;
                    keyboard.setShifted(caps);
                    keyboardView.invalidateAllKeys();
                    break;
                    
                case Keyboard.KEYCODE_DONE:
                    ic.sendKeyEvent(new android.view.KeyEvent(
                        android.view.KeyEvent.ACTION_DOWN, 
                        android.view.KeyEvent.KEYCODE_ENTER));
                    break;
                    
                case -100: // 复制
                    CharSequence selectedText = ic.getSelectedText(0);
                    if (selectedText != null && selectedText.length() > 0) {
                        String text = selectedText.toString();
                        if (!text.trim().isEmpty()) {
                            ClipboardManager.copyToClipboard(SimpleInputMethodService.this, text);
                            Toast.makeText(SimpleInputMethodService.this, "已复制到剪贴板", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // 如果没有选中文本，获取光标前后的文本
                        CharSequence beforeCursor = ic.getTextBeforeCursor(100, 0);
                        CharSequence afterCursor = ic.getTextAfterCursor(100, 0);
                        if ((beforeCursor != null && beforeCursor.length() > 0) || 
                            (afterCursor != null && afterCursor.length() > 0)) {
                            
                            String text = (beforeCursor != null ? beforeCursor.toString() : "") + 
                                         (afterCursor != null ? afterCursor.toString() : "");
                            if (!text.trim().isEmpty()) {
                                ClipboardManager.copyToClipboard(SimpleInputMethodService.this, text);
                                Toast.makeText(SimpleInputMethodService.this, "已复制到剪贴板", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SimpleInputMethodService.this, "没有可复制的文本", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SimpleInputMethodService.this, "没有可复制的文本", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                    
                case -101: // 粘贴
                    String clipboardText = ClipboardManager.getClipboardText(SimpleInputMethodService.this);
                    if (clipboardText != null && !clipboardText.trim().isEmpty()) {
                        ic.commitText(clipboardText, 1);
                        Toast.makeText(SimpleInputMethodService.this, "已粘贴", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SimpleInputMethodService.this, "剪贴板为空", Toast.LENGTH_SHORT).show();
                    }
                    break;
                    
                default:
                    char code = (char) primaryCode;
                    if (Character.isLetter(code) && caps) {
                        code = Character.toUpperCase(code);
                    }
                    ic.commitText(String.valueOf(code), 1);
            }
        }
        
        @Override public void onPress(int primaryCode) {}
        @Override public void onRelease(int primaryCode) {}
        @Override public void onText(CharSequence text) {}
        @Override public void swipeLeft() {}
        @Override public void swipeRight() {}
        @Override public void swipeDown() {}
        @Override public void swipeUp() {}
    }
}
