package com.mossco.za.mvpapp.article;

import androidx.appcompat.app.AppCompatActivity;
import com.mossco.za.mvpapp.news.NewsArticle;
import com.mossco.za.mvpapp.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ArticleDetailsActivity extends AppCompatActivity {

    public static final String NEWS_ARTICLE_KEY = "news_article";

    public static Intent getStartIntent(Context context, NewsArticle newsArticle) {
        Intent intent = new Intent(context, ArticleDetailsActivity.class);
        intent.putExtra(NEWS_ARTICLE_KEY,newsArticle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
    }
}
