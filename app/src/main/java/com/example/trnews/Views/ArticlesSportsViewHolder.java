package com.example.trnews.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.trnews.Activities.WebActivity;
import com.example.trnews.Model.Result;
import com.example.trnews.Model.ResultsTS;
import com.example.trnews.R;
import com.squareup.picasso.Picasso;

public class ArticlesSportsViewHolder extends RecyclerView.ViewHolder {
    private final Context context;

    Activity mMainActivity;
    LinearLayout mLinearLayout;
    TextView mTextViewTitle;
    TextView mTextViewDate;
    TextView mTextViewContentArticle;
    ImageView mImageView;


    public ArticlesSportsViewHolder(View itemView) {
        super(itemView);

        context = itemView.getContext();
        mLinearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        mTextViewDate = (TextView) itemView.findViewById(R.id.dateArticle);
        mTextViewTitle = (TextView) itemView.findViewById(R.id.titleArticle);
        mTextViewContentArticle = (TextView) itemView.findViewById(R.id.contentArticle);
        mImageView = (ImageView) itemView.findViewById(R.id.imageView);
    }


    public void updateWithArticlesSports(final ResultsTS articlesSports){

        //this.mTextView.setText(articleMostPopular.getTitle());
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWebsite(articlesSports.getUrl());
            }
        });
        this.mTextViewDate.setText(articlesSports.getPublishedDate().substring(0,10));
        this.mTextViewTitle.setText(articlesSports.getTitle().toUpperCase());
        this.mTextViewContentArticle.setText(articlesSports.getSection());
        if (articlesSports.getMultimedia() != null){
            showImage(articlesSports);
            //articleMostPopular.getMedia().get(0).getMediaMetadata().get(0).getUrl();

        }


    }

    private void showImage(ResultsTS articlesSports) {
        String url = articlesSports.getMultimedia().get(0).getUrl();
        Picasso.with(this.context).load(url).into(mImageView);
    }

    private void goToWebsite(String url){
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
}
