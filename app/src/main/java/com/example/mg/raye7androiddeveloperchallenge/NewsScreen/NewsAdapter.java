package com.example.mg.raye7androiddeveloperchallenge.NewsScreen;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.mg.raye7androiddeveloperchallenge.Data.Models.ArticlesModel;
import com.example.mg.raye7androiddeveloperchallenge.Data.Models.HeaderItem;
import com.example.mg.raye7androiddeveloperchallenge.Data.Models.ListItem;
import com.example.mg.raye7androiddeveloperchallenge.Data.Models.NewsItem;
import com.example.mg.raye7androiddeveloperchallenge.NewsScreen.DI.NewsScope;
import com.example.mg.raye7androiddeveloperchallenge.R;
import com.example.mg.raye7androiddeveloperchallenge.Utils.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


@NewsScope
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final RequestManager mGlide;
    private IItemClickListener mClickListener;
    private List<ListItem> mArticles;


    @Inject
    NewsAdapter(Activity activity, RequestManager glide) {
        mClickListener = (IItemClickListener) activity;
        mGlide = glide;
        mArticles = new ArrayList<>();
    }

    void setData(List<ListItem> articles) {
        mArticles = articles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ListItem.TYPE_HEADER: {
                View itemView = inflater.inflate(R.layout.news_item_header, parent, false);
                return new HeaderViewHolder(itemView);
            }
            case ListItem.TYPE_EVENT: {
                View itemView = inflater.inflate(R.layout.news_item, parent, false);
                return new ArticleViewHolder(itemView);
            }
            default:
                throw new IllegalStateException("unsupported item type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case ListItem.TYPE_HEADER: {
                HeaderItem header = (HeaderItem) mArticles.get(position);
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                headerViewHolder.header.setText(DateUtils.destinationDate((header.getDate())));

                break;
            }
            case ListItem.TYPE_EVENT: {
                NewsItem newsItem = (NewsItem) mArticles.get(position);
                ArticleViewHolder viewHolder = (ArticleViewHolder) holder;
                viewHolder.articleName.setText(newsItem.getmArticles().getTitle());

                try {
                    viewHolder.publishedAt.setText(DateUtils.timeDate(newsItem.getmArticles().getPublishedAt()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                mGlide.load(newsItem.getmArticles().getUrlToImage())
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.placeholder)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.DATA))
                        .into(viewHolder.articleImage);

                holder.itemView.setOnClickListener(v -> mClickListener.onItemClick(newsItem.getmArticles()));
                holder.itemView.setOnLongClickListener(v -> mClickListener.onItemLongClick(newsItem.getmArticles()));
            }
        }

    }


    @Override
    public int getItemCount() {
        try {
            return mArticles.size();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mArticles.get(position).getType();
    }


    public interface IItemClickListener {
        void onItemClick(ArticlesModel article);

        boolean onItemLongClick(ArticlesModel article);
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        ImageView articleImage;
        TextView articleName, publishedAt;

        ArticleViewHolder(View itemView) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.image_article);
            articleName = itemView.findViewById(R.id.text_auther);
            publishedAt = itemView.findViewById(R.id.text_published_at);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView header;

        HeaderViewHolder(View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.txt_header);
        }
    }

}
