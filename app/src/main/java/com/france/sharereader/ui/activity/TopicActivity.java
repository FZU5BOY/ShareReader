package com.france.sharereader.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.france.sharereader.R;
import com.france.sharereader.adapter.LeftMenuAdapter;
import com.france.sharereader.ui.view.TopicMultipleChoiceDialog;
import com.france.sharereader.util.LogUtil;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

/**
 * Created by Viko on 2015/11/14.
 */
public class TopicActivity extends BaseActivity {
    @ViewInject(id=R.id.no_topic)
    private TextView noTopic;
    @ViewInject(id=R.id.add_topic_button)
    private Button addTopic;
    @ViewInject(id = R.id.tl_custom)
    private Toolbar toolbar;
    @ViewInject(id = R.id.dl_left)
    private DrawerLayout mDrawerLayout;
    @ViewInject(id = R.id.lv_left_menu)
    private ListView lvLeftMenu;
    private ActionBarDrawerToggle mDrawerToggle;
    private SimpleAdapter simpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_topic);
        FinalActivity.initInjectedView(this);//实现IOC注解组件
        initAddTopic();//添加话题
        initLeftSlip();//侧滑
        initClickEvents();//注册事件
    }

    //添加话题
    private void initAddTopic() {
        addTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopicMultipleChoiceDialog topicDialog = new TopicMultipleChoiceDialog(TopicActivity.this, new TopicMultipleChoiceDialog.OnTestListening() {
                    @Override
                    public void getMutilChoice(boolean[] isSelectItem) {

                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 為了讓 Toolbar 的 Menu 有作用，這邊的程式不可以拿掉
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.new_plan:
                Toast.makeText(TopicActivity.this, "" + "new plan", Toast.LENGTH_SHORT).show();
                break;
            case R.id.new_book:
                Toast.makeText(TopicActivity.this, "" + "new book", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            LogUtil.ShowLog("menuItem");
            switch (item.getItemId()) {
                case R.id.new_plan:
                    Toast.makeText(TopicActivity.this, "" + "new plan", Toast.LENGTH_SHORT).show();
                    Intent intent_plan=new Intent(TopicActivity.this,PlanDetailActivity.class);
                    Bundle empty_plan = new Bundle();
                    empty_plan.putString("title","Title");
                    intent_plan.putExtras(empty_plan);
                    startActivity(intent_plan);
                    break;
                case R.id.new_book:
                    Toast.makeText(TopicActivity.this, "" + "new book", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(TopicActivity.this,FileManageActivity.class);
                    startActivity(intent);
                    break;
            }
            return true;
        }
    };
    private void initLeftSlip() {
        toolbar.setTitle("话题");//设置Toolbar标题
        toolbar.setTitleTextColor(getResources().getColor(R.color.white)); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setOnMenuItemClickListener(onMenuItemClick);//导入本地pdf
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //设置菜单列表
        simpleAdapter = new LeftMenuAdapter().getAdapter(this);
        lvLeftMenu.setAdapter(simpleAdapter);
    }

    private void initClickEvents() {
        lvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtil.ShowLog("position:" + position);
                if (position == 0) {
                    Intent intent = new Intent(TopicActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
