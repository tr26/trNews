package com.example.trnews.Views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trnews.Model.ResultsTS;
import com.example.trnews.R;

import java.util.List;

public class ArticlesTopStoriesAdapter extends RecyclerView.Adapter<ArticlesTopStoriesHolder> {


    private List<ResultsTS> mResultsList;

    public ArticlesTopStoriesAdapter(List<ResultsTS> resultsList) {
        this.mResultsList = resultsList;
    }

    @NonNull
    @Override
    public ArticlesTopStoriesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_row, viewGroup, false);
        ArticlesTopStoriesHolder articlesTopStoriesHolder = new ArticlesTopStoriesHolder(itemView);

        return articlesTopStoriesHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesTopStoriesHolder articlesTopStoriesHolder, int i) {

        articlesTopStoriesHolder.updateWithArticlesMostPopular(this.mResultsList.get(i));
    }

    @Override
    public int getItemCount() {
        return this.mResultsList.size();
    }
}
