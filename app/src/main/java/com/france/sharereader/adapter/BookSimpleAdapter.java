package com.france.sharereader.adapter;

/**
 * Created by Administrator on 2015/11/4.
 */

import android.content.Context;
import android.widget.SimpleAdapter;
import com.france.sharereader.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BookSimpleAdapter{
    private int[] imageIds=new int[]{R.drawable.img_1,R.drawable.img_2};
    private String[] names=new String[]{"构建之法","疯狂JAVA"};
    private String[] topics=new String[]{"软件工程","疯狂JAVA"};
    private Context mContext;

    public SimpleAdapter getBookSimpleAdapter(Context mContext) {
        this.mContext=mContext;
        List<Map<String, Object>> bookList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("book_page", imageIds[i]);
            listItem.put("book_name", names[i]);
            listItem.put("topic", topics[i]);
            bookList.add(listItem);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(mContext, bookList, R.layout.mybooks_list_item, new String[]{"book_page", "book_name", "topic"}, new int[]{R.id.book_page, R.id.book_name, R.id.topic});
        return simpleAdapter;
    }
}
