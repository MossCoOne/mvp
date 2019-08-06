package com.mossco.za.mvpapp.article.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.bumptech.glide.Glide;
import com.mossco.za.mvpapp.R;
import com.mossco.za.mvpapp.article.presenter.ArticlePresenter;
import com.mossco.za.mvpapp.article.presenter.ArticlesContract;
import com.mossco.za.mvpapp.databinding.ActivityArticleDetailsBinding;
import com.mossco.za.mvpapp.news.model.NewsArticle;
import com.mossco.za.mvpapp.utilities.DrawableUtils;
import com.mossco.za.mvpapp.utilities.StringsUtils;

import static com.mossco.za.mvpapp.utilities.StringsUtils.formatTextFromHtml;

public class ArticleDetailsActivity extends AppCompatActivity implements ArticlesContract.ArticleView {

    public static final String NEWS_ARTICLE_KEY = "news_article";
    ActivityArticleDetailsBinding binding;
    private ArticlePresenter articlePresenter;
    private ProgressDialog newsProgressDialog;

    public static Intent getStartIntent(Context context, NewsArticle newsArticle) {
        Intent intent = new Intent(context, ArticleDetailsActivity.class);
        intent.putExtra(NEWS_ARTICLE_KEY, newsArticle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_details);
        newsProgressDialog = new ProgressDialog(getApplicationContext());

        setSupportActionBar(binding.mainToolbar);
        getSupportActionBar().setTitle(getString(R.string.news_article));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        onArticleScreenCreated();
    }

    @Override
    public void showArticle(NewsArticle newsArticle) {
        binding.articleDateTextView.setText(StringsUtils.getFormattedDateWithTime(newsArticle.getDateCreated()));
        binding.articleTitleTextView.setText(newsArticle.getHeadline());
        binding.largeImageAltTextView.setText(newsArticle.getLargeImageAlt());
        binding.articleDescription.setText(formatTextFromHtml(newsArticle.getStoryBody()));

        Glide.with(getApplicationContext()).load(StringsUtils.REMOTE_IMAGE_URL.concat(newsArticle.getLargeImageName()))
                .dontAnimate().fitCenter().placeholder(DrawableUtils.getCircularProgressDrawable(getApplicationContext()))
                .error(R.drawable.ic_image_not_availabe).into(binding.articleImageView);
        binding.articleScrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressDialog() {
        newsProgressDialog.setTitle(getString(R.string.places_loading));
        newsProgressDialog.setMessage(getString(R.string.please_wait_message));
        newsProgressDialog.setIndeterminate(true);
        newsProgressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        newsProgressDialog.dismiss();
        finish();
    }

    @Override
    public void showFailedToLoadLatestNewsErrorMessage() {
        showCustomDialog(getString(R.string.failed_to_load_error));
    }

    public void showCustomDialog(String titleText) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle(titleText);
        builder.setIcon(R.drawable.ic_error_black_24dp);
        builder.setNegativeButton(R.string.cancel_text, (dialog, which) -> finish())
                .setPositiveButton(R.string.retry, (dialog, which) -> onArticleScreenCreated());
        builder.show().setCancelable(false);
    }

    private void onArticleScreenCreated() {
        articlePresenter = new ArticlePresenter(this);
        if (getIntent() != null && getIntent().hasExtra(NEWS_ARTICLE_KEY)) {
            NewsArticle newsArticle = (NewsArticle) getIntent().getSerializableExtra(NEWS_ARTICLE_KEY);
            if (isNetworkConnectionAvailable()) {
                articlePresenter.loadArticle(newsArticle);
            } else {
                showCustomDialog(getString(R.string.network_error));
            }
        }
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
