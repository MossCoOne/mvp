package com.mossco.za.mvpapp.news.model;

import java.util.List;

public interface NewsRepository {

    void loadLatestNews( LatestNewsCallback latestNewsCallback);

    interface LatestNewsCallback {
        void onLatestNewsLoaded(List<NewsArticle> newsArticles);

        void onErrorOccurred(String errorMessage);
    }

}
