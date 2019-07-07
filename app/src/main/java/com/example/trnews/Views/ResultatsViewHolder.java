package com.example.trnews.Views;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.trnews.Activities.WebActivity;
import com.example.trnews.Model.ArticlesResultsResearch;
import com.example.trnews.Model.Doc;
import com.example.trnews.Model.Person;
import com.example.trnews.Model.Response;
import com.example.trnews.Model.ResultsTS;
import com.example.trnews.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultatsViewHolder extends RecyclerView.ViewHolder {

    LinearLayout mLinearLayout;
    TextView mTextViewTitle;
    TextView mTextViewDate;
    TextView mTextViewContentArticle;
    Context context;
    ImageView mImageView;

    public ResultatsViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        mLinearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        mTextViewDate = (TextView) itemView.findViewById(R.id.dateArticle);
        mTextViewTitle = (TextView) itemView.findViewById(R.id.titleArticle);
        mTextViewContentArticle = (TextView) itemView.findViewById(R.id.contentArticle);
        mImageView = (ImageView) itemView.findViewById(R.id.imageView);
    }


    public void updateWithResultsOfResearch(final Doc resultsArticles){

        this.mTextViewTitle.setText(resultsArticles.getHeadline().getMain());
        //this.mTextViewContentArticle.setText();

        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWebsite(resultsArticles.getWebUrl());
            }
        });

        if (resultsArticles.getMultimedia() != null){
            showImage(resultsArticles);
        }

    }

    private void showImage(Doc resultsArticles) {
        if (resultsArticles.getMultimedia().size() == 0){
            Log.e("eee", "toto");
        } else {
            String url = resultsArticles.getMultimedia().get(0).getUrl();
            Picasso.with(this.context).load(url).into(mImageView);
        }
    }

    private void goToWebsite(String url){
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
}
