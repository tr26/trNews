package com.example.trnews.Views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trnews.Model.ArticlesResultsResearch;
import com.example.trnews.Model.Doc;
import com.example.trnews.Model.Response;
import com.example.trnews.R;

import java.util.List;

public class ResultatsAdapter extends RecyclerView.Adapter<ResultatsViewHolder> {


    private List<Doc> mListArticlesResultsResearches;

    public ResultatsAdapter(List<Doc> articlesResultsResearches) {
        this.mListArticlesResultsResearches = articlesResultsResearches;
    }



    @NonNull
    @Override
    public ResultatsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_row, viewGroup, false);
        ResultatsViewHolder resultatsViewHolder = new ResultatsViewHolder(itemView);


        return resultatsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultatsViewHolder resultatsViewHolder, int i) {
        resultatsViewHolder.updateWithResultsOfResearch(this.mListArticlesResultsResearches.get(i));
    }

    @Override
    public int getItemCount() {
        return this.mListArticlesResultsResearches.size();
    }
}
