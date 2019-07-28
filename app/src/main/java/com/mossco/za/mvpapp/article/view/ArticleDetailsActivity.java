package com.mossco.za.mvpapp.article.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.google.gson.Gson;
import com.mossco.za.mvpapp.R;
import com.mossco.za.mvpapp.databinding.ActivityArticleDetailsBinding;
import com.mossco.za.mvpapp.news.model.NewsArticle;
import com.mossco.za.mvpapp.utilities.StringsUtils;

import java.io.IOException;
import java.io.InputStream;

public class ArticleDetailsActivity extends AppCompatActivity {

    public static final String NEWS_ARTICLE_KEY = "news_article";
    ActivityArticleDetailsBinding binding;

    public static Intent getStartIntent(Context context, NewsArticle newsArticle) {
        Intent intent = new Intent(context, ArticleDetailsActivity.class);
        intent.putExtra(NEWS_ARTICLE_KEY,newsArticle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_details);
        NewsArticle newsArticle =  getNewsArcticleResponse();
        binding.articleDateTextView.setText(StringsUtils.getFormattedDateWithTime(newsArticle.getDateCreated()));
        binding.articleTitleTextView.setText(newsArticle.getHeadline());
        binding.largeImageAltTextView.setText(newsArticle.getLargeImageAlt());
        binding.articleDescription.setText(newsArticle.getStoryBody());
        binding.articleImageView.setImageResource(R.drawable.beast);
    }


    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("article_mock.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public NewsArticle getNewsArcticleResponse() {
        Gson gson = new Gson();

        NewsArticle newsArticles = gson.fromJson(loadJSONFromAsset(), NewsArticle.class);

        return newsArticles;
    }
}
