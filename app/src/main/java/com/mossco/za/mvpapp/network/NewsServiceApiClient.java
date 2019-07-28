package com.mossco.za.mvpapp.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mossco.za.mvpapp.utilities.StringsUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsServiceApiClient {

    public static NewsServiceApi newsServiceApi;

    private NewsServiceApiClient() {

    }

    public static NewsServiceApi getInstance() {
        Retrofit retrofit;
        if (newsServiceApi == null) {
            Gson gson = new GsonBuilder()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(StringsUtils.NEWS_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            newsServiceApi = retrofit.create(NewsServiceApi.class);
        }
        return newsServiceApi;
    }
}
