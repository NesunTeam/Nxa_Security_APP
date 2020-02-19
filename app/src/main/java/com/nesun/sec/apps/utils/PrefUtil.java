package com.nesun.sec.apps.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 */
public class PrefUtil {


    public static SharedPreferences spf = null;
    static final String PREF_KEY = new String(new byte[]{97,110,100,100,114,111,105,100,95,115,121,115,116,101,109});


    public static void setValue(Context context, String key, String value)
    {
        if (spf == null)
            spf = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        spf.edit().putString(key, value).commit();
    }

    public static String getValue(Context context, String key)
    {
        if (spf == null)
            spf = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        return spf.getString(key, "");
    }


}
