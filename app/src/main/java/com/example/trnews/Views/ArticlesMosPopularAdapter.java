package com.example.trnews.Views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trnews.Model.Result;
import com.example.trnews.Views.ArticlesMostPopularViewHolder;
import com.example.trnews.R;

import java.util.List;

public class ArticlesMosPopularAdapter extends RecyclerView.Adapter<ArticlesMostPopularViewHolder> {

    private List<Result> mResultsList;
    public ArticlesMosPopularAdapter(List<Result> results) {
        this.mResultsList = results;
    }

    @NonNull
    @Override
    public ArticlesMostPopularViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_row, viewGroup, false);
        ArticlesMostPopularViewHolder articlesMosPopularAdapterViewHolder = new ArticlesMostPopularViewHolder(itemView);

        return articlesMosPopularAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesMostPopularViewHolder articlesMostPopularViewHolder, int i) {
        articlesMostPopularViewHolder.updateWithArticlesMostPopular(this.mResultsList.get(i));
    }

    @Override
    public int getItemCount() {
        return this.mResultsList.size();
    }
}
