package com.xiaobozheng.gankio.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xiaobozheng on 7/25/2016.
 */
public class PreferencesManager {
    private static final String PREF_NAME = "com.example.app.PREF_NAME";
    private static final String KEY_VALUE = "com.example.app.KEY_VALUE";
    private static final String KEY_RECENTLY = "com.example.app.KEY_RECENTLY";
    private static final String KEY_NEWDETAIL = "com.example.app.KEY_NEWDETAIL";
    private static final String KRY_CATEGORY = "com.example.app.KRY_CATEGORY";

    private static PreferencesManager sInstance;
    private final SharedPreferences mPref;

    private PreferencesManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
    }

    public static synchronized PreferencesManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(PreferencesManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public void setValue(long value) {
        mPref.edit()
                .putLong(KEY_VALUE, value)
                .commit();
    }

    public long getValue() {
        return mPref.getLong(KEY_VALUE, 0);
    }

    //保存Drawlayout中Category是否点击事件
    public void setCategory_isChecked( boolean isCheck){
        mPref.edit().putBoolean(KRY_CATEGORY,isCheck).commit();
    }

    public boolean getCategory_isChecked(){
        return mPref.getBoolean(KRY_CATEGORY, false);
    }

    //保存Drawlayout中Newdetail是否点击事件
    public void setKeyNewdetail_isChecked(boolean isChecked){
        mPref.edit().putBoolean(KEY_NEWDETAIL,isChecked).commit();
    }

    public boolean getKeyNewdetail_isChecked(){
        return mPref.getBoolean(KEY_NEWDETAIL,false);
    }

    //保存Drawlayout中Recently是否点击事件
    public void setKeyRecently_isCheked(boolean isChecked){
        mPref.edit().putBoolean(KEY_RECENTLY, isChecked).commit();
    }

    public boolean getKeyRecently_isCheked(){
        return mPref.getBoolean(KEY_RECENTLY, false);
    }

    public void remove(String key) {
        mPref.edit()
                .remove(key)
                .commit();
    }

    public boolean clear() {
        return mPref.edit()
                .clear()
                .commit();
    }
}
