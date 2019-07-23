package com.example.trnews.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.trnews.Model.ArticlesResultsResearch;
import com.example.trnews.Model.Doc;
import com.example.trnews.R;
import com.example.trnews.Utils.NYTimesDataService;
import com.example.trnews.Utils.RetrofitInstance;
import com.example.trnews.Views.ArticlesMosPopularAdapter;
import com.example.trnews.Views.ResultatsAdapter;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class ResultActivity extends AppCompatActivity {

    public static final String API_KEY = "QAevoCdrGFGgi7mRDu8GBOME5RLn3veP";


    @BindView(R.id.recyclerViewResult)
    RecyclerView mRecyclerView;
    @BindView(R.id.textViewTitleResults)
    TextView mTextView;
    @BindView(R.id.imageButtonResult)
    ImageButton mImageButton;
    @BindView(R.id.textViewRecap)
    TextView mTextViewRecap;

    String mCategories;
    String mDateBegin;
    String mDateEnd;

    private List<Doc> mResponseList;
    private ResultatsAdapter mResultatsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        String dateBegin = bundle.get("dateBegin").toString();
        this.mDateBegin = dateBegin;

        String categories = bundle.getString("categories");
        this.mCategories = categories;

        String dateEnd = bundle.get("dateEnd").toString();
        this.mDateEnd = dateEnd;
        mTextViewRecap.setText("Récap de la recherche : \n" +
                "date de début - "+dateBegin+"\n" +
                "date de fin - "+dateEnd+"\n"+
                "catégories - "+categories);

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(mIntent);
                finish();
            }
        });

        this.HttpRequest();
    }

    private void configureRecyclerView(){
        this.mResponseList = new ArrayList<Doc>();
        this.mResultatsAdapter = new ResultatsAdapter(this.mResponseList);
        this.mRecyclerView.setAdapter(this.mResultatsAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void HttpRequest(){
        NYTimesDataService nyTimesDataService = RetrofitInstance.getRetrofitInstance().create(NYTimesDataService.class);
        Call<ArticlesResultsResearch> call;

        if (mDateBegin.equals(mDateEnd)) {
            call = nyTimesDataService.getResearchArticlesNoDate(mCategories, API_KEY);
        }
        else {
            call = nyTimesDataService.getResearchArticles(mCategories, mDateBegin, mDateEnd, API_KEY);
        }

        call.enqueue(new Callback<ArticlesResultsResearch>() {
            @Override
            public void onResponse(Call<ArticlesResultsResearch> call, retrofit2.Response<ArticlesResultsResearch> response) {
                List<Doc> articlesResearchList;
                articlesResearchList = response.body().getResponse().getDocs();

                mResponseList = new ArrayList<Doc>();

                for (Doc articleResearch : articlesResearchList) {
                    mResponseList.add(articleResearch);
                }

                mResultatsAdapter = new ResultatsAdapter(mResponseList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(mResultatsAdapter);
            }

            @Override
            public void onFailure(Call<ArticlesResultsResearch> call, Throwable t) {

            }
        });
    }
}
