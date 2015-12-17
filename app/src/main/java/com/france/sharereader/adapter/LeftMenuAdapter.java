package com.france.sharereader.adapter;

import android.content.Context;
import android.widget.SimpleAdapter;

import com.france.sharereader.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LeftMenuAdapter {

    private List<Map<String,Object>> leftMenus = new ArrayList<Map<String,Object>>();
    private String[] names={"主页","计划","话题","搜索","设置"};
    private int[] imgIds={
            R.drawable.iconfont_home,
            R.drawable.iconfont_plan,
            R.drawable.iconfont_info,
            R.drawable.iconfont_search,
            R.drawable.iconfont_set};
    public SimpleAdapter getAdapter(Context context){
        initData();
        return new SimpleAdapter(context,leftMenus,R.layout.simple_item,new String[]{"name","icon"},new int[]{R.id.title,R.id.img,});
    }
    private void initData(){
        for(int i=0;i<names.length;i++){
            Map<String,Object> listItem = new HashMap<String,Object>();
            listItem.put("name",names[i]);
            listItem.put("icon",imgIds[i]);
            leftMenus.add(listItem);
        }
    }



}
