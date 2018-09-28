package com.example.omerom88.application;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by omerom88 on 13-Sep-18
 */
public class Logger {
    private static final String TAG = "Logger";

    @Inject
    public Logger() {
    }

    public void d(String tag, String message) {
        Log.d(tag, message);
    }

    public void d(String message) {
        Log.d(TAG, message);
    }
}
