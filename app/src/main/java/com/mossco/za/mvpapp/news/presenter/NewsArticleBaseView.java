package com.mossco.za.mvpapp.news.presenter;

public interface NewsArticleBaseView {
    void showProgressDialog();

    void dismissProgressDialog();

    void showFailedToLoadLatestNewsErrorMessage();
}
