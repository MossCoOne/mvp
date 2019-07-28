package com.mossco.za.mvpapp.model;

import com.mossco.za.mvpapp.network.NewsServiceApi;
import com.mossco.za.mvpapp.network.NewsServiceApiClient;
import com.mossco.za.mvpapp.news.NewsArticle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class NewsRepositoryImplementation implements NewsRepository {

    NewsServiceApi newsServiceApi;

    public NewsRepositoryImplementation() {
        newsServiceApi = NewsServiceApiClient.getInstance();
    }

    @Override
    public void loadLatestNews(LatestNewsCallback latestNewsCallback) {
        newsServiceApi.getLatestNews().enqueue(new Callback<List<NewsArticle>>() {
            @Override
            public void onResponse(Call<List<NewsArticle>> call, Response<List<NewsArticle>> response) {
                if (response.isSuccessful() && response.body()!=null){
                    latestNewsCallback.onLatestNewsLoaded(response.body());
                }else {
                    latestNewsCallback.onErrorOccurred(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<NewsArticle>> call, Throwable throwable) {
                latestNewsCallback.onErrorOccurred(throwable.getMessage());
            }
        });
    }
}
