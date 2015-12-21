package com.france.sharereader.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.france.sharereader.R;
import com.france.sharereader.config.Config;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

/**
 * Created by Administrator on 2015/11/20.
 */
public class TopicDetailActivity extends BaseActivity{
    @ViewInject(id = R.id.tl_custom)
    private Toolbar toolbar;
    @ViewInject(id=R.id.topic_book1)
    private ImageView topic_book;
    @ViewInject(id=R.id.topic_book2)
    private ImageView topic_book2;
    @ViewInject(id=R.id.topic_book3)
    private ImageView topic_book3;
    @ViewInject(id=R.id.topic_name)
    private TextView topicName;
    @ViewInject(id=R.id.topic1)
    private TextView zhihu1;
    @ViewInject(id=R.id.topic2)
    private TextView zhihu2;
    @ViewInject(id=R.id.topic3)
    private TextView zhihu3;
    @ViewInject(id=R.id.topic4)
    private TextView zhihu4;
    @ViewInject(id=R.id.topic5)
    private TextView zhihu5;
    int bookTopicID=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_detail);
        FinalActivity.initInjectedView(this);//实现IOC注解组件 放setContentView后
        //获取该话题数据
        Bundle bundle = this.getIntent().getExtras();
        if(bundle!=null) {
            bookTopicID=bundle.getInt("bookTopicID");
            topicName.setText(Config.TOPICS[bookTopicID]);
        }
        topic_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent=new Intent(TopicDetailActivity.this,BookDetailActivity.class);
                startActivity(intent);
            }
        });
        zhihu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoBrowser("http://www.zhihu.com/question/22451397");
            }
        });
        zhihu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoBrowser("http://www.zhihu.com/question/20303645");
            }
        });
        zhihu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoBrowser("http://www.zhihu.com/question/22689579");
            }
        });
    }
    private void gotoBrowser(String s){
        Uri uri = Uri.parse(s);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}
