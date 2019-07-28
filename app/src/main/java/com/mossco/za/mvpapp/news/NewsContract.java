package com.mossco.za.mvpapp.news;

import java.util.List;

public interface NewsContract {
    interface View {
        void displayLatestNews(List<NewsArticle> newsArticles);

        void showProgressDialog();

        void dismissProgressDialog();

        void showFailedToLoadLatestNewsErrorMessage();
    }

    interface UserActionsListener {
        void loadLatestNews();
    }

}
