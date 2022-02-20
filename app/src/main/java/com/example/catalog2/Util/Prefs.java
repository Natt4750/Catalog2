package com.example.catalog2.Util;

import android.app.Activity;
import android.content.SharedPreferences;

import java.security.AccessControlContext;
import java.security.PublicKey;

public class Prefs
{
    SharedPreferences sharedPreferences;
    public Prefs(Activity activity)
    {
        sharedPreferences=activity.getPreferences(activity.MODE_PRIVATE);
    }
    public String getSearch()
    {
        return sharedPreferences.getString("search", "12");
    }
    public void setSearch(String search)
    {
        sharedPreferences.edit().putString("search", "12");
    }
}
