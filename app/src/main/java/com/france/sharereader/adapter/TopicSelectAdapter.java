package com.france.sharereader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.france.sharereader.R;
import com.france.sharereader.bean.Theme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lareina on 2015/11/20.
 */
public class TopicSelectAdapter extends BaseAdapter{
    private List<Theme> themes = new ArrayList<Theme>();
    Context ct;
    private LayoutInflater inflater;
    public TopicSelectAdapter(Context ct, List<Theme> themes) {
        // TODO Auto-generated constructor stub
        this.themes = themes;
        this.ct = ct;
        inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return themes.size();
    }

    @Override
    public Object getItem(int position) {
        return themes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    static class ViewHolder {
        TextView topic_name;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Theme theme = themes.get(position);
        if(convertView==null){
            convertView = inflater.inflate(R.layout.home_plan_item,parent,false);
            holder = new ViewHolder();
            holder.topic_name=(TextView)convertView.findViewById(R.id.text_plan);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.topic_name.setText(theme.getName());
        //http://www.2cto.com/kf/201108/101092.html
        return convertView;
    }
}
