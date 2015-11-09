package com.france.sharereader.adapter;

/**
 * Created by Lareina on 2015/11/1.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.france.sharereader.R;

import java.util.ArrayList;
import java.util.List;

public class PlanExpandAdapter extends BaseExpandableListAdapter{

    private List<String> groupData = new ArrayList<String>();
    private List<List<String>> childData = new ArrayList<List<String>>();

//    private String ListPlan = "计划";
//    private String[] ChildrenPlan = new String[]{"JAVA","ANDROID","PHP"};
    private Context mContext;

    public PlanExpandAdapter(Context context) {
        super();
        this.mContext = context;
        String ListPlan = "计划";
        groupData.add(ListPlan);
        List<String> ChildrenPlan = new ArrayList<String>();
        ChildrenPlan.add("JAVA");
        ChildrenPlan.add("Android");
        ChildrenPlan.add("PHP");
        childData.add(ChildrenPlan);
    }
    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return groupData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        return childData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return groupData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        GroupHolder holder = null;
        if (convertView == null) {
            convertView =LayoutInflater.from(mContext).inflate(R.layout.plan_list_item,null);
            holder = new GroupHolder();
            holder.list_plan = ((TextView) convertView.findViewById(R.id.title));
            holder.clock = (ImageView) convertView.findViewById(R.id.img);
            holder.more = (ImageView) convertView.findViewById(R.id.more);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        holder.list_plan.setText(getGroup(groupPosition).toString());
        holder.clock.setImageResource(R.drawable.ic_clock);
        holder.more.setImageResource(R.drawable.ic_add);
        return convertView;
    }
    class ChildrenHolder {
        protected TextView children_plan;
    }
   class GroupHolder{
        ImageView clock;
        TextView list_plan;
        ImageView more;
    }
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ChildrenHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.home_plan_item,
                    parent, false);
            holder = new ChildrenHolder();
            holder.children_plan = ((TextView) convertView.findViewById(R.id.text_plan));
            convertView.setTag(holder);
        } else {
            holder = (ChildrenHolder) convertView.getTag();
        }
        holder.children_plan.setText(getChild(groupPosition, childPosition).toString());
        return convertView;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return true;
    }

}

