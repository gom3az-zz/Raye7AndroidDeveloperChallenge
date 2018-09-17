package com.example.mg.raye7androiddeveloperchallenge.NewsScreen;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mg.raye7androiddeveloperchallenge.Data.Models.ArticlesModel;
import com.example.mg.raye7androiddeveloperchallenge.Data.Models.ListItem;
import com.example.mg.raye7androiddeveloperchallenge.R;
import com.example.mg.raye7androiddeveloperchallenge.Utils.DialogUtils;
import com.example.mg.raye7androiddeveloperchallenge.Utils.PaginationScrollListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.example.mg.raye7androiddeveloperchallenge.Utils.GroupingUtils.getListItems;

public class NewsActivity extends AppCompatActivity implements
        NewsActivityContract.IView,
        NewsAdapter.IItemClickListener {

    private static final int PAGE_START = 0;
    private static final int mTotalPages = 20;
    @Inject
    NewsActivityPresenter presenter;
    @Inject
    NewsAdapter adapter;
    RecyclerView recyclerView;
    ProgressDialog mProgressDialog;
    private List<ListItem> newsItems;
    private boolean mIsLoading = false;
    private boolean mIsLastPage = false;
    private int mCurrentPage = PAGE_START;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        AndroidInjection.inject(this);
        recyclerView = findViewById(R.id.recycler_news);
        recyclerView.setAdapter(adapter);
        newsItems = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart(++mCurrentPage);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerView.setAdapter(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_favourites:
                presenter.onFavouritesClicked();
                break;
            case R.id.action_menu_news:
                presenter.onStart(++mCurrentPage);
                break;
        }
        return true;
    }

    @Override
    public void initRecyclerData(List<ArticlesModel> articles) {
        List<ListItem> items = getListItems(articles);
        newsItems.addAll(items);
        recyclerView.addOnScrollListener(onScrollListener());
        getSupportActionBar().setTitle("News");
        adapter.setData(newsItems);
    }

    @Override
    public void initRecyclerFavourites(List<ArticlesModel> articles) {
        List<ListItem> items = getListItems(articles);
        recyclerView.clearOnScrollListeners();
        getSupportActionBar().setTitle("Favorites");
        adapter.setData(items);
    }

    @Override
    public void showLoading() {
        mProgressDialog = new DialogUtils(this).showLoadingDialog();
        mProgressDialog.setMessage("loading...");
        mProgressDialog.show();

    }

    @Override
    public void hideLoading() {
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    public void onErrorLoading(Throwable throwable) {
        Log.i("onErrorLoading: ", throwable.getMessage());
    }

    @Override
    public void onItemClick(ArticlesModel article) {
        presenter.onArticleClicked(article);

    }

    @Override
    public boolean onItemLongClick(ArticlesModel article) {
        presenter.onArticleLongClicked(article);
        return false;
    }

    @Override
    public void showAdded() {
        Toast.makeText(this, "added to favorites!", Toast.LENGTH_SHORT).show();
    }

    @NonNull
    private PaginationScrollListener onScrollListener() {
        return new PaginationScrollListener((LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            protected void loadMoreItems() {
                mIsLoading = true;
                ++mCurrentPage;
                new Handler().postDelayed(() -> {
                    presenter.onStart(mCurrentPage);
                    mIsLoading = false;
                }, 500);

            }

            @Override
            public int getTotalPageCount() {
                return mTotalPages;
            }

            @Override
            public boolean isLastPage() {
                return mIsLastPage;
            }

            @Override
            public boolean isLoading() {
                return mIsLoading;
            }
        };
    }

}
