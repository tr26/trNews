package com.example.trnews.Model;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.trnews.Activities.MainActivity;
import com.example.trnews.R;
import com.example.trnews.Utils.MyService;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MyBroadcastReceiver extends BroadcastReceiver {
    SharedPreferences sharedPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Intent i = new Intent(context.getApplicationContext(), MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 1, i, 0);

            Notification notification = new Notification.Builder(context.getApplicationContext())
                    .setContentTitle("premier test")
                    .setContentText("rtyuio")
                    .setSmallIcon(R.drawable.ic_account_box_white_24dp)
                    .setContentIntent(pendingIntent)
                    .build();

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(1, notification);



            Log.d("tahar", "tahar");
            Toast.makeText(context.getApplicationContext(), "toto1", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context.getApplicationContext(), "Alarm Manager just ran", Toast.LENGTH_LONG).show();
            Log.d("tahar2", "tahar2");

        }

    }
}
