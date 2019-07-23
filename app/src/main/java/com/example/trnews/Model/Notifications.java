package com.example.trnews.Model;

import android.app.AlarmManager;
import android.app.Notification;
import android.content.SharedPreferences;

public class Notifications {

    SharedPreferences mSharedPreferences;
    AlarmManager mAlarmManager;
    ResultsTS mResultsTS;
    Notification mNotification;

    public Notifications(SharedPreferences sharedPreferences, AlarmManager alarmManager, ResultsTS resultsTS, Notification notification) {
        mSharedPreferences = sharedPreferences;
        mAlarmManager = alarmManager;
        mResultsTS = resultsTS;
        mNotification = notification;
    }


}
