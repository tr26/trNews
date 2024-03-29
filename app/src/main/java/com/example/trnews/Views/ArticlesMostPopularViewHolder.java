package com.example.trnews.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.trnews.Model.Result;
import com.example.trnews.Activities.WebActivity;
import com.example.trnews.R;
import com.squareup.picasso.Picasso;


public class ArticlesMostPopularViewHolder  extends RecyclerView.ViewHolder {

    private final Context context;

    Activity mMainActivity;
    LinearLayout mLinearLayout;
    TextView mTextViewTitle;
    TextView mTextViewDate;
    TextView mTextViewContentArticle;
    ImageView mImageView;

    public ArticlesMostPopularViewHolder(View itemView) {
        super(itemView);

        context = itemView.getContext();
        mLinearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        mTextViewDate = (TextView) itemView.findViewById(R.id.dateArticle);
        mTextViewTitle = (TextView) itemView.findViewById(R.id.titleArticle);
        mTextViewContentArticle = (TextView) itemView.findViewById(R.id.contentArticle);
        mImageView = (ImageView) itemView.findViewById(R.id.imageView);
    }


    public void updateWithArticlesMostPopular(final Result articleMostPopular){

        //this.mTextView.setText(articleMostPopular.getTitle());
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWebsite(articleMostPopular.getUrl());
            }
        });
        this.mTextViewDate.setText(articleMostPopular.getPublishedDate());
        this.mTextViewTitle.setText(articleMostPopular.getTitle().toUpperCase());
        this.mTextViewContentArticle.setText(articleMostPopular.getSection());
        if (!articleMostPopular.getMedia().isEmpty()){
            showImage(articleMostPopular);
            //articleMostPopular.getMedia().get(0).getMediaMetadata().get(0).getUrl();

        }


    }

    private void showImage(Result articleMostPopular) {
        String url = articleMostPopular.getMedia().get(0).getMediaMetadata().get(0).getUrl();
        Picasso.with(this.context).load(url).into(mImageView);
    }

    private void goToWebsite(String url){
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
}
