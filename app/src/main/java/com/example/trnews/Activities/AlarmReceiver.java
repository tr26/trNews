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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class AlarmReceiver extends BroadcastReceiver {

    SharedPreferences mSharedPreferences;
    @Override
    public void onReceive(Context context, Intent intent) {
        mSharedPreferences = context.getSharedPreferences("CATEGORIES_CHECKED", 0);

        String toto = mSharedPreferences.getString("CATEGORIES_CHECKED", null);
        final String tata = mSharedPreferences.getString("firstId", null);

        Intent i = new Intent(context.getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 1, i, 0);





        NYTimesDataService nyTimesDataService = RetrofitInstance.getRetrofitInstance().create(NYTimesDataService.class);
        Call<ArticlesResultsResearch> call = nyTimesDataService.getFirstOfTheRequest(toto, "QAevoCdrGFGgi7mRDu8GBOME5RLn3veP");

        final String[] firstId = new String[1];
        final int[] nbNewMessages = {0};


        call.enqueue(new Callback<ArticlesResultsResearch>() {
            @Override
            public void onResponse(Call<ArticlesResultsResearch> call, retrofit2.Response<ArticlesResultsResearch> response) {
                //StringBuilder stringBuilder = new StringBuilder();
                List<Doc> articlesResearchList;
                articlesResearchList = response.body().getResponse().getDocs();


                //mResponseList = new ArrayList<Doc>();
                firstId[0] = articlesResearchList.get(0).getId();
                String aaaaaaa = firstId[0];

                if (tata.equals(aaaaaaa)) nbNewMessages[0] = 99;
                else {
                    int i = 0;
                    while (articlesResearchList.get(i).getId() != tata){
                        nbNewMessages[0]++;
                    }
                }



            }

            @Override
            public void onFailure(Call<ArticlesResultsResearch> call, Throwable t) {

            }
        });

        if (nbNewMessages[0] != 99){
            Notification notification = new Notification.Builder(context.getApplicationContext())
                    .setContentTitle("TR NEWS")
                    .setContentText("Vous avez "+nbNewMessages[0]+" nouveaux articles non lues")
                    .setSmallIcon(R.drawable.ic_account_box_white_24dp)
                    .setContentIntent(pendingIntent)
                    .build();

            NotificationManager notificationManager = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, notification);
        }

        //areNewMessages(context);

    }

    private int[] areNewMessages(Context context){

        mSharedPreferences = context.getSharedPreferences("CATEGORIES_CHECKED", 0);

        String toto = mSharedPreferences.getString("CATEGORIES_CHECKED", null);
        final String tata = mSharedPreferences.getString("firstId", null);

        String API_KEY = "QAevoCdrGFGgi7mRDu8GBOME5RLn3veP";
        final String[] firstId = new String[1];


        final int[] nbNewMessages = {0};


        NYTimesDataService nyTimesDataService = RetrofitInstance.getRetrofitInstance().create(NYTimesDataService.class);
        Call<ArticlesResultsResearch> call = nyTimesDataService.getFirstOfTheRequest(toto, API_KEY);

        call.enqueue(new Callback<ArticlesResultsResearch>() {
            @Override
            public void onResponse(Call<ArticlesResultsResearch> call, retrofit2.Response<ArticlesResultsResearch> response) {
                //StringBuilder stringBuilder = new StringBuilder();
                List<Doc> articlesResearchList;
                articlesResearchList = response.body().getResponse().getDocs();


                //mResponseList = new ArrayList<Doc>();
                firstId[0] = articlesResearchList.get(0).getId();
                String aaaaaaa = firstId[0];

                if (tata == aaaaaaa){
                    nbNewMessages[0] = 99;
                } else {
                    int i = 0;
                    //nbNewMessages = 0;
                    while (articlesResearchList.get(i).getId() != tata){
                        nbNewMessages[0]++;
                    }
                }



            }

            @Override
            public void onFailure(Call<ArticlesResultsResearch> call, Throwable t) {

            }
        });

        int tableauChaine[] = {0, nbNewMessages[0]};
        return tableauChaine;
    }
}
