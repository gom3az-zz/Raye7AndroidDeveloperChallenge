package com.example.mg.raye7androiddeveloperchallenge.Utils;

import android.support.annotation.NonNull;

import com.example.mg.raye7androiddeveloperchallenge.Data.Models.ArticlesModel;
import com.example.mg.raye7androiddeveloperchallenge.Data.Models.HeaderItem;
import com.example.mg.raye7androiddeveloperchallenge.Data.Models.ListItem;
import com.example.mg.raye7androiddeveloperchallenge.Data.Models.NewsItem;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GroupingUtils {

    @NonNull
    public static List<ListItem> getListItems(List<ArticlesModel> articles) {
        Map<Date, List<ArticlesModel>> articlesMap = toMap(articles);
        List<ListItem> items = new ArrayList<>();
        for (Date date : articlesMap.keySet()) {
            HeaderItem header = new HeaderItem(date);
            items.add(header);
            for (ArticlesModel article : articlesMap.get(date)) {
                NewsItem item = new NewsItem(article);
                items.add(item);
            }
        }
        return items;
    }

    @NonNull
    private static Map<Date, List<ArticlesModel>> toMap(@NonNull List<ArticlesModel> articles) {
        Map<Date, List<ArticlesModel>> map = new TreeMap<>(Collections.reverseOrder());
        for (ArticlesModel article : articles) {
            try {
                Date date = DateUtils.sourceDate((article.getPublishedAt()));
                List<ArticlesModel> value = map.get(date);
                if (value == null) {
                    value = new ArrayList<>();
                    map.put(date, value);
                }
                value.add(article);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
