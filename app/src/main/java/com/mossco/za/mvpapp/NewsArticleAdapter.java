package com.mossco.za.mvpapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.mossco.za.mvpapp.databinding.NewsArticleLayoutBinding;

import java.util.List;

import static com.mossco.za.mvpapp.utilities.StringsUtils.getFormattedDate;

public class NewsArticleAdapter extends RecyclerView.Adapter<NewsArticleAdapter.NewsArticleViewHolder> {

   private List<NewsArticle> newsArticleList;

    NewsArticleAdapter(List<NewsArticle> newsArticleList) {
        this.newsArticleList = newsArticleList;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public NewsArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsArticleLayoutBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.news_article_layout, parent, false);
        return new NewsArticleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsArticleViewHolder holder, int position) {
        NewsArticle newsArticle = newsArticleList.get(position);
        holder.bindDataToView(newsArticle);
    }

    @Override
    public int getItemCount() {
        return newsArticleList != null ? newsArticleList.size() : 0;
    }

    class NewsArticleViewHolder extends RecyclerView.ViewHolder {

        Chip categoryChip;
        TextView headlineTextView;
        AppCompatImageView thumbNailImageView;
        TextView dateTextView;
        TextView blurbTextView;

        NewsArticleViewHolder(NewsArticleLayoutBinding binding) {
            super(binding.getRoot());
            categoryChip = binding.categoryChip;
            headlineTextView = binding.headlineTextView;
            thumbNailImageView = binding.thumbNailImageView;
            dateTextView = binding.dateTextView;
            blurbTextView = binding.blurbTextView;
        }

        void bindDataToView(NewsArticle newsArticle) {

            categoryChip.setText(newsArticle.getCategory());
            headlineTextView.setText(newsArticle.getHeadline());
            dateTextView.setText(getFormattedDate(newsArticle.getDateCreated()));
            blurbTextView.setText(newsArticle.getBlurb());
            Glide.with(thumbNailImageView.getContext())
                    .load(newsArticle.getImageUrlRemote().concat(newsArticle.getSmallImageName())).dontAnimate().fitCenter()
                    .error(R.drawable.ic_image_not_availabe).into(thumbNailImageView);
        }

    }
}
