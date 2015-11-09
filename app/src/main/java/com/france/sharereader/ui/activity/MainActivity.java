package com.france.sharereader.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.france.sharereader.R;
import com.france.sharereader.adapter.BookSimpleAdapter;
import com.france.sharereader.adapter.LeftMenuAdapter;
import com.france.sharereader.adapter.PlanExpandAdapter;
import com.france.sharereader.util.LogUtil;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
	//声明相关变量
	@ViewInject(id=R.id.tl_custom)private Toolbar toolbar;
	@ViewInject(id=R.id.dl_left)private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	@ViewInject(id=R.id.lv_left_menu)private ListView lvLeftMenu;
	@ViewInject(id=R.id.list_plan)private ExpandableListView ListPlan;
	@ViewInject(id=R.id.mybook_list)private ListView BookList;
	@ViewInject(id=R.id.more)private ImageView addPlan;
	private SimpleAdapter simpleAdapter;
	private PlanExpandAdapter planExpandAdapter;
	private SimpleAdapter bookSimpleAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FinalActivity.initInjectedView(this);//实现IOC注解组件
//		initViews(); //获取控件
		initLeftSlip();//侧滑
		initClickEvents();//注册事件
        initListPlan();//计划列表
		initPlanListClickEvents();//进入具体计划页面
	//	initAddPlanClickEvent();//添加计划
		initBookList();//书籍列表
	}
	private void initViews() {
		toolbar = (Toolbar) findViewById(R.id.tl_custom);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
		lvLeftMenu = (ListView) findViewById(R.id.lv_left_menu);
		//判断侧滑点击的按钮并处理逻辑
	}
	private void initPlanListClickEvents(){
		ListPlan.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
										int groupPosition, int childPosition, long id) {
				LogUtil.ShowLog("Lareina_position:" + childPosition);
//				Intent intent = new Intent(MainActivity.this, PlanDetailActicity.class);
//				intent.setData();
//				Bundle bundle = new Bundle();
//				bundle.putString("title", planExpandAdapter.getChild(groupPosition, childPosition).toString());
//				intent.putExtras(bundle);
//				startActivity(intent);
				return true;
			}
		});
	}
	public void initAddPlanClickEvent(){
		//addPlan = (ImageView) findViewById(R.id.more);
		addPlan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
			}
		});
	}
	private void initClickEvents(){
		lvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				LogUtil.ShowLog("position:" + position);
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 為了讓 Toolbar 的 Menu 有作用，這邊的程式不可以拿掉
		getMenuInflater().inflate(R.menu.menu,menu);
		return true;
	}
	private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener(){
		@Override
		public boolean onMenuItemClick(MenuItem menuItem) {
			LogUtil.ShowLog("menuItem" );
			return true;
		}
	};
	private void initLeftSlip(){
		toolbar.setTitle(R.string.app_name);//设置Toolbar标题
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
    private void initListPlan(){
		ListPlan.setGroupIndicator(null);
		planExpandAdapter= new PlanExpandAdapter(MainActivity.this);
		ListPlan.setAdapter(planExpandAdapter);
	}
	private void initBookList(){
		bookSimpleAdapter=new BookSimpleAdapter().getBookSimpleAdapter(this);
		BookList.setAdapter(bookSimpleAdapter);
	}
}