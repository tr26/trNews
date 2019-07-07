package com.example.trnews.Views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trnews.Model.ArticlesSports;
import com.example.trnews.Model.Result;
import com.example.trnews.Model.ResultsTS;
import com.example.trnews.R;

import java.util.List;

public class ArticlesSportsAdapter extends RecyclerView.Adapter<ArticlesSportsViewHolder> {

    private List<ResultsTS> mListResult;

    public ArticlesSportsAdapter(List<ResultsTS> results) {
        this.mListResult = results;
    }

    @NonNull
    @Override
    public ArticlesSportsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                                      .inflate(R.layout.recyclerview_row, viewGroup, false);

        ArticlesSportsViewHolder articlesSportsViewHolder = new ArticlesSportsViewHolder(itemView);

        return articlesSportsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesSportsViewHolder articlesSportsViewHolder, int i) {
        articlesSportsViewHolder.updateWithArticlesSports(this.mListResult.get(i));
    }

    @Override
    public int getItemCount() {
        return this.mListResult.size();
    }
}
