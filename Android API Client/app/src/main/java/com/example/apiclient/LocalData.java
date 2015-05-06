package com.example.apiclient;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by brandycamacho on 5/5/15.
 */

public class LocalData extends ContextWrapper {
    private String TAG = "LocalData class";

    // used to help control contex
    public LocalData(Context base, String TAG) {
        super(base);
        this.TAG = TAG;
    }
    // TAG for loging and debuging
    public void log(String data) {
        Log.d(TAG, data);
    };

    /*
    Shared Preferences
       Two methods are used, one to create and store shared preference data and the other to retrieve the data from shared prefernces
       a.k.a. Getters and Setters
     */
    private String mPREF_FILE_NAME = "localData";

    // Shared Prefernce Getter method
    public String mGet_SHARED_PREFERENCE(Context context, String preferenceName, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(mPREF_FILE_NAME, Context.MODE_PRIVATE);
        log("Shared Preference is Getting "+preferenceName+":"+value);
        return sharedPreferences.getString(preferenceName, value);

    }

    // Share Preference Setter method
    public void mSetSharedPreferences (Context context, String preferenceName, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(mPREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, value);
        editor.apply();
        log("Shared Preference is Setting "+preferenceName+":"+value);
    }

}
