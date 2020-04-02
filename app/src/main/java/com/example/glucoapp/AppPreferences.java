//package com.example.glucoapp;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.preference.PreferenceManager;
//import android.view.View;
//
//public class AppPreferences {
//public static void setDefaults(String key, long value, View.OnClickListener context) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences((Context) context);
//        SharedPreferences.Editor editor = preferences.edit();
//
//        editor.putLong(key, value);
//        editor.commit();
//        }
//
//public static String getDefaults(String key, Context context) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//    //    return preferences.getString(key, null);
//       return preferences.getString(key,null);
//        }
//  }