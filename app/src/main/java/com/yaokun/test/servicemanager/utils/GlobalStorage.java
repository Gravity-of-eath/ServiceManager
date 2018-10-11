package com.yaokun.test.servicemanager.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.litesuits.orm.LiteOrm;

public class GlobalStorage {

    private static final String PREFS_CONF = "service_manager.sp";
    private static SharedPreferences global;
    private static final String DB_NAME = "sms.db";
    private static Context ctx;

    static LiteOrm liteOrm;

    public static LiteOrm getLiteOrm() {
        if (liteOrm == null) {
            liteOrm = LiteOrm.newSingleInstance(ctx, DB_NAME);
        }
        liteOrm.setDebugged(true); // open the log
        return liteOrm;
    }


    public static void initGlobalStorage(Context ctx) {
        GlobalStorage.ctx = ctx;
        liteOrm = LiteOrm.newSingleInstance(ctx, DB_NAME);
        global = ctx.getSharedPreferences(PREFS_CONF, Context.MODE_PRIVATE);
    }

    public static void clean() {
        SharedPreferences.Editor editor = global.edit();
        editor.clear();
        editor.commit();
    }

    public static void update(String name, String value) {
        Log.v("CSM DB", "name:" + name + ",value:" + value);
        SharedPreferences.Editor editor = global.edit();
        editor.putString(name, value);
        boolean isSuccess = editor.commit();
        Log.v("CSM DB", "name:" + name + ",value:" + value + ",isSuccess:"
                + isSuccess);
    }

    public static void update(String name, int value) {
        Log.v("hgy", "name:" + name + ",value:" + value);
        SharedPreferences.Editor editor = global.edit();
        editor.putInt(name, value);
        boolean isSuccess = editor.commit();
        Log.v("hgy", "name:" + name + ",value:" + value + ",isSuccess:"
                + isSuccess);
    }

    public static void update(String name, long value) {
        Log.v("hgy", "name:" + name + ",value:" + value);
        SharedPreferences.Editor editor = global.edit();
        editor.putLong(name, value);
        boolean isSuccess = editor.commit();
        Log.v("hgy", "name:" + name + ",value:" + value + ",isSuccess:"
                + isSuccess);
    }

    public static void update(String name, boolean value) {
        Log.v("hgy", "name:" + name + ",value:" + value);
        SharedPreferences.Editor editor = global.edit();
        editor.putBoolean(name, value);
        boolean isSuccess = editor.commit();
        Log.v("hgy", "name:" + name + ",value:" + value + ",isSuccess:"
                + isSuccess);
    }

    public static boolean getValue(String key, boolean defvalue) {
        return global.getBoolean(key, defvalue);
    }

    public static String getValue(String key, String defvalue) {
        return global.getString(key, defvalue);
    }

    public static int getValue(String key, int defvalue) {
        return global.getInt(key, defvalue);
    }

    public static long getValue(String key, long defvalue) {
        return global.getLong(key, defvalue);
    }

}
