package uz.isystem.forpics_oneword.core.model;

import android.content.Context;
import android.content.SharedPreferences;

import uz.isystem.forpics_oneword.R;

public class MemoryHelper {
    private static MemoryHelper helper;
    private final SharedPreferences preferences;

    private MemoryHelper(Context context) {
        // SharedPreferences
        preferences = context.getSharedPreferences("Picture and Word", Context.MODE_PRIVATE);
    }

    public static MemoryHelper getHelper() {
        return helper;
    }

    public static void init(Context context) {
        if (helper == null) {
            helper = new MemoryHelper(context);
        }
    }

    public boolean getThemeState() {
        return preferences.getBoolean("theme_state", false);
    }

    public void setThemeState(boolean b) {
        preferences.edit().putBoolean("theme_state", b).apply();
    }

    public int getThemeId() {
        return preferences.getInt("Theme_id", R.drawable.basic11);
    }

    public void setThemeId(int ThemeId) {
        preferences.edit().putInt("Theme_id", ThemeId).apply();
    }

//    public void setMusicState(boolean music) {preferences.edit().putBoolean("music_state",music).apply();
//    }
//    public boolean getMusicState() {
//        return preferences.getBoolean("music_state",true);
//    }
}