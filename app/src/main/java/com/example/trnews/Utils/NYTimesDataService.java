package com.example.trnews.Utils;

import com.example.trnews.Model.ArticlesMostPopular;
import com.example.trnews.Model.ArticlesResultsResearch;
import com.example.trnews.Model.ArticlesSports;
import com.example.trnews.Model.ArticlesTopStories;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

@SuppressWarnings("ALL")
public interface NYTimesDataService {


    @GET("mostpopular/v2/viewed/1.json")
    Call<ArticlesMostPopular> getMostPopularArticle(@Query("api-key") String key);

    @GET("topstories/v2/{section}")
    Call<ArticlesTopStories> getTopStoriesArticles(@Path("section") String section, @Query("api-key") String key);

    @GET("search/v2/articlesearch.json")
    Call<ArticlesResultsResearch> getResearchArticles(
            @Query("fq") String filteredQuery,
            @Query("begin_date") String dateBegin,
            @Query("end_date") String dateEnd,
            @Query("api-key") String key);

    @GET("topstories/v2/sports.json")
    Call<ArticlesSports> getSportsArticles(@Query("api-key") String key);



    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}

