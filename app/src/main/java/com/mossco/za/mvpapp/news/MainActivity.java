package com.mossco.za.mvpapp.news;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.mossco.za.mvpapp.R;
import com.mossco.za.mvpapp.article.ArticleDetailsActivity;
import com.mossco.za.mvpapp.databinding.ActivityMainBinding;
import com.mossco.za.mvpapp.utilities.StringsUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsContract.View {

    ActivityMainBinding binding;
    private List<NewsArticle> newsArticleList;
    private NewsPresenter newsPresenter;
    private NewsArticle mainStory;
    private ProgressDialog newsProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.mainToolbar);
        getSupportActionBar().setTitle(getString(R.string.news_title));
        newsPresenter = new NewsPresenter(this);

        newsPresenter.loadLatestNews();

        binding.mainStoryContainer
                .setOnClickListener(view -> startActivity(ArticleDetailsActivity.getStartIntent(view.getContext(), mainStory)));
    }

    @Override
    public void displayLatestNews(List<NewsArticle> newsArticles) {

        mainStory = getMainStory(newsArticles);

        if (mainStory != null) {
            populateMainStory(mainStory);
        } else {
            ratherDontShowMainStoryView();
        }
        newsArticles.remove(mainStory);

        binding.newsArticleRecyclerView.setAdapter(new NewsArticleAdapter(newsArticles));
        binding.newsArticleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showProgressDialog() {
        newsProgressDialog = new ProgressDialog(this);
        newsProgressDialog.setTitle(getString(R.string.places_loading));
        newsProgressDialog.setMessage(getString(R.string.please_wait_message));
        newsProgressDialog.setIndeterminate(true);
        newsProgressDialog.setCancelable(false);
        newsProgressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        newsProgressDialog.dismiss();
    }

    @Override
    public void showFailedToLoadLatestNewsErrorMessage() {

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
