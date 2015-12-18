package com.france.sharereader.ui.activity;

import android.content.Intent;
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
    }
}
