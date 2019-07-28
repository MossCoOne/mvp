package com.mossco.za.mvpapp.news.presenter;

import com.mossco.za.mvpapp.news.model.NewsArticle;

import java.util.List;

public interface NewsContract {
    interface NewsView extends NewsArticleBaseView {
        void displayLatestNews(List<NewsArticle> newsArticles);
    }

    interface UserActionsListener {
        void loadLatestNews();
    }

}
