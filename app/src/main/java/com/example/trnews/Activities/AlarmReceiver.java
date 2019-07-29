package com.example.trnews.Activities;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;

import com.example.trnews.Model.ArticlesResultsResearch;
import com.example.trnews.Model.Doc;
import com.example.trnews.R;
import com.example.trnews.Utils.NYTimesDataService;
import com.example.trnews.Utils.RetrofitInstance;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class AlarmReceiver extends BroadcastReceiver {
    int nbNewMessages;

    SharedPreferences mSharedPreferences;
    @Override
    public void onReceive(final Context context, Intent intent) {
        mSharedPreferences = context.getSharedPreferences("CATEGORIES_CHECKED", 0);

        String toto = mSharedPreferences.getString("CATEGORIES_CHECKED", null);
        final String tata = mSharedPreferences.getString("firstId", null);



        nbNewMessages=0;
        mSharedPreferences = context.getSharedPreferences("CATEGORIES_CHECKED", 0);

        String categories = mSharedPreferences.getString("CATEGORIES_CHECKED", null);
        String keyword = mSharedPreferences.getString("KEY_WORD", null);

        String API_KEY = "QAevoCdrGFGgi7mRDu8GBOME5RLn3veP";
        final String[] firstId = new String[1];

        Date date = new Date();
        long moment = date.getTime();

        areNewMessages(context);



    }

    private int areNewMessages(final Context context){

        mSharedPreferences = context.getSharedPreferences("CATEGORIES_CHECKED", 0);

        String categories = mSharedPreferences.getString("CATEGORIES_CHECKED", null);
        String keyword = mSharedPreferences.getString("KEY_WORD", null);
        final String tata = mSharedPreferences.getString("firstId", null);

        String API_KEY = "QAevoCdrGFGgi7mRDu8GBOME5RLn3veP";
        final String[] firstId = new String[1];





        NYTimesDataService nyTimesDataService = RetrofitInstance.getRetrofitInstance().create(NYTimesDataService.class);
        Call<ArticlesResultsResearch> call = nyTimesDataService.getNotificationSearch(categories,keyword ,API_KEY);

        call.enqueue(new Callback<ArticlesResultsResearch>() {
            @Override
            public void onResponse(Call<ArticlesResultsResearch> call, retrofit2.Response<ArticlesResultsResearch> response) {
                //StringBuilder stringBuilder = new StringBuilder();
                List<Doc> articlesResearchList;
                articlesResearchList = response.body().getResponse().getDocs();
                ArrayList<String> tabId = new ArrayList<String>();

                System.out.println("liste d'Id à la réponse ");
                for (Doc article : articlesResearchList) {
                    tabId.add(article.getId());
                    System.out.println("\n"+article.getId()+nbNewMessages);
                }
                tabId.add(0, "azertyuiopmlkjhgfdsqwxcvbn,");
                tabId.add(1, "nbvcxwmlkjhgfdsqpoiuytreza,");


                /*for (int i =0; i<tabId.size(); i++){
                    if (tata.equals(tabId.get(i))){
                        nbNewMessages = i;
                    } else nbNewMessages = 11;
                }*/
                int i = 0;
                while(!tata.equals(tabId.get(i)) && i<10){
                    nbNewMessages = i;
                    i++;
                }


                mSharedPreferences = context.getSharedPreferences("CATEGORIES_CHECKED", 0);

                String toto = mSharedPreferences.getString("CATEGORIES_CHECKED", null);
                final String tata = mSharedPreferences.getString("firstId", null);

                Intent intent10 = new Intent(context.getApplicationContext(), MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 1, intent10, 0);
                Date date = new Date();
                long moment = date.getTime();

                if (nbNewMessages != 9) {
                    Notification notification = new Notification.Builder(context.getApplicationContext())
                            .setContentTitle("TR NEWS" + moment)
                            .setContentText("Vous avez " + nbNewMessages + " nouveaux articles non lues")
                            .setSmallIcon(R.drawable.ic_account_box_white_24dp)
                            .setContentIntent(pendingIntent)
                            .build();

                    NotificationManager notificationManager = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(1, notification);
                } else {
                    Notification notification = new Notification.Builder(context.getApplicationContext())
                            .setContentTitle("TR NEWS" + moment)
                            .setContentText("Vous avez plus de 10 nouveaux articles non lues")
                            .setSmallIcon(R.drawable.ic_account_box_white_24dp)
                            .setContentIntent(pendingIntent)
                            .build();

                    NotificationManager notificationManager = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(1, notification);
                }



            }

            @Override
            public void onFailure(Call<ArticlesResultsResearch> call, Throwable t) {

            }
        });


        return nbNewMessages;
    }

}
