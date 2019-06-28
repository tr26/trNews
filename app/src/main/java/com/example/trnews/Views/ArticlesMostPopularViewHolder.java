package com.example.trnews.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.trnews.Activities.MainActivity;
import com.example.trnews.Activities.WebActivity;
import com.example.trnews.Model.Result;
import com.example.trnews.R;


import butterknife.BindView;
import butterknife.ButterKnife;


public class ArticlesMostPopularViewHolder  extends RecyclerView.ViewHolder {

    private final Context context;

    Activity mMainActivity;
    LinearLayout mLinearLayout;
    TextView mTextViewTitle;
    TextView mTextViewDate;
    TextView mTextViewContentArticle;

    public ArticlesMostPopularViewHolder(View itemView) {
        super(itemView);

        context = itemView.getContext();
        mLinearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        mTextViewDate = (TextView) itemView.findViewById(R.id.dateArticle);
        mTextViewTitle = (TextView) itemView.findViewById(R.id.titleArticle);
        mTextViewContentArticle = (TextView) itemView.findViewById(R.id.contentArticle);

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
        this.mTextViewTitle.setText(articleMostPopular.getTitle());
        this.mTextViewContentArticle.setText(articleMostPopular.getAbstract());

    }

    private void goToWebsite(String url){
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
}
