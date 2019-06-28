package com.example.trnews.Utils;

import android.database.Observable;

import com.example.trnews.Model.ArticlesMostPopular;
import com.example.trnews.Model.Result;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

@SuppressWarnings("ALL")
public interface NYTimesDataService {


    @GET("mostpopular/v2/viewed/1.json")
    Call<ArticlesMostPopular> getMostPopularArticle(@Query("api-key") String key);

    //@GET("topstories/v2/")
    //Call<ArticlesTopStories> getTopStoriesArticles(@Query("section") String section, @Query("api_key") String key);


    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}

