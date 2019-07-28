package com.mossco.za.mvpapp.article.model;
import com.mossco.za.mvpapp.news.model.NewsArticle;

public interface ArticleRepository {
    void loadArticle(NewsArticle articleInformation,ArticleCallback articleCallback);

    interface ArticleCallback {
        void onArticleLoaded(NewsArticle newsArticle);

        void onErrorOccurred(String errorMessage);
    }
}

