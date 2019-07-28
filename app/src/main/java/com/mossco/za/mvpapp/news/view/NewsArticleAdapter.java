package com.mossco.za.mvpapp.news.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.mossco.za.mvpapp.R;
import com.mossco.za.mvpapp.databinding.NewsArticleLayoutBinding;
import com.mossco.za.mvpapp.news.model.NewsArticle;
import com.mossco.za.mvpapp.utilities.DrawableUtils;

import java.util.List;

import static com.mossco.za.mvpapp.utilities.StringsUtils.getFormattedDate;

public class NewsArticleAdapter extends RecyclerView.Adapter<NewsArticleAdapter.NewsArticleViewHolder> {

   private List<NewsArticle> newsArticleList;
   private OnItemClickListener onItemClickListener;

    NewsArticleAdapter(List<NewsArticle> newsArticleList,OnItemClickListener onItemClickListener) {
        this.newsArticleList = newsArticleList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public NewsArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsArticleLayoutBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.news_article_layout, parent, false);
        return new NewsArticleViewHolder(binding,onItemClickListener);
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

    public interface OnItemClickListener {
        void onItemClicked(NewsArticle extraItem);
    }

    class NewsArticleViewHolder extends RecyclerView.ViewHolder{

        Chip categoryChip;
        TextView headlineTextView;
        AppCompatImageView thumbNailImageView;
        TextView dateTextView;
        TextView blurbTextView;
        ConstraintLayout itemContainerLayout;
        OnItemClickListener onItemClickListener;
        NewsArticle newsArticle;

        NewsArticleViewHolder(NewsArticleLayoutBinding binding,OnItemClickListener onItemClickListener) {
            super(binding.getRoot());
            categoryChip = binding.categoryChip;
            headlineTextView = binding.headlineTextView;
            thumbNailImageView = binding.thumbNailImageView;
            dateTextView = binding.dateTextView;
            blurbTextView = binding.blurbTextView;
            itemContainerLayout = binding.itemContainer;
            this.onItemClickListener = onItemClickListener;
            itemContainerLayout.setOnClickListener(v -> onItemClickListener.onItemClicked(newsArticle));
        }

        void bindDataToView(NewsArticle newsArticle) {
            this.newsArticle = newsArticle;
            categoryChip.setText(newsArticle.getCategory());
            headlineTextView.setText(newsArticle.getHeadline());
            dateTextView.setText(getFormattedDate(newsArticle.getDateCreated()));
            blurbTextView.setText(newsArticle.getBlurb());
            Glide.with(thumbNailImageView.getContext())
                    .load(newsArticle.getImageUrlRemote().concat(newsArticle.getSmallImageName())).dontAnimate().fitCenter()
                    .placeholder(DrawableUtils.getCircularProgressDrawable(thumbNailImageView.getContext()))
                    .error(R.drawable.ic_image_not_availabe).into(thumbNailImageView);
        }

    }
}
