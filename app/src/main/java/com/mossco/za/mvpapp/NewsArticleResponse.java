package com.mossco.za.mvpapp;

import java.io.Serializable;
import java.util.List;

public class NewsArticleResponse implements Serializable {
    private List<NewsArticle> newsArticleList;

    public List<NewsArticle> getNewsArticleList() {
        return newsArticleList;
    }

    public void setNewsArticleList(List<NewsArticle> newsArticleList) {
        this.newsArticleList = newsArticleList;
    }
}
