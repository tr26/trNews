package com.example.trnews.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trnews.Model.ArticlesTopStories;
import com.example.trnews.Model.ResultsTS;
import com.example.trnews.R;
import com.example.trnews.Utils.NYTimesDataService;
import com.example.trnews.Utils.RetrofitInstance;
import com.example.trnews.Views.ArticlesTopStoriesAdapter;

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
public class TopStoriesFragment extends Fragment{


    public static final String API_KEY = "QAevoCdrGFGgi7mRDu8GBOME5RLn3veP";
    public static final String SECTION = ".json";

    @BindView(R.id.recyclerViewTopStoriesArticles)
    RecyclerView mRecyclerViewTopStories;





    //private Disposable mDisposable;
    private List<ResultsTS> mResults;
    private ArticlesTopStoriesAdapter mArticlesTopStoriesAdapter;
    public TopStoriesFragment() {
        // Required empty public constructor
    }

    public static TopStoriesFragment newInstance() {
        return (new TopStoriesFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_top_stories, container, false);
        ButterKnife.bind(this, v);
        this.HttpRequest();


        //this.configureRecyclerView();
        return v;
    }


    private void HttpRequest(){
        NYTimesDataService nyTimesDataService = RetrofitInstance.getRetrofitInstance().create(NYTimesDataService.class);
        Call<ArticlesTopStories> call = nyTimesDataService.getTopStoriesArticles("politics"+SECTION, API_KEY);

        call.enqueue(new Callback<ArticlesTopStories>() {
            @Override
            public void onResponse(Call<ArticlesTopStories> call, Response<ArticlesTopStories> response) {
                //StringBuilder stringBuilder = new StringBuilder();
                List<ResultsTS> articlesTopStoriesList;
                articlesTopStoriesList = response.body().getResults();


                mResults = new ArrayList<>();



                for (ResultsTS articlesTopStories : articlesTopStoriesList) {
                    mResults.add(articlesTopStories);
                    //stringBuilder.append("-"+articlesMostPopular.getTitle()+"\n");

                }

                mArticlesTopStoriesAdapter = new ArticlesTopStoriesAdapter(mResults);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                mRecyclerViewTopStories.setLayoutManager(mLayoutManager);
                mRecyclerViewTopStories.setItemAnimator(new DefaultItemAnimator());
                mRecyclerViewTopStories.setAdapter(mArticlesTopStoriesAdapter);
                mResults.size();

                //mTextView.setText(stringBuilder.toString());
            }

            @Override
            public void onFailure(Call<ArticlesTopStories> call, Throwable t) {

            }
        });
    }



}
