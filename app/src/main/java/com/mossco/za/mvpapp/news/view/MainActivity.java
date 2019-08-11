package com.mossco.za.mvpapp.news.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.Glide;
import com.mossco.za.mvpapp.R;
import com.mossco.za.mvpapp.article.view.ArticleDetailsActivity;
import com.mossco.za.mvpapp.databinding.ActivityMainBinding;
import com.mossco.za.mvpapp.news.model.NewsArticle;
import com.mossco.za.mvpapp.news.presenter.NewsContract;
import com.mossco.za.mvpapp.news.presenter.NewsPresenter;
import com.mossco.za.mvpapp.utilities.DrawableUtils;
import com.mossco.za.mvpapp.utilities.StringsUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsContract.NewsView, NewsArticleAdapter.OnItemClickListener {

    ActivityMainBinding binding;
    private NewsPresenter newsPresenter;
    private NewsArticle mainStory;
    private ProgressDialog newsProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        newsProgressDialog = new ProgressDialog(this);
        setSupportActionBar(binding.mainToolbar);
        getSupportActionBar().setTitle(getString(R.string.news_title));

        onNewsScreenCreated();
        binding.mainStoryContainer
                .setOnClickListener(view -> startActivity(ArticleDetailsActivity.getStartIntent(view.getContext(), mainStory)));
    }

    private void onNewsScreenCreated() {
        newsPresenter = new NewsPresenter(this);
        if (isNetworkConnectionAvailable()) {
            newsPresenter.loadLatestNews();
        } else {
            showCustomDialog(getString(R.string.network_error));
        }
    }

    @Override
    public void displayLatestNews(List<NewsArticle> newsArticles) {

        newsArticles.get(0).setMainStory(true);

        mainStory = getMainStory(newsArticles);

        if (mainStory != null) {
            populateMainStory(mainStory);
        } else {
            ratherDontShowMainStoryView();
        }

        binding.newsArticleRecyclerView.setAdapter(new NewsArticleAdapter(newsArticles, this::onItemClicked));
        binding.newsArticleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.contentScrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressDialog() {
        if (newsProgressDialog != null) {
            newsProgressDialog.setTitle(getString(R.string.places_loading));
            newsProgressDialog.show();
            newsProgressDialog.setMessage(getString(R.string.please_wait_message));
            newsProgressDialog.setIndeterminate(true);
        }
    }

    @Override
    public void dismissProgressDialog() {
        newsProgressDialog.dismiss();
    }

    @Override
    public void showFailedToLoadLatestNewsErrorMessage() {
        newsProgressDialog.dismiss();
        showCustomDialog(getString(R.string.failed_to_load_error));
    }

    public void showCustomDialog(String titleText) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titleText);
        builder.setIcon(R.drawable.ic_error_black_24dp);
        builder.setNegativeButton(R.string.cancel_text, (dialog, which) -> finish())
                .setPositiveButton(R.string.retry, (dialog, which) -> onNewsScreenCreated());
        builder.show().setCancelable(false);
    }

    @Override
    public void onItemClicked(NewsArticle extraItem) {
        startActivity(ArticleDetailsActivity.getStartIntent(this, extraItem));
    }

    private void ratherDontShowMainStoryView() {
        binding.mainStoryContainer.setVisibility(View.GONE);
    }

    private void populateMainStory(NewsArticle mainStory) {
        binding.categoryChip.setText(mainStory.getCategory());
        binding.headlineStoryTitle.setText(mainStory.getHeadline());
        binding.headlineStoryDateTextView.setText(StringsUtils.getFormattedDate(mainStory.getDateCreated()));

        Glide.with(getApplicationContext()).load(StringsUtils.REMOTE_IMAGE_URL.concat(mainStory.getLargeImageName()))
                .dontAnimate().fitCenter().placeholder(DrawableUtils.getCircularProgressDrawable(this))
                .error(R.drawable.ic_image_not_availabe).into(binding.headlineStoryImageView);
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

    boolean isNetworkConnectionAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            return false;
        }
        NetworkInfo.State network = info.getState();
        return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);
    }
}
