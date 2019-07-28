package com.mossco.za.mvpapp.model;

import com.mossco.za.mvpapp.news.model.NewsArticle;

public interface ArticleRepository {
    void loadArticle(ArticleInformation articleInformation,ArticleCallback articleCallback);

    interface ArticleCallback {
        void onArticleLoaded(NewsArticle newsArticle);

        void onErrorOccurred(String errorMessage);
    }
}

