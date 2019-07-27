package com.mossco.za.mvpapp.news;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mossco.za.mvpapp.R;
import com.mossco.za.mvpapp.article.ArticleDetailsActivity;
import com.mossco.za.mvpapp.databinding.ActivityMainBinding;
import com.mossco.za.mvpapp.utilities.StringsUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private List<NewsArticle> newsArticleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        newsArticleList = getNewsArcticleResponse();
        NewsArticle mainStory = getMainStory(newsArticleList);
        if (mainStory != null) {
            populateMainStory(mainStory);
        } else {
            ratherDontShowMainStoryView();
        }

        newsArticleList.remove(mainStory);

        binding.newsArticleRecyclerView.setAdapter(new NewsArticleAdapter(newsArticleList));
        binding.newsArticleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.mainStoryContainer.setOnClickListener(
                view -> startActivity(ArticleDetailsActivity.getStartIntent(view.getContext(),mainStory)));
    }

    private void ratherDontShowMainStoryView() {
        binding.mainStoryContainer.setVisibility(View.GONE);
    }

    private void populateMainStory(NewsArticle mainStory) {
        binding.categoryChip.setText(mainStory.getCategory());
        binding.headlineStoryTitle.setText(mainStory.getHeadline());
        binding.headlineStoryDateTextView.setText(StringsUtils.getFormattedDate(mainStory.getDateCreated()));
        binding.headlineStoryImageView.setImageResource(R.drawable.beast);
    }

    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("news_response_mock.json");
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

    public List<NewsArticle> getNewsArcticleResponse() {
        Gson gson = new Gson();

        Type collectionType = new TypeToken<Collection<NewsArticle>>() {
        }.getType();
        Collection<NewsArticle> newsArticles = gson.fromJson(loadJSONFromAsset(), collectionType);

        return (List<NewsArticle>) newsArticles;
    }

    private NewsArticle getMainStory(List<NewsArticle> newsArticleList) {
        for (NewsArticle newsArticle : newsArticleList) {
            if (newsArticle.isMainStory()) {
                newsArticleList.remove(newsArticle);
                return newsArticle;
            }
        }
        return null;
    }
}
