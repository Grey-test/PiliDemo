package com.jstudio.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by Jason
 */
public class PreferencesUtils {

    private static PreferencesUtils INSTANCE;
    private SharedPreferences mPreference;
    private SharedPreferences.Editor mEditor;

    private String fileName;

    private PreferencesUtils(Context context, String fileName) {
        this.fileName = fileName;
        mPreference = context.getApplicationContext().getSharedPreferences(this.fileName, Context.MODE_PRIVATE);
        mEditor = mPreference.edit();
    }

    public static PreferencesUtils getInstance(Context context, String fileName) {
        if (INSTANCE == null) {
            INSTANCE = new PreferencesUtils(context, fileName);
        }
        return INSTANCE;
    }

    public SharedPreferences.Editor putString(String key, String value) {
        return mEditor.putString(key, value);
    }

    public SharedPreferences.Editor putInt(String key, int value) {
        return mEditor.putInt(key, value);
    }

    public SharedPreferences.Editor putLong(String key, long value) {
        return mEditor.putLong(key, value);
    }

    public SharedPreferences.Editor putFloat(String key, float value) {
        return mEditor.putFloat(key, value);
    }

    public SharedPreferences.Editor putBoolean(String key, boolean value) {
        return mEditor.putBoolean(key, value);
    }

    public SharedPreferences.Editor putSet(String key, Set<String> value) {
        return mEditor.putStringSet(key, value);
    }

    public void apply() {
        this.mEditor.apply();
    }

    public String getString(String key, String defValue) {
        return mPreference.getString(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return mPreference.getInt(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return mPreference.getLong(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return mPreference.getFloat(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mPreference.getBoolean(key, defValue);
    }

    public Set<String> getSet(String key, Set<String> defValue) {
        return mPreference.getStringSet(key, defValue);
    }

}
