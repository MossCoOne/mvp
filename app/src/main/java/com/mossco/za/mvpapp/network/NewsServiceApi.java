package com.mossco.za.mvpapp.network;

import com.mossco.za.mvpapp.news.model.NewsArticle;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface NewsServiceApi {

    @GET("news?format=json")
    Call<List<NewsArticle>> getLatestNews();

    @GET("{SiteName}/{UrlName}/news/{UrlFriendlyDate}/{UrlFriendlyHeadline}?format=json")
    Call<NewsArticle> getArticle(@Path("SiteName") String siteName, @Path("UrlName") String urlName,
            @Path("UrlFriendlyDate") String urlFriendlyDate, @Path("UrlFriendlyHeadline") String urlFriendlyHeadline);
}
