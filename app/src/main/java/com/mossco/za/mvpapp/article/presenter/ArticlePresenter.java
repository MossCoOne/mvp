package com.mossco.za.mvpapp.article.presenter;

import com.mossco.za.mvpapp.article.model.ArticleRepository;
import com.mossco.za.mvpapp.article.model.ArticleRepositoryImplementation;
import com.mossco.za.mvpapp.news.model.NewsArticle;

public class ArticlePresenter  implements ArticlesContract.UserActionsListener{

    private ArticleRepository articleRepository;
    private ArticlesContract.ArticleView articleView;

    public ArticlePresenter(ArticlesContract.ArticleView articleView) {
        articleRepository = new ArticleRepositoryImplementation();
        this.articleView = articleView;
    }

    @Override
    public void loadArticle(NewsArticle articleInformation) {
        articleView.showProgressDialog();
        articleRepository.loadArticle(articleInformation, new ArticleRepository.ArticleCallback() {
            @Override
            public void onArticleLoaded(NewsArticle newsArticle) {
                articleView.showArticle(newsArticle);
            }

            @Override
            public void onErrorOccurred(String errorMessage) {
                articleView.dismissProgressDialog();
                articleView.showFailedToLoadLatestNewsErrorMessage();
            }
        });
    }

}
