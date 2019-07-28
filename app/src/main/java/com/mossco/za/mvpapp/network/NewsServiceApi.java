package com.mossco.za.mvpapp.network;

import com.mossco.za.mvpapp.news.NewsArticle;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface NewsServiceApi {

    @GET("news/json?")
    Call<List<NewsArticle>> getLatestNews();

    @GET("{SiteName}/{UrlName}/news/{UrlFriendlyDate}/{UrlFriendlyHeadline}?json")
    Call<NewsArticle> getArticle(@Path("siteName") String siteName);

}
