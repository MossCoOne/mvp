package com.mossco.za.mvpapp.article.presenter;

import com.mossco.za.mvpapp.news.model.NewsArticle;
import com.mossco.za.mvpapp.news.presenter.NewsArticleBaseView;

public interface ArticlesContract {

    interface ArticleView extends NewsArticleBaseView {
        void showArticle(NewsArticle newsArticle);
    }

    interface UserActionsListener {
        void loadArticle(NewsArticle articleInformation);
    }
}
