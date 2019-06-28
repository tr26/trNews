package com.example.trnews.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.trnews.Activities.MainActivity;
import com.example.trnews.Activities.WebActivity;
import com.example.trnews.Model.ArticlesMostPopular;
import com.example.trnews.Model.Result;
import com.example.trnews.R;
import com.example.trnews.Utils.NYTimesDataService;
//import com.example.trnews.Utils.NytimesStreams;
import com.example.trnews.Utils.RetrofitInstance;
import com.example.trnews.Views.ArticlesMosPopularAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularFragment extends Fragment{

    public static final String API_KEY = "QAevoCdrGFGgi7mRDu8GBOME5RLn3veP";


    @BindView(R.id.recyclerViewMostPopularArticles)
    RecyclerView mRecyclerView;

    @BindView(R.id.toto)
    TextView mTextView;



    //private Disposable mDisposable;
    private List<Result> mResults;
    private ArticlesMosPopularAdapter mArticlesMosPopularAdapter;


    public MostPopularFragment() {
        // Required empty public constructor
    }



    public static MostPopularFragment newInstance() {
        return (new MostPopularFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_most_popular, container, false);
        ButterKnife.bind(this, v);
        this.HttpRequest();


        //this.configureRecyclerView();
        return v;
    }

    private void configureRecyclerView(){
        this.mResults = new ArrayList<>();
        this.mArticlesMosPopularAdapter = new ArticlesMosPopularAdapter(this.mResults);
        this.mRecyclerView.setAdapter(this.mArticlesMosPopularAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    /*private void executeHttpRequestWithRetrofit(){

        this.mDisposable = NytimesStreams.streamFetchResults(API_KEY).

    }*/
        private void HttpRequest(){
        NYTimesDataService nyTimesDataService = RetrofitInstance.getRetrofitInstance().create(NYTimesDataService.class);
        Call<ArticlesMostPopular> call = nyTimesDataService.getMostPopularArticle(API_KEY);

        call.enqueue(new Callback<ArticlesMostPopular>() {
            @Override
            public void onResponse(Call<ArticlesMostPopular> call, Response<ArticlesMostPopular> response) {
                //StringBuilder stringBuilder = new StringBuilder();
                List<Result> articlesMostPopularsList;
                articlesMostPopularsList = response.body().getResults();


                mResults = new ArrayList<>();



                for (Result articlesMostPopular : articlesMostPopularsList) {
                    mResults.add(articlesMostPopular);
                    //stringBuilder.append("-"+articlesMostPopular.getTitle()+"\n");

                }

                mArticlesMosPopularAdapter = new ArticlesMosPopularAdapter(mResults);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(mArticlesMosPopularAdapter);
                mResults.size();

                //mTextView.setText(stringBuilder.toString());
            }

            @Override
            public void onFailure(Call<ArticlesMostPopular> call, Throwable t) {

            }
        });
    }

    //Click to ho to article Website




}
