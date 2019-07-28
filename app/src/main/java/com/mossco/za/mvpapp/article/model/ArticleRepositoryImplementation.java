package com.mossco.za.mvpapp.article.model;

import com.mossco.za.mvpapp.network.NewsServiceApi;
import com.mossco.za.mvpapp.network.NewsServiceApiClient;
import com.mossco.za.mvpapp.news.model.NewsArticle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepositoryImplementation implements ArticleRepository {

    NewsServiceApi newsServiceApi;

    public ArticleRepositoryImplementation() {
        newsServiceApi = NewsServiceApiClient.getInstance();
    }

    @Override
    public void loadArticle(NewsArticle articleInformation, ArticleCallback articleCallback) {
        newsServiceApi.getArticle(articleInformation.getSiteName(), articleInformation.getUrlName(),
                articleInformation.getUrlFriendlyDate(), articleInformation.getUrlFriendlyHeadline())
                .enqueue(new Callback<NewsArticle>() {
                    @Override
                    public void onResponse(Call<NewsArticle> call, Response<NewsArticle> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            articleCallback.onArticleLoaded(response.body());
                        } else {
                            articleCallback.onErrorOccurred(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsArticle> call, Throwable throwable) {
                        articleCallback.onErrorOccurred(throwable.getMessage());
                    }
                });
    }
}
