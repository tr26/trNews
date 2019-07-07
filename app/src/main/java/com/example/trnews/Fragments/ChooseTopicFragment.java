package com.example.trnews.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trnews.Model.ArticlesSports;
import com.example.trnews.Model.Result;
import com.example.trnews.Model.ResultsTS;
import com.example.trnews.R;
import com.example.trnews.Utils.NYTimesDataService;
import com.example.trnews.Utils.RetrofitInstance;
import com.example.trnews.Views.ArticlesSportsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseTopicFragment extends Fragment {


    public static final String API_KEY = "QAevoCdrGFGgi7mRDu8GBOME5RLn3veP";


    @BindView(R.id.sports_fragment)
    RecyclerView mRecyclerView;




    //private Disposable mDisposable;
    private List<ResultsTS> mResults;
    private ArticlesSportsAdapter mArticlesSportsAdapter;


    public ChooseTopicFragment() {
        // Required empty public constructor
    }



    public static ChooseTopicFragment newInstance() {
        return (new ChooseTopicFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_choose_topic, container, false);
        ButterKnife.bind(this, v);
        this.HttpRequest();

        return v;
    }


    private void HttpRequest(){
        NYTimesDataService nyTimesDataService = RetrofitInstance.getRetrofitInstance().create(NYTimesDataService.class);
        Call<ArticlesSports> call = nyTimesDataService.getSportsArticles(API_KEY);

        call.enqueue(new Callback<ArticlesSports>() {
            @Override
            public void onResponse(Call<ArticlesSports> call, Response<ArticlesSports> response) {
                List<ResultsTS> articlesSportsList;
                articlesSportsList = response.body().getResults();


                mResults = new ArrayList<>();

                for (ResultsTS articlesSports : articlesSportsList) {
                    mResults.add(articlesSports);

                }

                mArticlesSportsAdapter = new ArticlesSportsAdapter(mResults);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(mArticlesSportsAdapter);
            }

            @Override
            public void onFailure(Call<ArticlesSports> call, Throwable t) {

            }
        });
    }

}
