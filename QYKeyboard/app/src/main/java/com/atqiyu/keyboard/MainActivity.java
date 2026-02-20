package com.atqiyu.keyboard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity extends AppCompatActivity {
    
    public static final String PREFS_NAME = "QYKeyboardPrefs";
    public static final String KEY_HAPTIC_ENABLED = "haptic_enabled";
    
    private SwitchCompat switchHaptic;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();
        loadSettings();
    }
    
    private void initViews() {
        switchHaptic = findViewById(R.id.switchHaptic);
        
        switchHaptic.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            prefs.edit().putBoolean(KEY_HAPTIC_ENABLED, isChecked).apply();
        });
        
        LinearLayout cardEnable = findViewById(R.id.cardEnableKeyboard);
        cardEnable.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "无法打开设置", Toast.LENGTH_SHORT).show();
            }
        });
        
        LinearLayout cardSetDefault = findViewById(R.id.cardSetDefault);
        cardSetDefault.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SUBTYPE_SETTINGS);
                intent.putExtra("input_method", "com.atqiyu.keyboard/.SimpleInputMethodService");
                startActivity(intent);
            } catch (Exception e) {
                try {
                    Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
                    startActivity(intent);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    Toast.makeText(this, "无法打开设置", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    
    private void loadSettings() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean hapticEnabled = prefs.getBoolean(KEY_HAPTIC_ENABLED, true);
        switchHaptic.setChecked(hapticEnabled);
    }
    
    public static boolean isHapticEnabled(android.content.Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return prefs.getBoolean(KEY_HAPTIC_ENABLED, true);
    }
}
