package com.example.mark.materialdesign;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mark on 11/9/15.
 */
public class SharedPreferencesHelper {


    public static void saveToSharePreferences( Context context, String fileName, String prefName, String prefValue){
        SharedPreferences sharedPreferences =
               context.getSharedPreferences(fileName, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(prefName, prefValue);
        editor.commit();
    }

    public static String readFromPreference( Context context, String fileName, String prefNamem, String defaultValue){
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(fileName, Context.MODE_PRIVATE);

        return sharedPreferences.getString(prefNamem, defaultValue);
    }
}
