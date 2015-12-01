package com.france.sharereader.adapter;

/**
 * Created by Lareina on 2015/11/1.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.france.sharereader.R;
import com.france.sharereader.bean.Plan;
import com.france.sharereader.dao.BaseDao;

import java.util.ArrayList;
import java.util.List;

public class PlanExpandAdapter extends BaseExpandableListAdapter{

    private List<String> groupData = new ArrayList<String>();
    private List<List<Plan>> childData = new ArrayList<List<Plan>>();

//    private String ListPlan = "计划";
//    private String[] ChildrenPlan = new String[]{"JAVA","ANDROID","PHP"};
    private Context mContext;

    public PlanExpandAdapter(Context context,List<Plan> plans) {
        super();
        this.mContext = context;
        String ListPlan = "计划";
        groupData.add(ListPlan);
        List<Plan> ChildrenPlan = new ArrayList<Plan>();
        Log.i("zjx","plans.size()"+plans.size());
        for( Plan item:plans){
            ChildrenPlan.add(item);
        }
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
            holder.list_plan = ((TextView) convertView.findViewById(R.id.home_plan_title));
            holder.clock = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(holder);
            convertView.setTag(R.id.home_plan_title,groupPosition);
            convertView.setTag(R.id.text_plan,-1);
           // convertView.setText(groupData.get(groupPosition));
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        holder.list_plan.setText(getGroup(groupPosition).toString());
        holder.clock.setImageResource(R.drawable.ic_clock);
        return convertView;
    }
    class ChildrenHolder {
        protected TextView children_plan;
    }
   class GroupHolder{
        ImageView clock;
        TextView list_plan;
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
            convertView.setTag(R.id.home_plan_title,groupPosition);
            convertView.setTag(R.id.text_plan,childPosition);
        } else {
            holder = (ChildrenHolder) convertView.getTag();
        }
        Log.i("zjx","groupPosition:"+groupPosition+"childPosition:"+childPosition);
        holder.children_plan.setText(((Plan)getChild(groupPosition, childPosition)).getTitle());
        return convertView;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return true;
    }

}

