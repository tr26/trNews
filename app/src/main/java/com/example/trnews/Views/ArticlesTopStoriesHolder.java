package com.example.trnews.Views;

import android.app.Activity;
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
import com.example.trnews.Model.Multimedia;
import com.example.trnews.Model.ResultsTS;
import com.example.trnews.R;
import com.squareup.picasso.Picasso;

import java.util.List;

class ArticlesTopStoriesHolder extends RecyclerView.ViewHolder {
    Activity mMainActivity;
    LinearLayout mLinearLayout;
    TextView mTextViewTitle;
    TextView mTextViewDate;
    TextView mTextViewContentArticle;
    Context context;
    ImageView mImageView;

    public ArticlesTopStoriesHolder(@NonNull View itemView) {
        super(itemView);

        context = itemView.getContext();
        mLinearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        mTextViewDate = (TextView) itemView.findViewById(R.id.dateArticle);
        mTextViewTitle = (TextView) itemView.findViewById(R.id.titleArticle);
        mTextViewContentArticle = (TextView) itemView.findViewById(R.id.contentArticle);
        mImageView = (ImageView) itemView.findViewById(R.id.imageView);
    }


    public void updateWithArticlesMostPopular(final ResultsTS articleTopStories){

        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWebsite(articleTopStories.getUrl());
            }
        });
        this.mTextViewDate.setText(articleTopStories.getPublishedDate().substring(0,10));
        this.mTextViewTitle.setText(articleTopStories.getTitle().toUpperCase());
        this.mTextViewContentArticle.setText(articleTopStories.getSection());

        if (articleTopStories.getMultimedia() != null){
            showImage(articleTopStories);


        }

    }

    private void showImage(ResultsTS articlesTopStories) {
        if (articlesTopStories.getMultimedia().size() == 0){
            Log.e("eee", "toto");
        } else {
            String url = articlesTopStories.getMultimedia().get(0).getUrl();
            Picasso.with(this.context).load(url).into(mImageView);
        }
    }

    private void goToWebsite(String url){
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
}
