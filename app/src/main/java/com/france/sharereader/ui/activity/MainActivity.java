package com.france.sharereader.ui.activity;

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
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.france.sharereader.R;
import com.france.sharereader.adapter.BookBaseAdapter;
import com.france.sharereader.adapter.LeftMenuAdapter;
import com.france.sharereader.adapter.PlanExpandAdapter;
import com.france.sharereader.bean.Book;
import com.france.sharereader.bean.Plan;
import com.france.sharereader.util.LogUtil;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    //声明相关变量
    @ViewInject(id = R.id.tl_custom)
    private Toolbar toolbar;
    @ViewInject(id = R.id.dl_left)
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    @ViewInject(id = R.id.lv_left_menu)
    private ListView lvLeftMenu;
    @ViewInject(id = R.id.list_plan)
    private ExpandableListView ListPlan;
    @ViewInject(id = R.id.mybook_list)
    private ListView bookList;
    private SimpleAdapter simpleAdapter;
    private BookBaseAdapter bookBaseAdapter;
    private PlanExpandAdapter planExpandAdapter;

//    private SimpleAdapter bookSimpleAdapter;

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
        initBookList();//书籍列表
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.tl_custom);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        lvLeftMenu = (ListView) findViewById(R.id.lv_left_menu);
        //判断侧滑点击的按钮并处理逻辑
    }

    private void initPlanListClickEvents() {
        ListPlan.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                LogUtil.ShowLog("Lareina_position:" + childPosition);
				Intent intent = new Intent(MainActivity.this, PlanDetailActivity.class);
				Bundle bundle = new Bundle();
                bundle.putSerializable("plan", (Plan) planExpandAdapter.getChild(groupPosition, childPosition));
//				bundle.putString("title",((Plan)planExpandAdapter.getChild(groupPosition, childPosition)).getTitle());
//                bundle.putString("content",((Plan)planExpandAdapter.getChild(groupPosition, childPosition)).getContent());
				intent.putExtras(bundle);
				startActivity(intent);
                MainActivity.this.finish();
                return true;
            }
        });
    }
    //Viko 2015/11/20 00:08
    private void initClickEvents() {
        lvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtil.ShowLog("position:" + position);
                if (position == 2) {
                    Intent intent = new Intent(MainActivity.this, TopicActivity.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(MainActivity.this, PlanDetailActivity.class);
                    startActivity(intent);
                }
                if (position == 0) {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                }
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
                Toast.makeText(MainActivity.this, "" + "new plan", Toast.LENGTH_SHORT).show();
                break;
            case R.id.new_book:
                Toast.makeText(MainActivity.this, "" + "new book", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(MainActivity.this, "" + "new plan", Toast.LENGTH_SHORT).show();
                    Intent intent_plan=new Intent(MainActivity.this,PlanDetailActivity.class);
//                    Bundle empty_plan = new Bundle();
//                    empty_plan.putString("title","Title");
//                    intent_plan.putExtras(empty_plan);
                    startActivity(intent_plan);
                    MainActivity.this.finish();
                    break;
                case R.id.new_book:
                    Toast.makeText(MainActivity.this, "" + "new book", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,FileManageActivity.class);
                    startActivity(intent);
                    MainActivity.this.finish();
                    break;
            }
            return true;
        }
    };

    private void initLeftSlip() {
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

    private void initListPlan() {
        List<Plan> plans=new ArrayList<>();

        plans=baseDaoImpl.FindAllPlan();
        Log.i("zjx","plan size:"+plans.size());
        ListPlan.setGroupIndicator(null);
        planExpandAdapter = new PlanExpandAdapter(MainActivity.this,plans);
        ListPlan.setAdapter(planExpandAdapter);
    }

    private void initBookList() {
        List<Book> books=new ArrayList<Book>();
        //数据获取 应该是从数据库 这边模拟
//        books.add(new Book("疯狂android讲义",38));
//        books.add(new Book("图解机器学习",77));
        books=baseDaoImpl.findAllBook();
        //数据获取结束
        bookBaseAdapter = new BookBaseAdapter(MainActivity.this,books);
        bookList.setAdapter(bookBaseAdapter);
        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                ShowLog ("你点击了ListView条目" + arg2);//在LogCat中输出信息
                Book book=(Book)bookList.getItemAtPosition(arg2);
                Intent intent=new Intent(MainActivity.this,PDFViewActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("pdf",book);
                intent.putExtras(bundle);
//                intent.putExtra("filePath",book.getLocalPath());
//                intent.putExtra("fileName", book.getBookName());//实际应该传递一个book对象比较好
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }
}